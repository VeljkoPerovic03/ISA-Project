-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               10.4.32-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             12.7.0.6850
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for clothingstore
CREATE DATABASE IF NOT EXISTS `clothingstore` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;
USE `clothingstore`;

-- Dumping structure for table clothingstore.orders
CREATE TABLE IF NOT EXISTS `orders` (
  `order_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `product_id` int(10) unsigned NOT NULL,
  `user_id` int(11) unsigned NOT NULL,
  `order_date` date NOT NULL,
  `status` varchar(255) NOT NULL,
  PRIMARY KEY (`order_id`),
  KEY `fk_orders_products` (`product_id`),
  KEY `fk_orders_users` (`user_id`),
  CONSTRAINT `fk_orders_products` FOREIGN KEY (`product_id`) REFERENCES `products` (`product_id`) ON UPDATE CASCADE,
  CONSTRAINT `fk_orders_users` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table clothingstore.orders: ~0 rows (approximately)

-- Dumping structure for table clothingstore.products
CREATE TABLE IF NOT EXISTS `products` (
  `product_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `type` varchar(255) NOT NULL,
  `size` varchar(255) NOT NULL,
  `manufacturer` varchar(255) NOT NULL,
  `production_date` date NOT NULL,
  `price` double NOT NULL,
  `status` varchar(255) NOT NULL,
  PRIMARY KEY (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table clothingstore.products: ~10 rows (approximately)
INSERT INTO `products` (`product_id`, `name`, `type`, `size`, `manufacturer`, `production_date`, `price`, `status`) VALUES
	(27, 'Majica', 'Letnja', 'M', 'Nike', '2024-06-19', 15, 'dostupna'),
	(28, 'Sorc', 'Letnji', 'L', 'Puma', '2024-06-18', 10, 'dostupan'),
	(29, 'Duks', 'Zimski', 'XL', 'Nike', '2024-06-17', 30, 'dostupan'),
	(30, 'Carape', 'Zimske', 'S', 'Puma', '2024-06-21', 5, 'nije dostupno'),
	(31, 'Patike', 'Letnje', '45', 'Adidas', '2024-06-19', 120, 'dostupno'),
	(32, 'Donji ves', 'duge', 'XXL', 'Calvin Klein', '2024-06-20', 20, 'dostupno'),
	(33, 'Dukserica', 'letnja', 'L', 'Hugo Boss', '2024-06-20', 20, 'dostupno'),
	(34, 'Kacket', 'Letnji', 'L', 'Nike', '2024-06-19', 30, 'dostpuno'),
	(35, 'Nakit', 'all', 'M', 'dior', '2024-06-21', 100, 'dostupno'),
	(36, 'Sorc', 'sport', 'M', 'Nike', '2024-06-20', 40, 'dostupno');

-- Dumping structure for table clothingstore.reviews
CREATE TABLE IF NOT EXISTS `reviews` (
  `review_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `product_id` int(10) unsigned NOT NULL,
  `user_id` int(10) unsigned NOT NULL,
  `rating` int(2) NOT NULL,
  `comment` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`review_id`),
  KEY `fk_reviews_products` (`product_id`),
  KEY `fk_reviews_users` (`user_id`),
  CONSTRAINT `fk_reviews_products` FOREIGN KEY (`product_id`) REFERENCES `products` (`product_id`) ON UPDATE CASCADE,
  CONSTRAINT `fk_reviews_users` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table clothingstore.reviews: ~0 rows (approximately)

-- Dumping structure for table clothingstore.roles
CREATE TABLE IF NOT EXISTS `roles` (
  `role_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `role_name` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`role_id`),
  UNIQUE KEY `role_name` (`role_name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table clothingstore.roles: ~2 rows (approximately)
INSERT INTO `roles` (`role_id`, `role_name`, `name`) VALUES
	(1, 'ROLE_USER', ''),
	(2, 'ROLE_ADMIN', '');

-- Dumping structure for table clothingstore.users
CREATE TABLE IF NOT EXISTS `users` (
  `user_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `full_name` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `phone` varchar(255) NOT NULL,
  `address` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table clothingstore.users: ~18 rows (approximately)
INSERT INTO `users` (`user_id`, `full_name`, `email`, `phone`, `address`, `password`) VALUES
	(2, 'Stefan Stajic', 'stefanstajic@gmail.com', '060101010', 'BG', ''),
	(3, 'Stefan Stajic', 'stefanstajic@gmail.com', '060101010', 'BG', ''),
	(5, 'Stefan Stajic', 'stefanstajic@gmail.com', '060101010', 'BG', '$2a$10$AfW0WJU5rscbpHwue490LOpIMB2kaVezO2aYz8D.1nEU7xHm1Qyfu'),
	(6, 'John Doe', 'test@example.com', '1234567890', '123 Main St', '$2a$10$R03PeHTO9LwxK4Fnzv74qOVORVW13fPglOCa7ewD18Y.c1B6ppCgm'),
	(7, 'Veljko Perovic', 'veljkoperovic0@gmail.com', '0603661172', 'Borska B.B', '$2a$10$95Ct7ZQxBNzdy4mnyOYhte6ozuO1dtvrgc2IGES8f7IrijCATlOqa'),
	(8, 'John Doe', 'test@example1.com', '1234567890', '123 Main St', '$2a$10$BOLfjIcBNbVSeaaAfNN4OuQUb0h378.s7jeiYlq3ybK2HtF1FV6.2'),
	(9, 'Veljko Perovic', 'veljkoperovic1@gmail.com', '0603661172', 'Borska B.B', '$2a$10$6SgG9UdWZUVg8m3DgijeK.zAI4dWJCsc7ydLa5Ivet4eZOM9KGVcC'),
	(10, 'John Doe', 'test@example2.com', '1234567890', '123 Main St', '$2a$10$L6V9lwFdbx.Yz.buCYiBC.QC.4WzTiaPAvzEmRsjJgVAa3idTIOcy'),
	(11, 'John Doe', 'test@example3.com', '1234567890', '123 Main St', '$2a$10$5k82vAoPYEPvJ.2zu9xHxu9d1dhwJx1gWW0JhKPqX3flO4PggSgRS'),
	(12, 'Veljko Perovic', 'veljkoperovic2@gmail.com', '0603661172', 'Borska B.B', '$2a$10$VPRY6/DhUs7KGmlEXKqXfOgG/yHZhFg36iyXIqVuWPXOCVGjtC10W'),
	(13, 'Veljko Perovic', 'veljkoperovic4@gmail.com', '0603661172', 'Borska B.B', '$2a$10$OZG8.qk/qOWoDIcwVkv7de7KNDE49zpugmhMypHK.EhLalTMKt0AO'),
	(14, 'John Doe', 'test@example4.com', '1234567890', '123 Main St', '$2a$10$6wbsJGOR2Mk2oe1D8tYejeB.HwWP/iTbAtGd/v35UY2N0a.Xxny9G'),
	(15, 'John Doe', 'test@example5.com', '1234567890', '123 Main St', '$2a$10$ZCi1cW9EvESAoqFqtKNobeb7JJxvyb.lqgqt9An/dbju9qz/bJtWm'),
	(16, 'John Doe', 'test@example6.com', '1234567890', '123 Main St', '$2a$10$p96rt1hwTAxVMWZlgYWUjuXSBu6I2ORc8brxLgjba3NivlflVbFMq'),
	(17, 'Veljko Perovic', 'veljkoperovic5@gmail.com', '0603661172', 'Borska B.B', '$2a$10$qzkb.CXL91k2amrb0KXK4uF2zl2lrBL1O2OgWD8h8mae63eo8fNMq'),
	(18, 'Veljko Perovic', 'veljkoperovic7@gmail.com', '0603661172', 'Borska B.B', '$2a$10$IFCyAR76roFQ6fDjyA36k.4YUqWweLBXi/LjW5UtEuSIncuvWTF7G'),
	(19, 'Stefan Stajic', 'stefanstajic@gmail.com', '060101010', 'BG', '$2a$10$ZIBuYFoti8w5S5XX0/0EGe29FHgNwzJDt6/GNIrdJMm44LTgiD.j2'),
	(20, 'Veljko Perovic', 'veljkoperovic9@gmail.com', '0603661172', 'Borska B.B', '$2a$10$sZT0JPh5IUQ/gG66MRvSTeC8QDeD.BBnJJbvV3FqdqxP6zD4SuLke');

-- Dumping structure for table clothingstore.user_roles
CREATE TABLE IF NOT EXISTS `user_roles` (
  `user_id` int(10) unsigned NOT NULL,
  `role_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `fk_user_roles_roles` (`role_id`),
  CONSTRAINT `fk_user_roles_roles` FOREIGN KEY (`role_id`) REFERENCES `roles` (`role_id`) ON UPDATE CASCADE,
  CONSTRAINT `fk_user_roles_users` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table clothingstore.user_roles: ~4 rows (approximately)
INSERT INTO `user_roles` (`user_id`, `role_id`) VALUES
	(2, 1),
	(3, 2),
	(13, 2),
	(16, 2);

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
