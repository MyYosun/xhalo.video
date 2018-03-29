<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<link rel="stylesheet" href="/css/bootstrap.min.css"/>
<link rel="stylesheet" href="/css/toastr.min.css"/>
<link rel="stylesheet" href="/css/buttons.css"/>
<link rel="stylesheet" href="/css/font-awesome.min.css"/>
<link rel="stylesheet" href="/css/checkbox.css">
<link rel="stylesheet" href="/css/all.css"/>
<script src="/js/jquery-3.3.1.min.js"></script>
<script src="/js/jquery.form.js"></script>
<script src="/js/bootstrap.min.js"></script>
<script src="/js/toastr.min.js"></script>
<script src="/js/sockjs.min.js"></script>
<script src="//cdn.jsdelivr.net/sockjs/1.0.0/sockjs.min.js"></script>
<script src="/js/xhalo-video.js"></script>
<nav id="head" class="navbar navbar-fixed-top navbar-default navbar-static-top" style="margin-bottom: 0;"
     role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse"
                    data-target="#navbar">
                <span class="sr-only">切换导航</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/index" id="head-logo"></a>
        </div>

        <div class="collapse navbar-collapse" id="navbar">

            <ul class="nav navbar-nav navbar-left">
                <li class="active" id="category1">
                    <a href="/category-1.html"><span class="glyphicon glyphicon-facetime-video"></span> Short Video</a>
                </li>
                <li id="category2">
                    <a href="/category-2.html"><span class="glyphicon glyphicon-film"></span> VR (Beta)</a>
                </li>
                <li id="category3">
                    <a href="/category-3.html"><span class="glyphicon glyphicon-music"></span> Music Video</a>
                </li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        <span class="glyphicon glyphicon-blackboard"></span> Other Video
                        <b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu" id="categoryOther">

                    </ul>
                </li>
            </ul>


            <ul class="nav navbar-nav navbar-right">
                <li><a href="/upload"><span class="glyphicon glyphicon-upload"></span> 上传视频</a></li>
                <li><a href="/user-page"><span class="glyphicon glyphicon-user"></span> 个人中心</a></li>
                <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <input id="isAdmin" type="hidden" value="true"/>
                    <li><a href="/admin-page"><span class="glyphicon glyphicon-cog"></span> 管理中心</a></li>
                </sec:authorize>
                <sec:authorize access="!hasRole('ROLE_USER')">
                    <input id="isLogin" type="hidden" value="false"/>
                    <li><a href="/login"><span class="glyphicon glyphicon-log-in"></span> 登录</a></li>
                </sec:authorize>
                <sec:authorize access="hasRole('ROLE_USER')">
                    <input id="isLogin" type="hidden" value="true"/>
                    <li><a href="/logout"><span class="glyphicon glyphicon-log-out"></span> 登出</a></li>
                    <script>
                        getWebSocket();
                    </script>
                </sec:authorize>
            </ul>

            <form class="navbar-form navbar-right" id="search-form" role="search" action="/search">
                <div class="input-group">
                    <input type="text" name="title" id="search-title" class="form-control" placeholder="Search...">
                    <span class="input-group-btn">
                        <button class="btn btn-default" onclick="searchAction()" type="button">搜索</button>
                    </span>
                </div>
            </form>
        </div>
    </div>
</nav>
<script>
    getCategory();

    function searchAction() {
        var searchTitle = $('#search-title').val();
        if (searchTitle == null || searchTitle == undefined || searchTitle == "") {
            toastr.error("请输入搜索内容!");
            return;
        }
        $('#search-form').submit();
    }

    if (isMobile()) {
        $('#head-logo').html('Xhalo-Video');
    } else {
        $('#head-logo').html('<img style="height:130%;" src="/images/logo.png"/>');
    }
</script>
