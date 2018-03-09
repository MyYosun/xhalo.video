//方法函数
function formatTime(seconds) {
    return [
        parseInt(seconds / 60),
        parseInt(seconds % 60)
    ]
        .join(":")
        .replace(/\b(\d)\b/g, "0$1");
}

function getQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]);
    return null;
}


//从这里开始
//设置提示框的参数
toastr.options = {
    closeButton: false,
    debug: false,
    progressBar: false,
    positionClass: "toast-bottom-right", //显示位置
    onclick: null,
    showDuration: "300",
    hideDuration: "1000",
    timeOut: "5000",
    extendedTimeOut: "1000",
    showEasing: "swing",
    hideEasing: "linear",
    showMethod: "fadeIn",
    hideMethod: "fadeOut"
};

/**head开始**/
function getCategory() {
    $.ajax({
        type:"get",
        url:"getAllCategories",
        // contentType:"application/json",
        async:true,
        success : showCategory
    });
}

function showCategory(categoryList) {
    var otherVideos = $("#categoryOther");
    for (var i = 0; i < categoryList.length; i++) {
        var category = categoryList[i];
        if(!category.belongToOther){
            continue;
        }
        var id = "category" + (i + 1).toString();
        var li = $("<li></li>");
        otherVideos.append(li);
        li.html("<a href='category-" + category.id + ".html" + "'>" + category.name + "</a>");
    }
}
/**head结束**/

/**通用方法开始**/
function createNewVideoModel(video) {
    var divTop = $("<div></div>");
    divTop.addClass("col-sm-6 col-md-4");

    var divSecond = $("<div></div>");
    divTop.append(divSecond);
    divSecond.addClass("thumbnail");

    var imgAndA = $("<a></a>");
    divSecond.append(imgAndA);
    imgAndA.addClass("lightBox");
    imgAndA.attr("href","/video-" + video.id + ".html");
    var videoImg = $("<img></img>");
    imgAndA.append(videoImg);
    videoImg.attr("src","/showImg?view=" + video.view);
    videoImg.attr("alt",video.title);

    var divInfo = $("<div></div>");
    divSecond.append(divInfo);
    divInfo.addClass("caption");
    var divTitle = $("<div></div>");
    divInfo.append(divTitle);
    divTitle.addClass("video-title");
    var title = $("<h3></h3>");
    divTitle.append(title);
    title.html("<span class='badge'>" + video.click + "views</span> " + video.title + "");
    var divVideoInfo = $("<div></div>");
    divInfo.append(divVideoInfo);
    divVideoInfo.addClass("video-info");
    var videoInfoP = $("<p></p>");
    divVideoInfo.append(videoInfoP);
    videoInfoP.text(video.info);
    var otherInfo = $("<ul></ul>");
    divInfo.append(otherInfo);
    var otherInfoUser = $("<li></li>");
    otherInfo.append(otherInfoUser);
    var userLogo = $("<span></span>");
    otherInfoUser.append(userLogo);
    userLogo.addClass("glyphicon glyphicon-user");
    var userName = $("<span></span>");
    otherInfoUser.append(userName);
    userName.html("<a href='/user-" + video.author.id + "'>" + video.author.nickname + "</a>");
    var otherInfoDuration = $("<li></li>");
    otherInfo.append(otherInfoDuration);
    otherInfoDuration.addClass("right-list");
    var timeLogo = $("<span></span>");
    otherInfoDuration.append(timeLogo);
    timeLogo.addClass("glyphicon glyphicon-time");
    var duration = $("<span></span>");
    otherInfoDuration.append(duration);
    duration.html(formatTime(video.duration));

    return divTop;
}
/**通用方法结束**/

/**主页方法开始**/
function getLatestVideos() {
    $.ajax({
        type:"get",
        url:"getLatestVideos",
        // contentType:"application/json",
        async:true,
        success : showLatestVideos
    });
}

function showLatestVideos(videoList) {
    for(var i = 0; i < videoList.length; i++) {
        var divTop = createNewVideoModel(videoList[i]);
        $('#mostPopular').append(divTop);
    }
}

function getRecommendVideos() {
    $.ajax({
        type:"get",
        url:"getRecommendVideos",
        // contentType:"application/json",
        async:true,
        success : showRecommendVideos
    });
}

function showRecommendVideos(videoList) {
    for(var i = 0; i < videoList.length; i++) {
        var video = videoList[i];
        if(i == 0) {
            $('#carousel-indicators').append($('<li data-target="#myCarousel" data-slide-to="' + i + '" class="active"></li>'));
            var divSingle = $('<div class="item active"></div>');
            divSingle.append($('<a href="/video-' + video.id + '.html"><img src="/showImg?isBig=true&view=' + video.view + '" alt="First slide"></a>'));
            divSingle.append($('<div class="carousel-caption">' + video.title + '</div>'));
            $('#carousel-inner').append(divSingle);
        } else {
            $('#carousel-indicators').append($('<li data-target="#myCarousel" data-slide-to="' + i + '"></li>'));
            var divSingle = $('<div class="item"></div>');
            divSingle.append($('<a href="/video-' + video.id + '.html"><img src="/showImg?isBig=true&view=' + video.view + '" alt="First slide"></a>'));
            divSingle.append($('<div class="carousel-caption">' + video.title + '</div>'));
            $('#carousel-inner').append(divSingle);
        }

    }
}
/**主页方法开始**/

/**视频展示开始**/
//TODO
function likeVideo(btnId) {

}

function lightHeart(btnId) {
    $("#"+btnId).css("color","red");
    $("#"+btnId).children().removeClass("glyphicon-heart-empty");
    $("#"+btnId).children().addClass("glyphicon-heart");
}

function clearHeart(btnId) {
    $("#"+btnId).css("color","black");
    $("#"+btnId).children().removeClass("glyphicon-heart");
    $("#"+btnId).children().addClass("glyphicon-heart-empty");
}

function getPopularList() {
    $.ajax({
        type:"get",
        url:"getRecommendVideos?pageSize=6",
        // contentType:"application/json",
        async:true,
        success : showPopularList
    });
}

function showPopularList(videoList) {
    for(var i = 0; i < videoList.length; i++) {
        var video = videoList[i];
        $("#popular-list").append(createPopularVideo(video));
    }
}

function createPopularVideo(video) {
    var li = $("<li class='video-li'></li>");
    var div_top = $('<div class="media"></div>');
    li.append(div_top);
    var a_img = $('<a class="media-left"></a>');
    div_top.append(a_img);
    a_img.attr("href","video-" + video.id + ".html");
    a_img.append($('<img class="media-object img-rounded video-img" src="/showImg?view=' + video.view + '" alt="head">'));
    var div_info = $('<div class="media-body"></div>');
    div_top.append(div_info);
    div_info.append($('<p class="media-heading video-title">' + video.title + '</p>'));
    var info_p = $("<p></p>");
    div_info.append(info_p);
    info_p.append($('<span class="video-info">' + video.author.nickname + '</span>'));
    info_p.append($('<br>'));
    info_p.append($('<span class="video-info">' + video.click + '次观看</span>'));
    return li;
}
/**视频展示结束**/

/**视频结果展示开始**/
//视频搜索
function getResultVideos(isReload) {
	$("#load-btn").button("loading");
    $("#load-btn").attr("onclick","javascript:void(0)");
    var videoTitle = $('#video-title').val();
    var categoryId = $('#category-id').val();
    var pageNum = $('#pageNum').val();
    if(isReload){
        $('#resultVideo').html("");
        pageNum = 1;
    }
    var option_duration = $('input[name="radio"]:checked').val();
    var option_order = $('input[name="radio_order"]:checked').val();
    var optionDuration = "all";
    var optionOrder = "date";
    switch (option_duration) {
        case "0" : optionDuration = "all";break;
        case "1" : optionDuration = "short";break;
        case "2" : optionDuration = "medium";break;
        case "3" : optionDuration = "long";break;
        case "4" : optionDuration = "other";break;
    }
    switch (option_order) {
        case "0" : optionOrder = "date";break;
        case "1" : optionOrder = "duration";break;
        case "2" : optionOrder = "click";break;
    }
    if(videoTitle != "" && videoTitle != undefined && videoTitle != null)
        var url = "getVideosByTitleAndPage?title=" + videoTitle;
    if(categoryId != "" && categoryId != undefined && categoryId != null)
        var url = "getVideosByCategoryAndPage?category.id=" + categoryId;
    url += "&pageNum=" + pageNum + "&optionDuration=" + optionDuration + "&optionOrder=" + optionOrder;
    $.ajax({
        type: "get",
        url: url,
		// contentType:"application/json;charset=utf-8",
        async: true,
        success : showResultVideos
    });
}

function showResultVideos(videoList) {
    for(var i = 0; i < videoList.length; i++) {
        var divTop = createNewVideoModel(videoList[i]);
        $('#resultVideo').append(divTop);
    }
    toastr.info("加载完成");
    $('#pageNum').val(parseInt($('#pageNum').val()) + 1);
    $("#load-btn").button("reset");
    if(videoList.length < 9) {
        $("#load-btn").attr("onclick", "javascript:void(0)");
    }
    else {
        $("#load-btn").attr("onclick", "getResultVideos()");
    }
}
/**视频结果展示结束**/

/**登录注册开始**/
function loginAction() {
    var loginUsername = $('#login_username').val();
    var loginPassword = $('#login_password').val();
    if(loginUsername.length < 2 || loginUsername.length > 20) {
        toastr.error("用户名长度为2-20之间!");
        return;
    }
    if(loginPassword.length < 6 || loginPassword.length > 20) {
        toastr.error("密码长度为6-20之间!");
        return;
    }
    $('#loginForm').submit();
}

function registerAction() {
    var registerUsername = $('#register_username').val();
    var registerPassword = $('#register_password').val();
    var registerNickname = $('#register_nickname').val();
    if(registerUsername.length < 2 || registerUsername.length > 20) {
        toastr.error("用户名长度为2-20之间!");
        return;
    }
    if(!(registerUsername[0] >= 'A' && registerUsername[0] <= 'z')){
        toastr.error("用户名格式不正确!");
        return;
    }
    if(registerPassword.length < 6 || registerPassword.length > 20) {
        toastr.error("密码长度为6-20之间!");
        return;
    }
    if(registerNickname.length > 20 || registerNickname.length < 2) {
        toastr.error("昵称长度为2-20之间!");
        return;
    }
    $('#registerForm').ajaxSubmit({
        url: "/processRegister",
        type: "post",
        success: function (data) {
            if(data == "registerSuccess") {
                toastr.info("注册成功,即将自动登录,请勿关闭页面.");
                setTimeout(function() {
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

/**上传开始**/
function loadCategories() {
    $.ajax({
        type:"get",
        url:"getAllCategories",
        // contentType:"application/json",
        async:true,
        success : showUploadCategory
    });
}

function showUploadCategory(categoryList) {
    $.each(categoryList,function(k, category) {
        $('#category-list').append($('<li><a href="javascript:void(0)" onclick="changeSelectedCategory(' +
            category.id + ',\'' + category.name + '\')">' + category.name + '</a></li>'));
    });
    changeSelectedCategory(categoryList[0].id, categoryList[0].name);
}

function changeSelectedCategory(categoryId, categoryName) {
    $('#category-name').val(categoryName);
    $('#category-id').val(categoryId);
}

function uploadAction() {
    var videoTitle = $('#video-title').val();
    var videoInfo = $('#video-info').val();
    var filePath = $('#upload-file').val();
    if(filePath == null || filePath == "" || filePath == undefined) {
        toastr.error("请选择视频");
        return;
    }
    if(videoTitle.length < 2 || videoTitle.length > 30) {
        toastr.error("上传视频标题的长度为2-30之间!");
        $('#video-title').focus();
        return;
    }
    if(videoInfo.length > 200) {
        toastr.error("视频简介的长度应小于200!");
        $('#video-info').focus();
        return;
    }
    var extStart = filePath.lastIndexOf(".");
    var ext = filePath.substring(extStart, filePath.length).toUpperCase();
    if(ext != ".MP4") {
        toastr.error("上传视频格式不正确!");
        return;
    }
    $('#upload-form').ajaxSubmit({
        url: "uploadVideo",
        type: "post",
        beforeSubmit: function() {
            $('#myModal').modal({backdrop:"static",
                keyboard: false});
        },
        success: function(data) {
            $('#myModal').modal("hide");
            if(data == "formatError") {
                toastr.info("视频信息填写错误!");
            }else if(data == "uploadFail") {
                toastr.info("上传失败，请检查相应的视频信息和文件格式与大小!");
            }else {
                toastr.info("上传成功,即将前往主页...");
                setTimeout(function() {
                    window.location.href = "/index";
                }, 4000);
            }
        }
    });
    return false;
}
/**上传结束**/

/**个人中心开始**/
function getPersonalInfo() {
    $.ajax({
        type: "get",
        url: "getLoginUserInfo",
        // contentType:"application/json;charset=utf-8",
        async: true,
        success : showPersonalInfo
    });
}

function showPersonalInfo(userInfo) {
    $('#user-id').val(userInfo.id);
    $('#pwd-user-id').val(userInfo.id);
    $('#user-username').val(userInfo.username);
    $('#pwd-user-username').val(userInfo.username);
    $('#user-nickname').val(userInfo.nickname);
    $('#user-sign').val(userInfo.sign);
    $('#user-head').attr("src", "/showImg?isHead=true&view=" + userInfo.headImg);
}

function updateUserInfoAction() {
    var userNickname = $('#user-nickname').val();
    var userSign = $('#user-sign').val();
    if(userNickname.length < 2 || userNickname.length > 20) {
        toastr.error("昵称长度为2-20之间!");
        return;
    }
    if(userSign.length > 50) {
        toastr.error("签名的长度应小于50!");
        return;
    }
    $('#userInfoForm').ajaxSubmit({
        url: "updateLoginUserInfo",
        type: "post",
        success: function (data) {
            if(data == "userNotLogin") {
                toastr.error("请先登录!");
                return;
            }
            if(data == "usernameNotMatch") {
                toastr.error("用户名不匹配!");
                return;
            }
            if(data == "updateUserInfoFail") {
                toastr.error("修改失败!");
                return;
            }
            if(data == "updateUserInfoSuccess") {
                toastr.info("修改成功!");
                getPersonalInfo();
            }
        }
    });
}

function clickFileBtn() {
    $('#upload-head').click();
}

function updateHeadImg() {
    var filePath = $('#upload-head').val();
    var extStart = filePath.lastIndexOf(".");
    var ext = filePath.substring(extStart, filePath.length).toUpperCase();
    if(ext != ".PNG" && ext != ".JPG" && ext != ".JPEG" && ext != ".GIF" && ext != ".BMP") {
        toastr.error("上传图片格式不正确!");
        return;
    }
    var data = new FormData();
    data.append("upload", document.getElementById('upload-head').files[0]);
    data.append("id", $('#user-id').val());
    data.append("username", $('#user-username').val());
    $.ajax({
        url: "updateLoginUserHeadImg",
        type: "post",
        cache: false,
        data: data,
        /**
         *必须false才会自动加上正确的Content-Type
         */
        contentType: false,
        /**
         * 必须false才会避开jQuery对 formdata 的默认处理
         * XMLHttpRequest会对 formdata 进行正确的处理
         */
        processData: false,
        success: function(data) {
            if(data == "userNotLogin") {
                toastr.error("请先登录!");
                return;
            }
            if(data == "usernameNotMatch") {
                toastr.error("用户名不匹配!");
                return;
            }
            if(data == "updateUserHeadImgFail") {
                toastr.error("修改失败!");
                return;
            }
            if(data == "updateUserHeadImgSuccess") {
                toastr.info("修改成功!");
                getPersonalInfo();
            }
        }
    });
}

function updateUserPasswordAction() {
    if($('#remeber-me-user').val() == 1) {
        toastr.error("请先验证身份");
        setTimeout(function() {
            window.location.href = "/login";
        }, 2000);
        return;
    }
    var userNewPsd = $('#user-password').val();
    var userNewPsdRe = $('#user-password-repeat').val();
    if(userNewPsd.length > 20 || userNewPsd.length < 6) {
        toastr.error("密码长度为6-20之间!");
        return;
    }
    if(userNewPsdRe != userNewPsd) {
        toastr.error("两次密码输入不一致!");
        return;
    }
    $('#userPasswordForm').ajaxSubmit({
        url: "updateLoginUserPassword",
        type: "post",
        success: function(data) {
            if(data == "userNotLogin") {
                toastr.error("请先登录!");
                return;
            }
            if(data == "usernameNotMatch") {
                toastr.error("用户名不匹配!");
                return;
            }
            if(data == "updateUserPasswordFail") {
                toastr.error("修改失败!");
                return;
            }
            if(data == "updateUserPasswordSuccess") {
                toastr.info("修改成功!");
                getPersonalInfo();
            }
        }
    });
}


