/**个人中心开始**/
function getPersonalInfo() {
    $.ajax({
        type: "get",
        url: "getLoginUserInfo",
        // contentType:"application/json;charset=utf-8",
        async: true,
        success: showPersonalInfo
    });
}

function showPersonalInfo(data) {
    var userInfo = eval('(' + data + ')');
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
    if (userNickname.length < 2 || userNickname.length > 20) {
        toastr.error("昵称长度为2-20之间!");
        return;
    }
    if (userSign.length > 50) {
        toastr.error("签名的长度应小于50!");
        return;
    }
    $('#userInfoForm').ajaxSubmit({
        url: "updateLoginUserInfo",
        type: "post",
        success: function (data) {
            if (data == "userNotLogin") {
                toastr.error("请先登录!");
                return;
            }
            if (data == "usernameNotMatch") {
                toastr.error("用户名不匹配!");
                return;
            }
            if (data == "updateUserInfoFail") {
                toastr.error("修改失败!");
                return;
            }
            if (data == "updateUserInfoSuccess") {
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
    if (ext != ".PNG" && ext != ".JPG" && ext != ".JPEG" && ext != ".GIF" && ext != ".BMP") {
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
        success: function (data) {
            if (data == "userNotLogin") {
                toastr.error("请先登录!");
                return;
            }
            if (data == "usernameNotMatch") {
                toastr.error("用户名不匹配!");
                return;
            }
            if (data == "updateUserHeadImgFail") {
                toastr.error("修改失败!");
                return;
            }
            if (data == "updateUserHeadImgSuccess") {
                toastr.info("修改成功!");
                getPersonalInfo();
            }
        }
    });
}

function updateUserPasswordAction() {
    if ($('#remeber-me-user').val() == 1) {
        toastr.error("请先验证身份");
        setTimeout(function () {
            window.location.href = "/login";
        }, 2000);
        return;
    }
    var userNewPsd = $('#user-password').val();
    var userNewPsdRe = $('#user-password-repeat').val();
    if (userNewPsd.length > 20 || userNewPsd.length < 6) {
        toastr.error("密码长度为6-20之间!");
        return;
    }
    if (userNewPsdRe != userNewPsd) {
        toastr.error("两次密码输入不一致!");
        return;
    }
    $('#userPasswordForm').ajaxSubmit({
        url: "updateLoginUserPassword",
        type: "post",
        success: function (data) {
            if (data == "userNotLogin") {
                toastr.error("请先登录!");
                return;
            }
            if (data == "usernameNotMatch") {
                toastr.error("用户名不匹配!");
                return;
            }
            if (data == "updateUserPasswordFail") {
                toastr.error("修改失败!");
                return;
            }
            if (data == "updateUserPasswordSuccess") {
                toastr.info("修改成功!");
                $('#userPasswordForm').resetForm();
            }
        }
    });
}

function getUploadVideoList() {
    var pageNum = $('#uploadVideoPage').val();
    $.ajax({
        url: "getUserUploadVideos?pageSize=12&pageNum=" + pageNum,
        type: "get",
        success: showUploadVideoList,
        async: true
    });
}

function showUploadVideoList(data) {
    var videoList = eval('(' + data + ')');
    for (var i = 0; i < videoList.length; i++) {
        var video = videoList[i];
        $('#upload-list').append(createLittleVideo(video, "upload", true));
    }
    if (videoList == null || videoList == "" || videoList.length < 12) {
        $('#upload-load-btn').attr("onclick", "javascript:void(0);");
        $('#upload-load-btn').addClass("disabled");
    }
    $('#uploadVideoPage').val(parseInt($('#uploadVideoPage').val()) + 1);
}

function getLikeVideoList() {
    var pageNum = $('#likeVideoPage').val();
    $.ajax({
        url: "getUserLikeVideos?pageSize=12&pageNum=" + pageNum,
        type: "get",
        success: showLikeVideoList,
        async: true
    });
}

function showLikeVideoList(data) {
    var videoList = eval('(' + data + ')');
    for (var i = 0; i < videoList.length; i++) {
        var video = videoList[i];
        $('#like-list').append(createLittleVideo(video, "like", true));
    }
    if (videoList == null || videoList == "" || videoList.length < 12) {
        $('#like-load-btn').attr("onclick", "javascript:void(0);");
        $('#like-load-btn').addClass("disabled");

    }
    $('#likeVideoPage').val(parseInt($('#likeVideoPage').val()) + 1);
}

function createLittleVideo(video, which, isDelete) {
    var whichVideo = '';
    var deleteTip = '';
    if (which == "upload") {
        whichVideo = 'uploadVideo';
        deleteTip = '删除';
    }
    if (which == "like") {
        whichVideo = 'likeVideo';
        deleteTip = '移除';
    }
    if (which == "admin") {
        whichVideo = 'adminVideo';
        deleteTip = '删除';
    }
    var liTop = $("<li class='col-sm-3' style='margin-top:20px;' id='" + whichVideo + video.id + "'></li>");
    var divTop = $("<div class='col-sm-12'></div>");
    liTop.append(divTop);
    var aTop = $("<a href='video-" + video.id + ".html' style='text-decoration:none;'></a>");
    divTop.append(aTop);
    aTop.append($('<img class="media-object img-rounded video-img-detail"' +
        ' src="/showImg?view=' + video.view + '">'));
    var divTitle = $('<div></div>');
    aTop.append(divTitle);
    divTitle.append($('<p class="media-heading video-title-detail">' +
        '<span class="badge">' + video.click + 'views</span> ' + video.title +
        '</p>'));
    if (isDelete == true) {
        var ul = $('<ul class="list-inline" style="font-size:12px;"></ul>');
        divTop.append(ul);
        var li = $('<li></li>');
        ul.append(li);
        li.append($('<a href="javascript:void(0)" onclick="deleteInfo(\'' + which + '\',' + video.id + ')" data-toggle="modal" data-target="#modal-confirm">' +
            '<i class="fa fa-trash-o fa-lg"></i> ' + deleteTip + '</a>'));
    }
    return liTop;
}

function deleteInfo(which, id) {
    if (which == "upload")
        $('#confirm-btn').attr("onclick", "deleteUploadVideo(" + id + ")");
    if (which == "like")
        $('#confirm-btn').attr("onclick", "deleteLikeVideo(" + id + ")");
    if (which == "admin")
        $('#confirm-btn').attr("onclick", "deleteAdminVideo(" + id + ")");
    return;
}

function deleteUploadVideo(id) {
    $.ajax({
        url: "deleteUserUploadVideo?id=" + id,
        type: "get",
        async: false,
        success: function (data) {
            $('#modal-confirm').modal('toggle');
            if (data == "deleteSuccess") {
                toastr.info("删除成功!");
                $('#uploadVideo' + id).remove();
            }
            if (data == "deleteFail")
                toastr.error("删除失败!");
        }
    });
}

function deleteLikeVideo(id) {
    $.ajax({
        url: "deleteUserLikeVideo?id=" + id,
        type: "get",
        async: false,
        success: function (data) {
            $('#modal-confirm').modal('toggle');
            if (data == "deleteSuccess") {
                toastr.info("删除成功!");
                $('#likeVideo' + id).remove();
            }
            if (data == "deleteFail")
                toastr.error("删除失败!");
        }
    });
}


function getAuthorUploadVideoList() {
    var authorId = $('#authorId').val();
    var pageNum = $('#videoPage').val();
    $.ajax({
        url: "getUploadVideosByAuthor?pageNum=" + pageNum + "&id=" + authorId,
        type: "get",
        success: showAuthorUploadVideoList,
        async: true
    });
}

function showAuthorUploadVideoList(data) {
    var videoList = eval('(' + data + ')');
    for (var i = 0; i < videoList.length; i++) {
        var video = videoList[i];
        $('#upload-list').append(createLittleVideo(video, "upload", false));
    }
    if (videoList == null || videoList == "" || videoList.length < 12) {
        $('#load-btn').attr("onclick", "javascript:void(0);");
        $('#load-btn').addClass("disabled");
    }
    $('#videoPage').val(parseInt($('#videoPage').val()) + 1);
}