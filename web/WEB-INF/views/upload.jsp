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

<title>Upload</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link rel="stylesheet"
	href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap.min.css">

</head>

<body>
	<jsp:include page="nav.jsp"></jsp:include>
	<!-- upload -->
	<div class="upload">
		<!-- container -->
		<div class="container">
			<div class="upload-grids">
				<div class="upload-right">
					<div class="upload-file">
						<form class="form-horizontal" id="uploadVideoForm" action="addVideo" method="post"
							enctype="multipart/form-data">
							<fieldset style=" float:relative;margin-left:40%; width:200px;">
								<div id="legend" class="">
									<legend>添加视频</legend>
								</div>


								<div class="control-group">

									<!-- Text input-->
									<label class="control-label" for="input01">视频名称</label>
									<div class="controls">
										<input type="text" placeholder="如:五月天MV" class="input-xlarge"
											name="video.title" id="title" style="width:200px;">
									</div>
								</div>
								<div class="control-group">
									<!-- Select Basic -->
									<label class="control-label">视频类型</label>
									<div class="controls">
										<select class="input-xlarge" name="video.category.id"
											id="categoryId" style="width:200px;">

										</select>
									</div>

								</div>

								<div class="control-group">
									<label class="control-label">视频文件</label>

									<!-- File Upload -->
									<div class="controls">
										<input class="input-file" id="upload" type="file"
											name="upload">
									</div>
								</div>
								<div class="control-group">
									<label class="control-label">简介</label>

									<!-- File Upload -->
									<div class="controls">
										<input class="input-text" type="text" id="video.info"
											name="video.info" size="31" />
									</div>
								</div>

								<div class="control-group">
									<label class="control-label"></label>

									<!-- Button -->
									<div class="controls">
										<button class="btn btn-success" id="uploadBtn" type="submit" disabled="disabled">提交</button>
									</div>
								</div>

							</fieldset>
						</form>
					</div>
					<div class="upload-info">
						<br>
						<br>
						<h5>选择视频文件上传</h5>
						<span>（支持mp4,avi,flv,3gp,wmv,mpg,asf,asx）格式的视频，另外一定要记得选择好看的封面哦！</span>
						<br> <br>
						<br>
						<br>
						<br>
						<br>
						<br>
						<br>
					</div>
				</div>

				<div class="clearfix"></div>
			</div>
		</div>
		<!-- //container -->
	</div>
	<!-- //upload -->
</body>
<script>
  	getCategoryOption();
  	uploadCheck();
</script>
</html>
