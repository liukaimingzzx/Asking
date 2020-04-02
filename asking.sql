-- MySQL dump 10.13  Distrib 8.0.18, for Win64 (x86_64)
--
-- Host: 59.110.152.103    Database: asking
-- ------------------------------------------------------
-- Server version	5.6.46-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `answer`
--

DROP TABLE IF EXISTS `answer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `answer` (
  `answer_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `question_id` int(11) DEFAULT NULL,
  `answer_content` text NOT NULL,
  `thumb` int(11) DEFAULT '0',
  `answer_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `username` varchar(1024) DEFAULT NULL,
  `user_avater` varchar(1024) DEFAULT NULL,
  `nickname` varchar(1024) DEFAULT NULL,
  `question_title` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`answer_id`),
  KEY `FK_answer` (`question_id`),
  CONSTRAINT `FK_answer` FOREIGN KEY (`question_id`) REFERENCES `question` (`question_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=68 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `box_answer`
--

DROP TABLE IF EXISTS `box_answer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `box_answer` (
  `box_aid` bigint(20) NOT NULL AUTO_INCREMENT,
  `box_id` int(11) DEFAULT NULL,
  `box_acontent` text NOT NULL,
  `box_atime` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `username` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`box_aid`),
  KEY `FK_private_answer` (`box_id`),
  CONSTRAINT `FK_private_answer` FOREIGN KEY (`box_id`) REFERENCES `question_box` (`box_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `question`
--

DROP TABLE IF EXISTS `question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `question` (
  `question_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(200) DEFAULT NULL,
  `question_title` text NOT NULL,
  `question_content` text NOT NULL,
  `tag` varchar(1024) NOT NULL,
  `question_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `question_view` bigint(20) DEFAULT '0',
  `comment_count` bigint(20) DEFAULT '0',
  `nickname` varchar(1024) DEFAULT NULL,
  `user_avater` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`question_id`),
  KEY `FK_ask` (`username`),
  CONSTRAINT `FK_ask` FOREIGN KEY (`username`) REFERENCES `user` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `question_box`
--

DROP TABLE IF EXISTS `question_box`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `question_box` (
  `box_id` int(11) NOT NULL,
  `username` varchar(200) DEFAULT NULL,
  `box_password` varchar(1024) NOT NULL,
  `box_title` text NOT NULL,
  `box_content` text NOT NULL,
  `box_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `box_count` bigint(20) DEFAULT '0',
  `nickname` varchar(1024) DEFAULT NULL,
  `user_avater` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`box_id`),
  KEY `FK_create` (`username`),
  CONSTRAINT `FK_create` FOREIGN KEY (`username`) REFERENCES `user` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `username` varchar(200) NOT NULL,
  `password` varchar(1024) NOT NULL,
  `nickname` varchar(1024) NOT NULL,
  `profile` varchar(200) DEFAULT '这个人很懒，什么也没有留下',
  `gender` int(11) DEFAULT NULL,
  `coin` int(11) DEFAULT '20',
  `avater` varchar(1024) NOT NULL DEFAULT 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1574701469761&di=0e1045169debf1aed834fc97ff9b2439&imgtype=0&src=http%3A%2F%2Fbpic.588ku.com%2Felement_origin_min_pic%2F01%2F31%2F87%2F96573b585a7c9c4.jpg',
  `last_sign` datetime DEFAULT NULL,
  `que_count` int(11) DEFAULT '0',
  `ans_count` int(11) DEFAULT '0',
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-04-02 10:25:22
