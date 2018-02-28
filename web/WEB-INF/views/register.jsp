<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<body>
<form method="post" action="/uploadVideo" enctype="multipart/form-data">
    <ul>
        <li>标题:<input name="title" id="title" type="text"/></li>
        <li>文件:<input name="upload" id="upload" type="file"/></li>
        <li><input id="register" type="submit" value="上传"/></li>
    </ul>
</form>
<sec:authorize access="hasRole('ROLE_ADMIN')">
    sdsds
    <a href="/index">index page</a>

</sec:authorize>
</body>