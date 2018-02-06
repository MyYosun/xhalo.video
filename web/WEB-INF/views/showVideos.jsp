<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title><s:property value="#request['category'].name" /><s:property
		value="#request['title']" /></title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<!-- //fonts -->
</head>
<body>

	<jsp:include page="nav.jsp"></jsp:include>
	<input type="hidden" id="currentPage" value="1" />
	<s:if test="#request['category'] != null">
		<input type="hidden" id="categoryId"
			value="<s:property value="#request['category'].getId()" />" />
	</s:if>
	<s:else>
		<input type="hidden" id="videoTitle"
			value="<s:property value="#request['title']" />" />
	</s:else>
	<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
		<div class="show-top-grids">
			<div class="col-sm-10 show-grid-left main-grids">
				<div class="heading">
					<h3>
						<s:property value="#request['category'].name" />
					</h3>
				</div>
				<div class="recommended" id="videoList"></div>
				<div style="width:100%;">
				 <button id="getMoreBtn" onclick="getResultVideos()" class="button button-rounded button-tiny" style="margin:0 auto;display:block;">加载更多</button>
				</div>
			</div>
		</div>
		<div class="col-md-2 show-grid-right">
			<h3>Upcoming Channels</h3>
			<div class="show-right-grids">
				<ul>
					<li class="tv-img"><a href="#"><img src="images/mv.png"
							alt="" /></a></li>
					<li><a href="#">English Movies</a></li>
				</ul>
			</div>
			<div class="show-right-grids">
				<ul>
					<li class="tv-img"><a href="#"><img src="images/mv.png"
							alt="" /></a></li>
					<li><a href="#">Chinese Movies</a></li>
				</ul>
			</div>
			<div class="show-right-grids">
				<ul>
					<li class="tv-img"><a href="#"><img src="images/mv.png"
							alt="" /></a></li>
					<li><a href="#">Hindi Movies</a></li>
				</ul>
			</div>
			<div class="show-right-grids">
				<ul>
					<li class="tv-img"><a href="#"><img src="images/mv.png"
							alt="" /></a></li>
					<li><a href="#">Telugu Movies</a></li>
				</ul>
			</div>
			<div class="show-right-grids">
				<ul>
					<li class="tv-img"><a href="#"><img src="images/mv.png"
							alt="" /></a></li>
					<li><a href="#">Tamil Movies</a></li>
				</ul>
			</div>
			<div class="show-right-grids">
				<ul>
					<li class="tv-img"><a href="#"><img src="images/mv.png"
							alt="" /></a></li>
					<li><a href="#">Kannada Movies</a></li>
				</ul>
			</div>
			<div class="show-right-grids">
				<ul>
					<li class="tv-img"><a href="#"><img src="images/mv.png"
							alt="" /></a></li>
					<li><a href="#">Marathi movies</a></li>
				</ul>
			</div>
		</div>
		<div class="clearfix"></div>
	</div>
	<div class="clearfix"></div>
	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="js/bootstrap.min.js"></script>
	<!-- Just to make our placeholder images work. Don't actually copy the next line! -->
</body>
<script>
	getResultVideos();
</script>
</html>

