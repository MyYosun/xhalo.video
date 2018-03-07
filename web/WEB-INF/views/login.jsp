<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" type="text/css" href="/css/login.css"/>
</head>
<body>
<div class="htmleaf-container">

    <div class="login-wrap">
        <div class="login-html">
            <input id="tab-1" type="radio" name="tab" class="sign-in" checked><label for="tab-1" class="tab">登录</label>
            <input id="tab-2" type="radio" name="tab" class="sign-up"><label for="tab-2" class="tab">注册</label>
            <div class="login-form" style="margin:20px 0 0 0;">
                <div class="sign-in-htm">
                    <form>
                    <div class="group">
                        <label for="login_username" class="label">Username</label>
                        <input id="login_username" name="username" type="text" class="input">
                    </div>
                    <div class="group">
                        <label for="login_password" class="label">Password</label>
                        <input id="login_password" name="password" type="password" class="input" data-type="password">
                    </div>
                    <div class="group">
                        <input id="check" name="remember-me" type="checkbox" class="check" checked>
                        <label for="check"><span class="icon"></span> Keep me Signed in</label>
                    </div>
                    <div class="group">
                        <input type="submit" class="button" value="Sign In">
                    </div>
                    </form>
                    <div class="hr"></div>
                    <div class="foot-lnk">
                        <label for="tab-2"/>还未拥有账号?注册一个</a>
                    </div>
                </div>
                <div class="sign-up-htm">
                    <div class="group">
                        <label for="register_username" class="label">Username</label>
                        <input id="register_username" type="text" class="input">
                    </div>
                    <div class="group">
                        <label for="register_password" class="label">Password</label>
                        <input id="register_password" type="password" class="input" data-type="password">
                    </div>
                    <div class="group">
                        <label for="register_nickname" class="label">Email Address</label>
                        <input id="register_nickname" type="text" class="input">
                    </div>
                    <div class="group">
                        <input type="submit" class="button" value="Sign Up">
                    </div>
                    <div class="hr"></div>
                    <div class="foot-lnk">
                        <label for="tab-1"/>已经拥有账号?立即登录</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
