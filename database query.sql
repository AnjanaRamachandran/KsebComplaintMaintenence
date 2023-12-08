CREATE DATABASE `kseb` /*!40100 DEFAULT CHARACTER SET utf8 */;
DROP TABLE IF EXISTS `kseb`.`complaint_details`;
CREATE TABLE  `kseb`.`complaint_details` (
  `complaint_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `reg_date` datetime NOT NULL,
  `consumer_name` varchar(45) NOT NULL,
  `address` varchar(45) NOT NULL,
  `complaint` varchar(45) NOT NULL,
  PRIMARY KEY (`complaint_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `kseb`.`materialrequest_details`;
CREATE TABLE  `kseb`.`materialrequest_details` (
  `request_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `date` datetime NOT NULL,
  `allocation_no` int(10) unsigned NOT NULL,
  `material_details` varchar(45) NOT NULL,
  PRIMARY KEY (`request_id`),
  KEY `allocation_no` (`allocation_no`),
  CONSTRAINT `materialrequest_details_ibfk_1` FOREIGN KEY (`allocation_no`) REFERENCES `work_allocation` (`allocation_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `kseb`.`request_status`;
CREATE TABLE  `kseb`.`request_status` (
  `status_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `date` datetime NOT NULL,
  `allocation_no` int(10) unsigned NOT NULL,
  `req_status` varchar(45) NOT NULL,
  PRIMARY KEY (`status_id`),
  KEY `allocation_no` (`allocation_no`),
  CONSTRAINT `request_status_ibfk_1` FOREIGN KEY (`allocation_no`) REFERENCES `work_allocation` (`allocation_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `kseb`.`user_login`;
CREATE TABLE  `kseb`.`user_login` (
  `user_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `user_name` varchar(45) NOT NULL,
  `user_password` varchar(45) NOT NULL,
  `user_type` varchar(45) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `kseb`.`work_allocation`;
CREATE TABLE  `kseb`.`work_allocation` (
  `allocation_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `allocation_date` datetime NOT NULL,
  `complaint_id` int(10) unsigned NOT NULL,
  `lineman` varchar(45) NOT NULL,
  PRIMARY KEY (`allocation_id`),
  KEY `complaint_id` (`complaint_id`),
  CONSTRAINT `work_allocation_ibfk_1` FOREIGN KEY (`complaint_id`) REFERENCES `complaint_details` (`complaint_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;



DROP TABLE IF EXISTS `kseb`.`work_status`;
CREATE TABLE  `kseb`.`work_status` (
  `aI.No` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `date` datetime NOT NULL,
  `allocation_id` int(10) unsigned NOT NULL,
  `work_status` varchar(45) NOT NULL,
  PRIMARY KEY (`aI.No`),
  KEY `allocation_id` (`allocation_id`),
  CONSTRAINT `work_status_ibfk_1` FOREIGN KEY (`allocation_id`) REFERENCES `work_allocation` (`allocation_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;













