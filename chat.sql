/*
SQLyog Ultimate v11.33 (64 bit)
MySQL - 5.7.19-log : Database - chat
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`chat` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `chat`;

/*Table structure for table `friend` */

DROP TABLE IF EXISTS `friend`;

CREATE TABLE `friend` (
  `userNameOne` varchar(100) NOT NULL,
  `userNameOther` varchar(100) NOT NULL,
  KEY `userNameOne` (`userNameOne`),
  KEY `userNameOther` (`userNameOther`),
  CONSTRAINT `friend_ibfk_1` FOREIGN KEY (`userNameOne`) REFERENCES `user` (`name`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `friend_ibfk_2` FOREIGN KEY (`userNameOther`) REFERENCES `user` (`name`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `friend` */

insert  into `friend`(`userNameOne`,`userNameOther`) values ('蔡荣镔','凌浩'),('蔡荣镔','廖健强'),('蔡荣镔','张艺隽'),('廖健强','凌浩'),('张艺隽','廖健强'),('凌浩','张艺隽');

/*Table structure for table `info` */

DROP TABLE IF EXISTS `info`;

CREATE TABLE `info` (
  `userOne` varchar(100) NOT NULL,
  `userAnother` varchar(100) NOT NULL,
  `content` varchar(5000) NOT NULL,
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`userOne`,`userAnother`),
  KEY `userAnother` (`userAnother`),
  CONSTRAINT `info_ibfk_1` FOREIGN KEY (`userOne`) REFERENCES `user` (`name`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `info_ibfk_2` FOREIGN KEY (`userAnother`) REFERENCES `user` (`name`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `info` */

insert  into `info`(`userOne`,`userAnother`,`content`,`time`) values ('蔡荣镔','凌浩','发送\n','2018-06-25 20:08:35');

/*Table structure for table `role` */

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `id` int(11) NOT NULL,
  `name` varchar(11) NOT NULL,
  `title` varchar(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `role` */

insert  into `role`(`id`,`name`,`title`) values (1,'user','普通用户'),(2,'member','会员'),(3,'superMember','超级会员'),(4,'admin','管理员');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `name` varchar(100) NOT NULL,
  `age` smallint(6) NOT NULL,
  `email` varchar(100) NOT NULL,
  `phoneNumber` varchar(11) NOT NULL,
  `password` varchar(100) NOT NULL,
  `gender` varchar(10) NOT NULL,
  `birthday` date DEFAULT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`name`,`age`,`email`,`phoneNumber`,`password`,`gender`,`birthday`) values ('凌浩',19,'2438062943@qq.com','15914760918','c4ca4238a0b923820dcc509a6f75849b','男',NULL),('廖健强',19,'i don\'t know','12345678901','c4ca4238a0b923820dcc509a6f75849b','男',NULL),('张艺隽',19,'i don\'t konw','12345678901','c4ca4238a0b923820dcc509a6f75849b','男',NULL),('蔡荣镔',19,'2601042086@qq.com','15521131057','c4ca4238a0b923820dcc509a6f75849b','男',NULL);

/*Table structure for table `user_role` */

DROP TABLE IF EXISTS `user_role`;

CREATE TABLE `user_role` (
  `user_name` varchar(100) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`user_name`,`role_id`),
  KEY `roleID` (`role_id`),
  CONSTRAINT `user_role_ibfk_1` FOREIGN KEY (`user_name`) REFERENCES `user` (`name`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `user_role_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `user_role` */

insert  into `user_role`(`user_name`,`role_id`) values ('凌浩',1),('廖健强',1),('张艺隽',1),('蔡荣镔',1),('蔡荣镔',2),('蔡荣镔',3),('蔡荣镔',4);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
