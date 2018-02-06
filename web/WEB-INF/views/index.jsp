<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>主页</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<script type="application/x-javascript">addEventListener("load", function() {
		setTimeout(hideURLbar, 0);
	}, false);
	function hideURLbar() {
		window.scrollTo(0, 1);
	}
</script>
<!-- bootstrap -->
<link href="css/bootstrap.min.css" rel='stylesheet' type='text/css'
	media="all" />
<!-- //bootstrap -->
<link href="css/dashboard.css" rel="stylesheet">
<!-- Custom Theme files -->
<link href="css/style.css" rel='stylesheet' type='text/css' media="all" />
<script src="js/jquery-1.11.1.min.js"></script>
<script src="js/index.js"></script>
<script src="js/responsiveslides.min.js"></script>
</head>
<body>
	<jsp:include page="nav.jsp"></jsp:include>
	<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
		<div class="main-grids">
			<div class="top-grids">
				<div class="recommended-info" id="recommandTitle1">
					<h3>最新上传</h3>
					<div class="animated-grids" id="latestVideoDiv"></div>
				</div>
			</div>
			<div class="recommended">
				<div class="recommended-grids">
					<div class="recommended-info" id="recommandTitle1">
						<h3>蜡笔小新</h3>
					</div>
					<div class="animated-grids" id="recommand1"></div>
				</div>
			</div>
			<div class="recommended">
				<div class="recommended-grids">
					<div class="recommended-info" id="recommandTitle2">
						<h3>五月天</h3>
					</div>
					<div class="animated-grids" id="recommand2"></div>
				</div>
			</div>
			<div class="recommended">
				<div class="recommended-grids">
					<div class="recommended-info" id="recommandTitle3">
						<h3>周杰伦</h3>
					</div>
					<div class="animated-grids" id="recommand3"></div>
				</div>
			</div>
		</div>
		<!-- footer -->
		<div class="footer">
			<div class="footer-grids">
				<div class="footer-top">
					<div class="footer-top-nav">
						<ul>
							<li class="languages"><span style="color:#eee;">Name</span>
							</li>
							<li class="languages"><span style="color:#eee;">&nbsp;&nbsp;&nbsp;&nbsp;QQ</span>
							</li>
						</ul>
					</div>
					<div class="footer-bottom-nav">
						<ul>
							<li class="languages"><span style="color:#eee;">@Yosun</span>
							</li>
							<li class="languages"><span style="color:#eee;">602704853</span>
							</li>
						</ul>
					</div>
				</div>
			</div>
		</div>
		<!-- //footer -->
	</div>
	<div class="clearfix"></div>
	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="js/bootstrap.min.js"></script>
	<!-- Just to make our placeholder images work. Don't actually copy the next line! -->
</body>
<script type="text/javascript">
	getIndex();
</script>
</html>
