-- MySQL dump 10.13  Distrib 5.7.21, for macos10.13 (x86_64)
--
-- Host: localhost    Database: market
-- ------------------------------------------------------
-- Server version	5.7.21

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `t_businessman`
--

DROP TABLE IF EXISTS `t_businessman`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_businessman` (
  `user_id` varchar(255) NOT NULL,
  `nickname` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `balance` double NOT NULL DEFAULT '0',
  `login_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `logout_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `nickname` (`nickname`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_businessman`
--

LOCK TABLES `t_businessman` WRITE;
/*!40000 ALTER TABLE `t_businessman` DISABLE KEYS */;
INSERT INTO `t_businessman` VALUES ('64c9ac2bb5fe36c3ac32844bb97be6bc','seller','981c57a5cfb0f868e064904b8745766f',32773,'2019-03-10 08:13:43','2019-03-10 08:16:52','2019-03-10 08:16:52');
/*!40000 ALTER TABLE `t_businessman` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_cart_info`
--

DROP TABLE IF EXISTS `t_cart_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_cart_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(255) NOT NULL,
  `product_id` int(11) NOT NULL,
  `product_name` varchar(255) NOT NULL,
  `cart_count` int(11) NOT NULL,
  `cart_price` double NOT NULL,
  `cart_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_cart_info`
--

LOCK TABLES `t_cart_info` WRITE;
/*!40000 ALTER TABLE `t_cart_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_cart_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_image`
--

DROP TABLE IF EXISTS `t_image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_image` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `product_id` int(11) NOT NULL,
  `product_name` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `url` varchar(1000) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_image`
--

LOCK TABLES `t_image` WRITE;
/*!40000 ALTER TABLE `t_image` DISABLE KEYS */;
INSERT INTO `t_image` VALUES (1,1254243769,'荣耀MagicBook','779a582d0c08beb3.jpg','https://img10.360buyimg.com/n7/jfs/t1/10718/1/857/207189/5bdbf1fcE4c6fdbfb/779a582d0c08beb3.jpg'),(2,-992714668,'联想(Lenovo)拯救者Y7000P','fd6035633ebff93a.jpg','https://img12.360buyimg.com/n7/jfs/t1/27368/40/7173/165300/5c67ed1eEc7ad4926/fd6035633ebff93a.jpg'),(3,-966949219,'Apple MacBook Pro 13.3英寸笔记本电脑','5b4ee806N5fdcb463.jpg','/images/5b4ee806N5fdcb463.jpg'),(4,1728684553,'Apple iPhone XS (A2100) 64GB 金色','0ace3ed19582dbe6.jpg','https://img13.360buyimg.com/n7/jfs/t1/1468/11/3377/138213/5b997bf3Eda5b24a4/0ace3ed19582dbe6.jpg'),(5,-383221563,'Apple iPhone 8 Plus (A1864) 64GB 银色','59b85834Ne59b864d.jpg','https://img10.360buyimg.com/n7/jfs/t9313/81/1352027902/75682/4abc569f/59b85834Ne59b864d.jpg'),(6,395854140,'百富帝（byford）四件套纯棉家纺','5b7bc83aNc0eefa18.jpg','https://img11.360buyimg.com/n7/jfs/t22987/40/2367936658/201422/3a149fa9/5b7bc83aNc0eefa18.jpg'),(7,-1086446541,'英特尔（Intel） i7 8700 酷睿六核','5afe4de8Nda23f166.jpg','https://img12.360buyimg.com/n7/jfs/t19876/121/551161046/273454/946beee7/5afe4de8Nda23f166.jpg'),(8,2102603512,'英特尔（Intel）i9-9900k 酷睿八核','5bbb0c22N7e7df7d2.jpg','https://img14.360buyimg.com/n7/jfs/t26098/41/1768184792/197098/91c61ded/5bbb0c22N7e7df7d2.jpg'),(9,1920167552,'耐克 NIKE REVOLUTION 3 男子跑步鞋','16751c5765a98f18.png','https://img14.360buyimg.com/n7/jfs/t1/24865/18/1406/92217/5c11bcf3E9428104a/16751c5765a98f18.png'),(11,-323959951,'极米（XGIMI）H2 投影机','5b63b66fN5843920c.jpg','https://img14.360buyimg.com/n7/jfs/t25228/325/51161811/225627/b746a90c/5b63b66fN5843920c.jpg');
/*!40000 ALTER TABLE `t_image` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_inventory`
--

DROP TABLE IF EXISTS `t_inventory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_inventory` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(255) NOT NULL,
  `product_id` int(11) NOT NULL,
  `count` int(11) DEFAULT '0',
  `has_sold` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=257 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_inventory`
--

LOCK TABLES `t_inventory` WRITE;
/*!40000 ALTER TABLE `t_inventory` DISABLE KEYS */;
INSERT INTO `t_inventory` VALUES (247,'64c9ac2bb5fe36c3ac32844bb97be6bc',1254243769,999,0),(248,'64c9ac2bb5fe36c3ac32844bb97be6bc',-992714668,1000,0),(249,'64c9ac2bb5fe36c3ac32844bb97be6bc',-966949219,222,1),(250,'64c9ac2bb5fe36c3ac32844bb97be6bc',1728684553,1234,0),(251,'64c9ac2bb5fe36c3ac32844bb97be6bc',-383221563,100,1),(252,'64c9ac2bb5fe36c3ac32844bb97be6bc',395854140,2000,0),(253,'64c9ac2bb5fe36c3ac32844bb97be6bc',-1086446541,120,0),(254,'64c9ac2bb5fe36c3ac32844bb97be6bc',2102603512,20,3),(255,'64c9ac2bb5fe36c3ac32844bb97be6bc',1920167552,95,0),(256,'64c9ac2bb5fe36c3ac32844bb97be6bc',-323959951,10,0);
/*!40000 ALTER TABLE `t_inventory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_product`
--

DROP TABLE IF EXISTS `t_product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_product` (
  `product_id` int(11) NOT NULL,
  `product_name` varchar(255) NOT NULL,
  `product_abstract` varchar(255) NOT NULL,
  `product_description` varchar(1100) NOT NULL,
  `product_price` double NOT NULL,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`product_id`),
  UNIQUE KEY `product_name` (`product_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_product`
--

LOCK TABLES `t_product` WRITE;
/*!40000 ALTER TABLE `t_product` DISABLE KEYS */;
INSERT INTO `t_product` VALUES (-1086446541,'英特尔（Intel） i7 8700 酷睿六核','英特尔（Intel） i7 8700 酷睿六核 盒装CPU处理器','英特尔（Intel） i7 8700 酷睿六核 盒装CPU处理器，LGA1151芯片接口,六核十二线程,睿频可至4.6GHz!联合购买B360主板享受畅快体验!',2499,'2019-03-09 05:59:04'),(-992714668,'联想(Lenovo)拯救者Y7000P','A面金属抗压防划抗腐蚀，144Hz更高刷新率消除图像闪烁抖动画面更流畅，三边窄边框，512G_PCIE大固态硬盘存储更快速','A面金属抗压防划抗腐蚀，144Hz更高刷新率消除图像闪烁抖动画面更流畅，三边窄边框，512G_PCIE大固态硬盘存储更快速。',8599,'2019-03-09 05:49:23'),(-966949219,'Apple MacBook Pro 13.3英寸笔记本电脑','深空灰色 2018新款（四核八代i5 8G 256G固态硬盘 MR9Q2CH/A）','深空灰色 2018新款（四核八代i5 8G 256G固态硬盘 MR9Q2CH/A）',14177,'2019-03-09 05:51:56'),(-383221563,'Apple iPhone 8 Plus (A1864) 64GB 银色','Apple iPhone 8 Plus (A1864) 64GB 银色 移动联通电信4G手机','Apple iPhone 8 Plus (A1864) 64GB 银色 移动联通电信4G手机',4799,'2019-03-09 05:56:25'),(-323959951,'极米（XGIMI）H2 投影机','投影仪 家用（1080P分辨率 1350ANSI流明 哈曼卡顿音响 京鱼座智能生态产品）','投影仪 家用（1080P分辨率 1350ANSI流明 哈曼卡顿音响 京鱼座智能生态产品）',4149,'2019-03-09 06:04:46'),(395854140,'百富帝（byford）四件套纯棉家纺','百富帝（byford）四件套纯棉家纺 床上用品床单枕套双人全棉斜纹套件1.5/1.8米床被套200*230 慕尼黑','百富帝（byford）四件套纯棉家纺 床上用品床单枕套双人全棉斜纹套件1.5/1.8米床被套200*230 慕尼黑',159,'2019-03-09 05:57:44'),(1254243769,'荣耀MagicBook','金属微边框轻薄本，512G大固态高性能机皇，薄至15.8mm，护眼全高清IPS屏','荣耀MagicBook 14英寸轻薄窄边框笔记本电脑（AMD锐龙5 8G 512G FHD IPS 正版Office）冰河银。金属微边框轻薄本，512G大固态高性能机皇，薄至15.8mm，护眼全高清IPS屏',4299,'2019-03-09 05:48:06'),(1728684553,'Apple iPhone XS (A2100) 64GB 金色','视网膜全面屏，1200万后置双摄。','视网膜全面屏，1200万后置双摄。',7888,'2019-03-09 05:55:14'),(1920167552,'耐克 NIKE REVOLUTION 3 男子跑步鞋','耐克 NIKE REVOLUTION 3 男子跑步鞋','耐克 NIKE REVOLUTION 3 男子跑步鞋',285,'2019-03-09 06:01:50'),(2102603512,'英特尔（Intel）i9-9900k 酷睿八核','LGA1151芯片接口,八核十六线程,睿频可至5.0GHz!推荐联合购买Z390主板享受超频快感!','LGA1151芯片接口,八核十六线程,睿频可至5.0GHz!推荐联合购买Z390主板享受超频快感!',4599,'2019-03-09 06:00:16');
/*!40000 ALTER TABLE `t_product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_shopping_info`
--

DROP TABLE IF EXISTS `t_shopping_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_shopping_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(255) NOT NULL,
  `product_id` int(11) NOT NULL,
  `product_name` varchar(255) NOT NULL,
  `shopping_count` int(11) NOT NULL,
  `shopping_price` double NOT NULL,
  `shopping_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=240 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_shopping_info`
--

LOCK TABLES `t_shopping_info` WRITE;
/*!40000 ALTER TABLE `t_shopping_info` DISABLE KEYS */;
INSERT INTO `t_shopping_info` VALUES (235,'794aad24cbd53461811ed9094b7fa212',-649681688,'小米（MI）小米盒子3',2,449,'2019-03-09 06:12:31'),(236,'794aad24cbd53461811ed9094b7fa212',-966949219,'Apple MacBook Pro 13.3英寸笔记本电脑',1,14177,'2019-03-09 06:12:31'),(237,'794aad24cbd53461811ed9094b7fa212',-383221563,'Apple iPhone 8 Plus (A1864) 64GB 银色',1,4799,'2019-03-09 06:12:31'),(238,'794aad24cbd53461811ed9094b7fa212',2102603512,'英特尔（Intel）i9-9900k 酷睿八核',3,4599,'2019-03-10 08:09:35'),(239,'794aad24cbd53461811ed9094b7fa212',29754951,'电灯泡',10,20,'2019-03-10 08:18:14');
/*!40000 ALTER TABLE `t_shopping_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_user`
--

DROP TABLE IF EXISTS `t_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_user` (
  `user_id` varchar(255) NOT NULL,
  `nickname` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `balance` double NOT NULL DEFAULT '0',
  `login_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `logout_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `nickname` (`nickname`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_user`
--

LOCK TABLES `t_user` WRITE;
/*!40000 ALTER TABLE `t_user` DISABLE KEYS */;
INSERT INTO `t_user` VALUES ('794aad24cbd53461811ed9094b7fa212','buyer','37254660e226ea65ce6f1efd54233424',966129,'2019-03-10 08:30:53','2019-03-10 08:31:44','2019-03-10 08:31:44');
/*!40000 ALTER TABLE `t_user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-03-12 10:16:48
