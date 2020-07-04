CREATE DATABASE secondhand CHARACTER SET utf8;


DROP TABLE IF EXISTS `china`;

CREATE TABLE `china` (

  `Id` int(11) NOT NULL,

  `Name` varchar(40) default NULL,

  `Pid` int(11) default NULL,

  PRIMARY KEY  (`Id`),

  KEY `FK_CHINA_REFERENCE_CHINA` (`Pid`),

  CONSTRAINT `FK_CHINA_REFERENCE_CHINA` FOREIGN KEY (`Pid`) REFERENCES `china` (`Id`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8;

 

-- ----------------------------

-- Records of china

-- ----------------------------

INSERT INTO `china` VALUES ('0', '�й�', '0');

INSERT INTO `china` VALUES ('110000', '������', '0');

INSERT INTO `china` VALUES ('110100', '������', '110000');

INSERT INTO `china` VALUES ('110200', '������', '110000');

INSERT INTO `china` VALUES ('110500', '������', '110000');

INSERT INTO `china` VALUES ('110600', '��̨��', '110000');

INSERT INTO `china` VALUES ('110700', 'ʯ��ɽ��', '110000');

INSERT INTO `china` VALUES ('110800', '������', '110000');

INSERT INTO `china` VALUES ('110900', '��ͷ����', '110000');

INSERT INTO `china` VALUES ('111100', '��ɽ��', '110000');

INSERT INTO `china` VALUES ('111200', 'ͨ����', '110000');

INSERT INTO `china` VALUES ('111300', '˳����', '110000');

INSERT INTO `china` VALUES ('111400', '��ƽ��', '110000');

INSERT INTO `china` VALUES ('111500', '������', '110000');

INSERT INTO `china` VALUES ('111600', '������', '110000');

INSERT INTO `china` VALUES ('111700', 'ƽ����', '110000');

INSERT INTO `china` VALUES ('112800', '������', '110000');

INSERT INTO `china` VALUES ('112900', '������', '110000');

INSERT INTO `china` VALUES ('120000', '�����', '0');

INSERT INTO `china` VALUES ('120100', '��ƽ��', '120000');

INSERT INTO `china` VALUES ('120200', '�Ӷ���', '120000');

INSERT INTO `china` VALUES ('120300', '������', '120000');

INSERT INTO `china` VALUES ('120400', '�Ͽ���', '120000');

INSERT INTO `china` VALUES ('120500', '�ӱ���', '120000');

INSERT INTO `china` VALUES ('120600', '������', '120000');

INSERT INTO `china` VALUES ('120900', '��������', '120000');

INSERT INTO `china` VALUES ('121000', '������', '120000');

INSERT INTO `china` VALUES ('121100', '������', '120000');

INSERT INTO `china` VALUES ('121200', '������', '120000');

INSERT INTO `china` VALUES ('121300', '������', '120000');

INSERT INTO `china` VALUES ('121400', '������', '120000');

INSERT INTO `china` VALUES ('121500', '������', '120000');

INSERT INTO `china` VALUES ('122100', '������', '120000');

INSERT INTO `china` VALUES ('122300', '������', '120000');

INSERT INTO `china` VALUES ('122500', '����', '120000');

INSERT INTO `china` VALUES ('130000', '�ӱ�ʡ', '0');

INSERT INTO `china` VALUES ('130100', 'ʯ��ׯ��', '130000');

INSERT INTO `china` VALUES ('130101', '��Ͻ��', '130100');

INSERT INTO `china` VALUES ('130102', '������', '130101');

INSERT INTO `china` VALUES ('130103', '�Ŷ���', '130101');

INSERT INTO `china` VALUES ('130104', '������', '130101');

INSERT INTO `china` VALUES ('130105', '�»���', '130101');

INSERT INTO `china` VALUES ('130107', '�������', '130101');

INSERT INTO `china` VALUES ('130108', 'ԣ����', '130101');

INSERT INTO `china` VALUES ('130121', '������', '130100');

INSERT INTO `china` VALUES ('130123', '������', '130100');

INSERT INTO `china` VALUES ('130124', '�����', '130100');

INSERT INTO `china` VALUES ('130125', '������', '130100');

INSERT INTO `china` VALUES ('130126', '������', '130100');

INSERT INTO `china` VALUES ('130127', '������', '130100');

INSERT INTO `china` VALUES ('130128', '������', '130100');

INSERT INTO `china` VALUES ('130129', '�޻���', '130100');

INSERT INTO `china` VALUES ('130130', '�޼���', '130100');

INSERT INTO `china` VALUES ('130131', 'ƽɽ��', '130100');

INSERT INTO `china` VALUES ('130132', 'Ԫ����', '130100');

INSERT INTO `china` VALUES ('130133', '����', '130100');

INSERT INTO `china` VALUES ('130181', '������', '130100');

INSERT INTO `china` VALUES ('130182', '޻����', '130100');

INSERT INTO `china` VALUES ('130183', '������', '130100');

INSERT INTO `china` VALUES ('130184', '������', '130100');

INSERT INTO `china` VALUES ('130185', '¹Ȫ��', '130100');

INSERT INTO `china` VALUES ('130200', '��ɽ��', '130000');

INSERT INTO `china` VALUES ('130201', '��Ͻ��', '130200');

INSERT INTO `china` VALUES ('130202', '·����', '130201');

INSERT INTO `china` VALUES ('130203', '·����', '130201');

INSERT INTO `china` VALUES ('130204', '��ұ��', '130201');

INSERT INTO `china` VALUES ('130205', '��ƽ��', '130201');

INSERT INTO `china` VALUES ('130207', '������', '130201');

INSERT INTO `china` VALUES ('130208', '������', '130201');

INSERT INTO `china` VALUES ('130223', '����', '130200');

INSERT INTO `china` VALUES ('130224', '������', '130200');

INSERT INTO `china` VALUES ('130225', '��ͤ��', '130200');

INSERT INTO `china` VALUES ('130227', 'Ǩ����', '130200');

INSERT INTO `china` VALUES ('130229', '������', '130200');

INSERT INTO `china` VALUES ('130230', '�ƺ���', '130200');

INSERT INTO `china` VALUES ('130281', '����', '130200');

INSERT INTO `china` VALUES ('130283', 'Ǩ����', '130200');

INSERT INTO `china` VALUES ('130300', '�ػʵ���', '130000');

INSERT INTO `china` VALUES ('130301', '������', '130300');

INSERT INTO `china` VALUES ('130303', 'ɽ������', '130300');

INSERT INTO `china` VALUES ('130304', '��������', '130300');

INSERT INTO `china` VALUES ('130321', '��������������', '130300');

INSERT INTO `china` VALUES ('130322', '������', '130300');

INSERT INTO `china` VALUES ('130323', '������', '130300');

INSERT INTO `china` VALUES ('130324', '¬����', '130300');

INSERT INTO `china` VALUES ('130400', '������', '130000');

INSERT INTO `china` VALUES ('130401', '��Ͻ��', '130400');

INSERT INTO `china` VALUES ('130402', '��ɽ��', '130401');

INSERT INTO `china` VALUES ('130403', '��̨��', '130401');

INSERT INTO `china` VALUES ('130404', '������', '130401');

INSERT INTO `china` VALUES ('130406', '������', '130401');

INSERT INTO `china` VALUES ('130421', '������', '130400');

INSERT INTO `china` VALUES ('130423', '������', '130400');

INSERT INTO `china` VALUES ('130424', '�ɰ���', '130400');

INSERT INTO `china` VALUES ('130425', '������', '130400');

INSERT INTO `china` VALUES ('130426', '����', '130400');

INSERT INTO `china` VALUES ('130427', '����', '130400');

INSERT INTO `china` VALUES ('130428', '������', '130400');

INSERT INTO `china` VALUES ('130429', '������', '130400');

INSERT INTO `china` VALUES ('130430', '����', '130400');

INSERT INTO `china` VALUES ('130431', '������', '130400');

INSERT INTO `china` VALUES ('130432', '��ƽ��', '130400');

INSERT INTO `china` VALUES ('130433', '������', '130400');

INSERT INTO `china` VALUES ('130434', 'κ��', '130400');

INSERT INTO `china` VALUES ('130435', '������', '130400');

INSERT INTO `china` VALUES ('130481', '�䰲��', '130400');

INSERT INTO `china` VALUES ('130500', '��̨��', '130000');

INSERT INTO `china` VALUES ('130501', '��Ͻ��', '130500');

INSERT INTO `china` VALUES ('130502', '�Ŷ���', '130501');

INSERT INTO `china` VALUES ('130503', '������', '130501');

INSERT INTO `china` VALUES ('130521', '��̨��', '130500');

INSERT INTO `china` VALUES ('130522', '�ٳ���', '130500');

INSERT INTO `china` VALUES ('130523', '������', '130500');

INSERT INTO `china` VALUES ('130524', '������', '130500');

INSERT INTO `china` VALUES ('130525', '¡Ң��', '130500');

INSERT INTO `china` VALUES ('130526', '����', '130500');

INSERT INTO `china` VALUES ('130527', '�Ϻ���', '130500');

INSERT INTO `china` VALUES ('130528', '������', '130500');

INSERT INTO `china` VALUES ('130529', '��¹��', '130529');

INSERT INTO `china` VALUES ('130530', '�º���', '130500');

INSERT INTO `china` VALUES ('130531', '������', '130500');

INSERT INTO `china` VALUES ('130532', 'ƽ����', '130500');

INSERT INTO `china` VALUES ('130533', '����', '130500');

INSERT INTO `china` VALUES ('130534', '�����', '130500');

INSERT INTO `china` VALUES ('130535', '������', '130500');

INSERT INTO `china` VALUES ('130581', '�Ϲ���', '130500');

INSERT INTO `china` VALUES ('130582', 'ɳ����', '130500');

INSERT INTO `china` VALUES ('130600', '������', '130000');

INSERT INTO `china` VALUES ('130601', '������', '130600');

INSERT INTO `china` VALUES ('130603', '������', '130600');

INSERT INTO `china` VALUES ('130604', '������', '130600');

INSERT INTO `china` VALUES ('130621', '������', '130600');

INSERT INTO `china` VALUES ('130622', '��Է��', '130600');

INSERT INTO `china` VALUES ('130623', '�ˮ��', '130600');

INSERT INTO `china` VALUES ('130624', '��ƽ��', '130600');

INSERT INTO `china` VALUES ('130625', '��ˮ��', '130600');

INSERT INTO `china` VALUES ('130626', '������', '130600');

INSERT INTO `china` VALUES ('130627', '����', '130600');

INSERT INTO `china` VALUES ('130628', '������', '130600');

INSERT INTO `china` VALUES ('130629', '�ݳ���', '130600');

INSERT INTO `china` VALUES ('130630', '�Դ��', '130600');

INSERT INTO `china` VALUES ('130631', '������', '130600');

INSERT INTO `china` VALUES ('130632', '������', '130600');

INSERT INTO `china` VALUES ('130633', '����', '130600');

INSERT INTO `china` VALUES ('130634', '������', '130600');

INSERT INTO `china` VALUES ('130635', '���', '130600');

INSERT INTO `china` VALUES ('130636', '˳ƽ��', '130600');

INSERT INTO `china` VALUES ('130637', '��Ұ��', '130600');

INSERT INTO `china` VALUES ('130638', '����', '130600');

INSERT INTO `china` VALUES ('130681', '������', '130600');

INSERT INTO `china` VALUES ('130682', '������', '130600');

INSERT INTO `china` VALUES ('130683', '������', '130600');

INSERT INTO `china` VALUES ('130684', '�߱�����', '130600');

INSERT INTO `china` VALUES ('130685', '�׹��³���', '130600');

INSERT INTO `china` VALUES ('130700', '�żҿ���', '130000');

INSERT INTO `china` VALUES ('130701', '��Ͻ��', '130700');

INSERT INTO `china` VALUES ('130702', '�Ŷ���', '130701');

INSERT INTO `china` VALUES ('130703', '������', '130701');

INSERT INTO `china` VALUES ('130705', '������', '130701');

INSERT INTO `china` VALUES ('130706', '�»�԰��', '130701');

INSERT INTO `china` VALUES ('130721', '������', '130700');

INSERT INTO `china` VALUES ('130722', '�ű���', '130700');

INSERT INTO `china` VALUES ('130723', '������', '130700');

INSERT INTO `china` VALUES ('130724', '��Դ��', '130700');

INSERT INTO `china` VALUES ('130725', '������', '130700');

INSERT INTO `china` VALUES ('130726', 'ε��', '130700');

INSERT INTO `china` VALUES ('130727', '��ԭ��', '130700');

INSERT INTO `china` VALUES ('130728', '������', '130700');

INSERT INTO `china` VALUES ('130729', '��ȫ��', '130700');

INSERT INTO `china` VALUES ('130730', '������', '130700');

INSERT INTO `china` VALUES ('130731', '��¹��', '130700');

INSERT INTO `china` VALUES ('130732', '�����', '130700');

INSERT INTO `china` VALUES ('130733', '������', '130700');

INSERT INTO `china` VALUES ('130800', '�е���', '130000');

INSERT INTO `china` VALUES ('130801', '��Ͻ��', '130800');

INSERT INTO `china` VALUES ('130802', '˫����', '130801');

INSERT INTO `china` VALUES ('130803', '˫����', '130801');

INSERT INTO `china` VALUES ('130804', 'ӥ��Ӫ�ӿ���', '130801');

INSERT INTO `china` VALUES ('130821', '�е���', '130800');

INSERT INTO `china` VALUES ('130822', '��¡��', '130800');

INSERT INTO `china` VALUES ('130823', 'ƽȪ��', '130800');

INSERT INTO `china` VALUES ('130824', '��ƽ��', '130800');

INSERT INTO `china` VALUES ('130825', '¡����', '130800');

INSERT INTO `china` VALUES ('130826', '��������������', '130800');

INSERT INTO `china` VALUES ('130827', '�������������', '130800');

INSERT INTO `china` VALUES ('130828', 'Χ�������ɹ���������', '130800');

INSERT INTO `china` VALUES ('130900', '������', '130000');

INSERT INTO `china` VALUES ('130901', '��Ͻ��', '130900');

INSERT INTO `china` VALUES ('130902', '�»���', '130901');

INSERT INTO `china` VALUES ('130903', '�˺���', '130901');

INSERT INTO `china` VALUES ('130921', '����', '130900');

INSERT INTO `china` VALUES ('130922', '����', '130900');

INSERT INTO `china` VALUES ('130923', '������', '130900');

INSERT INTO `china` VALUES ('130924', '������', '130900');

INSERT INTO `china` VALUES ('130925', '��ɽ��', '130900');

INSERT INTO `china` VALUES ('130926', '������', '130900');

INSERT INTO `china` VALUES ('130927', '��Ƥ��', '130900');

INSERT INTO `china` VALUES ('130928', '������', '130900');

INSERT INTO `china` VALUES ('130929', '����', '130900');

INSERT INTO `china` VALUES ('130930', '�ϴ����������', '130900');

INSERT INTO `china` VALUES ('130981', '��ͷ��', '130900');

INSERT INTO `china` VALUES ('130982', '������', '130900');

INSERT INTO `china` VALUES ('130983', '������', '130900');

INSERT INTO `china` VALUES ('130984', '�Ӽ���', '130900');

INSERT INTO `china` VALUES ('131000', '�ȷ���', '130000');

INSERT INTO `china` VALUES ('131001', '��Ͻ��', '131000');

INSERT INTO `china` VALUES ('131002', '������', '131001');

INSERT INTO `china` VALUES ('131003', '������', '131001');

INSERT INTO `china` VALUES ('131022', '�̰���', '131000');

INSERT INTO `china` VALUES ('131023', '������', '131000');

INSERT INTO `china` VALUES ('131024', '�����', '131000');

INSERT INTO `china` VALUES ('131025', '�����', '131000');

INSERT INTO `china` VALUES ('131026', '�İ���', '131000');

INSERT INTO `china` VALUES ('131028', '�󳧻���������', '131000');

INSERT INTO `china` VALUES ('131081', '������', '131000');

INSERT INTO `china` VALUES ('131082', '������', '131000');

INSERT INTO `china` VALUES ('131100', '��ˮ��', '130000');

INSERT INTO `china` VALUES ('131101', '��Ͻ��', '131100');

INSERT INTO `china` VALUES ('131102', '�ҳ���', '131101');

INSERT INTO `china` VALUES ('131121', '��ǿ��', '131100');

INSERT INTO `china` VALUES ('131122', '������', '131100');

INSERT INTO `china` VALUES ('131123', '��ǿ��', '131100');

INSERT INTO `china` VALUES ('131124', '������', '131100');

INSERT INTO `china` VALUES ('131125', '��ƽ��', '131100');

INSERT INTO `china` VALUES ('131126', '�ʳ���', '131100');

INSERT INTO `china` VALUES ('131127', '����', '131100');

INSERT INTO `china` VALUES ('131128', '������', '131100');

INSERT INTO `china` VALUES ('131181', '������', '131100');

INSERT INTO `china` VALUES ('131182', '������', '131100');

INSERT INTO `china` VALUES ('140000', 'ɽ��ʡ', '0');

INSERT INTO `china` VALUES ('140100', '̫ԭ��', '140000');

INSERT INTO `china` VALUES ('140101', '��Ͻ��', '140100');

INSERT INTO `china` VALUES ('140105', 'С����', '140101');

INSERT INTO `china` VALUES ('140106', 'ӭ����', '140101');

INSERT INTO `china` VALUES ('140107', '�ӻ�����', '140101');

INSERT INTO `china` VALUES ('140108', '���ƺ��', '140101');

INSERT INTO `china` VALUES ('140109', '�������', '140101');

INSERT INTO `china` VALUES ('140110', '��Դ��', '140101');

INSERT INTO `china` VALUES ('140121', '������', '140100');

INSERT INTO `china` VALUES ('140122', '������', '140100');

INSERT INTO `china` VALUES ('140123', '¦����', '140100');

INSERT INTO `china` VALUES ('140181', '�Ž���', '140100');

INSERT INTO `china` VALUES ('140200', '��ͬ��', '140000');

INSERT INTO `china` VALUES ('140201', '��Ͻ��', '140200');

INSERT INTO `china` VALUES ('140202', '����', '140201');

INSERT INTO `china` VALUES ('140203', '����', '140201');

INSERT INTO `china` VALUES ('140211', '�Ͻ���', '140201');

INSERT INTO `china` VALUES ('140212', '������', '140201');

INSERT INTO `china` VALUES ('140221', '������', '140200');

INSERT INTO `china` VALUES ('140222', '������', '140200');

INSERT INTO `china` VALUES ('140223', '������', '140200');

INSERT INTO `china` VALUES ('140224', '������', '140200');

INSERT INTO `china` VALUES ('140225', '��Դ��', '140200');

INSERT INTO `china` VALUES ('140226', '������', '140200');

INSERT INTO `china` VALUES ('140227', '��ͬ��', '140200');

INSERT INTO `china` VALUES ('140300', '��Ȫ��', '140000');

INSERT INTO `china` VALUES ('140301', '��Ͻ��', '140300');

INSERT INTO `china` VALUES ('140302', '����', '140301');

INSERT INTO `china` VALUES ('140303', '����', '140301');

INSERT INTO `china` VALUES ('140311', '����', '140301');

INSERT INTO `china` VALUES ('140321', 'ƽ����', '140300');

INSERT INTO `china` VALUES ('140322', '����', '140300');

INSERT INTO `china` VALUES ('140400', '������', '140000');

INSERT INTO `china` VALUES ('140401', '��Ͻ��', '140400');

INSERT INTO `china` VALUES ('140402', '����', '140401');

INSERT INTO `china` VALUES ('140411', '����', '140401');

INSERT INTO `china` VALUES ('140421', '������', '140400');

INSERT INTO `china` VALUES ('140423', '��ԫ��', '140400');

INSERT INTO `china` VALUES ('140424', '������', '140400');

INSERT INTO `china` VALUES ('140425', 'ƽ˳��', '140400');

INSERT INTO `china` VALUES ('140426', '�����', '140400');

INSERT INTO `china` VALUES ('140427', '������', '140400');

INSERT INTO `china` VALUES ('140428', '������', '140400');

INSERT INTO `china` VALUES ('140429', '������', '140400');

INSERT INTO `china` VALUES ('140430', '����', '140400');

INSERT INTO `china` VALUES ('140431', '��Դ��', '140400');

INSERT INTO `china` VALUES ('140481', 'º����', '140400');

INSERT INTO `china` VALUES ('140500', '������', '140000');

INSERT INTO `china` VALUES ('140501', '��Ͻ��', '140500');

INSERT INTO `china` VALUES ('140502', '����', '140501');

INSERT INTO `china` VALUES ('140521', '��ˮ��', '140500');

INSERT INTO `china` VALUES ('140522', '������', '140500');

INSERT INTO `china` VALUES ('140524', '�괨��', '140500');

INSERT INTO `china` VALUES ('140525', '������', '140500');

INSERT INTO `china` VALUES ('140581', '��ƽ��', '140500');

INSERT INTO `china` VALUES ('140600', '˷����', '140000');

INSERT INTO `china` VALUES ('140601', '��Ͻ��', '140600');

INSERT INTO `china` VALUES ('140602', '˷����', '140601');

INSERT INTO `china` VALUES ('140603', 'ƽ³��', '140601');

INSERT INTO `china` VALUES ('140621', 'ɽ����', '140600');

INSERT INTO `china` VALUES ('140622', 'Ӧ��', '140600');

INSERT INTO `china` VALUES ('140623', '������', '140600');

INSERT INTO `china` VALUES ('140624', '������', '140600');

INSERT INTO `china` VALUES ('140700', '������', '140000');

INSERT INTO `china` VALUES ('140701', '��Ͻ��', '140700');

INSERT INTO `china` VALUES ('140702', '�ܴ���', '140701');

INSERT INTO `china` VALUES ('140721', '������', '140700');

INSERT INTO `china` VALUES ('140722', '��Ȩ��', '140700');

INSERT INTO `china` VALUES ('140723', '��˳��', '140700');

INSERT INTO `china` VALUES ('140724', '������', '140700');

INSERT INTO `china` VALUES ('140725', '������', '140700');

INSERT INTO `china` VALUES ('140726', '̫����', '140700');

INSERT INTO `china` VALUES ('140727', '����', '140700');

INSERT INTO `china` VALUES ('140728', 'ƽң��', '140700');

INSERT INTO `china` VALUES ('140729', '��ʯ��', '140700');

INSERT INTO `china` VALUES ('140781', '������', '140700');

INSERT INTO `china` VALUES ('140800', '�˳���', '140000');

INSERT INTO `china` VALUES ('140801', '��Ͻ��', '140800');

INSERT INTO `china` VALUES ('140802', '�κ���', '140801');

INSERT INTO `china` VALUES ('140821', '�����', '140800');

INSERT INTO `china` VALUES ('140822', '������', '140800');

INSERT INTO `china` VALUES ('140823', '��ϲ��', '140800');

INSERT INTO `china` VALUES ('140824', '�ɽ��', '140800');

INSERT INTO `china` VALUES ('140825', '�����', '140800');

INSERT INTO `china` VALUES ('140826', '���', '140800');

INSERT INTO `china` VALUES ('140827', 'ԫ����', '140800');

INSERT INTO `china` VALUES ('140828', '����', '140800');

INSERT INTO `china` VALUES ('140829', 'ƽ½��', '140800');

INSERT INTO `china` VALUES ('140830', '�ǳ���', '140800');

INSERT INTO `china` VALUES ('140881', '������', '140800');

INSERT INTO `china` VALUES ('140882', '�ӽ���', '140800');

INSERT INTO `china` VALUES ('140900', '������', '140000');

INSERT INTO `china` VALUES ('140901', '�ø���', '140900');

INSERT INTO `china` VALUES ('140921', '������', '140900');

INSERT INTO `china` VALUES ('140922', '��̨��', '140900');

INSERT INTO `china` VALUES ('140923', '����', '140900');

INSERT INTO `china` VALUES ('140924', '������', '140900');

INSERT INTO `china` VALUES ('140925', '������', '140900');

INSERT INTO `china` VALUES ('140926', '������', '140900');

INSERT INTO `china` VALUES ('140927', '�����', '140900');

INSERT INTO `china` VALUES ('140928', '��կ��', '140900');

INSERT INTO `china` VALUES ('140929', '����', '140900');

INSERT INTO `china` VALUES ('140930', '������', '140900');

INSERT INTO `china` VALUES ('140931', '������', '140900');

INSERT INTO `china` VALUES ('140932', 'ƫ����', '140900');

INSERT INTO `china` VALUES ('140981', 'ԭƽ��', '140900');

INSERT INTO `china` VALUES ('141000', '�ٷ���', '140000');

INSERT INTO `china` VALUES ('141001', '��Ͻ��', '141000');

INSERT INTO `china` VALUES ('141002', 'Ң����', '141000');

INSERT INTO `china` VALUES ('141021', '������', '141000');

INSERT INTO `china` VALUES ('141022', '�����', '141000');

INSERT INTO `china` VALUES ('141023', '�����', '141000');

INSERT INTO `china` VALUES ('141024', '�鶴��', '141000');

INSERT INTO `china` VALUES ('141025', '����', '141000');

INSERT INTO `china` VALUES ('141026', '������', '141000');

INSERT INTO `china` VALUES ('141027', '��ɽ��', '141000');

INSERT INTO `china` VALUES ('141028', '����', '141000');

INSERT INTO `china` VALUES ('141029', '������', '141000');

INSERT INTO `china` VALUES ('141030', '������', '141000');

INSERT INTO `china` VALUES ('141031', '����', '141000');

INSERT INTO `china` VALUES ('141032', '������', '141000');

INSERT INTO `china` VALUES ('141033', '����', '141000');

INSERT INTO `china` VALUES ('141034', '������', '141000');

INSERT INTO `china` VALUES ('141081', '������', '141000');

INSERT INTO `china` VALUES ('141082', '������', '141000');

INSERT INTO `china` VALUES ('141100', '������', '140000');

INSERT INTO `china` VALUES ('141101', '��Ͻ��', '141100');

INSERT INTO `china` VALUES ('141102', '��ʯ��', '141101');

INSERT INTO `china` VALUES ('141121', '��ˮ��', '141100');

INSERT INTO `china` VALUES ('141122', '������', '141100');

INSERT INTO `china` VALUES ('141123', '����', '141100');

INSERT INTO `china` VALUES ('141124', '����', '141100');

INSERT INTO `china` VALUES ('141125', '������', '141100');

INSERT INTO `china` VALUES ('141126', 'ʯ¥��', '141100');

INSERT INTO `china` VALUES ('141127', '���', '141100');

INSERT INTO `china` VALUES ('141128', '��ɽ��', '141100');

INSERT INTO `china` VALUES ('141129', '������', '141100');

INSERT INTO `china` VALUES ('141130', '������', '141100');

INSERT INTO `china` VALUES ('141181', 'Т����', '141100');

INSERT INTO `china` VALUES ('141182', '������', '141100');

INSERT INTO `china` VALUES ('150000', '���ɹ�������', '0');

INSERT INTO `china` VALUES ('150100', '���ͺ�����', '150000');

INSERT INTO `china` VALUES ('150101', '��Ͻ��', '150100');

INSERT INTO `china` VALUES ('150102', '�³���', '150101');

INSERT INTO `china` VALUES ('150103', '������', '150101');

INSERT INTO `china` VALUES ('150104', '��Ȫ��', '150101');

INSERT INTO `china` VALUES ('150105', '������', '150101');

INSERT INTO `china` VALUES ('150121', '��Ĭ������', '150100');

INSERT INTO `china` VALUES ('150122', '�п�����', '150100');

INSERT INTO `china` VALUES ('150123', '���ָ����', '150100');

INSERT INTO `china` VALUES ('150124', '��ˮ����', '150100');

INSERT INTO `china` VALUES ('150125', '�䴨��', '150100');

INSERT INTO `china` VALUES ('150200', '��ͷ��', '150000');

INSERT INTO `china` VALUES ('150201', '��Ͻ��', '150200');

INSERT INTO `china` VALUES ('150202', '������', '150201');

INSERT INTO `china` VALUES ('150203', '��������', '150201');

INSERT INTO `china` VALUES ('150204', '��ɽ��', '150201');

INSERT INTO `china` VALUES ('150205', 'ʯ����', '150201');

INSERT INTO `china` VALUES ('150206', '���ƿ���', '150201');

INSERT INTO `china` VALUES ('150207', '��ԭ��', '150201');

INSERT INTO `china` VALUES ('150221', '��Ĭ������', '150200');

INSERT INTO `china` VALUES ('150222', '������', '150200');

INSERT INTO `china` VALUES ('150223', '�����ï����������', '150200');

INSERT INTO `china` VALUES ('150300', '�ں���', '150000');

INSERT INTO `china` VALUES ('150301', '��Ͻ��', '150300');

INSERT INTO `china` VALUES ('150302', '��������', '150301');

INSERT INTO `china` VALUES ('150303', '������', '150301');

INSERT INTO `china` VALUES ('150304', '�ڴ���', '150301');

INSERT INTO `china` VALUES ('150400', '�����', '150000');

INSERT INTO `china` VALUES ('150401', '��Ͻ��', '150400');

INSERT INTO `china` VALUES ('150402', '��ɽ��', '150401');

INSERT INTO `china` VALUES ('150403', 'Ԫ��ɽ��', '150401');

INSERT INTO `china` VALUES ('150404', '��ɽ��', '150401');

INSERT INTO `china` VALUES ('150421', '��³�ƶ�����', '150400');

INSERT INTO `china` VALUES ('150422', '��������', '150400');

INSERT INTO `china` VALUES ('150423', '��������', '150400');

INSERT INTO `china` VALUES ('150424', '������', '150400');

INSERT INTO `china` VALUES ('150425', '��ʲ������', '150400');

INSERT INTO `china` VALUES ('150426', '��ţ����', '150400');

INSERT INTO `china` VALUES ('150428', '��������', '150400');

INSERT INTO `china` VALUES ('150429', '������', '150400');

INSERT INTO `china` VALUES ('150430', '������', '150400');

INSERT INTO `china` VALUES ('150500', 'ͨ����', '150000');

INSERT INTO `china` VALUES ('150501', '��Ͻ��', '150500');

INSERT INTO `china` VALUES ('150502', '�ƶ�����', '150501');

INSERT INTO `china` VALUES ('150521', '�ƶ�����������', '150500');

INSERT INTO `china` VALUES ('150522', '�ƶ����������', '150500');

INSERT INTO `china` VALUES ('150523', '��³��', '150500');

INSERT INTO `china` VALUES ('150524', '������', '150500');

INSERT INTO `china` VALUES ('150525', '������', '150500');

INSERT INTO `china` VALUES ('150526', '��³����', '150500');

INSERT INTO `china` VALUES ('150581', '���ֹ�����', '150500');

INSERT INTO `china` VALUES ('150600', '������˹��', '150000');

INSERT INTO `china` VALUES ('150602', '��ʤ��', '150600');

INSERT INTO `china` VALUES ('150621', '��������', '150600');

INSERT INTO `china` VALUES ('150622', '׼�����', '150600');

INSERT INTO `china` VALUES ('150623', '���п�ǰ��', '150600');

INSERT INTO `china` VALUES ('150624', '���п���', '150600');

INSERT INTO `china` VALUES ('150625', '������', '150600');

INSERT INTO `china` VALUES ('150626', '������', '150600');

INSERT INTO `china` VALUES ('150627', '���������', '150600');

INSERT INTO `china` VALUES ('150700', '���ױ�����', '150000');

INSERT INTO `china` VALUES ('150701', '��Ͻ��', '150700');

INSERT INTO `china` VALUES ('150702', '��������', '150701');

INSERT INTO `china` VALUES ('150721', '������', '150700');

INSERT INTO `china` VALUES ('150722', 'Ī�����ߴ��Ӷ���������', '150700');

INSERT INTO `china` VALUES ('150723', '���״�������', '150700');

INSERT INTO `china` VALUES ('150724', '���¿���������', '150700');

INSERT INTO `china` VALUES ('150725', '�°Ͷ�����', '150700');

INSERT INTO `china` VALUES ('150726', '�°Ͷ�������', '150700');

INSERT INTO `china` VALUES ('150727', '�°Ͷ�������', '150700');

INSERT INTO `china` VALUES ('150781', '��������', '150700');

INSERT INTO `china` VALUES ('150782', '����ʯ��', '150700');

INSERT INTO `china` VALUES ('150783', '��������', '150700');

INSERT INTO `china` VALUES ('150784', '���������', '150700');

INSERT INTO `china` VALUES ('150785', '������', '150700');

INSERT INTO `china` VALUES ('150800', '�����׶���', '150000');

INSERT INTO `china` VALUES ('150801', '��Ͻ��', '150800');

INSERT INTO `china` VALUES ('150802', '�ٺ���', '150800');

INSERT INTO `china` VALUES ('150821', '��ԭ��', '150800');

INSERT INTO `china` VALUES ('150822', '�����', '150800');

INSERT INTO `china` VALUES ('150823', '������ǰ��', '150800');

INSERT INTO `china` VALUES ('150824', '����������', '150800');

INSERT INTO `china` VALUES ('150825', '�����غ���', '150800');

INSERT INTO `china` VALUES ('150826', '��������', '150800');

INSERT INTO `china` VALUES ('150900', '�����첼��', '150000');

INSERT INTO `china` VALUES ('150901', '��Ͻ��', '150900');

INSERT INTO `china` VALUES ('150902', '������', '150901');

INSERT INTO `china` VALUES ('150921', '׿����', '150900');

INSERT INTO `china` VALUES ('150922', '������', '150900');

INSERT INTO `china` VALUES ('150923', '�̶���', '150900');

INSERT INTO `china` VALUES ('150924', '�˺���', '150900');

INSERT INTO `china` VALUES ('150925', '������', '150900');

INSERT INTO `china` VALUES ('150926', '���������ǰ��', '150900');

INSERT INTO `china` VALUES ('150927', '�������������', '150900');

INSERT INTO `china` VALUES ('150928', '������������', '150900');

INSERT INTO `china` VALUES ('150929', '��������', '150900');

INSERT INTO `china` VALUES ('150981', '������', '150900');

INSERT INTO `china` VALUES ('152200', '�˰���', '150000');

INSERT INTO `china` VALUES ('152201', '����������', '152200');

INSERT INTO `china` VALUES ('152202', '����ɽ��', '152200');

INSERT INTO `china` VALUES ('152221', '�ƶ�������ǰ��', '152200');

INSERT INTO `china` VALUES ('152222', '�ƶ�����������', '152200');

INSERT INTO `china` VALUES ('152223', '��������', '152200');

INSERT INTO `china` VALUES ('152224', 'ͻȪ��', '152200');

INSERT INTO `china` VALUES ('152500', '���ֹ�����', '150000');

INSERT INTO `china` VALUES ('152501', '����������', '152500');

INSERT INTO `china` VALUES ('152502', '���ֺ�����', '152500');

INSERT INTO `china` VALUES ('152522', '���͸���', '152500');

INSERT INTO `china` VALUES ('152523', '����������', '152500');

INSERT INTO `china` VALUES ('152524', '����������', '152500');

INSERT INTO `china` VALUES ('152525', '������������', '152500');

INSERT INTO `china` VALUES ('152526', '������������', '152500');

INSERT INTO `china` VALUES ('152527', '̫������', '152500');

INSERT INTO `china` VALUES ('152528', '�����', '152500');

INSERT INTO `china` VALUES ('152529', '�������', '152500');

INSERT INTO `china` VALUES ('152530', '������', '152500');

INSERT INTO `china` VALUES ('152531', '������', '152500');

INSERT INTO `china` VALUES ('152900', '��������', '150000');

INSERT INTO `china` VALUES ('152921', '����������', '152900');

INSERT INTO `china` VALUES ('152922', '����������', '152900');

INSERT INTO `china` VALUES ('152923', '�������', '152900');

INSERT INTO `china` VALUES ('210000', '����ʡ', '0');

INSERT INTO `china` VALUES ('210100', '������', '210000');

INSERT INTO `china` VALUES ('210101', '��Ͻ��', '210100');

INSERT INTO `china` VALUES ('210102', '��ƽ��', '210101');

INSERT INTO `china` VALUES ('210103', '�����', '210101');

INSERT INTO `china` VALUES ('210104', '����', '210101');

INSERT INTO `china` VALUES ('210105', '�ʹ���', '210101');

INSERT INTO `china` VALUES ('210106', '������', '210101');

INSERT INTO `china` VALUES ('210111', '�ռ�����', '210101');

INSERT INTO `china` VALUES ('210112', '������', '210101');

INSERT INTO `china` VALUES ('210113', '�³�����', '210101');

INSERT INTO `china` VALUES ('210114', '�ں���', '210101');

INSERT INTO `china` VALUES ('210122', '������', '210100');

INSERT INTO `china` VALUES ('210123', '��ƽ��', '210100');

INSERT INTO `china` VALUES ('210124', '������', '210100');

INSERT INTO `china` VALUES ('210181', '������', '210100');

INSERT INTO `china` VALUES ('210182', '������', '210100');

INSERT INTO `china` VALUES ('210200', '������', '210000');

INSERT INTO `china` VALUES ('210201', '��Ͻ��', '210200');

INSERT INTO `china` VALUES ('210202', '��ɽ��', '210201');

INSERT INTO `china` VALUES ('210203', '������', '210201');

INSERT INTO `china` VALUES ('210204', 'ɳ�ӿ���', '210201');

INSERT INTO `china` VALUES ('210211', '�ʾ�����', '210201');

INSERT INTO `china` VALUES ('210212', '��˳����', '210201');

INSERT INTO `china` VALUES ('210213', '������', '210201');

INSERT INTO `china` VALUES ('210224', '������', '210200');

INSERT INTO `china` VALUES ('210281', '�߷�����', '210200');

INSERT INTO `china` VALUES ('210282', '��������', '210200');

INSERT INTO `china` VALUES ('210283', 'ׯ����', '210200');

INSERT INTO `china` VALUES ('210300', '��ɽ��', '210000');

INSERT INTO `china` VALUES ('210301', '��Ͻ��', '210300');

INSERT INTO `china` VALUES ('210302', '������', '210301');

INSERT INTO `china` VALUES ('210303', '������', '210301');

INSERT INTO `china` VALUES ('210304', '��ɽ��', '210301');

INSERT INTO `china` VALUES ('210311', 'ǧɽ��', '210301');

INSERT INTO `china` VALUES ('210321', '̨����', '210300');

INSERT INTO `china` VALUES ('210323', '�������������', '210300');

INSERT INTO `china` VALUES ('210381', '������', '210300');

INSERT INTO `china` VALUES ('210400', '��˳��', '210000');

INSERT INTO `china` VALUES ('210401', '��Ͻ��', '210400');

INSERT INTO `china` VALUES ('210402', '�¸���', '210401');

INSERT INTO `china` VALUES ('210403', '������', '210401');

INSERT INTO `china` VALUES ('210404', '������', '210401');

INSERT INTO `china` VALUES ('210411', '˳����', '210401');

INSERT INTO `china` VALUES ('210421', '��˳��', '210400');

INSERT INTO `china` VALUES ('210422', '�±�����������', '210400');

INSERT INTO `china` VALUES ('210423', '��ԭ����������', '210400');

INSERT INTO `china` VALUES ('210500', '��Ϫ��', '210000');

INSERT INTO `china` VALUES ('210501', '��Ͻ��', '210500');

INSERT INTO `china` VALUES ('210502', 'ƽɽ��', '210501');

INSERT INTO `china` VALUES ('210503', 'Ϫ��??', '210501');

INSERT INTO `china` VALUES ('210504', '��ɽ��', '210501');

INSERT INTO `china` VALUES ('210505', '�Ϸ���', '210501');

INSERT INTO `china` VALUES ('210521', '��Ϫ����������', '210500');

INSERT INTO `china` VALUES ('210522', '��������������', '210500');

INSERT INTO `china` VALUES ('210600', '������', '210000');

INSERT INTO `china` VALUES ('210601', '��Ͻ��', '210600');

INSERT INTO `china` VALUES ('210602', 'Ԫ����', '210601');

INSERT INTO `china` VALUES ('210603', '������', '210601');

INSERT INTO `china` VALUES ('210604', '����', '210601');

INSERT INTO `china` VALUES ('210624', '�������������', '210600');

INSERT INTO `china` VALUES ('210681', '������', '210600');

INSERT INTO `china` VALUES ('210682', '�����', '210600');

INSERT INTO `china` VALUES ('210700', '������', '210000');

INSERT INTO `china` VALUES ('210701', '��Ͻ��', '210700');

INSERT INTO `china` VALUES ('210702', '������', '210701');

INSERT INTO `china` VALUES ('210703', '�����', '210701');

INSERT INTO `china` VALUES ('210711', '̫����', '210701');

INSERT INTO `china` VALUES ('210726', '��ɽ��', '210700');

INSERT INTO `china` VALUES ('210727', '����', '210700');

INSERT INTO `china` VALUES ('210781', '�躣��', '210700');

INSERT INTO `china` VALUES ('210782', '������', '210700');

INSERT INTO `china` VALUES ('210800', 'Ӫ����', '210000');

INSERT INTO `china` VALUES ('210801', '��Ͻ��', '210800');

INSERT INTO `china` VALUES ('210802', 'վǰ��', '210801');

INSERT INTO `china` VALUES ('210803', '������', '210801');

INSERT INTO `china` VALUES ('210804', '����Ȧ��', '210801');

INSERT INTO `china` VALUES ('210811', '�ϱ���', '210801');

INSERT INTO `china` VALUES ('210881', '������', '210800');

INSERT INTO `china` VALUES ('210882', '��ʯ����', '210800');

INSERT INTO `china` VALUES ('210900', '������', '210000');

INSERT INTO `china` VALUES ('210901', '��Ͻ��', '210900');

INSERT INTO `china` VALUES ('210902', '������', '210901');

INSERT INTO `china` VALUES ('210903', '������', '210901');

INSERT INTO `china` VALUES ('210904', '̫ƽ��', '210901');

INSERT INTO `china` VALUES ('210905', '�������', '210901');

INSERT INTO `china` VALUES ('210911', 'ϸ����', '210901');

INSERT INTO `china` VALUES ('210921', '�����ɹ���������', '210900');

INSERT INTO `china` VALUES ('210922', '������', '210900');

INSERT INTO `china` VALUES ('211000', '������', '210000');

INSERT INTO `china` VALUES ('211001', '��Ͻ��', '211000');

INSERT INTO `china` VALUES ('211002', '������', '211001');

INSERT INTO `china` VALUES ('211003', '��ʥ��', '211001');

INSERT INTO `china` VALUES ('211004', '��ΰ��', '211001');

INSERT INTO `china` VALUES ('211005', '��������', '211001');

INSERT INTO `china` VALUES ('211011', '̫�Ӻ���', '211001');

INSERT INTO `china` VALUES ('211021', '������', '211000');

INSERT INTO `china` VALUES ('211081', '������', '211000');

INSERT INTO `china` VALUES ('211100', '�̽���', '210000');

INSERT INTO `china` VALUES ('211101', '��Ͻ��', '211100');

INSERT INTO `china` VALUES ('211102', '˫̨����', '211101');

INSERT INTO `china` VALUES ('211103', '��¡̨��', '211101');

INSERT INTO `china` VALUES ('211121', '������', '211100');

INSERT INTO `china` VALUES ('211122', '��ɽ��', '211100');

INSERT INTO `china` VALUES ('211200', '������', '210000');

INSERT INTO `china` VALUES ('211201', '��Ͻ��', '211200');

INSERT INTO `china` VALUES ('211202', '������', '211201');

INSERT INTO `china` VALUES ('211204', '�����', '211201');

INSERT INTO `china` VALUES ('211221', '������', '211200');

INSERT INTO `china` VALUES ('211223', '������', '211200');

INSERT INTO `china` VALUES ('211224', '��ͼ��', '211200');

INSERT INTO `china` VALUES ('211281', '����ɽ��', '211200');

INSERT INTO `china` VALUES ('211282', '��ԭ��', '211200');

INSERT INTO `china` VALUES ('211300', '������', '210000');

INSERT INTO `china` VALUES ('211301', '��Ͻ��', '211300');

INSERT INTO `china` VALUES ('211302', '˫����', '211301');

INSERT INTO `china` VALUES ('211303', '������', '211301');

INSERT INTO `china` VALUES ('211321', '������', '211300');

INSERT INTO `china` VALUES ('211322', '��ƽ��', '211300');

INSERT INTO `china` VALUES ('211324', '�����������ɹ���������', '211300');

INSERT INTO `china` VALUES ('211381', '��Ʊ��', '211300');

INSERT INTO `china` VALUES ('211382', '��Դ��', '211300');

INSERT INTO `china` VALUES ('211400', '��«����', '210000');

INSERT INTO `china` VALUES ('211401', '��Ͻ��', '211400');

INSERT INTO `china` VALUES ('211402', '��ɽ��', '211401');

INSERT INTO `china` VALUES ('211403', '������', '211401');

INSERT INTO `china` VALUES ('211404', '��Ʊ��', '211401');

INSERT INTO `china` VALUES ('211421', '������', '211400');

INSERT INTO `china` VALUES ('211422', '������', '211400');

INSERT INTO `china` VALUES ('211481', '�˳���', '211400');

INSERT INTO `china` VALUES ('220000', '����ʡ', '0');

INSERT INTO `china` VALUES ('220100', '������', '220000');

INSERT INTO `china` VALUES ('220101', '��Ͻ��', '220100');

INSERT INTO `china` VALUES ('220102', '�Ϲ���', '220101');

INSERT INTO `china` VALUES ('220103', '�����', '220101');

INSERT INTO `china` VALUES ('220104', '������', '220101');

INSERT INTO `china` VALUES ('220105', '������', '220101');

INSERT INTO `china` VALUES ('220106', '��԰��', '220101');

INSERT INTO `china` VALUES ('220112', '˫����', '220101');

INSERT INTO `china` VALUES ('220122', 'ũ����', '220100');

INSERT INTO `china` VALUES ('220181', '��̨��', '220100');

INSERT INTO `china` VALUES ('220182', '������', '220100');

INSERT INTO `china` VALUES ('220183', '�»���', '220100');

INSERT INTO `china` VALUES ('220200', '������', '220000');

INSERT INTO `china` VALUES ('220201', '��Ͻ��', '220200');

INSERT INTO `china` VALUES ('220202', '������', '220201');

INSERT INTO `china` VALUES ('220203', '��̶��', '220201');

INSERT INTO `china` VALUES ('220204', '��Ӫ��', '220201');

INSERT INTO `china` VALUES ('220211', '������', '220201');

INSERT INTO `china` VALUES ('220221', '������', '220200');

INSERT INTO `china` VALUES ('220281', '�Ժ���', '220200');

INSERT INTO `china` VALUES ('220282', '�����', '220200');

INSERT INTO `china` VALUES ('220283', '������', '220200');

INSERT INTO `china` VALUES ('220284', '��ʯ��', '220200');

INSERT INTO `china` VALUES ('220300', '��ƽ��', '220000');

INSERT INTO `china` VALUES ('220301', '��Ͻ��', '220300');

INSERT INTO `china` VALUES ('220302', '������', '220301');

INSERT INTO `china` VALUES ('220303', '������', '220301');

INSERT INTO `china` VALUES ('220322', '������', '220300');

INSERT INTO `china` VALUES ('220323', '��ͨ����������', '220300');

INSERT INTO `china` VALUES ('220381', '��������', '220300');

INSERT INTO `china` VALUES ('220382', '˫����', '220300');

INSERT INTO `china` VALUES ('220400', '��Դ��', '220000');

INSERT INTO `china` VALUES ('220401', '��Ͻ��', '220400');

INSERT INTO `china` VALUES ('220402', '��ɽ��', '220401');

INSERT INTO `china` VALUES ('220403', '������', '220401');

INSERT INTO `china` VALUES ('220421', '������', '220400');

INSERT INTO `china` VALUES ('220422', '������', '220400');

INSERT INTO `china` VALUES ('220500', 'ͨ����', '220000');

INSERT INTO `china` VALUES ('220501', '��Ͻ��', '220500');

INSERT INTO `china` VALUES ('220502', '������', '220501');

INSERT INTO `china` VALUES ('220503', '��������', '220501');

INSERT INTO `china` VALUES ('220521', 'ͨ����', '220500');

INSERT INTO `china` VALUES ('220523', '������', '220500');

INSERT INTO `china` VALUES ('220524', '������', '220500');

INSERT INTO `china` VALUES ('220581', '÷�ӿ���', '220500');

INSERT INTO `china` VALUES ('220582', '������', '220500');

INSERT INTO `china` VALUES ('220600', '��ɽ��', '220000');

INSERT INTO `china` VALUES ('220601', '��Ͻ��', '220600');

INSERT INTO `china` VALUES ('220602', '�˵�����', '220601');

INSERT INTO `china` VALUES ('220621', '������', '220600');

INSERT INTO `china` VALUES ('220622', '������', '220600');

INSERT INTO `china` VALUES ('220623', '���׳�����������', '220600');

INSERT INTO `china` VALUES ('220625', '��Դ��', '220600');

INSERT INTO `china` VALUES ('220681', '�ٽ���', '220600');

INSERT INTO `china` VALUES ('220700', '��ԭ��', '220000');

INSERT INTO `china` VALUES ('220701', '��Ͻ��', '220700');

INSERT INTO `china` VALUES ('220702', '������', '220701');

INSERT INTO `china` VALUES ('220721', 'ǰ������˹�ɹ���������', '220700');

INSERT INTO `china` VALUES ('220722', '������', '220700');

INSERT INTO `china` VALUES ('220723', 'Ǭ����', '220700');

INSERT INTO `china` VALUES ('220724', '������', '220700');

INSERT INTO `china` VALUES ('220800', '�׳���', '220000');

INSERT INTO `china` VALUES ('220801', '��Ͻ��', '220800');

INSERT INTO `china` VALUES ('220802', '䬱���', '220800');

INSERT INTO `china` VALUES ('220821', '������', '220800');

INSERT INTO `china` VALUES ('220822', 'ͨ����', '220800');

INSERT INTO `china` VALUES ('220881', '�����', '220800');

INSERT INTO `china` VALUES ('220882', '����', '220800');

INSERT INTO `china` VALUES ('222400', '�ӱ߳�����������', '220000');

INSERT INTO `china` VALUES ('222401', '�Ӽ���', '222400');

INSERT INTO `china` VALUES ('222402', 'ͼ����', '222400');

INSERT INTO `china` VALUES ('222403', '�ػ���', '222400');

INSERT INTO `china` VALUES ('222404', '������', '222400');

INSERT INTO `china` VALUES ('222405', '������', '222400');

INSERT INTO `china` VALUES ('222406', '������', '222400');

INSERT INTO `china` VALUES ('222424', '������', '222400');

INSERT INTO `china` VALUES ('222426', '��ͼ��', '222400');

INSERT INTO `china` VALUES ('230000', '������ʡ', '0');

INSERT INTO `china` VALUES ('230100', '��������', '230000');

INSERT INTO `china` VALUES ('230101', '��Ͻ��', '230100');

INSERT INTO `china` VALUES ('230102', '������', '230101');

INSERT INTO `china` VALUES ('230103', '�ϸ���', '230101');

INSERT INTO `china` VALUES ('230104', '������', '230101');

INSERT INTO `china` VALUES ('230106', '�㷻��', '230101');

INSERT INTO `china` VALUES ('230107', '������', '230101');

INSERT INTO `china` VALUES ('230108', 'ƽ����', '230101');

INSERT INTO `china` VALUES ('230109', '�ɱ���', '230101');

INSERT INTO `china` VALUES ('230111', '������', '230101');

INSERT INTO `china` VALUES ('230123', '������', '230100');

INSERT INTO `china` VALUES ('230124', '������', '230100');

INSERT INTO `china` VALUES ('230125', '����', '230100');

INSERT INTO `china` VALUES ('230126', '������', '230100');

INSERT INTO `china` VALUES ('230127', 'ľ����', '230100');

INSERT INTO `china` VALUES ('230128', 'ͨ����', '230100');

INSERT INTO `china` VALUES ('230129', '������', '230100');

INSERT INTO `china` VALUES ('230181', '������', '230100');

INSERT INTO `china` VALUES ('230182', '˫����', '230100');

INSERT INTO `china` VALUES ('230183', '��־��', '230100');

INSERT INTO `china` VALUES ('230184', '�峣��', '230100');

INSERT INTO `china` VALUES ('230200', '���������', '230000');

INSERT INTO `china` VALUES ('230201', '��Ͻ��', '230200');

INSERT INTO `china` VALUES ('230202', '��ɳ��', '230201');

INSERT INTO `china` VALUES ('230203', '������', '230201');

INSERT INTO `china` VALUES ('230204', '������', '230201');

INSERT INTO `china` VALUES ('230205', '����Ϫ��', '230201');

INSERT INTO `china` VALUES ('230206', '����������', '230201');

INSERT INTO `china` VALUES ('230207', '����ɽ��', '230201');

INSERT INTO `china` VALUES ('230208', '÷��˹���Ӷ�����', '230201');

INSERT INTO `china` VALUES ('230221', '������', '230200');

INSERT INTO `china` VALUES ('230223', '������', '230200');

INSERT INTO `china` VALUES ('230224', '̩����', '230200');

INSERT INTO `china` VALUES ('230225', '������', '230200');

INSERT INTO `china` VALUES ('230227', '��ԣ��', '230200');

INSERT INTO `china` VALUES ('230229', '��ɽ��', '230200');

INSERT INTO `china` VALUES ('230230', '�˶���', '230200');

INSERT INTO `china` VALUES ('230231', '��Ȫ��', '230200');

INSERT INTO `china` VALUES ('230281', 'ګ����', '230200');

INSERT INTO `china` VALUES ('230300', '������', '230000');

INSERT INTO `china` VALUES ('230301', '��Ͻ��', '230300');

INSERT INTO `china` VALUES ('230302', '������', '230301');

INSERT INTO `china` VALUES ('230303', '��ɽ��', '230301');

INSERT INTO `china` VALUES ('230304', '�ε���', '230301');

INSERT INTO `china` VALUES ('230305', '������', '230301');

INSERT INTO `china` VALUES ('230306', '���Ӻ���', '230301');

INSERT INTO `china` VALUES ('230307', '��ɽ��', '230301');

INSERT INTO `china` VALUES ('230321', '������', '230300');

INSERT INTO `china` VALUES ('230381', '������', '230300');

INSERT INTO `china` VALUES ('230382', '��ɽ��', '230300');

INSERT INTO `china` VALUES ('230400', '�׸���', '230000');

INSERT INTO `china` VALUES ('230401', '��Ͻ��', '230400');

INSERT INTO `china` VALUES ('230402', '������', '230401');

INSERT INTO `china` VALUES ('230403', '��ũ��', '230401');

INSERT INTO `china` VALUES ('230404', '��ɽ��', '230401');

INSERT INTO `china` VALUES ('230405', '�˰���', '230401');

INSERT INTO `china` VALUES ('230406', '��ɽ��', '230401');

INSERT INTO `china` VALUES ('230407', '��ɽ��', '230401');

INSERT INTO `china` VALUES ('230421', '�ܱ���', '230400');

INSERT INTO `china` VALUES ('230422', '�����', '230400');

INSERT INTO `china` VALUES ('230500', '˫Ѽɽ��', '230000');

INSERT INTO `china` VALUES ('230501', '��Ͻ��', '230500');

INSERT INTO `china` VALUES ('230502', '��ɽ��', '230501');

INSERT INTO `china` VALUES ('230503', '�붫��', '230501');

INSERT INTO `china` VALUES ('230505', '�ķ�̨��', '230501');

INSERT INTO `china` VALUES ('230506', '��ɽ��', '230501');

INSERT INTO `china` VALUES ('230521', '������', '230500');

INSERT INTO `china` VALUES ('230522', '������', '230500');

INSERT INTO `china` VALUES ('230523', '������', '230500');

INSERT INTO `china` VALUES ('230524', '�ĺ���', '230500');

INSERT INTO `china` VALUES ('230600', '������', '230000');

INSERT INTO `china` VALUES ('230601', '��Ͻ��', '230600');

INSERT INTO `china` VALUES ('230602', '����ͼ��', '230601');

INSERT INTO `china` VALUES ('230603', '������', '230601');

INSERT INTO `china` VALUES ('230604', '�ú�·��', '230601');

INSERT INTO `china` VALUES ('230605', '�����', '230601');

INSERT INTO `china` VALUES ('230606', '��ͬ��', '230601');

INSERT INTO `china` VALUES ('230621', '������', '230600');

INSERT INTO `china` VALUES ('230622', '��Դ��', '230600');

INSERT INTO `china` VALUES ('230623', '�ֵ���', '230600');

INSERT INTO `china` VALUES ('230624', '�Ŷ������ɹ���������', '230600');

INSERT INTO `china` VALUES ('230700', '������', '230000');

INSERT INTO `china` VALUES ('230701', '��Ͻ��', '230700');

INSERT INTO `china` VALUES ('230702', '������', '230701');

INSERT INTO `china` VALUES ('230703', '�ϲ���', '230701');

INSERT INTO `china` VALUES ('230704', '�Ѻ���', '230701');

INSERT INTO `china` VALUES ('230705', '������', '230701');

INSERT INTO `china` VALUES ('230706', '������', '230701');

INSERT INTO `china` VALUES ('230707', '������', '230701');

INSERT INTO `china` VALUES ('230708', '��Ϫ��', '230701');

INSERT INTO `china` VALUES ('230709', '��ɽ����', '230701');

INSERT INTO `china` VALUES ('230710', '��Ӫ��', '230701');

INSERT INTO `china` VALUES ('230711', '�������', '230701');

INSERT INTO `china` VALUES ('230712', '��������', '230701');

INSERT INTO `china` VALUES ('230713', '������', '230701');

INSERT INTO `china` VALUES ('230714', '��������', '230701');

INSERT INTO `china` VALUES ('230715', '������', '230701');

INSERT INTO `china` VALUES ('230716', '�ϸ�����', '230701');

INSERT INTO `china` VALUES ('230722', '������', '230700');

INSERT INTO `china` VALUES ('230781', '������', '230700');

INSERT INTO `china` VALUES ('230800', '��ľ˹��', '230000');

INSERT INTO `china` VALUES ('230801', '��Ͻ��', '230800');

INSERT INTO `china` VALUES ('230803', '������', '230801');

INSERT INTO `china` VALUES ('230804', 'ǰ����', '230801');

INSERT INTO `china` VALUES ('230805', '������', '230801');

INSERT INTO `china` VALUES ('230811', '����', '230801');

INSERT INTO `china` VALUES ('230822', '������', '230800');

INSERT INTO `china` VALUES ('230826', '�봨��', '230800');

INSERT INTO `china` VALUES ('230828', '��ԭ��', '230800');

INSERT INTO `china` VALUES ('230833', '��Զ��', '230800');

INSERT INTO `china` VALUES ('230881', 'ͬ����', '230800');

INSERT INTO `china` VALUES ('230882', '������', '230800');

INSERT INTO `china` VALUES ('230900', '��̨����', '230000');

INSERT INTO `china` VALUES ('230901', '��Ͻ��', '230900');

INSERT INTO `china` VALUES ('230902', '������', '230901');

INSERT INTO `china` VALUES ('230903', '��ɽ��', '230901');

INSERT INTO `china` VALUES ('230904', '���Ӻ���', '230901');

INSERT INTO `china` VALUES ('230921', '������', '230900');

INSERT INTO `china` VALUES ('231000', 'ĵ������', '230000');

INSERT INTO `china` VALUES ('231001', '��Ͻ��', '231000');

INSERT INTO `china` VALUES ('231002', '������', '231001');

INSERT INTO `china` VALUES ('231003', '������', '231001');

INSERT INTO `china` VALUES ('231004', '������', '231001');

INSERT INTO `china` VALUES ('231005', '������', '231001');

INSERT INTO `china` VALUES ('231024', '������', '231000');

INSERT INTO `china` VALUES ('231025', '�ֿ���', '231000');

INSERT INTO `china` VALUES ('231081', '��Һ���', '231000');

INSERT INTO `china` VALUES ('231083', '������', '231000');

INSERT INTO `china` VALUES ('231084', '������', '231000');

INSERT INTO `china` VALUES ('231085', '������', '231000');

INSERT INTO `china` VALUES ('231100', '�ں���', '230000');

INSERT INTO `china` VALUES ('231101', '��Ͻ��', '231100');

INSERT INTO `china` VALUES ('231102', '������', '231101');

INSERT INTO `china` VALUES ('231121', '�۽���', '231100');

INSERT INTO `china` VALUES ('231123', 'ѷ����', '231100');

INSERT INTO `china` VALUES ('231124', '������', '231100');

INSERT INTO `china` VALUES ('231181', '������', '231100');

INSERT INTO `china` VALUES ('231182', '���������', '231100');

INSERT INTO `china` VALUES ('231200', '�绯��', '230000');

INSERT INTO `china` VALUES ('231201', '������', '231200');

INSERT INTO `china` VALUES ('231221', '������', '231200');

INSERT INTO `china` VALUES ('231222', '������', '231200');

INSERT INTO `china` VALUES ('231223', '�����', '231200');

INSERT INTO `china` VALUES ('231224', '�찲��', '231200');

INSERT INTO `china` VALUES ('231225', '��ˮ��', '231200');

INSERT INTO `china` VALUES ('231226', '������', '231200');

INSERT INTO `china` VALUES ('231281', '������', '231200');

INSERT INTO `china` VALUES ('231282', '�ض���', '231200');

INSERT INTO `china` VALUES ('231283', '������', '231200');

INSERT INTO `china` VALUES ('232700', '���˰������', '230000');

INSERT INTO `china` VALUES ('232701', '�Ӹ������', '232700');

INSERT INTO `china` VALUES ('232702', '������', '232700');

INSERT INTO `china` VALUES ('232703', '������', '232700');

INSERT INTO `china` VALUES ('232704', '������', '232700');

INSERT INTO `china` VALUES ('232721', '������', '232700');

INSERT INTO `china` VALUES ('232722', '������', '232700');

INSERT INTO `china` VALUES ('232723', 'Į����', '232700');

INSERT INTO `china` VALUES ('310000', '�Ϻ���', '0');

INSERT INTO `china` VALUES ('310100', '������', '310000');

INSERT INTO `china` VALUES ('310300', '¬����', '310000');

INSERT INTO `china` VALUES ('310400', '�����', '310000');

INSERT INTO `china` VALUES ('310500', '������', '310000');

INSERT INTO `china` VALUES ('310600', '������', '310000');

INSERT INTO `china` VALUES ('310700', '������', '310000');

INSERT INTO `china` VALUES ('310800', 'բ����', '310000');

INSERT INTO `china` VALUES ('310900', '�����', '310000');

INSERT INTO `china` VALUES ('311000', '������', '310000');

INSERT INTO `china` VALUES ('311200', '������', '310000');

INSERT INTO `china` VALUES ('311300', '��ɽ��', '310000');

INSERT INTO `china` VALUES ('311400', '�ζ���', '310000');

INSERT INTO `china` VALUES ('311500', '�ֶ�����', '310000');

INSERT INTO `china` VALUES ('311600', '��ɽ��', '310000');

INSERT INTO `china` VALUES ('311700', '�ɽ���', '310000');

INSERT INTO `china` VALUES ('311800', '������', '310000');

INSERT INTO `china` VALUES ('311900', '�ϻ���', '310000');

INSERT INTO `china` VALUES ('312000', '������', '310000');

INSERT INTO `china` VALUES ('313000', '������', '310000');

INSERT INTO `china` VALUES ('320000', '����ʡ', '0');

INSERT INTO `china` VALUES ('320100', '�Ͼ���', '320000');

INSERT INTO `china` VALUES ('320101', '��Ͻ��', '320100');

INSERT INTO `china` VALUES ('320102', '������', '320101');

INSERT INTO `china` VALUES ('320103', '������', '320101');

INSERT INTO `china` VALUES ('320104', '�ػ���', '320101');

INSERT INTO `china` VALUES ('320105', '������', '320101');

INSERT INTO `china` VALUES ('320106', '��¥��', '320101');

INSERT INTO `china` VALUES ('320107', '�¹���', '320101');

INSERT INTO `china` VALUES ('320111', '�ֿ���', '320101');

INSERT INTO `china` VALUES ('320113', '��ϼ��', '320101');

INSERT INTO `china` VALUES ('320114', '�껨̨��', '320101');

INSERT INTO `china` VALUES ('320115', '������', '320101');

INSERT INTO `china` VALUES ('320116', '������', '320101');

INSERT INTO `china` VALUES ('320124', '��ˮ��', '320100');

INSERT INTO `china` VALUES ('320125', '�ߴ���', '320100');

INSERT INTO `china` VALUES ('320200', '������', '320000');

INSERT INTO `china` VALUES ('320201', '��Ͻ��', '320200');

INSERT INTO `china` VALUES ('320202', '�簲��', '320201');

INSERT INTO `china` VALUES ('320203', '�ϳ���', '320201');

INSERT INTO `china` VALUES ('320204', '������', '320201');

INSERT INTO `china` VALUES ('320205', '��ɽ��', '320201');

INSERT INTO `china` VALUES ('320206', '��ɽ��', '320201');

INSERT INTO `china` VALUES ('320211', '������', '320201');

INSERT INTO `china` VALUES ('320281', '������', '320200');

INSERT INTO `china` VALUES ('320282', '������', '320200');

INSERT INTO `china` VALUES ('320300', '������', '320000');

INSERT INTO `china` VALUES ('320301', 'Ȫɽ��', '320300');

INSERT INTO `china` VALUES ('320302', '��¥��', '320300');

INSERT INTO `china` VALUES ('320303', '������', '320300');

INSERT INTO `china` VALUES ('320304', '������', '320301');

INSERT INTO `china` VALUES ('320305', '������', '320300');

INSERT INTO `china` VALUES ('320321', '����', '320300');

INSERT INTO `china` VALUES ('320322', '����', '320300');

INSERT INTO `china` VALUES ('320323', 'ͭɽ��', '320300');

INSERT INTO `china` VALUES ('320324', '�����', '320300');

INSERT INTO `china` VALUES ('320381', '������', '320300');

INSERT INTO `china` VALUES ('320382', '������', '320300');

INSERT INTO `china` VALUES ('320400', '������', '320000');

INSERT INTO `china` VALUES ('320401', '��Ͻ��', '320400');

INSERT INTO `china` VALUES ('320402', '������', '320401');

INSERT INTO `china` VALUES ('320404', '��¥��', '320401');

INSERT INTO `china` VALUES ('320405', '��������', '320401');

INSERT INTO `china` VALUES ('320411', '�±���', '320401');

INSERT INTO `china` VALUES ('320412', '�����', '320401');

INSERT INTO `china` VALUES ('320481', '������', '320400');

INSERT INTO `china` VALUES ('320482', '��̳��', '320400');

INSERT INTO `china` VALUES ('320500', '������', '320000');

INSERT INTO `china` VALUES ('320501', '��Ͻ��', '320500');

INSERT INTO `china` VALUES ('320502', '������', '320501');

INSERT INTO `china` VALUES ('320503', 'ƽ����', '320501');

INSERT INTO `china` VALUES ('320504', '������', '320501');

INSERT INTO `china` VALUES ('320505', '������', '320501');

INSERT INTO `china` VALUES ('320506', '������', '320501');

INSERT INTO `china` VALUES ('320507', '�����', '320501');

INSERT INTO `china` VALUES ('320581', '������', '320500');

INSERT INTO `china` VALUES ('320582', '�żҸ���', '320500');

INSERT INTO `china` VALUES ('320583', '��ɽ��', '320500');

INSERT INTO `china` VALUES ('320584', '�⽭��', '320500');

INSERT INTO `china` VALUES ('320585', '̫����', '320500');

INSERT INTO `china` VALUES ('320600', '��ͨ��', '320000');

INSERT INTO `china` VALUES ('320601', '��Ͻ��', '320600');

INSERT INTO `china` VALUES ('320602', '�紨��', '320601');

INSERT INTO `china` VALUES ('320611', '��բ��', '320601');

INSERT INTO `china` VALUES ('320621', '������', '320600');

INSERT INTO `china` VALUES ('320623', '�綫��', '320600');

INSERT INTO `china` VALUES ('320681', '������', '320600');

INSERT INTO `china` VALUES ('320682', '�����', '320600');

INSERT INTO `china` VALUES ('320683', 'ͨ����', '320600');

INSERT INTO `china` VALUES ('320684', '������', '320600');

INSERT INTO `china` VALUES ('320700', '���Ƹ���', '320000');

INSERT INTO `china` VALUES ('320701', '��Ͻ��', '320700');

INSERT INTO `china` VALUES ('320703', '������', '320701');

INSERT INTO `china` VALUES ('320705', '������', '320701');

INSERT INTO `china` VALUES ('320706', '������', '320701');

INSERT INTO `china` VALUES ('320721', '������', '320700');

INSERT INTO `china` VALUES ('320722', '������', '320700');

INSERT INTO `china` VALUES ('320723', '������', '320700');

INSERT INTO `china` VALUES ('320724', '������', '320700');

INSERT INTO `china` VALUES ('320800', '������', '320000');

INSERT INTO `china` VALUES ('320801', '��Ͻ��', '320800');

INSERT INTO `china` VALUES ('320802', '�����', '320801');

INSERT INTO `china` VALUES ('320803', '������', '320801');

INSERT INTO `china` VALUES ('320804', '������', '320801');

INSERT INTO `china` VALUES ('320811', '������', '320801');

INSERT INTO `china` VALUES ('320826', '��ˮ��', '320800');

INSERT INTO `china` VALUES ('320829', '������', '320800');

INSERT INTO `china` VALUES ('320830', '������', '320800');

INSERT INTO `china` VALUES ('320831', '�����', '320800');

INSERT INTO `china` VALUES ('320900', '�γ���', '320000');

INSERT INTO `china` VALUES ('320901', '��Ͻ��', '320900');

INSERT INTO `china` VALUES ('320902', 'ͤ����', '320901');

INSERT INTO `china` VALUES ('320903', '�ζ���', '320901');

INSERT INTO `china` VALUES ('320921', '��ˮ��', '320900');

INSERT INTO `china` VALUES ('320922', '������', '320900');

INSERT INTO `china` VALUES ('320923', '������', '320900');

INSERT INTO `china` VALUES ('320924', '������', '320900');

INSERT INTO `china` VALUES ('320925', '������', '320900');

INSERT INTO `china` VALUES ('320981', '��̨��', '320900');

INSERT INTO `china` VALUES ('320982', '�����', '320900');

INSERT INTO `china` VALUES ('321000', '������', '320000');

INSERT INTO `china` VALUES ('321001', '��Ͻ��', '321000');

INSERT INTO `china` VALUES ('321002', '������', '321001');

INSERT INTO `china` VALUES ('321003', '������', '321001');

INSERT INTO `china` VALUES ('321011', 'ά����', '321001');

INSERT INTO `china` VALUES ('321023', '��Ӧ��', '321000');

INSERT INTO `china` VALUES ('321081', '������', '321000');

INSERT INTO `china` VALUES ('321084', '������', '321000');

INSERT INTO `china` VALUES ('321088', '������', '321000');

INSERT INTO `china` VALUES ('321100', '����', '320000');

INSERT INTO `china` VALUES ('321101', '��Ͻ��', '321100');

INSERT INTO `china` VALUES ('321102', '������', '321101');

INSERT INTO `china` VALUES ('321111', '������', '321101');

INSERT INTO `china` VALUES ('321112', '��ͽ��', '321101');

INSERT INTO `china` VALUES ('321181', '������', '321100');

INSERT INTO `china` VALUES ('321182', '������', '321100');

INSERT INTO `china` VALUES ('321183', '������', '321100');

INSERT INTO `china` VALUES ('321200', '̩����', '320000');

INSERT INTO `china` VALUES ('321201', '��Ͻ��', '321200');

INSERT INTO `china` VALUES ('321202', '������', '321201');

INSERT INTO `china` VALUES ('321203', '�߸���', '321201');

INSERT INTO `china` VALUES ('321281', '�˻���', '321200');

INSERT INTO `china` VALUES ('321282', '������', '321200');

INSERT INTO `china` VALUES ('321283', '̩����', '321200');

INSERT INTO `china` VALUES ('321284', '������', '321200');

INSERT INTO `china` VALUES ('321300', '��Ǩ��', '320000');

INSERT INTO `china` VALUES ('321301', '��Ͻ��', '321300');

INSERT INTO `china` VALUES ('321302', '�޳���', '321301');

INSERT INTO `china` VALUES ('321311', '��ԥ��', '321301');

INSERT INTO `china` VALUES ('321322', '������', '321300');

INSERT INTO `china` VALUES ('321323', '������', '321300');

INSERT INTO `china` VALUES ('321324', '������', '321300');

INSERT INTO `china` VALUES ('330000', '�㽭ʡ', '0');

INSERT INTO `china` VALUES ('330100', '������', '330000');

INSERT INTO `china` VALUES ('330101', '��Ͻ��', '330100');

INSERT INTO `china` VALUES ('330102', '�ϳ���', '330101');

INSERT INTO `china` VALUES ('330103', '�³���', '330101');

INSERT INTO `china` VALUES ('330104', '������', '330101');

INSERT INTO `china` VALUES ('330105', '������', '330101');

INSERT INTO `china` VALUES ('330106', '������', '330101');

INSERT INTO `china` VALUES ('330108', '������', '330101');

INSERT INTO `china` VALUES ('330109', '��ɽ��', '330101');

INSERT INTO `china` VALUES ('330110', '�ຼ��', '330101');

INSERT INTO `china` VALUES ('330122', 'ͩ®��', '330100');

INSERT INTO `china` VALUES ('330127', '������', '330100');

INSERT INTO `china` VALUES ('330182', '������', '330100');

INSERT INTO `china` VALUES ('330183', '������', '330100');


