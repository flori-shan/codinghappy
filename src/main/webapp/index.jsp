<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
  request.setCharacterEncoding("UTF-8");
  response.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
  <meta charset="UTF-8" http-equiv="Content-Type" content="text/html;charset=UTF-8"/>
  <title>Index Page</title>
  <link rel="stylesheet" href="<%=path%>/css/index.css">
</head>
<body>
path = <%=path%><br />
basePath = <%=basePath%>
<hr/>
Welcome Page.<br />

<hr />
<img src="<%=path%>/image/index.jpg" alt="index page image" width="300px" height="300px" /> <br />

<a href="<%=path%>/html/index.html">index test html</a>

<hr />
<a href="<%=path%>/inner/model">Controller 向 JSP 传输数据</a>

<hr />
<a href="data-bind.jsp">Spring MVC 数据封装</a>

<script src="<%=path%>/js/index.js"></script>
</body>
</html>
