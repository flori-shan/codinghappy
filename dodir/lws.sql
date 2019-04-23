select count(1) from m_product where nvl(ATTRIBUTE1,'N')='Y' and id=10


select id from i_user where username = (select iu.parent from i_user iu where iu.id = 1 ) ;

select iu.* from i_user iu where iu.id = 1 ;

select * from i_user ;

declare
       vid NUMBER := 1;
begin
    dbms_output.put_line('id = ' || vid) ;
    loop
        select id into vid from i_user where username = (select iu.parent from i_user iu where iu.id = vid );
        exit when vid is null ;
        dbms_output.put_line('id = ' || vid) ;
   end loop ;
   EXCEPTION WHEN NO_DATA_FOUND THEN
       dbms_output.put_line('error = ' || vid) ;
end ;
/        
       


function hello(v in number) return varchar2;

create or replace function func(v in varchar2) 
       return varchar2
is
       v_out varchar2(100) ;
begin
    

end ;            
