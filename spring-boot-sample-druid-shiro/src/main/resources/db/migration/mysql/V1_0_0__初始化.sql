
SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for JEEBIZ_DEMO
-- ----------------------------
DROP TABLE IF EXISTS `JEEBIZ_DEMO`;
CREATE TABLE `JEEBIZ_DEMO` (
  `ID` int(11) NOT NULL COMMENT 'ID',
  `NAME` varchar(50) DEFAULT NULL COMMENT '名称',
  `TEXT` varchar(255) DEFAULT NULL COMMENT '简述'
  PRIMARY KEY (`M_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Demo示例表';

