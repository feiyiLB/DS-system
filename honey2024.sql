/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50737
 Source Host           : localhost:3306
 Source Schema         : honey2024

 Target Server Type    : MySQL
 Target Server Version : 50737
 File Encoding         : 65001

 Date: 15/10/2023 11:57:15
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for logs
-- ----------------------------
DROP TABLE IF EXISTS `logs`;
CREATE TABLE `logs`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `operation` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '操作名称',
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '操作类型',
  `ip` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT 'ip地址',
  `user` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '操作人',
  `time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '系统日志' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of logs
-- ----------------------------
INSERT INTO `logs` VALUES (4, '公告', '新增', '127.0.0.1', 'admin', '2023-10-06 20:22:54');
INSERT INTO `logs` VALUES (5, '公告', '修改', '127.0.0.1', 'admin', '2023-10-06 20:25:03');
INSERT INTO `logs` VALUES (6, '公告', '修改', '127.0.0.1', 'admin', '2023-10-06 20:25:12');
INSERT INTO `logs` VALUES (7, '公告', '删除', '127.0.0.1', 'admin', '2023-10-06 20:25:23');
INSERT INTO `logs` VALUES (8, '公告', '新增', '127.0.0.1', 'admin', '2023-10-06 20:25:31');
INSERT INTO `logs` VALUES (9, '公告', '批量删除', '127.0.0.1', 'admin', '2023-10-06 20:25:36');
INSERT INTO `logs` VALUES (10, '文件', '新增', '127.0.0.1', 'admin', '2023-10-06 20:28:39');
INSERT INTO `logs` VALUES (11, '用户', '修改', '127.0.0.1', 'admin', '2023-10-06 20:28:40');
INSERT INTO `logs` VALUES (12, '用户', '删除', '127.0.0.1', 'admin', '2023-10-06 20:29:09');
INSERT INTO `logs` VALUES (13, '用户', '注册', '127.0.0.1', 'gbb', '2023-10-06 20:35:11');
INSERT INTO `logs` VALUES (14, '用户', '登录', '127.0.0.1', 'admin', '2023-10-06 20:35:20');
INSERT INTO `logs` VALUES (15, '用户', '登录', '127.0.0.1', 'admin', '2023-10-08 19:33:00');
INSERT INTO `logs` VALUES (16, '用户', '登录', '127.0.0.1', 'admin', '2023-10-08 21:45:50');
INSERT INTO `logs` VALUES (17, '订单', '新增', '127.0.0.1', 'admin', '2023-10-08 21:46:21');
INSERT INTO `logs` VALUES (18, '订单', '新增', '127.0.0.1', 'admin', '2023-10-08 21:46:41');
INSERT INTO `logs` VALUES (19, '订单', '新增', '127.0.0.1', 'admin', '2023-10-08 21:46:56');
INSERT INTO `logs` VALUES (20, '订单', '新增', '127.0.0.1', 'admin', '2023-10-08 21:47:07');
INSERT INTO `logs` VALUES (21, '订单', '修改', '127.0.0.1', 'admin', '2023-10-08 21:47:33');
INSERT INTO `logs` VALUES (22, '订单', '修改', '127.0.0.1', 'admin', '2023-10-08 21:47:36');

-- ----------------------------
-- Table structure for news
-- ----------------------------
DROP TABLE IF EXISTS `news`;
CREATE TABLE `news`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '标题',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '简介',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '内容',
  `authorid` int(11) NULL DEFAULT NULL COMMENT '发布人id',
  `time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '发布时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '新闻信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of news
-- ----------------------------
INSERT INTO `news` VALUES (1, '1111', '111', '111', NULL, NULL);
INSERT INTO `news` VALUES (2, '222', '222', '222', 25, '2023-09-12 21:09:56');
INSERT INTO `news` VALUES (3, '111343124', '222', '3333', 25, '2023-09-12 21:16:16');
INSERT INTO `news` VALUES (4, '6666', '6666', '666', 25, '2023-09-12 21:18:27');
INSERT INTO `news` VALUES (5, '21111', NULL, NULL, 25, '2023-09-12 21:24:12');
INSERT INTO `news` VALUES (6, '2222', NULL, NULL, 25, '2023-09-12 21:26:14');
INSERT INTO `news` VALUES (7, '哈哈 换键盘了', '哈哈 换键盘了', '<p><b>哈哈 今天换键盘了</b></p><pre><code class=\"JavaScript\"><span class=\"hljs-attribute\">leta</span> <span class=\"hljs-operator\">=</span> <span class=\"hljs-number\">1</span></code></pre><table border=\"0\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\"><tbody><tr><th>1</th><th>2</th><th>3</th><th>4</th><th>5</th></tr><tr><td>上课了</td><td></td><td></td><td></td><td></td></tr><tr><td></td><td></td><td></td><td></td><td></td></tr><tr><td></td><td></td><td></td><td></td><td></td></tr><tr><td></td><td></td><td></td><td></td><td></td></tr></tbody></table><p><img src=\"http://localhost:9090/file/download/QQ截图20230407195912.png\" style=\"max-width:100%;\" contenteditable=\"false\"/></p>', 25, '2023-09-12 21:32:43');
INSERT INTO `news` VALUES (8, '我来上传一个图片', '我来上传一个图片', '<p><img src=\"http://localhost:9090/file/download/1694526647797_微信截图_20230803195808.png\" style=\"max-width:100%;\" contenteditable=\"false\"/><iframe src=\"//player.bilibili.com/player.html?aid=361595852&amp;bvid=BV1g94y1e7MW&amp;cid=1215041025&amp;p=1\" scrolling=\"no\" border=\"0\" frameborder=\"no\" framespacing=\"0\" allowfullscreen=\"true\"> </iframe></p>', 25, '2023-09-12 21:50:49');
INSERT INTO `news` VALUES (9, '我来上传视频试试', '我来上传视频试试', '<p>我来上传视频试试</p><p data-we-empty-p=\"\" style=\"text-align:left;\"><video src=\"http://localhost:9090/file/download/1694527574710_Clouds - 64759.mp4\" controls=\"controls\" style=\"max-width:100%\" width=\"50%\"></video></p>', 25, '2023-09-12 22:06:37');
INSERT INTO `news` VALUES (10, '哈啊哈哈', '哈啊哈哈', '<p><video src=\"http://localhost:9090/file/download/Clouds-450k.mp4\" controls=\"controls\" style=\"max-width:100%\"></video></p>', 25, '2023-09-12 22:08:58');
INSERT INTO `news` VALUES (11, '图片设置', '图片设置', '<p><b>青哥哥是不是很牛逼？？</b></p><p><b><br/></b></p><p><b>三个小6扣起来！！</b></p><p><b><br/></b></p><pre><code class=\"Java\"><span class=\"hljs-class\"><span class=\"hljs-keyword\">class</span> 青哥哥 </span>{\n\n}</code></pre><p><img src=\"http://localhost:9090/file/download/微信截图_20230801220015.png\" style=\"max-width:100%;\" contenteditable=\"false\" width=\"30%\"/><br/><video src=\"http://localhost:9090/file/download/测试.mp4\" controls=\"controls\" style=\"max-width:100%\"></video></p>', 25, '2023-09-12 22:10:24');

-- ----------------------------
-- Table structure for notice
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '标题',
  `content` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '内容',
  `userid` int(11) NULL DEFAULT NULL COMMENT '发布人id',
  `time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '发布时间',
  `open` tinyint(1) NULL DEFAULT 1 COMMENT '是否公开',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '系统公告表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of notice
-- ----------------------------
INSERT INTO `notice` VALUES (3, '青戈原创精品项目合集', '<a href=\"https://www.yuque.com/gebilaowu-ogtnh/uf1tw3\">2023精品项目合集</a>\n<br><br>\n<a href=\"https://www.yuque.com/xiaqing-en2ii/re3a5a/fhzudexeiin2m0vg\">往期精品项目合集</a>', 25, '2023-09-28 19:14:52', 1);
INSERT INTO `notice` VALUES (4, '毕设代做找青哥哥，安全又靠谱', '毕设代做找青哥哥，安全有靠谱\n\n详情请看：<a href=\"https://www.yuque.com/xiaqing-en2ii/re3a5a/uodafris8wwyovzd\">代做介绍</a>\n', 25, '2023-09-28 19:14:56', 1);

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `no` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '订单编号',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '订单名称',
  `money` decimal(10, 2) NULL DEFAULT NULL COMMENT '订单金额',
  `userid` int(11) NULL DEFAULT NULL COMMENT '用户ID',
  `category` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '订单分类',
  `date` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '订单日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '订单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES (1, '7676b0d84ffb48eea42ea1bb93c4d4a8', '面包', 15.00, 25, '糕点', '2023-10-05');
INSERT INTO `orders` VALUES (2, '1088005da31840dab7a759129631373d', '香蕉', 9.90, 25, '水果', '2023-10-06');
INSERT INTO `orders` VALUES (3, '8e2b94f03c634e91b2e74ee0e0c3a4cc', '西红柿', 6.90, 25, '蔬菜', '2023-10-06');
INSERT INTO `orders` VALUES (4, 'b6e4e0945c094bf8b0e181b40b5d42c3', 'AD钙奶', 20.00, 25, '零食', '2023-10-07');
INSERT INTO `orders` VALUES (5, '1088005da31840dab7a759129631373d', '香蕉', 9.90, 25, '水果', '2023-10-08');
INSERT INTO `orders` VALUES (6, '8e2b94f03c634e91b2e74ee0e0c3a4cc', '西红柿', 6.90, 25, '蔬菜', '2023-10-08');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '密码',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '姓名',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '电话',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '邮箱',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '地址',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '头像',
  `role` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '角色',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 51 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (13, 'qing2', '123', '青哥哥', '13588999966', 'qing@163.com', '安徽省合肥市蜀山区', NULL, '用户');
INSERT INTO `user` VALUES (14, 'qing3', '123', '青哥哥', '13588999966', 'qing@163.com', '安徽省合肥市蜀山区', NULL, '用户');
INSERT INTO `user` VALUES (15, 'qing4', '123', '青哥哥', '13588999966', 'qing@163.com', '安徽省合肥市蜀山区', NULL, '用户');
INSERT INTO `user` VALUES (16, 'qing5', '123', '青哥哥', '13588999966', 'qing@163.com', '安徽省合肥市蜀山区', NULL, '用户');
INSERT INTO `user` VALUES (25, 'admin', 'admin', 'admin', NULL, NULL, NULL, NULL, '管理员');
INSERT INTO `user` VALUES (29, 'qing6', '123', '青哥哥', '13588999966', 'qing@163.com', '安徽省合肥市蜀山区', '', '用户');
INSERT INTO `user` VALUES (30, 'qing7', '123', '青哥哥', '13588999966', 'qing@163.com', '安徽省合肥市蜀山区', '', '用户');
INSERT INTO `user` VALUES (47, 'qing11', '123', '青哥哥', '13588999966', 'qing@163.com', '安徽省合肥市蜀山区', '', '用户');
INSERT INTO `user` VALUES (48, 'qing12', '123', '青哥哥', '13588999966', 'qing@163.com', '安徽省合肥市蜀山区', 'http://localhost:9090/file/download/QQ截图20230330090359.png', '用户');
INSERT INTO `user` VALUES (49, 'qing13', '123', '青哥哥', '13588999966', 'qing@163.com', '安徽省合肥市蜀山区', 'http://localhost:9090/file/download/微信截图_20230801220029.png', '用户');
INSERT INTO `user` VALUES (50, 'gbb', '123', 'gbb', NULL, NULL, NULL, NULL, '用户');

SET FOREIGN_KEY_CHECKS = 1;
