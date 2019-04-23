select * from user_tables ;

select * from emp ;

create table empcopy
as
select * from emp ;

select * from empcopy ;

select sysdate from dual ;
select * from CLOB_TABLE ;

insert into clob_table values(2, 'ÄãºÃ', 'test²Ù×÷', sysdate, sysdate);

-------------------------------------------

declare 
