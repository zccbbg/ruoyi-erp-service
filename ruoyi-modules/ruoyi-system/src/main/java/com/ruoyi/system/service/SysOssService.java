package com.ruoyi.system.service;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.common.core.constant.CacheNames;
import com.ruoyi.common.mybatis.core.page.PageQuery;
import com.ruoyi.common.mybatis.core.page.TableDataInfo;
import com.ruoyi.common.core.service.OssService;
import com.ruoyi.common.core.exception.ServiceException;
import com.ruoyi.common.core.utils.MapstructUtils;
import com.ruoyi.common.core.utils.StreamUtils;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.core.utils.file.FileUtils;
import com.ruoyi.common.core.utils.SpringUtils;
import com.ruoyi.common.oss.core.OssClient;
import com.ruoyi.common.oss.entity.UploadResult;
import com.ruoyi.common.oss.enumd.AccessPolicyType;
import com.ruoyi.common.oss.factory.OssFactory;
import com.ruoyi.system.domain.entity.SysOss;
import com.ruoyi.system.domain.bo.SysOssBo;
import com.ruoyi.system.domain.vo.SysOssVo;
import com.ruoyi.system.mapper.SysOssMapper;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

/**
 * 文件上传 服务层实现
 *
 * @author Lion Li
 */
@RequiredArgsConstructor
@Service
public class SysOssService implements OssService {

    private static final Set<String> ALLOWED_UPLOAD_SUFFIXES = Set.of(
        "bmp", "gif", "jpg", "jpeg", "png",
        "pdf",
        "doc", "docx", "xls", "xlsx", "ppt", "pptx"
    );

    private final SysOssMapper ossMapper;

    public TableDataInfo<SysOssVo> queryPageList(SysOssBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<SysOss> lqw = buildQueryWrapper(bo);
        Page<SysOssVo> result = ossMapper.selectVoPage(pageQuery.build(), lqw);
        List<SysOssVo> filterResult = StreamUtils.toList(result.getRecords(), this::matchingUrl);
        result.setRecords(filterResult);
        return TableDataInfo.build(result);
    }

    public List<SysOssVo> listByIds(Collection<Long> ossIds) {
        List<SysOssVo> list = new ArrayList<>();
        for (Long id : ossIds) {
            SysOssVo vo = SpringUtils.getAopProxy(this).getById(id);
            if (ObjectUtil.isNotNull(vo)) {
                try {
                    list.add(this.matchingUrl(vo));
                } catch (Exception ignored) {
                    // 如果oss异常无法连接则将数据直接返回
                    list.add(vo);
                }            }
        }
        return list;
    }

    public String selectUrlByIds(String ossIds) {
        List<String> list = new ArrayList<>();
        for (Long id : StringUtils.splitTo(ossIds, Convert::toLong)) {
            SysOssVo vo = SpringUtils.getAopProxy(this).getById(id);
            if (ObjectUtil.isNotNull(vo)) {
                try {
                    list.add(this.matchingUrl(vo).getUrl());
                } catch (Exception ignored) {
                    // 如果oss异常无法连接则将数据直接返回
                    list.add(vo.getUrl());
                }
            }
        }
        return String.join(StringUtils.SEPARATOR, list);
    }

    private LambdaQueryWrapper<SysOss> buildQueryWrapper(SysOssBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<SysOss> lqw = Wrappers.lambdaQuery();
        lqw.like(StringUtils.isNotBlank(bo.getFileName()), SysOss::getFileName, bo.getFileName());
        lqw.like(StringUtils.isNotBlank(bo.getOriginalName()), SysOss::getOriginalName, bo.getOriginalName());
        lqw.eq(StringUtils.isNotBlank(bo.getFileSuffix()), SysOss::getFileSuffix, bo.getFileSuffix());
        lqw.eq(StringUtils.isNotBlank(bo.getUrl()), SysOss::getUrl, bo.getUrl());
        lqw.between(params.get("beginCreateTime") != null && params.get("endCreateTime") != null,
            SysOss::getCreateTime, params.get("beginCreateTime"), params.get("endCreateTime"));
        lqw.eq(StringUtils.isNotBlank(bo.getCreateBy()), SysOss::getCreateBy, bo.getCreateBy());
        lqw.eq(StringUtils.isNotBlank(bo.getService()), SysOss::getService, bo.getService());
        return lqw;
    }

    @Cacheable(cacheNames = CacheNames.SYS_OSS, key = "#ossId")
    public SysOssVo getById(Long ossId) {
        return ossMapper.selectVoById(ossId);
    }

    public void download(Long ossId, HttpServletResponse response) throws IOException {
        SysOssVo sysOss = SpringUtils.getAopProxy(this).getById(ossId);
        if (ObjectUtil.isNull(sysOss)) {
            throw new ServiceException("文件数据不存在!");
        }
        FileUtils.setAttachmentResponseHeader(response, sysOss.getOriginalName());
        response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE + "; charset=UTF-8");
        OssClient storage = OssFactory.instance(sysOss.getService());
        try(InputStream inputStream = storage.getObjectContent(sysOss.getUrl())) {
            int available = inputStream.available();
            IoUtil.copy(inputStream, response.getOutputStream(), available);
            response.setContentLength(available);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public SysOssVo upload(MultipartFile file) {
        String originalfileName = file.getOriginalFilename();
        String suffix = getFileSuffix(originalfileName);
        OssClient storage = OssFactory.instance();
        UploadResult uploadResult;
        try {
            byte[] fileBytes = file.getBytes();
            validateUploadFile(fileBytes, suffix);
            uploadResult = storage.uploadSuffix(fileBytes, "." + suffix, getContentTypeBySuffix(suffix));
        } catch (IOException e) {
            throw new ServiceException("读取上传文件失败");
        }
        // 保存文件信息
        return buildResultEntity(originalfileName, "." + suffix, storage.getConfigKey(), uploadResult);
    }

    public SysOssVo upload(File file) {
        String originalfileName = file.getName();
        String suffix = StringUtils.substring(originalfileName, originalfileName.lastIndexOf("."), originalfileName.length());
        OssClient storage = OssFactory.instance();
        UploadResult uploadResult = storage.uploadSuffix(file, suffix);
        // 保存文件信息
        return buildResultEntity(originalfileName, suffix, storage.getConfigKey(), uploadResult);
    }

    /**
     * 方法用途：获取并规范化上传文件的扩展名。
     * 参数：originalFileName 为客户端提交的原始文件名。
     * 返回值：不包含点号的小写扩展名。
     */
    private String getFileSuffix(String originalFileName) {
        if (StringUtils.isBlank(originalFileName)) {
            throw new ServiceException("文件名不能为空");
        }
        int suffixIndex = originalFileName.lastIndexOf('.');
        if (suffixIndex < 1 || suffixIndex == originalFileName.length() - 1) {
            throw new ServiceException("文件扩展名不合法");
        }
        return originalFileName.substring(suffixIndex + 1).toLowerCase(Locale.ROOT);
    }

    /**
     * 方法用途：校验上传文件的扩展名、文件大小和二进制文件头。
     * 参数：fileBytes 为上传文件内容；suffix 为规范化后的扩展名。
     * 返回值：无；校验失败时抛出业务异常。
     */
    private void validateUploadFile(byte[] fileBytes, String suffix) {
        if (!ALLOWED_UPLOAD_SUFFIXES.contains(suffix)) {
            throw new ServiceException("不支持的文件类型");
        }
        if (fileBytes.length == 0) {
            throw new ServiceException("上传文件不能为空");
        }
        if (!isFileSignatureValid(fileBytes, suffix)) {
            throw new ServiceException("文件内容与扩展名不匹配");
        }
    }

    /**
     * 方法用途：验证上传文件的魔数是否符合其允许的文件类型。
     * 参数：fileBytes 为上传文件内容；suffix 为规范化后的扩展名。
     * 返回值：文件头合法时返回 true，否则返回 false。
     */
    private boolean isFileSignatureValid(byte[] fileBytes, String suffix) {
        return switch (suffix) {
            case "jpg", "jpeg" -> hasFileHeader(fileBytes, 0xFF, 0xD8, 0xFF);
            case "png" -> hasFileHeader(fileBytes, 0x89, 0x50, 0x4E, 0x47);
            case "gif" -> hasFileHeader(fileBytes, "GIF87a".getBytes()) || hasFileHeader(fileBytes, "GIF89a".getBytes());
            case "bmp" -> hasFileHeader(fileBytes, 0x42, 0x4D);
            case "pdf" -> hasFileHeader(fileBytes, 0x25, 0x50, 0x44, 0x46, 0x2D);
            case "doc", "xls", "ppt" -> hasFileHeader(fileBytes, 0xD0, 0xCF, 0x11, 0xE0, 0xA1, 0xB1, 0x1A, 0xE1);
            case "docx", "xlsx", "pptx" -> hasFileHeader(fileBytes, 0x50, 0x4B, 0x03, 0x04)
                || hasFileHeader(fileBytes, 0x50, 0x4B, 0x05, 0x06)
                || hasFileHeader(fileBytes, 0x50, 0x4B, 0x07, 0x08);
            default -> false;
        };
    }

    /**
     * 方法用途：比对文件内容是否以指定的二进制文件头开头。
     * 参数：fileBytes 为上传文件内容；header 为预期文件头字节。
     * 返回值：文件头完全匹配时返回 true，否则返回 false。
     */
    private boolean hasFileHeader(byte[] fileBytes, int... header) {
        if (fileBytes.length < header.length) {
            return false;
        }
        for (int index = 0; index < header.length; index++) {
            if ((fileBytes[index] & 0xFF) != header[index]) {
                return false;
            }
        }
        return true;
    }

    /**
     * 方法用途：比对文件内容是否以指定的字节数组文件头开头。
     * 参数：fileBytes 为上传文件内容；header 为预期文件头字节数组。
     * 返回值：文件头完全匹配时返回 true，否则返回 false。
     */
    private boolean hasFileHeader(byte[] fileBytes, byte[] header) {
        if (fileBytes.length < header.length) {
            return false;
        }
        for (int index = 0; index < header.length; index++) {
            if (fileBytes[index] != header[index]) {
                return false;
            }
        }
        return true;
    }

    /**
     * 方法用途：根据受信任的扩展名确定对象存储使用的 MIME 类型。
     * 参数：suffix 为规范化后的扩展名。
     * 返回值：服务端确定的 MIME 类型。
     */
    private String getContentTypeBySuffix(String suffix) {
        return switch (suffix) {
            case "bmp" -> "image/bmp";
            case "gif" -> "image/gif";
            case "jpg", "jpeg" -> "image/jpeg";
            case "png" -> "image/png";
            case "pdf" -> "application/pdf";
            case "doc" -> "application/msword";
            case "docx" -> "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
            case "xls" -> "application/vnd.ms-excel";
            case "xlsx" -> "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
            case "ppt" -> "application/vnd.ms-powerpoint";
            case "pptx" -> "application/vnd.openxmlformats-officedocument.presentationml.presentation";
            default -> throw new ServiceException("不支持的文件类型");
        };
    }

    private SysOssVo buildResultEntity(String originalfileName, String suffix, String configKey, UploadResult uploadResult) {
        SysOss oss = new SysOss();
        oss.setUrl(uploadResult.getUrl());
        oss.setFileSuffix(suffix);
        oss.setFileName(uploadResult.getFilename());
        oss.setOriginalName(originalfileName);
        oss.setService(configKey);
        ossMapper.insert(oss);
        SysOssVo sysOssVo = MapstructUtils.convert(oss, SysOssVo.class);
        return this.matchingUrl(sysOssVo);
    }

    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if (isValid) {
            // 做一些业务上的校验,判断是否需要校验
        }
        List<SysOss> list = ossMapper.selectBatchIds(ids);
        for (SysOss sysOss : list) {
            OssClient storage = OssFactory.instance(sysOss.getService());
            storage.delete(sysOss.getUrl());
        }
        return ossMapper.deleteBatchIds(ids) > 0;
    }

    /**
     * 匹配Url
     *
     * @param oss OSS对象
     * @return oss 匹配Url的OSS对象
     */
    private SysOssVo matchingUrl(SysOssVo oss) {
        OssClient storage = OssFactory.instance(oss.getService());
        // 仅修改桶类型为 private 的URL，临时URL时长为120s
        if (AccessPolicyType.PRIVATE == storage.getAccessPolicy()) {
            oss.setUrl(storage.getPrivateUrl(oss.getFileName(), 120));
        }
        return oss;
    }
}
