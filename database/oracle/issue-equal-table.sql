create view v
as
select * from emp where deptno != 10
union all
select * from emp where ename = 'WARD' ;

select * from v ; 

(
select empno, ename, job, mgr, hiredate, sal, comm, deptno, count(*) as cnt
  from v
 group by empno, ename, job, mgr, hiredate, sal, comm, deptno
minus
select empno, ename, job, mgr, hiredate, sal, comm, deptno, count(*) as cnt
  from emp
 group by empno, ename, job, mgr, hiredate, sal, comm, deptno
)
union all
(
select empno, ename, job, mgr, hiredate, sal, comm, deptno, count(*) as cnt
  from emp
 group by empno, ename, job, mgr, hiredate, sal, comm, deptno
minus
select empno, ename, job, mgr, hiredate, sal, comm, deptno, count(*) as cnt
  from v
 group by empno, ename, job, mgr, hiredate, sal, comm, deptno
)

--- coalesce 返回第一个不为 null 的值
select coalesce(null, 'first not null') from dual ;

---------------------
-- INSERT ALL | INSERT FIRST | WHEN-THEN-ELSE

select * from emp where empno = 7369;
update emp e set (e.mgr, e.comm) = (select 200, 300 from dual) -- 此处必须是子查询
where e.empno = 7369 ;

-- oracle 
update (select e.sal  as emp_sal,
               e.comm as emp_comm,
               400    as ns_sal,
               699    as ns_comm
          from emp e
         where e.empno = 7369)
   set emp_sal = ns_sal, emp_comm = ns_comm;


select column_name, data_type, column_id
from all_tab_columns 
where owner = 'SCOTT'
and table_name = 'EMP';
