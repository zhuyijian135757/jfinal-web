CREATE DATABASE blogs;

USE blogs;

CREATE TABLE `blog` (
  `id` int(11) NOT NULL auto_increment,
  `jtype` varchar(200) NOT NULL,
  `title` varchar(200) NOT NULL,
  `url` varchar(200) NOT NULL,
  `content` mediumtext NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO `blog` VALUES (1, 'java', 'Google Guava 库用法整理', 'http://macrochen.iteye.com/blog/737058', 'test1');
INSERT INTO `blog` VALUES (2, 'java', 'Metrics-Java版的指标度量工具之一 ', 'http://www.cnblogs.com/nexiyi/p/metrics_sample_1.html', 'test2');
INSERT INTO `blog` VALUES (3, 'java', '避免HttpClient的”javax.net.ssl.SSLPeerUnverifiedException: peer not authenticated”异常', 'http://blog.csdn.net/asd1415926/article/details/12341187', 'test3');
INSERT INTO `blog` VALUES (4, 'java', '聊聊并发-Java中的Copy-On-Write容器', 'http://ifeve.com/java-copy-on-write/', 'test4');
INSERT INTO `blog` VALUES (5, 'java', 'Java多线程--让主线程等待所有子线程执行完毕', 'http://3ccoder.iteye.com/blog/581476', 'test5');
INSERT INTO `blog` VALUES (6, 'socket', 'Java网络编程之非阻塞I/O服务器TCP实例', 'http://computerdragon.blog.51cto.com/6235984/1197556', 'test1');
INSERT INTO `blog` VALUES (7, 'socket', '浅谈 Linux 系统中的 SNMP Trap', 'http://www.ibm.com/developerworks/cn/linux/l-cn-snmp/', 'test2');
INSERT INTO `blog` VALUES (8, 'socket', 'SNMP 原理与实战详解', 'http://freeloda.blog.51cto.com/2033581/1306743', 'test3');
INSERT INTO `blog` VALUES (9, 'socket', '简单文本协议', 'http://book.51cto.com/art/200902/110034.htm', 'test4');
INSERT INTO `blog` VALUES (10, 'socket', 'Buffer透视：duplicate()，slice()', 'http://book.51cto.com/art/200902/109721.htm', 'test5');
INSERT INTO `blog` VALUES (11, 'web', 'GET POST方法长度限制', 'http://blog.csdn.net/blueling51/article/details/6935901', 'test1');
INSERT INTO `blog` VALUES (12, 'web', 'Hibernate 注解 annotation', 'http://gaolixu.iteye.com/blog/659858', 'test2');
INSERT INTO `blog` VALUES (13, 'web', 'HTTPClient请求网页抓取数据', 'http://www.sxrczx.com/t/article/3864b5a8c2774191bcb7972246c8d578.htm', 'test3');
INSERT INTO `blog` VALUES (14, 'web', 'web项目http和https跳转session失效解决', 'http://ye-liang.iteye.com/blog/1992358', 'test4');
INSERT INTO `blog` VALUES (15, 'web', 'HTTP 报头,Content-disposition', 'http://wenku.baidu.com/view/3efc6ef90242a8956bece4d7.html', 'test5');
INSERT INTO `blog` VALUES (16, 'linux', 'linux安装jdk出现java/lang/NoClassDefFoundError', 'http://www.cnblogs.com/chenguangyu/archive/2009/11/06/1597706.html', 'test1');
INSERT INTO `blog` VALUES (17, 'linux', 'linux 安装中文包和设置中文环境', 'http://www.cnblogs.com/djinmusic/archive/2013/02/09/2909551.html', 'test2');
INSERT INTO `blog` VALUES (18, 'linux', 'linux下的C语言编程', 'http://blog.csdn.net/feixiaoxing/article/details/7271937', 'test3');
INSERT INTO `blog` VALUES (19, 'linux', 'Linux下gcc编译生成动态链接库*.so文件并调用它', 'http://blog.sina.com.cn/s/blog_54f82cc20101153x.html', 'test4');
INSERT INTO `blog` VALUES (20, 'linux', 'Linux平台静态接库与动态链接库的创建和使用', 'http://blog.chinaunix.net/uid-26403665-id-3178137.html', 'test5');
