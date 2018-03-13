<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Personal Page</title>
    <link href="/css/xhalo-video.css" rel="stylesheet">
</head>
<body>
<jsp:include page="head.jsp"></jsp:include>
<div class="container" style="background-color: #ffffff;border-radius:10px;min-height: 52%;">
    <div class="row clearfix col-sm-10 col-sm-offset-1">
        <ul id="myTab" class="nav nav-tabs nav-pills" style="margin:10px 0 0 0;">
            <li class="active">
                <a href="#home" data-toggle="tab">
                    个人信息
                </a>
            </li>
            <li><a href="#security" data-toggle="tab">修改密码</a></li>
            <li><a href="#jmeter" data-toggle="tab">上传的视频</a></li>
            <li><a href="#ejb" data-toggle="tab">喜欢的视频</a></li>
        </ul>
        <div id="myTabContent" class="tab-content" style="margin: 10px 0 0 0;">
            <div class="tab-pane fade in active" id="home" style="font-size:16px;margin:30px 0 0 0;">
                <div class="col-sm-8">
                    <form class="form-horizontal" role="form" id="userInfoForm">
                        <input type="hidden" name="id" id="user-id" readonly>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">用户名</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" name="username" id="user-username" readonly>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="user-nickname" class="col-sm-2 control-label">昵 称</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" name="nickname" id="user-nickname"
                                       placeholder="请输入新昵称">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="user-sign" class="col-sm-2 control-label">个性签名</label>
                            <div class="col-sm-10">
                                <textarea id="user-sign" name="sign" class="form-control" rows="2"></textarea>
                            </div>
                        </div>
                        <button type="button" onclick="updateUserInfoAction()" class="btn btn-default col-sm-offset-2">
                            更新信息
                        </button>
                    </form>
                </div>
                <div class="col-sm-3 col-sm-offset-1" style="text-align:center;">
                    <label>你的头像</label>
                    <div class="col-sm-12" style="margin:10px 0 10px 0;">
                        <a href="javascript:void(0)" onclick="clickFileBtn()"><img id="user-head" class="img-circle"
                                                                                   style="height:100px;width:100px;"></a>
                    </div>
                    <br>
                    <span class="label label-info" style="margin:20px 0 0 0;">点击图片可更换头像</span>
                </div>
                <input type="file" onchange="updateHeadImg()" accept="image/*" id="upload-head" style="display:none;">
            </div>
            <div class="tab-pane fade" id="security" style="font-size:16px;margin:30px 0 0 0;">
                <div class="col-sm-8">
                    <form class="form-horizontal" role="form" id="userPasswordForm">
                        <input type="hidden" name="id" id="pwd-user-id" readonly>
                        <input type="hidden" name="username" id="pwd-user-username" readonly>
                        <div class="form-group">
                            <label for="user-password" class="col-sm-2 control-label">新密码</label>
                            <div class="col-sm-10">
                                <input type="password" class="form-control" name="password" id="user-password"
                                       placeholder="请输入新密码">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="user-password-repeat" class="col-sm-2 control-label">重复密码</label>
                            <div class="col-sm-10">
                                <input type="password" class="form-control" id="user-password-repeat"
                                       placeholder="请再次输入新密码">
                            </div>
                        </div>
                        <sec:authorize access="isRememberMe()">
                            <input type="hidden" id="remeber-me-user" value="1">
                        </sec:authorize>
                        <button type="button" onclick="updateUserPasswordAction()"
                                class="btn btn-default col-sm-offset-2">更新密码
                        </button>
                    </form>
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
            <div class="tab-pane fade" id="ejb" style="font-size:16px;margin:30px 0 0 0;">
                <div class="col-sm-12">
                    <ul class="list-inline" id="like-list">
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
                                    <li><a href="javascript:void(0)" data-toggle="modal" data-target="#modal-confirm"><i class="fa fa-trash-o fa-lg"></i> 删除</a></li>
                                </ul>
                            </div>
                        </li>--%>
                    </ul>
                </div>
            </div>

            <div class="modal fade" id="modal-confirm" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            <h4 class="modal-title text-center" id="myModalLabel">提示</h4>
                        </div>
                        <div class="modal-body text-center">
                            <p style="font-size:16px;">确认删除??</p>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                            <button type="button" id="confirm-btn" class="btn btn-primary">确认删除</button>
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
    getPersonalInfo();
    getUploadVideoList();
    getLikeVideoList();
</script>
</html>
