<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<body>
<form method="post" action="/processLogin">
    <ul>
        <li>用户名:<input name="username" id="username" type="text"/></li>
        <li>密码:<input name="password" id="password" type="text"/></li>
        <li> <input id="remember-me" name="remember-me" type="checkbox" checked="checked"/>自动登录</li>
        <li><input id="login" type="submit" value="登录"/></li>
    </ul>
</form>
<sec:authorize access="hasRole('ROLE_ADMIN')">
sdsds
    <a href="/index">index page</a>

</sec:authorize>
</body>