select ename, sal, job, comm, case when job = 'SALESMAN' then comm else sal end as ordered
from emp 
order by 5 ;

select ename, sal, job, comm
from emp
order by case when job = 'SALESMAN' then comm else sal end ; 

select ename as ename_and_dname, deptno
from emp
where deptno = 10
union all
select '---------', null
from dual
union all
select dname, deptno
from dept ;

union all; -- 不会去除重复
union ; -- 会去除重复， 相当于对 union all 执行了一次 distinct 操作

-- oralce 限定行数 rownumber
select * from emp where rownum <= 5 ;
/*
1. Oralce 执行查询， 2. Oracle 取得第一行数据，并把它的行号定为 1
3. 此行的行号是否小于等于 5， 满足条件返回此行
4. Oracle 取得下一行数据，并将行号加 1 （然后得到2 ，3， 4 类推）
5. 返回第三步骤
注意： rownumber = 1 是可以返回数据， 但是 rownumber = 大于 1 的数据就是空
*/

select count(*) from emp ;
select count(*) from dept ;
select e.ename, d.loc, e.deptno as emp_deptno, d.deptno as dept_deptno
  from emp e, dept d
 where e.deptno = d.deptno
   and e.deptno = 10;
   
select e.ename, d.loc, e.deptno as emp_deptno, d.deptno as dept_deptno
  from emp e inner join dept d on (e.deptno = d.deptno)
  where e.deptno = 10 ;

-- 查找唯一数据，仅存在一表中
-- oracle
select deptno from dept
minus
select deptno from emp ;
-- mysql 
select deptno from dept
where deptno not in (select deptno from emp );


create table new_dept(deptno integer);
insert into new_dept values(50);
insert into new_dept values(10);
insert into new_dept values(null);

select *
from dept
where deptno not in (select deptno from new_dept) ;

-- TRUE or NUll -> TRUE; FALSE OR NULL -> NULL
-- 一旦混入了 NULl 结果就会一直保持为 NULL -> 在 IN 和 OR 逻辑运算时要考虑是否存在 NULL
-- EXISTS(..), NOT EXISTS(..) 当子查询有结果返回就是 TRUE，外层查询就会返回当前行

select d.*
from dept d left join emp e on (d.deptno = e.deptno)
where e.deptno is null ;
-- oralce 专有连接
select d.*
from dept d, emp e
where d.deptno = e.deptno(+)
and e.deptno is null ;

select e.ename, e.deptno as emp_deptno, d.*
from dept d left join emp e on (d.deptno = e.deptno) ;

select e.ename, d.loc
from emp e, dept d
where e.deptno = d.deptno ;

select e.name, d.loc
from emp e join dept d on (e.deptno = d.deptno)
     left join emp_bonus eb on (e.empno = eb.empno)
order by 2 ;

-- 标量子查询， 必须确保返回的是标量值(单值)
select e.ename, d.loc, (select eb.received from emp_bonus eb where eb.empno = e.empno) as received
from emp e, dept d
where e.deptno = d.deptno ;
