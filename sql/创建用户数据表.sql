use secondhand; 

DROP TABLE IF EXISTS TransactionInfo; 
DROP TABLE IF EXISTS ShoppingCartInfo; 
DROP TABLE IF EXISTS GoodsPictureInfo;
DROP TABLE IF EXISTS GoodsInfo;
DROP TABLE IF EXISTS ClassificationofGoodsInfo;
DROP TABLE IF EXISTS ReceivingAddressInfo;
DROP TABLE IF EXISTS UserInfo;


CREATE TABLE UserInfo(-- 用户表
 pkid INT PRIMARY KEY	auto_increment 
 ,AccountNumber VARCHAR(10) NOT NULL UNIQUE-- 账号
 ,PassWords VARCHAR(10) NOT NULL-- 密码   
 ,Nickname VARCHAR(5) NOT NULL-- 昵称
 ,Sex VARCHAR(2) -- 性别
 ,Birthday DATE -- 生日
 ,ResidentCity INT NOT NULL -- 城市外键 常驻城市
 ,PhoneNumber VARCHAR(11)-- 用户电话
 ,HeadPortraitData longblob NOT NULL -- 头像图片数据
 ,HeadPortraitName VARCHAR(20) NOT NULL-- 头像图片名称
 ,RegisterTime date NOT NULL-- 注册时间
 ,OnlineStatus VARCHAR(5)-- 在线状态
,Balance DOUBLE(11,2)-- 余额
 ,CONSTRAINT fk_city1_id FOREIGN KEY(ResidentCity) REFERENCES china(Id)-- 外联城市表		
 )CHARACTER SET utf8;


CREATE TABLE ReceivingAddressInfo(-- 收货地址表
 pkid INT PRIMARY KEY	auto_increment
,ConsigneeName VARCHAR(25) NOT NULL-- 收货人姓名
,PhoneNumber VARCHAR(11) NOT NULL-- 收货人联系电话
,sheng_name varchar(20) not NULL-- 省
,shi_name varchar(20) not NULL-- 市
,qu_name varchar(20) not NULL-- 区
,fk_user_id INT NOT NULL -- 用户外键
,DetailedAddress VARCHAR(80) NOT NULL -- 详细地址
,DefaultAddress bit-- 是否为默认地址
,CONSTRAINT fk_user1_id FOREIGN KEY(fk_user_id) REFERENCES UserInfo(pkid)-- 外联用户表
)CHARACTER SET utf8;


CREATE TABLE ClassificationofGoodsInfo(-- 货物分类
 pkid INT PRIMARY KEY	auto_increment
,ClassificationName VARCHAR(20) NOT NULL-- 分类名称
)CHARACTER SET utf8;


CREATE TABLE GoodsInfo(-- 商品表
 pkid INT PRIMARY KEY	auto_increment
,Title VARCHAR(30) NOT NULL-- 标题
,SellReason	VARCHAR(200) NOT NULL-- 售卖原因 商品描述
,Price DOUBLE(11,2) NOT NULL-- 价格
,Initialprice DOUBLE(11,2) NOT NULL-- 入手价格
,Freight DOUBLE(11,2) NOT NULL-- 运费
,ClassificationId INT NOT NULL-- 商品分类外键
,CreateTime date NOT NULL-- 创建时间
,ModifyTime date NOT NULL-- 最近修改时间
,Location INT NOT NULL-- 所在地
,fk_user_id INT NOT NULL -- 用户外键
,SalesStatus VARCHAR(5) -- 销售状态
,CONSTRAINT fk_city3_id FOREIGN KEY(Location) REFERENCES china(Id)-- 外联城市表
,CONSTRAINT fk_user2_id FOREIGN KEY(fk_user_id) REFERENCES UserInfo(pkid)-- 外联用户表
,CONSTRAINT fk_Classificationof_id FOREIGN KEY(ClassificationId) REFERENCES ClassificationofGoodsInfo(pkid)-- 外联货物分类表
)CHARACTER SET utf8;


CREATE TABLE GoodsPictureInfo(-- 商品图片
 pkid INT PRIMARY KEY	auto_increment
,GoodsPictureData longblob NOT NULL -- 商品图片数据
,GoodsPictureName VARCHAR(20) NOT NULL-- 商品图片名称
,GoodsId INT NOT NULL
,CONSTRAINT fk_goods1_id FOREIGN KEY(GoodsId) REFERENCES GoodsInfo(pkid)-- 外联商品表
)CHARACTER SET utf8;


CREATE TABLE ShoppingCartInfo(-- 购物车
 pkid INT PRIMARY KEY	auto_increment
,GoodsId INT NOT NULL-- 商品外键
,fk_user_id INT NOT NULL -- 用户外键
,CONSTRAINT fk_goods2_id FOREIGN KEY(GoodsId) REFERENCES GoodsInfo(pkid)-- 外联商品表
,CONSTRAINT fk_user3_id FOREIGN KEY(fk_user_id) REFERENCES UserInfo(pkid)-- 外联用户表
)CHARACTER SET utf8;

CREATE TABLE TransactionInfo(-- 交易表
 pkid INT PRIMARY KEY	auto_increment
,TransactionTime date NOT NULL-- 交易时间
,fk_goods_id INT NOT NULL UNIQUE-- 商品外键
,fk_Selleruser_id INT NOT NULL-- 卖方用户外键
,fk_Buyeruser_id INT NOT NULL -- 买方用户外键
,CONSTRAINT fk_goods3_id FOREIGN KEY(fk_goods_id) REFERENCES GoodsInfo(pkid)-- 外联商品表
,CONSTRAINT fk_user5_id FOREIGN KEY(fk_Selleruser_id) REFERENCES UserInfo(pkid)-- 外联用户表
,CONSTRAINT fk_user6_id FOREIGN KEY(fk_Buyeruser_id) REFERENCES UserInfo(pkid)-- 外联用户表
)CHARACTER SET utf8;



INSERT INTO classificationofgoodsinfo (ClassificationName) VALUES ('衣');
INSERT INTO classificationofgoodsinfo (ClassificationName) VALUES ( '食');
INSERT INTO classificationofgoodsinfo (ClassificationName) VALUES ( '住');
INSERT INTO classificationofgoodsinfo (ClassificationName) VALUES ('行');
INSERT INTO classificationofgoodsinfo (ClassificationName) VALUES ( '其它');




GRANT ALL PRIVILEGES ON *.* TO 'root'@'%' IDENTIFIED BY '' WITH GRANT OPTION; -- 开启权限

-- show VARIABLES like '%max_allowed_packet%';-- 查看传输最大值
-- SET GLOBAL max_allowed_packet=1024*1024*1024-- 改变传输文件大小
