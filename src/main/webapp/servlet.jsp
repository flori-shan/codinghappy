<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8" http-equiv="Content-Type" content="text/html;charset=UTF-8"/>
    <title>servlet</title>
</head>
<body>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
    request.setCharacterEncoding("UTF-8");
    response.setCharacterEncoding("UTF-8");
%>
path = <%=path%><br />
basePath = <%=basePath%>
<hr/>

<p>Say <a id="hello" href="servlet">Hello</a></p>

<form method="post" action="servlet">
    <h2>Name:</h2>
    <input type="text" id="say-hello-text-input" name="name" />
    <input type="submit" id="say-hello-button" value="Say Hello" />
</form>

</body>
</html>
