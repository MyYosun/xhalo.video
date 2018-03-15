<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="icon" href="/images/favicon.ico" type="image/x-icon"/>
    <link rel="shortcut icon" href="/images/favicon.ico" type="image/x-icon"/>
    <link rel="bookmark" href="/images/favicon.ico" type="image/x-icon"/>
    <link rel="stylesheet" type="text/css" href="/css/login.css"/>
    <link rel="stylesheet" href="/css/toastr.min.css"/>
    <script src="/js/jquery-3.3.1.min.js"></script>
    <script src="/js/toastr.min.js"></script>
    <script src="/js/jquery.form.js"></script>
    <script src="/js/xhalo-video.js"></script>
</head>
<body>
<div class="htmleaf-container">
    <div class="login-wrap">
        <div class="login-html">
            <input id="tab-1" type="radio" name="tab" class="sign-in" checked><label for="tab-1" class="tab">登录</label>
            <input id="tab-2" type="radio" name="tab" class="sign-up"><label for="tab-2" class="tab">注册</label>
            <div class="login-form" style="margin:20px 0 0 0;">
                <div class="sign-in-htm">
                    <form action="/processLogin" id="loginForm" method="post">
                        <div class="group">
                            <label for="login_username" class="label">Username</label>
                            <input id="login_username" name="username" type="text" class="input">
                        </div>
                        <div class="group">
                            <label for="login_password" class="label">Password</label>
                            <input id="login_password" name="password" type="password" class="input"
                                   data-type="password">
                        </div>
                        <div class="group">
                            <input id="check" name="remember-me" type="checkbox" class="check" value="false">
                            <label for="check"><span class="icon"></span> Keep me Signed in</label>
                        </div>
                    </form>
                    <div class="group">
                        <input type="button" onclick="loginAction()" class="button" value="Sign In">
                    </div>
                    <div class="hr"></div>
                    <div class="foot-lnk">
                        <label for="tab-2"><a>还未拥有账号?注册一个</a></label><br>
                        <a style="font-size:36px;" class="htmleaf-icon icon-htmleaf-home-outline" href="/index"></a>
                    </div>
                </div>
                <div class="sign-up-htm">
                    <form id="registerForm" method="post">
                        <div class="group">
                            <label for="register_username" class="label">Username</label>
                            <input id="register_username" name="username" type="text" class="input">
                        </div>
                        <div class="group">
                            <label for="register_password" class="label">Password</label>
                            <input id="register_password" name="password" type="password" class="input"
                                   data-type="password">
                        </div>
                        <div class="group">
                            <label for="register_nickname" class="label">Nickname</label>
                            <input id="register_nickname" name="nickname" type="text" class="input">
                        </div>
                    </form>
                    <div class="group">
                        <input type="button" disabled id="register_btn" onclick="registerAction()" class="button"
                               value="Sign Up">
                    </div>
                    <div class="hr"></div>
                    <div class="foot-lnk">
                        <label for="tab-1"/><a>已经拥有账号?立即登录</a><br>
                        <a style="font-size:36px;" class="htmleaf-icon icon-htmleaf-home-outline" href="/index"></a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script>
    if (getQueryString("error")) {
        toastr.error("账号密码不匹配!");
    }
    $('#check').on("change", function () {
        if ($('#check').val() == "true")
            $('#check').val(false);
        else
            $('#check').val(true);
    });

    //实时验证用户名是否被注册
    $('#register_username').blur(function (e) {
        $.ajax({
            url: "validateUsername?username=" + $('#register_username').val(),
            type: "get",
            success: function (data) {
                if (data == "userExist") {
                    toastr.error("用户名已被注册!");
                    $('#register_username').focus();
                    $('#register_btn').attr("disabled", true);
                } else {
                    $('#register_btn').attr("disabled", false);
                }
            }
        });
    });

</script>
</html>
