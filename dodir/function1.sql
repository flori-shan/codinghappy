select * from user_tables ;


select * from bs_main_status ;
select * from bs_main_interface;


prompt Importing table bs_main_interface...
set feedback off
set define off
insert into bs_main_interface (ID, AD_ORG_ID, MODULE_TYPE, NAME, X, Y, IMAGE, URL, OWNERID, MODIFIERID, CREATIONDATE, MODIFIEDDATE, ISACTIVE, KEY)
values (1, 27, 'prepare', '总裁致辞', 20, 20, '1-8.png', 'fairapp://WebStage/?type=link&base=1#/servlets/binserv/ListResources?transactions=%5B%7B%22id%22%3A0%2C%22command%22%3A%22com.agilecontrol.fair.FairCmd%22%2C%22params%22%3A%7B%22cmd%22%3A%22ListResources%22%2C%22type%22%3A%22+prepare_speech+%22%7D%7D%5D', null, 893, to_date('22-04-2016 14:39:27', 'dd-mm-yyyy hh24:mi:ss'), to_date('20-10-2017 21:12:07', 'dd-mm-yyyy hh24:mi:ss'), 'N', 'prepare_speech');

insert into bs_main_interface (ID, AD_ORG_ID, MODULE_TYPE, NAME, X, Y, IMAGE, URL, OWNERID, MODIFIERID, CREATIONDATE, MODIFIEDDATE, ISACTIVE, KEY)
values (2, 27, 'prepare', '日程安排', 10, 60, '1-7.png', 'fairapp://WebStage/?type=file&base=1#/pdt/pdf/schedule.pdf', null, 893, to_date('22-04-2016 14:39:27', 'dd-mm-yyyy hh24:mi:ss'), to_date('20-10-2017 21:10:41', 'dd-mm-yyyy hh24:mi:ss'), 'N', 'prepare_schedule');

insert into bs_main_interface (ID, AD_ORG_ID, MODULE_TYPE, NAME, X, Y, IMAGE, URL, OWNERID, MODIFIERID, CREATIONDATE, MODIFIEDDATE, ISACTIVE, KEY)
values (3, 27, 'prepare', '订货政策', 10, 50, '1-6.png', 'fairapp://WebStage/?type=file&base=1#/pdt/pdf/policy.pdf', null, 893, to_date('22-04-2016 14:39:27', 'dd-mm-yyyy hh24:mi:ss'), to_date('19-04-2018 10:25:30', 'dd-mm-yyyy hh24:mi:ss'), 'Y', 'prepare_policy');

insert into bs_main_interface (ID, AD_ORG_ID, MODULE_TYPE, NAME, X, Y, IMAGE, URL, OWNERID, MODIFIERID, CREATIONDATE, MODIFIEDDATE, ISACTIVE, KEY)
values (4, 27, 'prepare', 'T台魅影', 10, 40, '1-5.png', 'fairapp://WebStage/?type=link&base=1#/servlets/binserv/ListResources?transactions=%5B%7B%22id%22%3A0%2C%22command%22%3A%22com.agilecontrol.fair.FairCmd%22%2C%22params%22%3A%7B%22cmd%22%3A%22ListResources%22%2C%22type%22%3A%22prepare_Tshow%22%7D%7D%5D', null, 893, to_date('22-04-2016 14:39:27', 'dd-mm-yyyy hh24:mi:ss'), to_date('20-10-2017 21:12:07', 'dd-mm-yyyy hh24:mi:ss'), 'N', 'prepare_Tshow');

insert into bs_main_interface (ID, AD_ORG_ID, MODULE_TYPE, NAME, X, Y, IMAGE, URL, OWNERID, MODIFIERID, CREATIONDATE, MODIFIEDDATE, ISACTIVE, KEY)
values (5, 27, 'prepare', '图册展示', 10, 30, '1-4.png', 'fairapp://WebStage/?type=link&base=1#/pdt/pdf/man.json', null, 893, to_date('22-04-2016 14:39:27', 'dd-mm-yyyy hh24:mi:ss'), to_date('20-10-2017 21:11:34', 'dd-mm-yyyy hh24:mi:ss'), 'Y', 'prepare_photo');

insert into bs_main_interface (ID, AD_ORG_ID, MODULE_TYPE, NAME, X, Y, IMAGE, URL, OWNERID, MODIFIERID, CREATIONDATE, MODIFIEDDATE, ISACTIVE, KEY)
values (6, 27, 'prepare', '品牌理念', 10, 20, '1-3.png', 'fairapp://WebStage/?type=file&base=1#/pdt/pdf/brand.pdf', null, 893, to_date('22-04-2016 14:39:27', 'dd-mm-yyyy hh24:mi:ss'), to_date('20-10-2017 21:12:07', 'dd-mm-yyyy hh24:mi:ss'), 'N', 'prepare_brand');

insert into bs_main_interface (ID, AD_ORG_ID, MODULE_TYPE, NAME, X, Y, IMAGE, URL, OWNERID, MODIFIERID, CREATIONDATE, MODIFIEDDATE, ISACTIVE, KEY)
values (7, 27, 'prepare', '私享家', 10, 10, '4-1.png', 'fairapp://WebStage/?type=file&base=1#/pdt/pdf/sixiangjia.pdf', null, 893, to_date('22-04-2016 14:39:27', 'dd-mm-yyyy hh24:mi:ss'), to_date('20-10-2017 21:12:07', 'dd-mm-yyyy hh24:mi:ss'), 'N', 'pdf');

insert into bs_main_interface (ID, AD_ORG_ID, MODULE_TYPE, NAME, X, Y, IMAGE, URL, OWNERID, MODIFIERID, CREATIONDATE, MODIFIEDDATE, ISACTIVE, KEY)
values (8, 27, 'report', '审核', 40, 20, '3-3.png', 'fairapp://WebStage/?type=url&base=1#/fair/ipad/suggest.jsp', null, null, to_date('22-04-2016 14:39:27', 'dd-mm-yyyy hh24:mi:ss'), null, 'Y', 'verify');

insert into bs_main_interface (ID, AD_ORG_ID, MODULE_TYPE, NAME, X, Y, IMAGE, URL, OWNERID, MODIFIERID, CREATIONDATE, MODIFIEDDATE, ISACTIVE, KEY)
values (9, 27, 'report', '报表分析', 40, 10, '3-1.png', 'fairapp://WebStage/?type=url&base=1&persist=1&fullscreen=1#/fair/ipad/kpi.jsp', null, null, to_date('22-04-2016 14:39:27', 'dd-mm-yyyy hh24:mi:ss'), null, 'Y', 'submit');

insert into bs_main_interface (ID, AD_ORG_ID, MODULE_TYPE, NAME, X, Y, IMAGE, URL, OWNERID, MODIFIERID, CREATIONDATE, MODIFIEDDATE, ISACTIVE, KEY)
values (10, 27, 'report', '设置', 40, 50, '3-6.png', 'fairapp://WebStage/?type=url&base=1#/fair/ipad/changepassword.jsp', null, 893, to_date('22-04-2016 14:39:27', 'dd-mm-yyyy hh24:mi:ss'), to_date('29-08-2016 17:34:30', 'dd-mm-yyyy hh24:mi:ss'), 'Y', 'setting');

insert into bs_main_interface (ID, AD_ORG_ID, MODULE_TYPE, NAME, X, Y, IMAGE, URL, OWNERID, MODIFIERID, CREATIONDATE, MODIFIEDDATE, ISACTIVE, KEY)
values (11, 27, 'order', '商品调整', 30, 50, '3-2.png', 'fairapp://WebStage/?type=url&base=1&fullscreen=1#/fair/ipad/orderdetails.jsp', null, 893, to_date('22-04-2016 14:39:27', 'dd-mm-yyyy hh24:mi:ss'), to_date('29-08-2016 17:33:41', 'dd-mm-yyyy hh24:mi:ss'), 'N', 'pproduct_dispatch');

insert into bs_main_interface (ID, AD_ORG_ID, MODULE_TYPE, NAME, X, Y, IMAGE, URL, OWNERID, MODIFIERID, CREATIONDATE, MODIFIEDDATE, ISACTIVE, KEY)
values (12, 27, 'order', '单品订货', 30, 40, '2-3.png', 'fairapp://ListStage?gtd=1&sql=select%20pdtid%20from%20products%0A%20where%20ismodel%20%3D%20''N''%20', null, 893, to_date('22-04-2016 14:39:27', 'dd-mm-yyyy hh24:mi:ss'), to_date('28-08-2018 11:29:17', 'dd-mm-yyyy hh24:mi:ss'), 'Y', 'order_gtd');

insert into bs_main_interface (ID, AD_ORG_ID, MODULE_TYPE, NAME, X, Y, IMAGE, URL, OWNERID, MODIFIERID, CREATIONDATE, MODIFIEDDATE, ISACTIVE, KEY)
values (13, 27, 'order', '搭配', 30, 30, '2-6.png', 'fairapp://DisplayListStage/?settype=pair&noshow=Y', null, 893, to_date('22-04-2016 14:39:27', 'dd-mm-yyyy hh24:mi:ss'), to_date('27-08-2018 13:48:31', 'dd-mm-yyyy hh24:mi:ss'), 'N', 'vmpair');

insert into bs_main_interface (ID, AD_ORG_ID, MODULE_TYPE, NAME, X, Y, IMAGE, URL, OWNERID, MODIFIERID, CREATIONDATE, MODIFIEDDATE, ISACTIVE, KEY)
values (14, 27, 'order', '陈列', 30, 20, '2-2.png', 'fairapp://DisplayListStage?settype=vm', null, 893, to_date('22-04-2016 14:39:27', 'dd-mm-yyyy hh24:mi:ss'), to_date('29-08-2016 17:33:34', 'dd-mm-yyyy hh24:mi:ss'), 'N', 'vmbook');

insert into bs_main_interface (ID, AD_ORG_ID, MODULE_TYPE, NAME, X, Y, IMAGE, URL, OWNERID, MODIFIERID, CREATIONDATE, MODIFIEDDATE, ISACTIVE, KEY)
values (15, 27, 'order', '画册', 30, 60, '2-1.png', 'fairapp://DisplayListStage?settype=lookbook', null, 893, to_date('22-04-2016 14:39:27', 'dd-mm-yyyy hh24:mi:ss'), to_date('27-08-2018 13:49:23', 'dd-mm-yyyy hh24:mi:ss'), 'N', 'lookbook');

insert into bs_main_interface (ID, AD_ORG_ID, MODULE_TYPE, NAME, X, Y, IMAGE, URL, OWNERID, MODIFIERID, CREATIONDATE, MODIFIEDDATE, ISACTIVE, KEY)
values (16, 27, 'report', '公告', 40, 40, '2-5.png', 'fairapp://WebStage/?type=url&base=1#/fair/ipad/about.jsp', null, null, to_date('22-04-2016 14:39:27', 'dd-mm-yyyy hh24:mi:ss'), null, 'Y', 'notice');

insert into bs_main_interface (ID, AD_ORG_ID, MODULE_TYPE, NAME, X, Y, IMAGE, URL, OWNERID, MODIFIERID, CREATIONDATE, MODIFIEDDATE, ISACTIVE, KEY)
values (18, 27, 'report', '龙虎榜', 40, 60, '3-5.png', 'fairapp://WebStage/?type=url&base=1&persist=1&fullscreen=1#/fair/ipad/ranking.jsp', 893, 893, to_date('07-09-2016 15:03:04', 'dd-mm-yyyy hh24:mi:ss'), to_date('27-08-2018 13:50:16', 'dd-mm-yyyy hh24:mi:ss'), 'N', 'TOP');

insert into bs_main_interface (ID, AD_ORG_ID, MODULE_TYPE, NAME, X, Y, IMAGE, URL, OWNERID, MODIFIERID, CREATIONDATE, MODIFIEDDATE, ISACTIVE, KEY)
values (24, 27, 'prepare', '促销手册', 20, 10, '4-2.png', 'fairapp://WebStage/?type=link&base=1#/pdt/pdf/promo.json', 893, 893, to_date('18-05-2017 13:26:47', 'dd-mm-yyyy hh24:mi:ss'), to_date('20-10-2017 21:12:07', 'dd-mm-yyyy hh24:mi:ss'), 'N', 'promotion');

insert into bs_main_interface (ID, AD_ORG_ID, MODULE_TYPE, NAME, X, Y, IMAGE, URL, OWNERID, MODIFIERID, CREATIONDATE, MODIFIEDDATE, ISACTIVE, KEY)
values (27, 27, 'order', '系列下单', 30, 10, '2-6.png', 'fairapp://SeriesListStage/?settype=series&noshow=Y', 893, 893, to_date('23-08-2018 20:32:05', 'dd-mm-yyyy hh24:mi:ss'), to_date('27-08-2018 13:49:31', 'dd-mm-yyyy hh24:mi:ss'), 'Y', 'series');

prompt Done.
