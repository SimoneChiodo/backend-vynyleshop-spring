CREATE DATABASE  IF NOT EXISTS `vynyleshop` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `vynyleshop`;
-- MySQL dump 10.13  Distrib 8.0.40, for Win64 (x86_64)
--
-- Host: localhost    Database: vynyleshop
-- ------------------------------------------------------
-- Server version	8.0.40

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
-- Table structure for table `artist`
--

DROP TABLE IF EXISTS `artist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `artist` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `images` varbinary(255) DEFAULT NULL,
  `bio` varchar(500) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `artist`
--

LOCK TABLES `artist` WRITE;
/*!40000 ALTER TABLE `artist` DISABLE KEYS */;
INSERT INTO `artist` VALUES (1,'David Bowie',NULL,'David Bowie was a pioneering English singer, songwriter, and actor, known for his distinctive voice, innovative music, and alter egos like Ziggy Stardust. His career spanned over five decades, during which he pushed boundaries in rock, pop, and electronic music. Bowie influenced generations of artists with his bold style and artistic reinvention.'),(2,'The Beatles',NULL,'The Beatles were an iconic British rock band formed in Liverpool in 1960. Comprising John Lennon, Paul McCartney, George Harrison, and Ringo Starr, they revolutionized music with their groundbreaking songwriting, harmonies, and studio techniques. Their influence on pop culture and music is unparalleled, making them one of the most successful and beloved bands in history.\n\n'),(3,'Queen',NULL,'Queen is a British rock band formed in London in 1970, known for their eclectic style, powerful vocals, and theatrical performances. Fronted by the charismatic Freddie Mercury, Queen produced timeless hits like \"Bohemian Rhapsody\" and \"We Will Rock You.\" Their fusion of rock, opera, and pop continues to captivate audiences worldwide.\n\n');
/*!40000 ALTER TABLE `artist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'ADMIN'),(2,'USER');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `password` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (2,'{noop}123','admin'),(3,'{noop}123','user');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_role` (
  `user_id` int NOT NULL,
  `role_id` int NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FKa68196081fvovjhkek5m97n3y` (`role_id`),
  CONSTRAINT `FK859n2jvi8ivhui0rl0esws6o` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKa68196081fvovjhkek5m97n3y` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (2,1),(3,2);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vinyl`
--

DROP TABLE IF EXISTS `vinyl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vinyl` (
  `id` int NOT NULL AUTO_INCREMENT,
  `available` int NOT NULL,
  `color` varchar(255) NOT NULL,
  `country` varchar(255) NOT NULL,
  `edition` varchar(255) NOT NULL,
  `format` varchar(255) NOT NULL,
  `genre` varchar(255) NOT NULL,
  `images` varbinary(255) DEFAULT NULL,
  `label` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `release_year` int NOT NULL,
  `sideone` varchar(500) NOT NULL,
  `sidetwo` varchar(500) NOT NULL,
  `artist_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKd1x2w7v83y1d2btd7mwvvwfoe` (`artist_id`),
  CONSTRAINT `FKd1x2w7v83y1d2btd7mwvvwfoe` FOREIGN KEY (`artist_id`) REFERENCES `artist` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vinyl`
--

LOCK TABLES `vinyl` WRITE;
/*!40000 ALTER TABLE `vinyl` DISABLE KEYS */;
INSERT INTO `vinyl` VALUES (1,10,'Black','UK','Original','45','Glam Rock',NULL,'RCA Records','The Rise and Fall of Ziggy Stardust and the Spiders from Mars',1972,'[\"Five Years (4:42)\", \"Soul Love (3:34)\"]','[\"Moonage Daydream (4:40)\", \"Starman (4:13)\"]',1),(2,15,'Silver','Germany','Remastered','45','Art Rock',NULL,'RCA Records','Heroes',1977,'[\"Beauty and the Beast (3:35)\"]','[\"Joe the Lion (3:05)\", \"Heroes (6:07)\"]',1),(3,50,'Black','UK','50th Anniversary Edition','33','Rock',NULL,'Apple Records','Abbey Road',1969,'[\"Come Together (4:20)\", \"Something (3:03)\", \"Maxwell\'s Silver Hammer (3:27)\", \"Oh! Darling (3:27)\", \"Octopus\'s Garden (2:51)\", \"I Want You (She’s So Heavy) (7:47)\"]','[\"Here Comes the Sun (3:06)\", \"Because (2:45)\", \"You Never Give Me Your Money (4:02)\", \"Sun King (2:26)\", \"Mean Mr. Mustard (1:06)\", \"Polythene Pam (1:12)\", \"She Came In Through the Bathroom Window (1:57)\", \"Golden Slumbers (1:31)\", \"Carry That Weight (1:36)\", \"The End (2:21)\"]',2),(4,0,'Black','Italy','180 gram reissue','33','Rock',NULL,'EMI','Queen I',2024,'[\"Keep Yourself Alive (3:47)\", \"Doing All Right (4:10)\", \"Great King Rat (4:06)\", \"My Fairy King (4:32)\", \"Liar (6:28)\"]','[\"The Night Comes Down (3:41)\", \"Modern Times Rock \'N\' Roll (2:47)\", \"Son And Daughter (3:19)\", \"Jesus (2:07)\", \"Seven Seas Of Rhye (2:47)\"]',3),(6,10,'White','UK','Collector’s Edition','33','Progressive Rock',NULL,'EMI','A Night at the Opera',1975,'[\"Death on Two Legs (3:43)\", \"Lazing on a Sunday Afternoon (1:08)\", \"I\'m in Love with My Car (3:05)\", \"You\'re My Best Friend (2:50)\", \"\'39 (3:30)\", \"Sweet Lady (4:01)\", \"Seaside Rendezvous (2:13)\"]','[\"The Prophet\'s Song (8:21)\", \"Love of My Life (3:39)\", \"Good Company (3:23)\", \"Bohemian Rhapsody (5:55)\", \"God Save the Queen (1:12)\"]',3),(7,10,'Black','UK','First Pressing','33','Rock',NULL,'Deram Records','David Bowie',1967,' [\"Uncle Arthur (2:07)\", \"Sell Me a Coat (2:58)\", \"Rubber Band (2:17)\", \"Love You till Tuesday (3:09)\", \"There Is a Happy Land (3:11)\", \"We Are Hungry Men (2:58)\", \"When I Live My Dream (3:22)\"]','[\"Little Bombardier (3:24)\", \"Silly Boy Blue (3:48)\", \"Come and Buy My Toys (2:07)\", \"Join the Gang (2:16)\", \"She\'s Got Medals (2:23)\", \"Maid of Bond Street (1:44)\", \"Please Mr. Gravedigger (2:35)\"]',1),(8,15,'Black','UK','First Pressing','33','Rock',NULL,'Philips','Space Oddity',1969,'[\"Space Oddity (5:15)\", \"Unwashed and Somewhat Slightly Dazed (6:15)\", \"Don\'t Sit Down (0:39)\", \"Letter to Hermione (2:30)\", \"Cygnet Committee (9:35)\"]','[\"Janine (3:21)\", \"An Occasional Dream (2:54)\", \"Wild Eyed Boy from Freecloud (4:46)\", \"God Knows I\'m Good (3:16)\", \"Memory of a Free Festival (7:09)\"]',1),(9,20,'Black','UK','First Pressing','33','Rock',NULL,'Parlophone','Please Please Me',1963,'[\"I Saw Her Standing There (2:55)\", \"Misery (1:50)\", \"Anna (Go to Him) (2:57)\", \"Chains (2:26)\", \"Boys (2:27)\", \"Ask Me Why (2:26)\", \"Please Please Me (2:00)\"]','[\"Love Me Do (2:22)\", \"P.S. I Love You (2:05)\", \"Baby It\'s You (2:38)\", \"Do You Want to Know a Secret (1:59)\", \"A Taste of Honey (2:05)\", \"There\'s a Place (1:52)\", \"Twist and Shout (2:33)\"]',2),(10,18,'Black','UK','First Pressing','33','Rock',NULL,'Parlophone','With the Beatles',1963,'[\"It Won\'t Be Long (2:13)\", \"All I\'ve Got to Do (2:03)\", \"All My Loving (2:10)\", \"Don\'t Bother Me (2:28)\", \"Little Child (1:46)\", \"Till There Was You (2:14)\", \"Please Mister Postman (2:34)\"]','[\"Roll Over Beethoven (2:45)\", \"Hold Me Tight (2:30)\", \"You Really Got a Hold on Me (3:01)\", \"I Wanna Be Your Man (1:59)\", \"Devil in Her Heart (2:26)\", \"Not a Second Time (2:07)\", \"Money (That\'s What I Want) (2:50)\"]',2),(11,5,'Black','UK','First Pressing','33','Rock',NULL,'EMI','Queen',1973,'[\"Keep Yourself Alive (3:47)\", \"Doing All Right (4:09)\", \"Great King Rat (5:43)\", \"My Fairy King (4:08)\"]','[\"Liar (6:25)\", \"The Night Comes Down (4:23)\", \"Modern Times Rock \'n\' Roll (1:48)\", \"Son and Daughter (3:20)\", \"Jesus (3:44)\", \"Seven Seas of Rhye (1:15)\"]',3),(12,0,'Black','UK','First Pressing','33','Rock',NULL,'EMI','Queen II',1974,'[\"Procession (1:12)\", \"Father to Son (6:14)\", \"White Queen (As It Began) (4:34)\", \"Some Day One Day (4:22)\", \"The Loser in the End (4:01)\"]','[\"Ogre Battle (4:08)\", \"The Fairy Feller\'s Master-Stroke (2:41)\", \"Nevermore (1:18)\", \"The March of the Black Queen (6:33)\", \"Funny How Love Is (2:50)\", \"Seven Seas of Rhye (2:47)\"]',3);
/*!40000 ALTER TABLE `vinyl` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-06-05  2:11:13
