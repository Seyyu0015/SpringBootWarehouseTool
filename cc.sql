/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80029
 Source Host           : localhost:3306
 Source Schema         : cc

 Target Server Type    : MySQL
 Target Server Version : 80029
 File Encoding         : 65001

 Date: 10/09/2022 16:53:58
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for food
-- ----------------------------
DROP TABLE IF EXISTS `food`;
CREATE TABLE `food`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `food` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `value` double(5, 2) UNSIGNED ZEROFILL NULL DEFAULT NULL,
  `user1ate` int(1) UNSIGNED ZEROFILL NOT NULL DEFAULT 0,
  `user2ate` int(1) UNSIGNED ZEROFILL NOT NULL DEFAULT 0,
  `user3ate` int(1) UNSIGNED ZEROFILL NOT NULL DEFAULT 0,
  `user4ate` int(1) UNSIGNED ZEROFILL NOT NULL DEFAULT 0,
  `user5ate` int(1) UNSIGNED ZEROFILL NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of food
-- ----------------------------
INSERT INTO `food` VALUES (1, '鸡蛋', 02.50, 1, 0, 1, 0, 0);
INSERT INTO `food` VALUES (2, '韭菜', 05.00, 1, 1, 0, 0, 1);
INSERT INTO `food` VALUES (3, '丸子', 18.20, 0, 1, 0, 1, 0);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '张三');
INSERT INTO `user` VALUES (2, '左瀚文');
INSERT INTO `user` VALUES (3, '程哲啉');
INSERT INTO `user` VALUES (4, '李家宇');
INSERT INTO `user` VALUES (5, '翟浩淼');

SET FOREIGN_KEY_CHECKS = 1;
