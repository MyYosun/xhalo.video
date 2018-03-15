<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>${author.nickname}的频道</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="icon" href="/images/favicon.ico" type="image/x-icon"/>
    <link rel="shortcut icon" href="/images/favicon.ico" type="image/x-icon"/>
    <link rel="bookmark" href="/images/favicon.ico" type="image/x-icon"/>
    <link href="/css/xhalo-video.css" rel="stylesheet">
</head>
<body>
<jsp:include page="head.jsp"></jsp:include>
<input type="hidden" value="${author.id}" id="authorId"/>
<div class="container" style="background-color: #ffffff;border-radius:10px;min-height: 52%;">
    <div class="row clearfix col-sm-10 col-sm-offset-1">
        <ul id="myTab" class="nav nav-tabs nav-pills" style="margin:10px 0 0 0;">
            <li class="active">
                <a href="#home" data-toggle="tab">他的个人信息</a>
            </li>
            <li><a href="#jmeter" data-toggle="tab">他上传的视频</a></li>
        </ul>
        <div id="myTabContent" class="tab-content" style="margin: 10px 0 0 0;">
            <div class="tab-pane fade in active" id="home" style="font-size:16px;margin:30px 0 0 0;">
                <div class="col-sm-8">
                    <form class="form-horizontal" role="form" id="userInfoForm">
                        <div class="form-group">
                            <label class="col-sm-2 control-label">昵 称</label>
                            <div class="col-sm-10">
                                <p class="form-control-static">${author.nickname}</p>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">个性签名</label>
                            <div class="col-sm-10">
                                <p class="form-control-static">${author.sign}</p>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="col-sm-3 col-sm-offset-1" style="text-align:center;">
                    <label>他的头像</label>
                    <div class="col-sm-12" style="margin:10px 0 10px 0;">
                        <img id="user-head" src="/showImg?isHead=true&view=${author.headImg}" class="img-circle"
                             style="height:100px;width:100px;">
                    </div>
                </div>
            </div>
            <div class="tab-pane fade" id="jmeter" style="font-size:16px;margin:30px 0 0 0;">
                <div class="col-sm-12">
                    <ul class="list-inline" id="upload-list">
                        <!--下列列表由jquery动态生成，以下为单例示例-->
                        <%--<li class="col-sm-3">
                            <div class="col-sm-12">
                                <img class="media-object img-rounded video-img-detail"
                                     src="/showImg?view=123qqqqcfcfba869d7cf8ed654a3aa676eebbd1.jpg"
                                     alt="head">
                                <div>
                                    <p class="media-heading video-title-detail">
                                        <span class="badge">5views</span> 这是视频标题
                                    </p>
                                </div>
                                <ul class="list-inline" style="font-size:12px;">
                                    <li><a href="javascript:void(0);" data-toggle="modal" data-target="#modal-confirm"><i class="fa fa-trash-o fa-lg"></i> 删除</a></li>
                                </ul>
                            </div>
                        </li>--%>
                    </ul>
                </div>
            </div>

        </div>
    </div>
</div>
<jsp:include page="footer.jsp"></jsp:include>
</body>
<script>
    $('#head').removeClass("navbar-fixed-top");
    getAuthorUploadVideoList();
</script>
</html>
