<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8" http-equiv="Content-Type" content="text/html;charset=UTF-8"/>
    <title>MVC Controller Data Binding</title>
</head>
<body>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
    request.setCharacterEncoding("UTF-8");
    response.setCharacterEncoding("UTF-8");
%>
path = <%=path%><br/>
basePath = <%=basePath%>
<hr/>

基础数据绑定: <br />
<form action="<%=path%>/bind/origin" method="get">
    int value : <input type="text" value="18" name="iValue" /> <br />
    long value : <input type="text" value="20" name="lValue" /> <br />
    float value : <input type="text" value="22.22F" name="fValue" /> <br />
    double value : <input type="text" value="24.24D" name="dValue" /> <br />
    char value : <input type="text" value="C" name="cValue" /> <br />
    boolean value : <input type="text" value="false" name="bValue" /> <br />
    <input type="submit" value="submit" />
</form>
<hr />

封装数据绑定: <br />
<form action="<%=path%>/bind/reference" method="get">
    int value : <input type="text" value="18" name="iValue" /> <br />
    long value : <input type="text" value="20" name="lValue" /> <br />
    float value : <input type="text" value="22.22F" name="fValue" /> <br />
    double value : <input type="text" value="24.24D" name="dValue" /> <br />
    char value : <input type="text" value="C" name="cValue" /> <br />
    boolean value : <input type="text" value="false" name="bValue" /> <br />
    <input type="submit" value="submit" />
</form>
<hr />

List 数据绑定: <br />
<form action="<%=path%>/bind/list" method="get">
    obj1 : <input type="checkbox" value="百合-tel" name="entities[0].tel" /> <br />
    obj1 : <input type="checkbox" value="水仙-address" name="entities[0].address" /> <br />
    obj2 : <input type="checkbox" value="莲花-tel" name="entities[1].tel" /> <br />
    obj2 : <input type="checkbox" value="菊花-address" name="entities[1].address" /> <br />
    <input type="submit" value="submit" />
</form>
<hr />

POJO 数据绑定: <br />
<form action="<%=path%>/bind/pojo" method="get">
    用户ID : <input type="text" value="100" name="id" /> <br />
    用户姓名 : <input type="text" value="水仙" name="name" /> <br />
    出生日期 : <input type="text" value="2019-06-05 12:12:12" name="birthDate" /> <br />
    <input type="submit" value="submit" />
</form>
<hr />

复杂 POJO 数据绑定: <br />
<form action="<%=path%>/bind/multi-pojo" method="get">
    用户ID : <input type="text" value="100" name="id" /> <br />
    用户姓名 : <input type="text" value="水仙" name="name" /> <br />
    出生日期 : <input type="text" value="2019-06-05 12:12:12" name="birthDate" /> <br />

    <%-- 注意： 此处的 name 为： 实体内属性.属性字段 --%>
    地址 : <input type="text" value="未知地址" name="info.address" /> <br />
    联系电话 : <input type="text" value="未知电话" name="info.tel" /> <br />
    id : <input type="text" value="10000" name="info.id" /> <br />

    <input type="submit" value="submit" />
</form>
<hr />

Array 数据绑定: <br />
<form action="<%=path%>/bind/array" method="get">
    百合 : <input type="checkbox" value="百合" name="array" /> <br />
    水仙 : <input type="checkbox" value="水仙" name="array" /> <br />
    莲花 : <input type="checkbox" value="莲花" name="array" /> <br />
    菊花 : <input type="checkbox" value="菊花" name="array" /> <br />
    <input type="submit" value="submit" />
</form>
<hr />

</body>
</html>
