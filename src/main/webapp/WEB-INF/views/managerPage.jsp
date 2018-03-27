<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Personal Page</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="icon" href="/images/favicon.ico" type="image/x-icon"/>
    <link rel="shortcut icon" href="/images/favicon.ico" type="image/x-icon"/>
    <link rel="bookmark" href="/images/favicon.ico" type="image/x-icon"/>
    <link href="/css/xhalo-video.css" rel="stylesheet">
    <script src="/js/xhalo/managerPage.js"></script>
    <script src="/js/xhalo/userInfo.js"></script>
</head>
<body>
<jsp:include page="head.jsp"></jsp:include>
<div class="container" style="background-color: #ffffff;border-radius:10px;min-height: 52%;">
    <div class="row clearfix col-sm-10 col-sm-offset-1">
        <ul id="myTab" class="nav nav-tabs nav-pills" style="margin:10px 0 0 0;">
            <li class="active"><a href="#allVideo" data-toggle="tab">所有视频</a></li>
            <li><a href="#categoryManage" data-toggle="tab">新增类别</a></li>
            <li><a href="#otherManage" data-toggle="tab">其他管理</a></li>
        </ul>
        <div id="myTabContent" class="tab-content" style="margin: 10px 0 0 0;">
            <div class="tab-pane fade in active" id="allVideo" style="font-size:16px;margin:30px 0 0 0;">
                <input type="hidden" id="videoPage" value="1"/>
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
                    <div class="col-sm-12" style="text-align:center;margin-top:20px;">
                        <a id="load-btn" href="javascript:void(0);" onclick="adminGetUploadVideoList()"
                           class="button button-caution button-pill button-small">load more</a>
                    </div>
                </div>
            </div>

            <div class="tab-pane fade" id="categoryManage" style="font-size:16px;margin:30px 0 0 0;">
                <div class="col-sm-12">
                    <form class="form-horizontal" role="form" id="categoryInfoForm">
                        <div class="form-group">
                            <label class="col-sm-2 control-label">类别名称</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" name="name" id="categoryName">
                            </div>
                        </div>
                        <button type="button" onclick="addCategoryAction()" class="btn btn-default col-sm-offset-2">
                            增加
                        </button>
                    </form>
                </div>
            </div>

            <div class="tab-pane fade" id="otherManage" style="font-size:16px;margin:30px 0 0 0;">
                <div class="col-sm-12">
                    <div>
                        <label class="col-sm-10 control-label">删除无用视频（无用视频指无标题，无作者，无类别，无视频文件,视频长度小于10秒的视频等）</label>
                        <div class="col-sm-2">
                            <button type="button" class="btn btn-warning" data-toggle="modal"
                                    data-target="#modal-confirm"
                                    onclick="confirmDeleteUselessVideos()">执行
                            </button>
                        </div>
                    </div>
                    <div style="margin-top:50px;">
                        <label class="col-sm-10 control-label">修复视频的缩略图（对于无缩略图有视频文件的视频进行修复，请先执行删除无用视频）</label>
                        <div class="col-sm-2">
                            <button type="button" class="btn btn-info" data-toggle="modal" data-target="#modal-confirm"
                                    onclick="confirmRepairUselessVideos()">执行
                            </button>
                        </div>
                    </div>
                </div>
            </div>

            <div class="modal fade" id="modal-confirm" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
                 aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            <h4 class="modal-title text-center" id="myModalLabel">提示</h4>
                        </div>
                        <div class="modal-body text-center">
                            <p style="font-size:16px;">确认执行???</p>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                            <button type="button" id="confirm-btn" class="btn btn-primary">执行</button>
                        </div>
                    </div>
                </div>
            </div>

        </div>
    </div>
</div>
<jsp:include page="footer.jsp"></jsp:include>
</body>
<script>
    $('#head').removeClass("navbar-fixed-top");
    adminGetUploadVideoList();
</script>
</html>
