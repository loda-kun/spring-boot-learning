CREATE DATABASE lodadb;
use lodadb;

CREATE TABLE `app_params` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `param_name` varchar(45) NOT NULL,
  `param_value` varchar(255) DEFAULT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;