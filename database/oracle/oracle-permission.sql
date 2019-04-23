/*
一、权限分类：
系统权限：系统规定用户使用数据库的权限。（系统权限是对用户而言)。
实体权限：某种权限用户对其它用户的表或视图的存取权限。（是针对表或视图而言的）。

二、系统权限管理：
1、系统权限分类：
DBA: 拥有全部特权，是系统最高权限，只有DBA才可以创建数据库结构。
RESOURCE: 拥有Resource权限的用户只可以创建实体，不可以创建数据库结构。
CONNECT: 拥有Connect权限的用户只可以登录Oracle，不可以创建实体，不可以创建数据库结构。

对于普通用户：授予connect, resource权限。
对于DBA管理用户：授予connect，resource, dba权限。

2、系统权限授权命令：
[系统权限只能由DBA用户授出：sys, system(最开始只能是这两个用户)]
授权命令：SQL> grant connect, resource, dba to 用户名1 [,用户名2]...;
[普通用户通过授权可以具有与system相同的用户权限，但永远不能达到与sys用户相同的权限，system用户的权限也可以被回收。]

connect system/manager
Create user user50 identified by user50;
grant connect, resource to user50;

查询用户拥有哪里权限：
select * from dba_role_privs;
select * from dba_sys_privs;
select * from role_sys_privs;

删除用户：SQL> drop user 用户名 cascade; //加上cascade则将用户连同其创建的东西全部删除
*/

select * from dba_role_privs;
select * from dba_sys_privs;
select * from role_sys_privs;

grant create view to scott ;
