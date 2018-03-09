<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<html>
<head>
    <title>Personal Page</title>
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
                                <input type="text" class="form-control" name="nickname" id="user-nickname" placeholder="请输入新昵称">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="user-sign" class="col-sm-2 control-label">个性签名</label>
                            <div class="col-sm-10">
                                <textarea id="user-sign" name="sign" class="form-control" rows="2"></textarea>
                            </div>
                        </div>
                        <button type="button" onclick="updateUserInfoAction()" class="btn btn-default col-sm-offset-2">更新信息</button>
                    </form>
                </div>
                <div class="col-sm-3 col-sm-offset-1" style="text-align:center;">
                    <label >你的头像</label>
                    <div class="col-sm-12" style="margin:10px 0 10px 0;">
                        <a href="javascript:void(0)" onclick="clickFileBtn()"><img id="user-head" class="img-circle" style="height:100px;width:100px;"></a>
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
                                <input type="password" class="form-control" name="password" id="user-password" placeholder="请输入新密码">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="user-password-repeat" class="col-sm-2 control-label">重复密码</label>
                            <div class="col-sm-10">
                                <input type="password" class="form-control" id="user-password-repeat" placeholder="请再次输入新密码">
                            </div>
                        </div>
                        <sec:authorize access="isRememberMe()">
                            <input type="hidden" id="remeber-me-user" value="1">
                        </sec:authorize>
                        <button type="button" onclick="updateUserPasswordAction()" class="btn btn-default col-sm-offset-2">更新密码</button>
                    </form>
                </div>
            </div>
            <div class="tab-pane fade" id="jmeter" style="font-size:16px;margin:30px 0 0 0;">
                <p>jMeter 是一款开源的测试软件。它是 100% 纯 Java 应用程序，用于负载和性能测试。</p>
            </div>
            <div class="tab-pane fade" id="ejb" style="font-size:16px;margin:30px 0 0 0;">
                <p>Enterprise Java Beans（EJB）是一个创建高度可扩展性和强大企业级应用程序的开发架构，部署在兼容应用程序服务器（比如 JBOSS、Web Logic 等）的 J2EE 上。
                </p>
            </div>
        </div>
    </div>
</div>
<jsp:include page="footer.jsp"></jsp:include>
</body>
<script>
    $('#head').removeClass("navbar-fixed-top");
    getPersonalInfo();
</script>
</html>
