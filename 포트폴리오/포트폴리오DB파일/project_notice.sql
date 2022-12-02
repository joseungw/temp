-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: project
-- ------------------------------------------------------
-- Server version	8.0.29

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
-- Table structure for table `notice`
--

DROP TABLE IF EXISTS `notice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `notice` (
  `notenum` int NOT NULL AUTO_INCREMENT,
  `userid` varchar(15) DEFAULT NULL,
  `usernick` varchar(10) DEFAULT NULL,
  `category` varchar(10) DEFAULT NULL,
  `notedate` varchar(20) DEFAULT NULL,
  `title` text,
  `contents` text,
  `region` text,
  PRIMARY KEY (`notenum`)
) ENGINE=InnoDB AUTO_INCREMENT=81 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notice`
--

LOCK TABLES `notice` WRITE;
/*!40000 ALTER TABLE `notice` DISABLE KEYS */;
INSERT INTO `notice` VALUES (7,'paul796','조승우','배드민턴','2022-9-7-1:18','테스트','테스트','남양주'),(18,'test12','미니언','축구','2022-9-14-10:34','테스트712','테스트712','서울'),(21,'paul796','조승우','축구','2022-9-15-9:26','test구합니다','ㄴㅁㅇㅎ테스트에여\r\n안드로이드 할사람\r\nㅇㄻㅇㄹ','대구'),(23,'test12','미니언','축구','2022-9-15-2:51','이야야야야양','우히ㅏㅁㄴ휘ㅏㅁ후;ㅣㅁㄴ\r\n모뫃ㅁㄹㅇㅎㄹㅇ','서울'),(25,'test12','미니언','농구','2022-9-15-4:7','농구 테스트이ㅏㅂ니다.','사라묵ㅎ마ㅣ호;ㅣ모히ㅏ뮈ㅏ무히;ㅜ\r\nㅁ흄;ㅣㅏ휴밓','수원'),(57,'test12','미니언','축구','2022-9-16-3:48','123124','12412412413','부산'),(58,'test12','미니언','골프','2022-9-16-3:49','gsagdeagedagae','dgasgfgfdsghfdshsdf','대구'),(59,'test12','미니언','농구','2022-9-20-2:0','dfdafdafdasf','adfsadfdsafsadasdfsdafsadf','수원'),(60,'test12','미니언','축구','2022-9-20-5:45','노원구 8시 마들공원에서 축구 하실분 구합니다.','노원구 마들근린공원에서 8시 입니다. 용병 2분 구합니다. 유니폼 색은 검은색이며 실력은 중하정도 됩니다.','서울'),(61,'test11','호날두','축구','2022-9-22-2:55','보이나여','보이는 테스트','서울'),(62,'test12','미니언','골프','2022-9-26-3:12','골프 라운딩 같이 갈 사람 구해요','제주도로 라운딩 가실분 구합니다.','수원'),(63,'test12','미니언','축구','2022-9-26-3:13','토요일 오후에 축구 하실 분!!!','토요일 오후 5시에 노원구에서 축구 합니다. 용병 두분 연락주세요.','인천'),(64,'test12','미니언','야구','2022-9-26-5:21','야구 인원 모집합니다.','모집해요','남양주'),(65,'test12','미니언','야구','2022-9-26-5:22','야구 인원 모집합니다.','모집해요','남양주'),(66,'test12','미니언','골프','2022-9-26-5:22','부산 골프 하실분','스크린 골프에요','부산'),(67,'test12','미니언','골프','2022-9-26-5:22','부산 골프 하실분','스크린 골프에요','부산'),(68,'test12','미니언','야구','2022-9-26-5:23','의정부 야구입니다.','시합니에요','의정부'),(69,'test12','미니언','농구','2022-9-26-5:24','길거리 농구 하실분','농구시합입니다.','남양주'),(70,'test12','미니언','축구','2022-9-26-5:28','상대편 구합니다. 구장은 준비 되어있어요.','댓글 달아주세요.','남양주'),(71,'test12','미니언','축구','2022-9-27-9:31','123','123123','서울'),(72,'test12','미니언','축구','2022-9-27-9:32','축구 사람구합니다.','축구 해요','서울'),(73,'test12','미니언','축구','2022-9-27-9:33','축구 사람구합니다.','축구 해요','서울'),(74,'test12','미니언','축구','2022-9-27-9:33','도봉구 축구 하실분','축구축구','서울'),(75,'test12','미니언','축구','2022-9-27-9:34','구로축구장 10시 입니다.','축구 10에 하실분','서울'),(76,'test12','미니언','축구','2022-9-27-9:34','축구 합니다. 회비 없어요.','회비 없어요 축구 합시다','서울'),(77,'test12','미니언','축구','2022-9-27-9:35','12341234','12341234','서울'),(78,'test12','미니언','농구','2022-9-27-0:52','중랑천에서 농구하실분','농구 3:3입니다.','수원'),(79,'test12','미니언','축구','2022-9-27-1:53','축구 할사람구합니다.','축구 할사람','서울'),(80,'test12','미니언','축구','2022-9-27-1:53','축구','축구','서울');
/*!40000 ALTER TABLE `notice` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-09-28 16:31:02
