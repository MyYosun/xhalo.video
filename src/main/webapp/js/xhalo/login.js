/**登录注册开始**/
function loginAction() {
    var loginUsername = $('#login_username').val();
    var loginPassword = $('#login_password').val();
    if (loginUsername.length < 2 || loginUsername.length > 20) {
        toastr.error("用户名长度为2-20之间!");
        return;
    }
    if (loginPassword.length < 6 || loginPassword.length > 20) {
        toastr.error("密码长度为6-20之间!");
        return;
    }
    $('#loginForm').submit();
}

function registerAction() {
    var registerUsername = $('#register_username').val();
    var registerPassword = $('#register_password').val();
    var registerNickname = $('#register_nickname').val();
    if (registerUsername.length < 2 || registerUsername.length > 20) {
        toastr.error("用户名长度为2-20之间!");
        return;
    }
    if (!(registerUsername[0] >= 'A' && registerUsername[0] <= 'z')) {
        toastr.error("用户名格式不正确!");
        return;
    }
    if (registerPassword.length < 6 || registerPassword.length > 20) {
        toastr.error("密码长度为6-20之间!");
        return;
    }
    if (registerNickname.length > 20 || registerNickname.length < 2) {
        toastr.error("昵称长度为2-20之间!");
        return;
    }
    $('#registerForm').ajaxSubmit({
        url: "/processRegister",
        type: "post",
        success: function (data) {
            if (data == "registerSuccess") {
                toastr.info("注册成功,即将自动登录,请勿关闭页面.");
                setTimeout(function () {
                    $('#login_username').val(registerUsername);
                    $('#login_password').val(registerPassword);
                    loginAction();
                }, 4000);
            } else {
                toastr.error("注册失败,请检查用户信息的格式和长度!");
            }
        }
    });
}

/**登录注册结束**/