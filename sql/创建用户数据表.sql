use secondhand; 

DROP TABLE IF EXISTS TransactionInfo; 
DROP TABLE IF EXISTS ShoppingCartInfo; 
DROP TABLE IF EXISTS GoodsPictureInfo;
DROP TABLE IF EXISTS GoodsInfo;
DROP TABLE IF EXISTS ClassificationofGoodsInfo;
DROP TABLE IF EXISTS ReceivingAddressInfo;
DROP TABLE IF EXISTS UserInfo;


CREATE TABLE UserInfo(-- �û���
 pkid INT PRIMARY KEY	auto_increment 
 ,AccountNumber VARCHAR(10) NOT NULL UNIQUE-- �˺�
 ,PassWords VARCHAR(10) NOT NULL-- ����   
 ,Nickname VARCHAR(5) NOT NULL-- �ǳ�
 ,Sex VARCHAR(2) -- �Ա�
 ,Birthday DATE -- ����
 ,ResidentCity INT NOT NULL -- ������� ��פ����
 ,PhoneNumber VARCHAR(11)-- �û��绰
 ,HeadPortraitData longblob NOT NULL -- ͷ��ͼƬ����
 ,HeadPortraitName VARCHAR(20) NOT NULL-- ͷ��ͼƬ����
 ,RegisterTime date NOT NULL-- ע��ʱ��
 ,OnlineStatus VARCHAR(5)-- ����״̬
,Balance DOUBLE(11,2)-- ���
 ,CONSTRAINT fk_city1_id FOREIGN KEY(ResidentCity) REFERENCES china(Id)-- �������б�		
 )CHARACTER SET utf8;


CREATE TABLE ReceivingAddressInfo(-- �ջ���ַ��
 pkid INT PRIMARY KEY	auto_increment
,ConsigneeName VARCHAR(25) NOT NULL-- �ջ�������
,PhoneNumber VARCHAR(11) NOT NULL-- �ջ�����ϵ�绰
,sheng_name varchar(20) not NULL-- ʡ
,shi_name varchar(20) not NULL-- ��
,qu_name varchar(20) not NULL-- ��
,fk_user_id INT NOT NULL -- �û����
,DetailedAddress VARCHAR(80) NOT NULL -- ��ϸ��ַ
,DefaultAddress bit-- �Ƿ�ΪĬ�ϵ�ַ
,CONSTRAINT fk_user1_id FOREIGN KEY(fk_user_id) REFERENCES UserInfo(pkid)-- �����û���
)CHARACTER SET utf8;


CREATE TABLE ClassificationofGoodsInfo(-- �������
 pkid INT PRIMARY KEY	auto_increment
,ClassificationName VARCHAR(20) NOT NULL-- ��������
)CHARACTER SET utf8;


CREATE TABLE GoodsInfo(-- ��Ʒ��
 pkid INT PRIMARY KEY	auto_increment
,Title VARCHAR(30) NOT NULL-- ����
,SellReason	VARCHAR(200) NOT NULL-- ����ԭ�� ��Ʒ����
,Price DOUBLE(11,2) NOT NULL-- �۸�
,Initialprice DOUBLE(11,2) NOT NULL-- ���ּ۸�
,Freight DOUBLE(11,2) NOT NULL-- �˷�
,ClassificationId INT NOT NULL-- ��Ʒ�������
,CreateTime date NOT NULL-- ����ʱ��
,ModifyTime date NOT NULL-- ����޸�ʱ��
,Location INT NOT NULL-- ���ڵ�
,fk_user_id INT NOT NULL -- �û����
,SalesStatus VARCHAR(5) -- ����״̬
,CONSTRAINT fk_city3_id FOREIGN KEY(Location) REFERENCES china(Id)-- �������б�
,CONSTRAINT fk_user2_id FOREIGN KEY(fk_user_id) REFERENCES UserInfo(pkid)-- �����û���
,CONSTRAINT fk_Classificationof_id FOREIGN KEY(ClassificationId) REFERENCES ClassificationofGoodsInfo(pkid)-- ������������
)CHARACTER SET utf8;


CREATE TABLE GoodsPictureInfo(-- ��ƷͼƬ
 pkid INT PRIMARY KEY	auto_increment
,GoodsPictureData longblob NOT NULL -- ��ƷͼƬ����
,GoodsPictureName VARCHAR(20) NOT NULL-- ��ƷͼƬ����
,GoodsId INT NOT NULL
,CONSTRAINT fk_goods1_id FOREIGN KEY(GoodsId) REFERENCES GoodsInfo(pkid)-- ������Ʒ��
)CHARACTER SET utf8;


CREATE TABLE ShoppingCartInfo(-- ���ﳵ
 pkid INT PRIMARY KEY	auto_increment
,GoodsId INT NOT NULL-- ��Ʒ���
,fk_user_id INT NOT NULL -- �û����
,CONSTRAINT fk_goods2_id FOREIGN KEY(GoodsId) REFERENCES GoodsInfo(pkid)-- ������Ʒ��
,CONSTRAINT fk_user3_id FOREIGN KEY(fk_user_id) REFERENCES UserInfo(pkid)-- �����û���
)CHARACTER SET utf8;

CREATE TABLE TransactionInfo(-- ���ױ�
 pkid INT PRIMARY KEY	auto_increment
,TransactionTime date NOT NULL-- ����ʱ��
,fk_goods_id INT NOT NULL UNIQUE-- ��Ʒ���
,fk_Selleruser_id INT NOT NULL-- �����û����
,fk_Buyeruser_id INT NOT NULL -- ���û����
,CONSTRAINT fk_goods3_id FOREIGN KEY(fk_goods_id) REFERENCES GoodsInfo(pkid)-- ������Ʒ��
,CONSTRAINT fk_user5_id FOREIGN KEY(fk_Selleruser_id) REFERENCES UserInfo(pkid)-- �����û���
,CONSTRAINT fk_user6_id FOREIGN KEY(fk_Buyeruser_id) REFERENCES UserInfo(pkid)-- �����û���
)CHARACTER SET utf8;



INSERT INTO classificationofgoodsinfo (ClassificationName) VALUES ('��');
INSERT INTO classificationofgoodsinfo (ClassificationName) VALUES ( 'ʳ');
INSERT INTO classificationofgoodsinfo (ClassificationName) VALUES ( 'ס');
INSERT INTO classificationofgoodsinfo (ClassificationName) VALUES ('��');
INSERT INTO classificationofgoodsinfo (ClassificationName) VALUES ( '����');




GRANT ALL PRIVILEGES ON *.* TO 'root'@'%' IDENTIFIED BY '' WITH GRANT OPTION; -- ����Ȩ��

-- show VARIABLES like '%max_allowed_packet%';-- �鿴�������ֵ
-- SET GLOBAL max_allowed_packet=1024*1024*1024-- �ı䴫���ļ���С
