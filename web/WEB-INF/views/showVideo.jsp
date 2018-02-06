<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title><s:property value="#request['video'].title" /></title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link href="css/video-js.min.css" rel="stylesheet">
<script src="js/video.min.js"></script>
</head>
<body>

	<jsp:include page="nav.jsp"></jsp:include>
	<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
		<div class="show-top-grids">
			<div class="col-sm-8 single-left">
				<div class="song" style="width:100%;">
					<div class="song-info">
						<h3>
							<s:property value="#request['video'].title" />
						</h3>
					</div>
					<div class="video-grid" style=" width:100%; position:relative; padding-bottom:56.25%; height: 0;">
						<video id="my-player"
							class="video-js vjs-default-skin vjs-big-play-centered" controls
							preload="auto" data-setup='{}'
							src="videoPlay?videoAddress=<s:property value="#request['video'].address"/>"
							style="position: absolute;top:0;left: 0;width: 100%;height: 100%;">
						<p class="vjs-no-js">
							To view this video please enable JavaScript, and consider
							upgrading to a web browser that <a
								href="http://videojs.com/html5-video-support/" target="_blank">
								supports HTML5 video </a>
						</p>
						</video>
					</div>
				</div>
				<div class="clearfix"></div>
				<div class="published">
					<script src="js/jquery-1.11.1.min.js"></script>
					<script>
								$(document).ready(function () {
									size_li = $("#myList li").size();
									x=1;
									$('#myList li:lt('+x+')').show();
									$('#loadMore').click(function () {
										x= (x+1 <= size_li) ? x+1 : size_li;
										$('#myList li:lt('+x+')').show();
									});
									$('#showLess').click(function () {
										x=(x-1<0) ? 1 : x-1;
										$('#myList li').not(':lt('+x+')').hide();
									});
								});
							</script>
					<div class="load_more">
						<ul id="myList">
							<li>
								<h4>
									Published on
									<s:property value="#request['video'].date" />
								</h4>
								<p>
									<s:property value="#request['video'].info" />
								</p>
							</li>

						</ul>
					</div>
				</div>
			</div>
			<div class="col-md-4 single-right">
				<h3>Most Popular</h3>
				<div class="single-grid-right" id="popularVideos"></div>
			</div>
			<div class="clearfix"></div>
		</div>
	</div>
	<script type="text/javascript">
		getPopular();
	</script>
</body>
</html>
