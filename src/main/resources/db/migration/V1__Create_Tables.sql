CREATE TABLE `tb_person` (
  `id_person` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `address` varchar(100) NOT NULL,
  `gender` varchar(12) DEFAULT NULL,
  `dt_birthday` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id_person`)
);
