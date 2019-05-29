<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8" http-equiv="Content-Type" content="text/html;charset=UTF-8"/>
    <title>servlet response</title>
</head>
<body>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
    request.setCharacterEncoding("UTF-8");
    response.setCharacterEncoding("UTF-8");
%>
path = <%=path%> <br />
basePath = <%=basePath%>
<hr/>
<h2>Hello, ${user}!</h2>
</body>
</html>
