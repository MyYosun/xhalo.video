<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<base href="<%=basePath%>">

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script src="js/jquery-3.2.1.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
	<!-- bootstrap -->
	<link href="css/bootstrap.min.css" rel='stylesheet' type='text/css'
		  media="all" />
	<link href="css/dashboard.css" rel="stylesheet">
	<link href="css/style.css" rel='stylesheet' type='text/css' media="all" />
	<link href="css/button.css" rel='stylesheet' type='text/css'>
	<link href="css/css.css" rel='stylesheet' type='text/css'>
	<script src="js/index.js"></script>
	<link href="css/popuo-box.css" rel="stylesheet" type="text/css"
		  media="all" />

</head>

<body>

<nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navbar" aria-expanded="false"
					aria-controls="navbar">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="index"><h1>
				<img src="images/logo.png" alt="" />
			</h1></a>
		</div>
		<div id="navbar" class="navbar-collapse collapse">
			<div class="top-search">
				<form class="navbar-form navbar-right" action="/search"
					  method="post">
					<input type="text" class="form-control" placeholder="Search..."
						   name="title" /> <input type="submit" value=" " />
				</form>
			</div>
			<div class="header-top-right">
				<div class="file">
					<a href="upload">上 传</a>
				</div>
				<div class="signin" id="registBtn">
					<a href="javascript:void(0);" onclick="openLoginModal();" class="play-icon popup-with-zoom-anim">注
						册</a>
				</div>
				<div class="signin" id="loginBtn">
					<sec:authorize access="!hasRole('ROLE_USER')">

						<a href="javascript:return;"
						   class="play-icon popup-with-zoom-anim">登 录</a>
					</sec:authorize>
					<sec:authorize access="hasRole('ROLE_USER')">

						<a href="/logout" class="play-icon popup-with-zoom-anim">登 出</a>
					</sec:authorize>
					<div class="clearfix"></div>
				</div>
			</div>
			<div class="clearfix"></div>
		</div>
	</div>
</nav>



<div class="col-sm-3 col-md-2 sidebar">
	<div class="top-navigation">
		<div class="t-menu">MENU</div>
		<div class="t-img">
			<img src="images/lines.png" alt="" />
		</div>
		<div class="clearfix"> </div>
	</div>
	<div class="drop-navigation drop-navigation">
		<ul class="nav nav-sidebar">
			<li class="active"><a href="index.html" class="home-icon"><span
					class="glyphicon glyphicon-home" aria-hidden="true"></span>主页</a></li>
			<li id="category1"></li>
			<li><a href="#" onclick="return false" class="menu1"><span
					class="glyphicon glyphicon-music" aria-hidden="true"></span>音乐视频<span
					class="glyphicon glyphicon-menu-down" aria-hidden="true"></span></a></li>
			<ul class="cl-effect-2">
				<li id="category2"></li>
				<li id="category3"></li>
				<li id="category4"></li>
			</ul>
			<li><a href="#" onclick="return false" class="menu"><span
					class="glyphicon glyphicon-film" aria-hidden="true"></span>其他类别<span
					class="glyphicon glyphicon-menu-down" aria-hidden="true"></span></a></li>
			<ul class="cl-effect-1">
				<li id="category5"></li>
				<li id="category6"></li>
				<li id="category7"></li>
				<li id="category8"></li>
			</ul>
			<li><a href="http://www.facebook.com/myyosun" class="news-icon"><span
					class="glyphicon glyphicon-envelope" aria-hidden="true"></span>联系我吧</a></li>
		</ul>
		<!-- script-for-menu -->
		<script>
            $( "li a.menu1" ).click(function() {
                $( "ul.cl-effect-2" ).slideToggle( 300, function() {
                });
            });
		</script>
		<script>
            $( "li a.menu" ).click(function() {
                $( "ul.cl-effect-1" ).slideToggle( 300, function() {
                });
            });
		</script>
		<script>
            $( ".top-navigation" ).click(function() {
                $( ".drop-navigation" ).slideToggle( 300, function() {
                });
            });
		</script>
	</div>
</div>
</body>
<script>
    getCategory();
</script>
</html>
