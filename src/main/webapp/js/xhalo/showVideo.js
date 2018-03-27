/**视频展示开始**/
function modifyLikeBtn(btnId) {
    var videoId = $('#videoId').val();
    $.ajax({
        url: "validateUserLikeVideo",
        type: "post",
        data: {id: videoId},
        success: function (data) {
            if (data == "like") {
                lightHeart(btnId);
            }
        }
    });
}

function likeVideo(btnId) {
    if ($('#isLogin').val() == "false") {
        toastr.error("请先登录");
        setTimeout(function () {
            window.location.href = "/login";
        }, 2000);
    }
    var videoId = $('#videoId').val();
    $.ajax({
        url: "userAddLikeVideo",
        type: "post",
        data: {id: videoId},
        success: function (data) {
            if (data == "addSuccess") {
                lightHeart(btnId);
            }
        }
    });
}

function dislikeVideo(btnId) {
    var videoId = $('#videoId').val();
    $.ajax({
        url: "userDeleteLikeVideo",
        type: "post",
        data: {id: videoId},
        success: function (data) {
            if (data == "deleteSuccess") {
                clearHeart(btnId);
            }
        }
    });
}

function lightHeart(btnId) {
    $("#" + btnId).css("color", "red");
    $("#" + btnId).children().removeClass("glyphicon-heart-empty");
    $("#" + btnId).children().addClass("glyphicon-heart");
    $('#' + btnId).attr("onclick", "dislikeVideo('" + btnId + "')");
}

function clearHeart(btnId) {
    $("#" + btnId).css("color", "black");
    $("#" + btnId).children().removeClass("glyphicon-heart");
    $("#" + btnId).children().addClass("glyphicon-heart-empty");
    $('#' + btnId).attr("onclick", "likeVideo('" + btnId + "')");
}

function submitComment() {
    if ($('#isLogin').val() == "false") {
        toastr.error("请先登录");
        setTimeout(function () {
            window.location.href = "/login";
        }, 2000);
        return;
    }
    if ($('#comment').val().length < 1 || $('#comment').val().length > 250) {
        toastr.error("请检查评论长度然后重试!");
        return;
    }
    $('#comment-form').ajaxSubmit({
        url: "userAddVideoComment",
        type: "post",
        success: function (data) {
            if (data == "addSuccess") {
                toastr.info("评论成功!");
                $('#comment-ul').html("");
                $('#comment-pageNum').val(1);
                $('#comment-load-btn').attr("onclick", "getCommentList()");
                $('#comment-load-btn').removeClass("disabled");
                getCommentList();
                $('#comment-form').resetForm();
            } else {
                toastr.error("评论失败,请重试!");
            }
        }
    });
}

function getCommentList() {
    var videoId = $('#videoId').val();
    var pageNum = $('#comment-pageNum').val();
    $.ajax({
        url: "getVideoCommentByVideo",
        type: "post",
        data: {
            id: videoId,
            pageNum: pageNum
        },
        success: showCommentList
    });
}

function showCommentList(data) {
    var commentList = eval('(' + data + ')');
    for (var i = 0; i < commentList.length; i++) {
        $('#comment-ul').append(createCommentSingle(commentList[i]));
    }
    if (commentList == null || commentList == "" || commentList.length < 6) {
        $('#comment-load-btn').attr("onclick", "javascript:void(0);");
        $('#comment-load-btn').addClass("disabled");
    }
    $('#comment-pageNum').val(parseInt($('#comment-pageNum').val()) + 1);
}

function createCommentSingle(comment) {
    var liTop = $('<li></li>');
    var divTop = $('<div class="media" style="margin-top:15px;"></div>');
    liTop.append(divTop);
    var userA = $('<a class="media-left" href="/author-' + comment.user.username + '"></a>');
    divTop.append(userA);
    userA.append($('<img class="media-object img-circle small-img" ' +
        'src="/showImg?isHead=true&view=' + comment.user.headImg + '">'));
    var divComment = $('<div class="media-body"></div>');
    divTop.append(divComment);
    divComment.append($('<h4 class="media-heading">' + comment.user.nickname +
        ' <small>' + formatDate(Date.parse(comment.date)) + '</small></h4>'));
    divComment.append($('<p>' + comment.content + '</p>'));
    return liTop;
}

function getPopularList() {
    $.ajax({
        type: "get",
        url: "getRecommendVideos?pageSize=6",
        // contentType:"application/json",
        async: true,
        success: showPopularList
    });
}

function showPopularList(data) {
    var videoList = eval('(' + data + ')');
    for (var i = 0; i < videoList.length; i++) {
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
    a_img.attr("href", "video-" + video.id + ".html");
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

/**vr开始**/
function initVrVideo(url, divId) {
    var scene, renderer;
    var container;
    AVR.debug = true;
    if (!AVR.Broswer.isIE() && AVR.Broswer.webglAvailable()) {
        renderer = new THREE.WebGLRenderer();
    } else {
        renderer = new THREE.CanvasRenderer();
    }
    renderer.setPixelRatio(window.devicePixelRatio);
    container = document.getElementById(divId);
    container.appendChild(renderer.domElement);
    scene = new THREE.Scene();

    var vr = new VR(scene, renderer, container, {"fov": 50});

    vr.vrbox.radius = 600;
    if (AVR.isCrossScreen()) {
        vr.effect.separation = 1.2;
    }

    //小行星视角配置
    vr.asteroidConfig = {
        enable: false, //是否使用小行星视角
        assteroidFPS: 36, //视角移动速度，值越小，移动越快 ms
        assteroidFov: 135, //俯视视角大小
        asteroidForwardTime: 2000, //小行星视角到正常视角更新完成总耗时 ms
        asteroidWaitTime: 1000, //小行星开始之前等待时间 ms
        asteroidDepressionRate: 0.5, //
        asteroidTop: 1, //小行星视角方向[1 俯视/-1 仰视]
        cubeResolution: 2048 //立体相机宽度
    };

    //AVR.useGyroscope=false;

    vr.defaultVolume = 1; //默认音量大小
    vr.init();
    //全景视频则是vr.resType.video  正六面体为vr.resType.box 切片并补天播放器类别为vr.resType.slice
    vr.playPanorama(url, vr.resType.video);
    vr.video.setAttribute("loop", "loop");
    vr.video.crossOrigin = "Anonymous";
    vr.VRhint = "请取消屏幕翻转锁定后装入VR盒子中"; //VR 模式提示文字
    vr.defaultAutoHideLeftTime = 3; //播放器工具栏自动隐藏时间
    vr.defaultVoiceHideLeftTime = 2; //播放器音量控制条隐藏时间
    vr.autoplayPanoImg = false; //播放器镜头是否自动旋转
    vr.loadProgressManager.onLoad = function () {
        vr.video.muted = false;
        vr.VRObject.getObjectByName("__panoContainer").visible = true;
    }
    vr.video.onended = function () {
    }
}