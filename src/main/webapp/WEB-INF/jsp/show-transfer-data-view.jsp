<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8" http-equiv="Content-Type" content="text/html;charset=UTF-8"/>
    <title>show transfer data view</title>
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
Model 来传输数据：<br />
model data : ${model}
<hr />

Model Map 来传输数据：<br />
model map data : ${modelmap}
<hr />

Model And View 来传输数据：<br />
Model And View data : ${modelAndView}
<hr />

Hash Map 来传输数据：<br />
Hash Map data : ${hashMap}
<hr />

Http Session 来传输数据：<br />
Http Session data : ${httpSession}
<hr />

Http Request 来传输数据：<br />
Http Request data : ${httpRequest}
<hr />

session id : <%=session.getId()%>
</body>
</html>
