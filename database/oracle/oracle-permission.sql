/*
һ��Ȩ�޷��ࣺ
ϵͳȨ�ޣ�ϵͳ�涨�û�ʹ�����ݿ��Ȩ�ޡ���ϵͳȨ���Ƕ��û�����)��
ʵ��Ȩ�ޣ�ĳ��Ȩ���û��������û��ı����ͼ�Ĵ�ȡȨ�ޡ�������Ա����ͼ���Եģ���

����ϵͳȨ�޹���
1��ϵͳȨ�޷��ࣺ
DBA: ӵ��ȫ����Ȩ����ϵͳ���Ȩ�ޣ�ֻ��DBA�ſ��Դ������ݿ�ṹ��
RESOURCE: ӵ��ResourceȨ�޵��û�ֻ���Դ���ʵ�壬�����Դ������ݿ�ṹ��
CONNECT: ӵ��ConnectȨ�޵��û�ֻ���Ե�¼Oracle�������Դ���ʵ�壬�����Դ������ݿ�ṹ��

������ͨ�û�������connect, resourceȨ�ޡ�
����DBA�����û�������connect��resource, dbaȨ�ޡ�

2��ϵͳȨ����Ȩ���
[ϵͳȨ��ֻ����DBA�û��ڳ���sys, system(�ʼֻ�����������û�)]
��Ȩ���SQL> grant connect, resource, dba to �û���1 [,�û���2]...;
[��ͨ�û�ͨ����Ȩ���Ծ�����system��ͬ���û�Ȩ�ޣ�����Զ���ܴﵽ��sys�û���ͬ��Ȩ�ޣ�system�û���Ȩ��Ҳ���Ա����ա�]

connect system/manager
Create user user50 identified by user50;
grant connect, resource to user50;

��ѯ�û�ӵ������Ȩ�ޣ�
select * from dba_role_privs;
select * from dba_sys_privs;
select * from role_sys_privs;

ɾ���û���SQL> drop user �û��� cascade; //����cascade���û���ͬ�䴴���Ķ���ȫ��ɾ��
*/

select * from dba_role_privs;
select * from dba_sys_privs;
select * from role_sys_privs;

grant create view to scott ;
