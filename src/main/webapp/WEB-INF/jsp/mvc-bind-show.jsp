<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8" http-equiv="Content-Type" content="text/html;charset=UTF-8"/>
    <title>data bind result show</title>
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
基础/封装 数据绑定展示 ：<br />
iValue = ${iValue} | lvalue = ${lValue} | fValue = ${fValue} | dValue = ${dValue} | cValue = ${cValue} | bValue = ${bValue}
<hr />

List 数据绑定展示 ：<br />
list : ${dto.entities} <br />
size : ${fn:length(dto.entities)} <br>
<c:forEach items="${dto.entities}" var="item">
    tel : ${item.tel} | address : ${item.address} <br />
</c:forEach>
<hr />

POJO 数据绑定 ： <br>
id = ${entity.id} | name = ${entity.name} | birthdate = ${entity.birthDate} <br />
address = ${entity.info.address} | tel = ${entity.info.tel} | id = ${entity.info.id} <br />
<hr>

Array 数据绑定展示 ：<br />
Array : ${array} <br />
size : ${fn:length(array)} <br>
<c:forEach items="${array}" var="item">
    value : ${item} <br />
</c:forEach>
<hr />

</body>
</html>
