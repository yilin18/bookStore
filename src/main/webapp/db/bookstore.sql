/*
 Navicat MySQL Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50560
 Source Host           : 127.0.0.1:3306
 Source Schema         : bookstore

 Target Server Type    : MySQL
 Target Server Version : 50560
 File Encoding         : 65001

 Date: 27/04/2019 19:20:32
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for notice
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice`  (
  `n_id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `details` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `n_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`n_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of notice
-- ----------------------------
INSERT INTO `notice` VALUES (1, '公共告1', '11111111111', '2018-04-05 00:00:00');
INSERT INTO `notice` VALUES (2, '公告2', '本周图书销售量再创新高', '2018-04-14 15:40:34');
INSERT INTO `notice` VALUES (3, '公告3', '你好吗', '2018-04-14 15:42:13');
INSERT INTO `notice` VALUES (4, NULL, '儿童袜无无无无无无无无拖无无无无无', '2018-04-14 15:43:34');

-- ----------------------------
-- Table structure for orderitem
-- ----------------------------
DROP TABLE IF EXISTS `orderitem`;
CREATE TABLE `orderitem`  (
  `order_id` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `product_id` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `buynum` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`order_id`, `product_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of orderitem
-- ----------------------------
INSERT INTO `orderitem` VALUES ('15f0c0bd-5e13-48c3-bdcc-0e5c4c68e336', '2', 1);
INSERT INTO `orderitem` VALUES ('1b2a940d-08c3-45c0-bdc6-635ab3857189', '2', 1);
INSERT INTO `orderitem` VALUES ('1c6368c4-fcdd-4fbc-ba00-49f93e52ec18', '2', 2);
INSERT INTO `orderitem` VALUES ('32622d69-1a6a-40e9-9d04-c9a710622d65', '2', 1);
INSERT INTO `orderitem` VALUES ('3bd317c7-0d6a-4139-a916-00a546864fa6', '1', 1);
INSERT INTO `orderitem` VALUES ('3ec21bf2-8817-4c2c-9acd-1c605206cd7e', '1', 1);
INSERT INTO `orderitem` VALUES ('3ec21bf2-8817-4c2c-9acd-1c605206cd7e', '2', 1);
INSERT INTO `orderitem` VALUES ('4244854b-1ee5-46e5-b9e3-7a5ab2313d81', '2', 1);
INSERT INTO `orderitem` VALUES ('45abfac8-1d29-4a7c-8ad6-b0c44c6eecd9', '2', 1);
INSERT INTO `orderitem` VALUES ('662d2949-7d26-4ac8-bed9-bd740ef05653', '1', 1);
INSERT INTO `orderitem` VALUES ('662d2949-7d26-4ac8-bed9-bd740ef05653', '2', 1);
INSERT INTO `orderitem` VALUES ('66a70e53-4328-4801-943d-18cf8262d8c3', '2', 2);
INSERT INTO `orderitem` VALUES ('6e375e30-c333-4d4f-a44b-96285d73b65b', '1', 1);
INSERT INTO `orderitem` VALUES ('6e375e30-c333-4d4f-a44b-96285d73b65b', '2', 1);
INSERT INTO `orderitem` VALUES ('88d91237-f8f4-4975-8fb4-3fc55715f4d6', '1', 1);
INSERT INTO `orderitem` VALUES ('8e784e52-f0bd-43d7-bd26-565540395159', '2', 1);
INSERT INTO `orderitem` VALUES ('8fc052b4-39fd-44fb-84eb-87f5ee924f47', '2', 1);
INSERT INTO `orderitem` VALUES ('a420067a-ab21-4f3f-9c2a-846ec66e64fe', '1', 1);
INSERT INTO `orderitem` VALUES ('d1d81bff-fcff-48f4-bbaf-d2d7ac5b0eea', '1', 1);
INSERT INTO `orderitem` VALUES ('d1d81bff-fcff-48f4-bbaf-d2d7ac5b0eea', '2', 1);
INSERT INTO `orderitem` VALUES ('dc3866d1-3776-4d3c-8e14-2052df55f1e2', '1', 1);
INSERT INTO `orderitem` VALUES ('dc3866d1-3776-4d3c-8e14-2052df55f1e2', '2', 1);
INSERT INTO `orderitem` VALUES ('e8597d2c-05c2-4651-a46b-93ff0bd9a495', '2', 1);

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`  (
  `id` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `money` double NULL DEFAULT NULL,
  `receiverAddress` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `receiverName` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `receiverPhone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `paystate` int(11) NULL DEFAULT 0,
  `ordertime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `user_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES ('e8597d2c-05c2-4651-a46b-93ff0bd9a495', 34, '安阳', '于亚芳', '123456', 0, '2018-04-14 21:29:31', 7);

-- ----------------------------
-- Table structure for products
-- ----------------------------
DROP TABLE IF EXISTS `products`;
CREATE TABLE `products`  (
  `id` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `name` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `price` double NULL DEFAULT NULL,
  `category` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `pnum` int(11) NULL DEFAULT NULL,
  `imgurl` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of products
-- ----------------------------
INSERT INTO `products` VALUES ('', '深入分析Java Web技术内幕（修订版）', 79, NULL, NULL, NULL, NULL);
INSERT INTO `products` VALUES ('1', 'java web', 32, '计算机', 5, '/productImg/10/5/36ee63bc-c251-49ce-9b9a-b5e1e2e75ec0.jpg', '由传智播客高教产品研发部编著的《Java基础入门》从初学者的角度详细讲解了Java开发中重点用到的多种技术。全书共11章，包括Java开发环境的搭建及其运行机制、基本语法、面向对象的思想，采用典型翔实的例子、通俗易懂的语言阐述面向对象中的抽象概念。在多线程、常用API、集合、IO、GUI、网络编程章节中，通过剖析案例、分析代码结构含义、解决常见问题等方式，帮助初学者培养良好的编程习惯。最后，讲解了Eclipse开发工具，帮助初学者熟悉开发工具的使用。\r\n\r\n《Java基础入门》附有配套视频、源代码、测试题');
INSERT INTO `products` VALUES ('2', '时空穿行', 34, '科技', 4, '/productImg/11/4/d79dc124-de69-4b77-847e-bc461bfdb857.jpg', '《时空穿行(中国乡村人类学世纪回访)》对20世纪80年代以来中国云南大理西镇、广东潮州凤凰村、华南茶山等八个代表性乡村田野进行调查，探讨了中国乡村文化的多样性、宗族制度、农民社会等课题。\r\n\r\n《时空穿行(中国乡村人类学世纪回访)》对20世纪80年代以来中国云南大理西镇、广东潮州凤凰村、华南茶山等八个代表性乡村田野进行调查，探讨了中国乡村文化的多样性、宗族制度、农民社会等课题。\r\n\r\n');
INSERT INTO `products` VALUES ('3', '大勇和小花的欧洲日记', 27.5, '少儿', 10, '/productImg/12/1/986b5e98-ee73-4717-89fd-b6ac26a8dc2c.jpg', '大勇和小花：我想去欧洲啊我想去欧洲，所以要挤时间啊，所以要省钱……\r\n\r\n自助：准备充分，不被干扰地享受过程。\r\n\r\n旅行：让你和心爱的地方相遇。\r\n\r\n欧洲：美丽的开始和下一个目标。\r\n\r\n完美：100%的安全，90%的计划执行率和10%的意外惊喜！');
INSERT INTO `products` VALUES ('4', '活着', 38, '文学', 8, '/productImg/1/s29053580.jpg', '《活着(新版)》讲述了农村人福贵悲惨的人生遭遇。福贵本是个阔少爷，可他嗜赌如命，终于赌光了家业，一贫如洗。他的父亲被他活活气死，母亲则在穷困中患了重病，福贵前去求药，却在途中被国民党抓去当壮丁。经过几番波折回到家里，才知道母亲早已去世，妻子家珍含辛茹苦地养大两个儿女。此后更加悲惨的命运一次又一次降临到福贵身上，他的妻子、儿女和孙子相继死去，最后只剩福贵和一头老牛相依为命，但老人依旧活着，仿佛比往日更加洒脱与坚强。\r\n\r\n《活着(新版)》荣获意大利格林扎纳•卡佛文学奖最高奖项（1998年）、台湾《中国时报》');
INSERT INTO `products` VALUES ('5', '别做正常的傻瓜', 19.5, '励志', 3, '/productImg/14/1/792116e7-6d83-4be4-b3e5-4dd11b0b4565.jpg', '你正常吗？也许是的。你傻吗？也许也是的。“正常”的决策者往往做着“傻瓜”的决策，而他们自己还蒙在鼓里。这些傻的错误在许多决策中会出现，包括购物、投资、用人、择偶等。\r\n\r\n这本书基于获得诺贝尔奖的行为决策学，又基于作者10余年的管理教学经验，帮你揭示人们在工作和生活中熟视无睹的决策误区，并教你如何纠正。读完本书，希望你能“少几分正常，多几分理性”，在这充满竞争的世界里胜人一筹。');
INSERT INTO `products` VALUES ('6', '中国国家地理', 23.8, '社科', 20, '/productImg/2/0/2105fbe5-400f-4193-a7db-d7ebac389550.jpg', '《中国国家地理》一书分为上、中、下三卷，以传导自然关怀和人文情感为使命，按地理位置的顺序，用1000多幅美丽绝伦的图片配合生动精良的文字，为读者营造一个感受中国自然地理和人文环境的良好氛围，并将地理知识实现潜移默化式的传递。\r\n\r\n本书禀承现代地理学将地理知识和人文历史有机融合的理念，依照知识严谨性、内容系统性的编写原则，全面介绍中国丰富多彩的地质地貌，近千幅高品质的图片跟随科学、生动的文字，带领读者纵览中国的万里江山，同时了解相关的地理，历史和民俗知识。');
INSERT INTO `products` VALUES ('7', '学会宽容', 28, '励志', 15, '/productImg/6/5/a2da626c-c72d-4972-83de-cf48405c5563.jpg', '宽容是深藏爱心的体谅，宽容是一种智慧和力量，宽容是对生命的洞见 宽容不仅是一种雅里、文明、胸怀，更是一种人生的境界，宽容了别人就等于宽容了自己，宽容的同时，也创造生命的美丽。 宽容是一种仁爱的的光芒、无上的福分，是对别人的释怀，也是对自己的善待，一个人的胸怀能容得下多少人，才能够赢得多少人。 宽容不受约束，它像天下的细雨滋润大地，带来双重祝福：祝福施予者，也祝福被施予者。它力量巨大，贵比皇冠，它与王权同在，与上帝并存。');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `PASSWORD` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `gender` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `telephone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `introduce` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `activeCode` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `state` int(11) NULL DEFAULT 0,
  `role` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '普通用户',
  `registTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 47 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (8, 'abc', '123456', '女', '123@123.com', '12211121212222', '', '38ea92ab-26c2-4725-aa73-b5e899e9767a', 1, '普通用户', '2019-04-27 19:07:47');
INSERT INTO `user` VALUES (16, 'aaa', '111111', '男', 'yuyafangh@163.com', '', '', '9039c221-fef5-4f2a-b1b1-5f6944252cca', 1, '普通用户', '2018-04-15 22:50:35');
INSERT INTO `user` VALUES (37, 'asda', '123456', '男', '964498287@qq.com', '', '', 'aec8aa41-663e-4e61-b0d0-3d742baf7ee8', 0, '普通用户', '2019-04-18 11:12:52');
INSERT INTO `user` VALUES (41, 'suyilin', '123456', '男', '18337281963@163.com', '18337281963', '哈哈', 'b833d309-0696-4399-b1c7-1f9b88354836', 1, '普通用户', '2019-04-18 12:36:21');
INSERT INTO `user` VALUES (42, 'zhy', '123456', NULL, '1218998613@qq.com', '打完', 'null', '478ccd4a-4e7d-4222-8da1-76e434964852', 1, '普通用户', '2019-04-18 15:41:35');
INSERT INTO `user` VALUES (43, 'daw', '123456', '男', '4545@qq.com', '454545454', '545454545', '67cb8493-973a-499c-8649-4a5b31988ab8', 0, '普通用户', '2019-04-18 16:00:48');
INSERT INTO `user` VALUES (44, 'asd', '123456', '男', '1836663155@qq.com', '45454', '4545454', '19284c07-01a0-4251-a610-6b71d8185e3b', 1, '普通用户', '2019-04-24 20:55:41');
INSERT INTO `user` VALUES (45, 'dasda5', '123456', '男', '45454@qq.com', '1212', '12121', 'fc37b0b8-afad-4b0c-a7ab-b9808ec78920', 0, '普通用户', '2019-04-25 19:06:17');
INSERT INTO `user` VALUES (46, 'xux', '123456', '男', '3203811393@qq.com', '1212', '12121', '19b5919a-a65a-4b03-a556-4bd503cb9bcc', 0, '普通用户', '2019-04-25 20:46:13');

SET FOREIGN_KEY_CHECKS = 1;
