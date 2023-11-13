CREATE DATABASE  IF NOT EXISTS `event_horizondp`;
USE `event_horizondp`;
-- Table structure for table `information`
--
DROP TABLE IF EXISTS `information_tbl`;
CREATE TABLE `information_tbl` (
  `id` int NOT NULL AUTO_INCREMENT,
   `user_name` varchar(45) DEFAULT NULL,
   `password` varchar(45) DEFAULT NULL,
   `first_name` varchar(45) DEFAULT NULL,
   `last_name` varchar(45) DEFAULT NULL,
   `email` varchar(45) DEFAULT NULL,
   `gender` varchar(45) DEFAULT NULL,
   `pay_pal_Account` varchar(45) DEFAULT NULL,
   `role` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

--
-- Data for table `employee`
--

--
-- Table structure for table `Organizer`
--

CREATE TABLE `client_tbl` (
  `id` int NOT NULL AUTO_INCREMENT,
  `information_id` int,
  PRIMARY KEY (`id`),
  foreign key (information_id) references information_tbl (id)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `organizer_tbl`;
CREATE TABLE `organizer_tbl` (
  `id` int NOT NULL AUTO_INCREMENT,
  `information_id` int,
  `rate` float ,
  PRIMARY KEY (`id`),
  foreign key (information_id) references information_tbl (id)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


CREATE TABLE `sponsor_tbl` (
  `id` int NOT NULL AUTO_INCREMENT,
  `information_id` int,
  PRIMARY KEY (`id`),
  foreign key (information_id) references information_tbl (id)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


CREATE TABLE `moderator_tbl` (
  `id` int NOT NULL AUTO_INCREMENT,
  `information_id` int,
  PRIMARY KEY (`id`),
  foreign key (information_id) references information_tbl (id)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

USE `event_horizondp`;
ALTER TABLE `information_id` ADD COLUMN active int