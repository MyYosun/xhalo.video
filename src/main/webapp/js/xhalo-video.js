//方法函数
function formatTime(seconds) {
    return [
        parseInt(seconds / 60),
        parseInt(seconds % 60)
    ]
        .join(":")
        .replace(/\b(\d)\b/g, "0$1");
}

function formatDate(str) {
    var oDate = new Date(str),
        oYear = oDate.getFullYear(),
        oMonth = oDate.getMonth() + 1,
        oDay = oDate.getDate(),
        oHour = oDate.getHours(),
        oMinute = oDate.getMinutes(),
        oTime = oYear.toString() + '-' + oMonth.toString() + '-' +
            oDay.toString() + ' ' + oHour.toString() + ':' + oMinute.toString();//最后拼接时间
    return oTime;
};

function getQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]);
    return null;
}

function isMobile() {
    var userAgentInfo = navigator.userAgent;

    var mobileAgents = ["Android", "iPhone", "SymbianOS", "Windows Phone", "iPad", "iPod"];

    var mobile_flag = false;

    //根据userAgent判断是否是手机
    for (var v = 0; v < mobileAgents.length; v++) {
        if (userAgentInfo.indexOf(mobileAgents[v]) > 0) {
            mobile_flag = true;
            break;
        }
    }

    var screen_width = window.screen.width;
    var screen_height = window.screen.height;

    //根据屏幕分辨率判断是否是手机
    if (screen_width < 500 && screen_height < 800) {
        mobile_flag = true;
    }

    return mobile_flag;
}

function getRootPath() {
    var curWwwPath = window.document.location.href;
    var pathName = window.document.location.pathname;
    var pos = curWwwPath.indexOf(pathName);
    var localhostPath = curWwwPath.substring(0, pos);
    var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
    return (localhostPath + projectName);
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
    timeOut: "8000",
    extendedTimeOut: "1000",
    showEasing: "swing",
    hideEasing: "linear",
    showMethod: "fadeIn",
    hideMethod: "fadeOut"
};

/**head开始**/
var webSocket;
function getWebSocket() {
    var url = getRootPath();
    var path = url.substring(url.indexOf('/') + 2, url.length);
    if ('WebSocket' in window) {
        webSocket = new WebSocket("ws://" + path + "/webSocket/handler");
    } else {
        webSocket = new SockJS("http://" + path + "/webSocket/sockJs/handler");
    }
    webSocket.onopen = function (evnt) {
    }
    webSocket.onmessage = function (evnt) {
        toastr.info(evnt.data);
    }
}

function getCategory() {
    $.ajax({
        type: "get",
        url: "getAllCategories",
        // contentType:"application/json",
        async: true,
        success: showCategory
    });
}

function showCategory(data) {
    var categoryList = eval('(' + data + ')');
    var otherVideos = $("#categoryOther");
    for (var i = 0; i < categoryList.length; i++) {
        var category = categoryList[i];
        if (!category.belongToOther) {
            continue;
        }
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
    imgAndA.attr("href", "/video-" + video.id + ".html");
    var videoImg = $("<img></img>");
    imgAndA.append(videoImg);
    videoImg.attr("src", "/showImg?view=" + video.view);
    videoImg.attr("alt", video.title);

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
    userName.html("<a href='/author-" + video.author.username + "'>" + video.author.nickname + "</a>");
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



