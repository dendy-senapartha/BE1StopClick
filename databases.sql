/*
SQLyog Enterprise - MySQL GUI v8.14 
MySQL - 5.5.5-10.1.21-MariaDB : Database - 1stopclick
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`1stopclick` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `1stopclick`;

/*Table structure for table `actor` */

DROP TABLE IF EXISTS `actor`;

CREATE TABLE `actor` (
  `id` int(100) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(1000) DEFAULT NULL,
  `last_name` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

/*Data for the table `actor` */

insert  into `actor`(`id`,`first_name`,`last_name`) values (1,'Peter','Dinklage'),(2,'Emilia ','Clarke'),(3,'Kit','Harington '),(4,'Charlie','Chaplin'),(5,'Tom','Hardy'),(6,'Charlize','Theron'),(7,'Shia','LaBeouf'),(8,'Rosie','Huntington-Whiteley');

/*Table structure for table `actor_video` */

DROP TABLE IF EXISTS `actor_video`;

CREATE TABLE `actor_video` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `actor_id` int(100) DEFAULT NULL,
  `video_id` int(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_actor_video_actor` (`actor_id`),
  KEY `FK_video_actor_video` (`video_id`),
  CONSTRAINT `FK_actor_video_actor` FOREIGN KEY (`actor_id`) REFERENCES `actor` (`id`) ON DELETE CASCADE,
  CONSTRAINT `FK_video_actor_video` FOREIGN KEY (`video_id`) REFERENCES `video` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=latin1;

/*Data for the table `actor_video` */

insert  into `actor_video`(`id`,`actor_id`,`video_id`) values (1,4,1),(2,4,2),(3,1,7),(4,2,7),(5,3,7),(6,1,8),(7,2,8),(8,3,8),(9,1,9),(10,2,9),(11,3,9),(12,1,10),(13,2,10),(14,3,10),(15,1,11),(16,2,11),(17,3,11),(18,1,12),(19,2,12),(20,3,12),(21,1,13),(22,2,13),(23,3,13),(24,1,14),(25,2,14),(26,3,14),(27,5,3),(28,6,3),(29,5,4),(30,6,4),(31,7,5),(32,8,5),(33,7,6),(34,NULL,6);

/*Table structure for table `albums` */

DROP TABLE IF EXISTS `albums`;

CREATE TABLE `albums` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(2000) DEFAULT NULL,
  `release_date` date DEFAULT NULL,
  `album_image_url` varchar(2000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `albums` */

insert  into `albums`(`id`,`name`,`release_date`,`album_image_url`) values (1,'By The Way','2002-01-01','https://upload.wikimedia.org/wikipedia/en/2/23/Rhcp9.jpg'),(2,'RHCP Compilation','2002-01-01','https://cps-static.rovicorp.com/3/JPG_500/MI0001/787/MI0001787862.jpg');

/*Table structure for table `artist` */

DROP TABLE IF EXISTS `artist`;

CREATE TABLE `artist` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(2000) DEFAULT NULL,
  `last_name` varchar(2000) DEFAULT NULL,
  `dob` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

/*Data for the table `artist` */

insert  into `artist`(`id`,`first_name`,`last_name`,`dob`) values (1,'Anthony','Kiedis',NULL),(2,'John ','Frusciante ',NULL),(3,'Flea ',NULL,NULL),(4,'Chad ','Smith ',NULL);

/*Table structure for table `balance` */

DROP TABLE IF EXISTS `balance`;

CREATE TABLE `balance` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(255) DEFAULT NULL,
  `balance` decimal(10,2) DEFAULT NULL,
  `last_usage` datetime DEFAULT NULL,
  `balance_type_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_balance_type_balance` (`balance_type_id`),
  KEY `FK_user_balance` (`user_id`),
  CONSTRAINT `FK_balance_type_balance` FOREIGN KEY (`balance_type_id`) REFERENCES `balance_type` (`id`) ON DELETE NO ACTION,
  CONSTRAINT `FK_user_balance` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `balance` */

insert  into `balance`(`id`,`user_id`,`balance`,`last_usage`,`balance_type_id`) values (1,8,'4900000.00','2019-07-25 13:25:14',1),(2,33,'870000.00','2019-07-19 10:36:48',1);

/*Table structure for table `balance_type` */

DROP TABLE IF EXISTS `balance_type`;

CREATE TABLE `balance_type` (
  `id` int(100) NOT NULL AUTO_INCREMENT,
  `code` varchar(2000) DEFAULT NULL,
  `name` varchar(2000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `balance_type` */

insert  into `balance_type`(`id`,`code`,`name`) values (1,'1stopWalet','1stopWalet'),(3,'test','test');

/*Table structure for table `category` */

DROP TABLE IF EXISTS `category`;

CREATE TABLE `category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `target` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `priority` int(11) DEFAULT NULL,
  `created` datetime NOT NULL,
  `is_active` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `category` */

insert  into `category`(`id`,`name`,`target`,`priority`,`created`,`is_active`) values (3,'Movies','#movies',1,'2016-10-25 08:12:06',''),(4,'Aplications','#aplications',2,'2016-10-25 08:14:17',''),(5,'Books','#books',3,'2016-11-02 08:37:45',''),(6,'Musics','#musics',4,'2016-11-02 09:37:23','');

/*Table structure for table `director` */

DROP TABLE IF EXISTS `director`;

CREATE TABLE `director` (
  `id` int(100) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(2000) DEFAULT NULL,
  `last_name` varchar(2000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

/*Data for the table `director` */

insert  into `director`(`id`,`first_name`,`last_name`) values (1,'David','Nutter'),(2,'Miguel','Sapochnik'),(3,'David','Benioff '),(4,'Charlie','Chaplin'),(5,'George','Miller'),(6,'Michael','Bay');

/*Table structure for table `director_video` */

DROP TABLE IF EXISTS `director_video`;

CREATE TABLE `director_video` (
  `id` int(100) NOT NULL AUTO_INCREMENT,
  `director_id` int(100) DEFAULT NULL,
  `video_id` int(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_director_video_director` (`director_id`),
  KEY `FK_video_director_video` (`video_id`),
  CONSTRAINT `FK_director_video_director` FOREIGN KEY (`director_id`) REFERENCES `director` (`id`) ON DELETE CASCADE,
  CONSTRAINT `FK_video_director_video` FOREIGN KEY (`video_id`) REFERENCES `video` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;

/*Data for the table `director_video` */

insert  into `director_video`(`id`,`director_id`,`video_id`) values (1,4,1),(2,4,2),(3,1,7),(4,2,8),(5,3,9),(6,1,10),(7,2,11),(8,3,12),(9,1,13),(10,2,14),(12,5,3),(13,5,4),(14,6,5),(15,6,6);

/*Table structure for table `genres` */

DROP TABLE IF EXISTS `genres`;

CREATE TABLE `genres` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(2000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `genres` */

/*Table structure for table `image_type_product` */

DROP TABLE IF EXISTS `image_type_product`;

CREATE TABLE `image_type_product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `image_type_product` */

insert  into `image_type_product`(`id`,`code`,`name`) values (1,'MovieArt','Movie Art'),(2,'MovieBackdrop','Movie Backdrop'),(3,'test','test');

/*Table structure for table `invoice` */

DROP TABLE IF EXISTS `invoice`;

CREATE TABLE `invoice` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `orders_id` int(11) DEFAULT NULL,
  `user_id` bigint(255) DEFAULT NULL,
  `payment_method_id` int(11) DEFAULT NULL,
  `description` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `status` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'Draft, Issued, Paid, Void; https://www.replicon.com/help/setting-the-status-of-an-invoice/',
  `created` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_user_transaction` (`orders_id`),
  KEY `FK_invoice` (`user_id`),
  KEY `FK_invoice_payment_method` (`payment_method_id`),
  CONSTRAINT `FK_invoice` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION,
  CONSTRAINT `FK_invoice_payment_method` FOREIGN KEY (`payment_method_id`) REFERENCES `payment_method` (`id`) ON DELETE NO ACTION,
  CONSTRAINT `FK_order_invoice` FOREIGN KEY (`orders_id`) REFERENCES `orders` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `invoice` */

insert  into `invoice`(`id`,`orders_id`,`user_id`,`payment_method_id`,`description`,`status`,`created`) values (1,1,8,1,'ealah','PAID','2019-02-02 12:00:00'),(2,2,8,1,'yahooo','PAID','2019-12-06 12:00:00'),(8,9,8,1,'Ealah','VOID','2012-12-12 00:00:00'),(9,10,7,1,'yahooo','PAID','2012-12-12 00:00:00'),(12,13,8,1,'dfghndfdfgjfdg','PAID','2019-07-16 00:00:00'),(13,14,8,1,'','PAID','2019-07-17 00:00:00'),(15,16,8,1,'','PAID','2019-07-17 00:00:00'),(18,19,33,1,'','PAID','2019-07-19 00:00:00'),(19,20,33,1,'','PAID','2019-07-19 00:00:00'),(20,21,8,1,'','PAID','2019-07-25 00:00:00');

/*Table structure for table `order_item` */

DROP TABLE IF EXISTS `order_item`;

CREATE TABLE `order_item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_id` int(11) DEFAULT NULL,
  `product_id` int(11) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `subtotal` decimal(8,2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_product_order_item` (`product_id`),
  KEY `FK_order_item` (`order_id`),
  CONSTRAINT `FK_order_item` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`) ON DELETE CASCADE,
  CONSTRAINT `FK_product_order_item` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`) ON DELETE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=latin1;

/*Data for the table `order_item` */

insert  into `order_item`(`id`,`order_id`,`product_id`,`quantity`,`subtotal`) values (1,1,81,1,'5000.00'),(2,1,75,1,'55000.00'),(3,2,84,1,'5000.00'),(11,9,82,1,'10000.00'),(12,10,75,1,'55000.00'),(13,10,81,1,'5000.00'),(14,9,81,1,'5000.00'),(15,9,75,1,'55000.00'),(24,9,84,1,'5000.00'),(29,13,79,1,'10000.00'),(30,14,80,1,'10000.00'),(33,16,85,1,'5000.00'),(38,19,80,1,'10000.00'),(39,20,77,1,'100000.00'),(40,20,82,1,'10000.00'),(41,20,86,1,'5000.00'),(42,20,81,1,'5000.00'),(43,21,77,1,'100000.00');

/*Table structure for table `orders` */

DROP TABLE IF EXISTS `orders`;

CREATE TABLE `orders` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_date` date DEFAULT NULL,
  `total_amount` decimal(8,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=latin1;

/*Data for the table `orders` */

insert  into `orders`(`id`,`order_date`,`total_amount`) values (1,'2019-02-02','60000.00'),(2,'2019-12-06','5000.00'),(9,'2012-12-12','75000.00'),(10,'2019-02-02','60000.00'),(13,'2019-07-16','10000.00'),(14,'2019-07-17','10000.00'),(16,'2019-07-17','5000.00'),(19,'2019-07-19','10000.00'),(20,'2019-07-19','120000.00'),(21,'2019-07-25','100000.00');

/*Table structure for table `payment_method` */

DROP TABLE IF EXISTS `payment_method`;

CREATE TABLE `payment_method` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `name` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `payment_method` */

insert  into `payment_method`(`id`,`code`,`name`) values (1,'1stopWalet','1stopWalet'),(2,'test','test');

/*Table structure for table `prdposition` */

DROP TABLE IF EXISTS `prdposition`;

CREATE TABLE `prdposition` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `product_id` int(11) NOT NULL,
  `position` int(11) NOT NULL,
  `category` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `subcategory` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `position_top` int(11) DEFAULT NULL,
  `created` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_45985d8e4584665a` (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `prdposition` */

insert  into `prdposition`(`id`,`product_id`,`position`,`category`,`subcategory`,`position_top`,`created`) values (49,75,0,'3','6',0,'2016-11-02 11:24:00'),(51,77,0,'5','7',0,'2016-11-02 12:59:42'),(52,78,0,'3','7',0,'2016-11-02 14:18:27');

/*Table structure for table `product` */

DROP TABLE IF EXISTS `product`;

CREATE TABLE `product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `product_name` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `package_code` decimal(10,0) DEFAULT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  `description` text COLLATE utf8_unicode_ci,
  `compatibility` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `status` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  `category_id` int(11) DEFAULT NULL,
  `subcategory_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_d34a04ad12469de2` (`category_id`),
  KEY `FK_product_subcategory` (`subcategory_id`),
  CONSTRAINT `FK_product_category` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=87 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `product` */

insert  into `product`(`id`,`product_name`,`package_code`,`price`,`description`,`compatibility`,`status`,`created`,`category_id`,`subcategory_id`) values (75,'Charlie Chaplin - Pawn Shop','35252','55000.00','The Pawnshop was Charlie Chaplin\'s sixth film for Mutual Film Corporation. Released on October 2, 1916, it stars Chaplin in the role of assistant to the pawnshop owner, played by Henry Bergman. Edna Purviance plays the owner\'s daughter, while Albert Austin appears as an alarm clock owner who watches Chaplin in dismay as he dismantles the clock; the massive Eric Campbell\'s character attempts to rob the shop.','All Platform','ACTIVE','2016-11-02 11:24:00',3,6),(77,'Mad Max : Fury Road','347824','100000.00','Years after the collapse of civilization, the tyrannical Immortan Joe enslaves apocalypse survivors inside the desert fortress the Citadel. When the warrior Imperator Furiosa (Charlize Theron) leads the despot\'s five wives in a daring escape, she forges an alliance with Max Rockatansky (Tom Hardy), a loner and former captive. Fortified in the massive, armored truck the War Rig, they try to outrun the ruthless warlord and his henchmen in a deadly high-speed chase through the Wasteland.','All Platform','ACTIVE','2016-11-02 12:59:42',3,7),(78,'Transformers 3: Dark of the Moon','342414','50000.00','This is new movie release in the end of 2016','All Platform','ACTIVE','2016-11-02 14:18:27',3,7),(79,'Game Of Thrones: Season 8 Eps 1','353124','10000.00','u yhea','All Platform','ACTIVE','2018-04-23 13:06:02',3,7),(80,'Game Of Thrones: Season 8 Eps 2','353125','10000.00','Hae Hae','All Platform','ACTIVE','2016-11-02 14:18:27',3,7),(81,'RHCP Dosed','335514','5000.00','eah\r\n','All Platform','ACTIVE','2018-04-23 13:06:02',6,5),(82,'Game Of Thrones: Season 8 Eps 3','335515','10000.00','eah','All Platofrm','ACTIVE','2018-04-23 13:06:02',3,7),(83,'Game Of Thrones: Season 8 Eps 4','335516','10000.00','hh\r\n','All Platform','ACTIVE','2018-04-23 13:06:02',3,7),(84,'The Zephyr Song','339429','5000.00','[2002] - By the Way','All Platform','ACTIVE','2018-04-23 13:06:02',6,5),(85,'By The Way','339430','5000.00','By The way','All Platform','ACTIVE','2018-04-23 13:06:02',6,5),(86,'Throw Away Your Television','339431','5000.00','throw away your television','All Platform','ACTIVE','2018-04-23 13:06:02',NULL,NULL);

/*Table structure for table `product_image` */

DROP TABLE IF EXISTS `product_image`;

CREATE TABLE `product_image` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `product_id` int(11) DEFAULT NULL,
  `image_type_id` int(11) NOT NULL,
  `image_url` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_64617f034584665a` (`product_id`),
  KEY `FK_image_image_type` (`image_type_id`),
  CONSTRAINT `FK_image_image_type` FOREIGN KEY (`image_type_id`) REFERENCES `image_type_product` (`id`),
  CONSTRAINT `FK_product_image` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=75 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `product_image` */

insert  into `product_image`(`id`,`product_id`,`image_type_id`,`image_url`) values (44,77,1,'http://13.59.254.174/1stopclickdata/images/prodart/madmax_furryroad.jpg'),(59,75,1,'https://upload.wikimedia.org/wikipedia/commons/3/37/%27The_Pawnshop%27.jpg'),(61,78,1,'http://cdn.collider.com/wp-content/uploads/T3-IMAX-One-Sheet-FINAL.jpg'),(62,78,2,'https://stmed.net/sites/default/files/transformers%3A-dark-of-the-moon-wallpapers-30151-9400750.jpg'),(63,79,1,'http://13.59.254.174/1stopclickdata/images/prodart/GOT_poster.jpg'),(64,79,2,'http://13.59.254.174/1stopclickdata/images/backdrop/game-of-thrones-season-8-social.jpeg'),(65,80,1,'http://13.59.254.174/1stopclickdata/images/prodart/GOT_poster.jpg'),(66,80,2,'http://13.59.254.174/1stopclickdata/images/backdrop/game-of-thrones-season-8-social.jpeg'),(67,82,1,'http://13.59.254.174/1stopclickdata/images/prodart/GOT_poster.jpg'),(68,82,2,'http://13.59.254.174/1stopclickdata/images/backdrop/game-of-thrones-season-8-social.jpeg'),(69,83,1,'http://13.59.254.174/1stopclickdata/images/prodart/GOT_poster.jpg'),(70,83,2,'http://13.59.254.174/1stopclickdata/images/backdrop/GOT2.jpg'),(71,77,2,'http://13.59.254.174/1stopclickdata/images/backdrop/MadMaxFuryRoad.jpg'),(72,75,2,'https://centuryfilmproject.files.wordpress.com/2016/01/pawnshop_lobby_card_1916.jpg'),(73,81,1,'https://upload.wikimedia.org/wikipedia/en/2/23/Rhcp9.jpg'),(74,84,1,'https://upload.wikimedia.org/wikipedia/en/3/3d/Zephyrsong.jpg');

/*Table structure for table `product_preview` */

DROP TABLE IF EXISTS `product_preview`;

CREATE TABLE `product_preview` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `product_id` int(11) NOT NULL,
  `link` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_product_preview_product_id` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `product_preview` */

/*Table structure for table `receipt` */

DROP TABLE IF EXISTS `receipt`;

CREATE TABLE `receipt` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `invoice_id` int(11) DEFAULT NULL,
  `payment_method_id` int(11) DEFAULT NULL,
  `description` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `total_amount` decimal(10,2) DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_invoice_receipt` (`invoice_id`),
  KEY `FK_payment_method_receipt` (`payment_method_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `receipt` */

insert  into `receipt`(`id`,`invoice_id`,`payment_method_id`,`description`,`total_amount`,`created`) values (1,1,1,'test buy','60000.00','2019-02-02 12:12:00');

/*Table structure for table `review` */

DROP TABLE IF EXISTS `review`;

CREATE TABLE `review` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `product_id` int(11) NOT NULL,
  `rating` int(11) NOT NULL,
  `description` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fki_review_user` (`user_id`),
  KEY `fki_review_product` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `review` */

/*Table structure for table `subcategory` */

DROP TABLE IF EXISTS `subcategory`;

CREATE TABLE `subcategory` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `target` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `priority` int(11) DEFAULT NULL,
  `is_active` tinyint(1) DEFAULT NULL,
  `created` datetime NOT NULL,
  `category_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_ddca44812469de2` (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `subcategory` */

insert  into `subcategory`(`id`,`name`,`target`,`priority`,`is_active`,`created`,`category_id`) values (2,'Socials','#apsocial',2,1,'2016-10-25 08:41:21',4),(3,'Tools','#aptools',2,1,'2016-10-31 10:02:12',4),(4,'Pop','#mscpop',1,1,'2016-11-02 09:38:45',6),(5,'Rock','#mscrock',2,1,'2016-11-02 09:49:47',6),(6,'Comedy','#mvcomedy',1,1,'2016-11-02 11:22:54',3),(7,'Fiksi','#mvfiksi',2,1,'2016-11-02 11:29:42',3),(8,'Novel','#bknovel',1,1,'2016-11-02 12:54:37',5),(9,'Psycology','#bkpsycology',2,1,'2016-11-02 12:56:47',5);

/*Table structure for table `track_artist` */

DROP TABLE IF EXISTS `track_artist`;

CREATE TABLE `track_artist` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `track_id` int(11) DEFAULT NULL,
  `artist_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_album_track_artist_artist` (`artist_id`),
  KEY `FK_track_artist` (`track_id`),
  CONSTRAINT `FK_album_track_artist_artist` FOREIGN KEY (`artist_id`) REFERENCES `artist` (`id`) ON DELETE CASCADE,
  CONSTRAINT `FK_track_artist` FOREIGN KEY (`track_id`) REFERENCES `tracks` (`id`) ON DELETE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

/*Data for the table `track_artist` */

insert  into `track_artist`(`id`,`track_id`,`artist_id`) values (1,1,1),(2,1,2),(3,1,3),(4,1,4),(5,2,1),(6,2,2),(7,2,3),(8,2,4);

/*Table structure for table `track_type` */

DROP TABLE IF EXISTS `track_type`;

CREATE TABLE `track_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(200) DEFAULT NULL,
  `name` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `track_type` */

insert  into `track_type`(`id`,`code`,`name`) values (1,'music','music main'),(2,'preview','preview music');

/*Table structure for table `tracks` */

DROP TABLE IF EXISTS `tracks`;

CREATE TABLE `tracks` (
  `id` int(100) NOT NULL AUTO_INCREMENT,
  `product_id` int(11) DEFAULT NULL,
  `track_type_id` int(11) DEFAULT NULL,
  `stream_url` varchar(2000) DEFAULT NULL,
  `length` int(11) DEFAULT NULL,
  `album_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_tracks` (`product_id`),
  KEY `FK_tracks_track_type` (`track_type_id`),
  KEY `FK_tracks_album` (`album_id`),
  CONSTRAINT `FK_tracks` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`) ON DELETE CASCADE,
  CONSTRAINT `FK_tracks_album` FOREIGN KEY (`album_id`) REFERENCES `albums` (`id`) ON DELETE NO ACTION,
  CONSTRAINT `FK_tracks_track_type` FOREIGN KEY (`track_type_id`) REFERENCES `track_type` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

/*Data for the table `tracks` */

insert  into `tracks`(`id`,`product_id`,`track_type_id`,`stream_url`,`length`,`album_id`) values (1,81,1,'http://13.59.254.174/1stopclickdata/music/rhcp_Dosed.mp3',20,1),(2,84,1,'http://13.59.254.174/1stopclickdata/music/TheZephyrSong.mp3',5,2),(3,85,1,'http://13.59.254.174/1stopclickdata/music/By_the_Way.mp3',5,1),(4,86,1,'http://13.59.254.174/1stopclickdata/music/ThrowAwayYourTelevision.mp3',5,1);

/*Table structure for table `user_profile` */

DROP TABLE IF EXISTS `user_profile`;

CREATE TABLE `user_profile` (
  `id` bigint(255) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `dob` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `image_url` varchar(255) DEFAULT NULL,
  `user_id` bigint(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=latin1;

/*Data for the table `user_profile` */

insert  into `user_profile`(`id`,`name`,`dob`,`phone`,`image_url`,`user_id`) values (7,'dendy prtha','12-12-2012','0123456','Ealah',8),(8,'wadsd','12-12-2012','DRAFT','Ealah',7),(14,'testing','12-12-2012','123456l','asu.bjg',25),(15,'stop test satu','20/6/2019','085243494846','tt',27),(17,'dendy prtha',NULL,NULL,NULL,31),(18,'One Stop Click',NULL,NULL,NULL,32),(19,'kamfrettt','24-07-19','0123456','Ealah',33),(20,'kampret','24/7/2019','0154852616','asdawfw',34);

/*Table structure for table `users` */

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `id` bigint(255) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `email_verified` bit(1) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `provider` varchar(255) DEFAULT NULL,
  `provider_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=latin1;

/*Data for the table `users` */

insert  into `users`(`id`,`email`,`email_verified`,`password`,`provider`,`provider_id`) values (7,'local','','local','local-account','116984227318604993526'),(8,'prtha.dendy@gmail.com','','12345','google','116984227318604997150'),(25,'testing','\0','test3','local-account','04505765147770811172'),(27,'stoptest1@yopmail.com','\0','P@ssw0rd11','local-account','41483015074826100724'),(31,'dendyprtha@gmail.com','',NULL,'google','102715366000470368630'),(32,'testonestopclick@gmail.com','',NULL,'google','100837819285246640790'),(33,'stopclick1@yopmail.com','\0','P@ssw0rd11','local-account','11250530825020281151'),(34,'kampret@kampret.com','\0','12345','local-account','62874067547478527245');

/*Table structure for table `video` */

DROP TABLE IF EXISTS `video`;

CREATE TABLE `video` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `product_id` int(11) DEFAULT NULL,
  `video_type_id` int(100) DEFAULT NULL,
  `release_date` date NOT NULL,
  `studio` varchar(50) NOT NULL,
  `age_rating` int(11) NOT NULL,
  `avg_rating` float DEFAULT NULL,
  `overall_rank` int(11) DEFAULT NULL,
  `stream_url` varchar(2000) NOT NULL,
  `duration` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_product_video` (`product_id`),
  KEY `FK_video_video_type` (`video_type_id`),
  CONSTRAINT `FK_product_video` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  CONSTRAINT `FK_video_video_type` FOREIGN KEY (`video_type_id`) REFERENCES `video_type` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;

/*Data for the table `video` */

insert  into `video`(`id`,`product_id`,`video_type_id`,`release_date`,`studio`,`age_rating`,`avg_rating`,`overall_rank`,`stream_url`,`duration`) values (1,75,1,'1916-10-02','1',1,1,1,'http://13.59.254.174/1stopclickdata/movie/ThePawnshop.mp4',26),(2,75,2,'1916-10-02','1',1,1,1,'8UAy9ynS-l4',2),(3,77,1,'2014-01-01','1',1,1,1,'http://13.59.254.174/1stopclickdata/movie/MAMAFUR1572264SG-RENAME.mkv',200),(4,77,2,'2014-01-01','1',1,1,1,'hEJnMQG9ev8',4),(5,78,1,'2014-01-01','1',1,1,1,'https://ia802609.us.archive.org/4/items/darkmoontrailerilp/transformdarkmoontrail_512kb.mp4',20),(6,78,2,'2014-01-01','1',1,1,1,'3H8bnKdf654',20),(7,79,1,'2019-01-01','1',1,1,1,'http://13.59.254.174/1stopclickdata/movie/GOT08E01.mkv',80),(8,79,2,'2019-01-01','1',1,1,1,'rlR4PJn8b8I',20),(9,80,1,'2019-02-02','1',1,1,1,'http://13.59.254.174/1stopclickdata/movie/GOT08E02.mkv',80),(10,80,2,'2019-02-02','1',1,1,1,'R6YCfVe4eR0',20),(11,82,1,'2019-03-03','1',1,1,1,'http://13.59.254.174/1stopclickdata/movie/GOT08E03.mkv',808),(12,82,2,'2019-03-03','1',1,1,1,'TdkS4Xazz7Q',20),(13,83,1,'2019-04-04','1',1,1,1,'http://13.59.254.174/1stopclickdata/movie/GOT08E04.mkv',90),(14,83,2,'2019-04-04','1',1,1,1,'ksTqLXLUvQ4',20);

/*Table structure for table `video_type` */

DROP TABLE IF EXISTS `video_type`;

CREATE TABLE `video_type` (
  `id` int(100) NOT NULL AUTO_INCREMENT,
  `code` varchar(2000) DEFAULT NULL,
  `name` varchar(2000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `video_type` */

insert  into `video_type`(`id`,`code`,`name`) values (1,'movie','movie'),(2,'trailer','trailer'),(3,'test','test');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
