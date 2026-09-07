-- 删除代码生成菜单及权限关联
DELETE rm
FROM sys_role_menu rm
INNER JOIN sys_menu sm ON sm.menu_id = rm.menu_id
WHERE sm.component = 'tool/gen/index'
   OR sm.perms LIKE 'tool:gen:%';

-- 删除代码生成菜单及其按钮权限
DELETE FROM sys_menu
WHERE component = 'tool/gen/index'
   OR perms LIKE 'tool:gen:%';

-- 删除代码生成元数据表
DROP TABLE IF EXISTS gen_table_column;
DROP TABLE IF EXISTS gen_table;
