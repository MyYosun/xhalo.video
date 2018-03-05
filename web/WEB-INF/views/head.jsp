<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<html>
<head>
    <meta charset="utf-8">
    <title>Head</title>
    <link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
    <script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="/js/index.js"></script>
</head>
<body>
<nav class="navbar navbar-default navbar-fixed-top navbar-static-top" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse"
                    data-target="#navbar">
                <span class="sr-only">切换导航</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/index">Xhalo-Video</a>
        </div>

        <div class="collapse navbar-collapse" id="navbar">

            <ul class="nav navbar-nav navbar-left">
                <li class="active" id="category1">
                    <a href="/category-1.html"><span class="glyphicon glyphicon-facetime-video"></span>  Short Video</a>
                </li>
                <li id="category2">
                    <a href="/category-2.html"><span class="glyphicon glyphicon-film"></span>  Movie</a>
                </li>
                <li id="category3">
                    <a href="/category-3.html"><span class="glyphicon glyphicon-music"></span>   Music Video</a>
                </li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        <span class="glyphicon glyphicon-blackboard"></span>  Other Video
                        <b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu" id="categoryOther">

                    </ul>
                </li>
            </ul>


            <ul class="nav navbar-nav navbar-right">
                <li><a href="#"><span class="glyphicon glyphicon-upload	"></span> 上传视频</a></li>
                <li><a href="#"><span class="glyphicon glyphicon-user"></span> 个人中心</a></li>
                <sec:authorize access="!hasRole('ROLE_USER')">
                    <li><a href="/login"><span class="glyphicon glyphicon-log-in"></span> 登录</a></li>
                </sec:authorize>
                <sec:authorize access="hasRole('ROLE_USER')">
                    <li><a href="/logout"><span class="glyphicon glyphicon-log-out"></span> 登出</a></li>
                </sec:authorize>
            </ul>

            <form class="navbar-form navbar-right" role="search" action="/search">
                <div class="input-group">
                    <input type="text" name="title" class="form-control" placeholder="Search...">
                    <span class="input-group-btn">
                        <button class="btn btn-default" type="submit">搜索</button>
                    </span>
                </div>
            </form>
        </div>
    </div>
</nav>
<script>
    getCategory1();
</script>
</body>
</html>
