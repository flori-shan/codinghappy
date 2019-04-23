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

union all; -- ����ȥ���ظ�
union ; -- ��ȥ���ظ��� �൱�ڶ� union all ִ����һ�� distinct ����

-- oralce �޶����� rownumber
select * from emp where rownum <= 5 ;
/*
1. Oralce ִ�в�ѯ�� 2. Oracle ȡ�õ�һ�����ݣ����������кŶ�Ϊ 1
3. ���е��к��Ƿ�С�ڵ��� 5�� �����������ش���
4. Oracle ȡ����һ�����ݣ������кż� 1 ��Ȼ��õ�2 ��3�� 4 ���ƣ�
5. ���ص�������
ע�⣺ rownumber = 1 �ǿ��Է������ݣ� ���� rownumber = ���� 1 �����ݾ��ǿ�
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

-- ����Ψһ���ݣ�������һ����
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
-- һ�������� NULl ����ͻ�һֱ����Ϊ NULL -> �� IN �� OR �߼�����ʱҪ�����Ƿ���� NULL
-- EXISTS(..), NOT EXISTS(..) ���Ӳ�ѯ�н�����ؾ��� TRUE������ѯ�ͻ᷵�ص�ǰ��

select d.*
from dept d left join emp e on (d.deptno = e.deptno)
where e.deptno is null ;
-- oralce ר������
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

-- �����Ӳ�ѯ�� ����ȷ�����ص��Ǳ���ֵ(��ֵ)
select e.ename, d.loc, (select eb.received from emp_bonus eb where eb.empno = e.empno) as received
from emp e, dept d
where e.deptno = d.deptno ;
