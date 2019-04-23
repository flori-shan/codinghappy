create table t
(
       t1 date ,
       t2 timestamp
) ;

insert into t(t1, t2) values (date'2010-2-12', timestamp'2010-2-12 12:13:14.123456') ;
insert into t(t1, t2)
values (to_date('2010-2-12 10:12:19', 'YYYY-MM-DD HH24:MI:SS'),
       to_timestamp('2010-2-12 10:13:15.123456', 'YYYY-MM-DD HH24:MI:SS.FF6')) ;


select * from t ;
