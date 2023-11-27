DROP TABLE IF EXISTS information_tbl;
CREATE TABLE information_tbl (
  id int NOT NULL AUTO_INCREMENT,
   user_name varchar(45) DEFAULT NULL,
   password varchar(100) DEFAULT NULL,
   active int DEFAULT 1,
   first_name varchar(45) DEFAULT NULL,
   last_name varchar(45) DEFAULT NULL,
   email varchar(45) DEFAULT NULL,
   gender varchar(45) DEFAULT NULL,
   pay_pal_Account varchar(45) DEFAULT NULL,
   role varchar(45) DEFAULT NULL,
   sign_in_with_email int DEFAULT 0,
   verify_code varchar(7) DEFAULT NULL,
   enable int DEFAULT 0,
   PRIMARY KEY (id)
);

CREATE TABLE `client_tbl` (
  `id` int NOT NULL AUTO_INCREMENT,
  `information_id` int,
  PRIMARY KEY (`id`),
  foreign key (information_id) references information_tbl (id)
);

DROP TABLE IF EXISTS `organizer_tbl`;
CREATE TABLE `organizer_tbl` (
  `id` int NOT NULL AUTO_INCREMENT,
  `information_id` int,
  `rate` float ,
  PRIMARY KEY (`id`),
  foreign key (information_id) references information_tbl (id)
);

CREATE TABLE `sponsor_tbl` (
  `id` int NOT NULL AUTO_INCREMENT,
  `information_id` int,
  PRIMARY KEY (`id`),
  foreign key (information_id) references information_tbl (id)
);

CREATE TABLE `moderator_tbl` (
  `id` int NOT NULL AUTO_INCREMENT,
  `information_id` int,
  PRIMARY KEY (`id`),
  foreign key (information_id) references information_tbl (id)
);
CREATE TABLE IF NOT EXISTS AdsOption (
    id INT PRIMARY KEY AUTO_INCREMENT,
    priority INT NOT NULL,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS Location (
    id INT PRIMARY KEY AUTO_INCREMENT,
    country VARCHAR(255) NOT NULL,
    city VARCHAR(255) NOT NULL,
    address VARCHAR(1000)
);

CREATE TABLE IF NOT EXISTS Event (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(2550),
    event_category VARCHAR(255),
    event_date datetime(6),
    location_id INT,
    ads_id INT NOT NULL,
    FOREIGN KEY (location_id) REFERENCES Location(id) ON DELETE SET NULL,
    FOREIGN KEY (ads_id) REFERENCES AdsOption(id)
);

