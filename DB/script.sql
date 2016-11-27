
CREATE DATABASE `gas-station`;

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) DEFAULT NULL,
  `email` varchar(150) DEFAULT NULL,
  `site_number` varchar(20) DEFAULT NULL,
  `site_name` varchar(100) DEFAULT NULL,
  `security_question` varchar(500) DEFAULT NULL,
  `security_answer` varchar(500) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `active` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
