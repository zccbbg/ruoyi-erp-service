package com.ruoyi.warehouse.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.ruoyi.base.domain.vo.BaseDocVo;
import com.ruoyi.warehouse.domain.entity.CheckDoc;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 库存盘点单据视图对象 wms_check_order
 *
 * @author zcc
 * @date 2024-08-13
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ExcelIgnoreUnannotated
@AutoMapper(target = CheckDoc.class)
public class CheckDocVo extends BaseDocVo<CheckDocDetailVo> {

}
