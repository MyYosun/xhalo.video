$(function () {
    H_login = {};
    H_login.openLogin = function(){
        $('#loginBtn a').click(function(){
            $('.login').show();
            $('.login-bg').show();
        });
        $('#registBtn a').click(function(){
            $('.regist').show();
            $('.regist-bg').show();
        });
    };
    H_login.closeLogin = function(){
        $('.close-login').click(function(){
            $('.login').hide();
            $('.login-bg').hide();
        });
        $('.close-regist').click(function(){
            $('.regist').hide();
            $('.regist-bg').hide();
        });
    };
    H_login.run = function () {
        this.closeLogin();
        this.openLogin();
    };
    H_login.run();
});