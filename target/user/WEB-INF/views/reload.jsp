<%@ page import="org.springframework.context.support.AbstractRefreshableApplicationContext" %>
<%@ page import="org.springframework.web.context.WebApplicationContext" %>
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils" %>
<%@ page import="org.springframework.web.context.support.AbstractRefreshableWebApplicationContext" %>
<%@ page import="org.springframework.web.servlet.DispatcherServlet" %>
<%--
  Created by IntelliJ IDEA.
  User: steve
  Date: 4/15/16
  Time: 3:15 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<%
    WebApplicationContext context1 = (WebApplicationContext)request.getAttribute(DispatcherServlet.WEB_APPLICATION_CONTEXT_ATTRIBUTE);
    out.println(context1);
    ((AbstractRefreshableWebApplicationContext)context1).refresh();
%>
</body>
</html>
