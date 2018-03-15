<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Upload Video</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="icon" href="/images/favicon.ico" type="image/x-icon"/>
    <link rel="shortcut icon" href="/images/favicon.ico" type="image/x-icon"/>
    <link rel="bookmark" href="/images/favicon.ico" type="image/x-icon"/>
</head>
<body>
<jsp:include page="head.jsp"></jsp:include>
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <button style="display:none;" type="button" class="close" data-dismiss="modal" aria-hidden="true">
                &times;
            </button>

            <div class="modal-body">
                正在上传，请不要关闭界面并耐心等待...
            </div>

        </div>
    </div>
</div>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-6 column col-md-offset-3" style="background-color: white; border-radius: 10px;">
            <h3 class="text-center">
                上传视频
            </h3>
            <hr>
            <form role="form" id="upload-form" method="post">
                <div class="form-group">
                    <label for="video-title">视频标题</label>
                    <input type="text" name="title" class="form-control" id="video-title"/>
                </div>
                <div class="form-group">
                    <div class="input-group">
                        <div class="input-group-btn">
                            <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                                视频类别
                                <span class="caret"></span>
                            </button>
                            <ul class="dropdown-menu" id="category-list">
                                <!--使用jquery生成，下为单例-->
                                <%--<li><a href="#">类别1</a></li>--%>
                            </ul>
                        </div>
                        <input type="text" id="category-name" class="form-control" readonly="true">
                        <input type="hidden" id="category-id" name="category.id" class="form-control" value="1">
                    </div>
                </div>
                <div class="form-group">
                    <label for="video-info">视频简介</label>
                    <textarea id="video-info" name="info" class="form-control" rows="3"></textarea>
                </div>
                <div class="form-group">
                    <label for="upload-file">视频文件</label>
                    <input type="file" accept="video/mp4" name="upload" id="upload-file"/>
                    <p class="help-block">
                        支持上传1GB以内的视频文件,只允许上传mp4格式视频哦!
                    </p>
                </div>
                <button type="button" id="uploadBtn" onclick="uploadAction()" class="btn btn-default">上传视频</button>
            </form>
        </div>
    </div>
</div>
<jsp:include page="footer.jsp"></jsp:include>
</body>
<script>
    $('#head').removeClass("navbar-fixed-top");
    loadCategories();
</script>
</html>
