//方法函数
function formatTime(seconds) {
    return [
        parseInt(seconds / 60),
        parseInt(seconds % 60)
    ]
        .join(":")
        .replace(/\b(\d)\b/g, "0$1");
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
    videoImg.attr("src","/showImg?video.view=" + video.view);
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
            divSingle.append($('<a href="/video-' + video.id + '.html"><img src="/showImg?isBig=true&video.view=' + video.view + '" alt="First slide"></a>'));
            divSingle.append($('<div class="carousel-caption">' + video.title + '</div>'));
            $('#carousel-inner').append(divSingle);
        } else {
            $('#carousel-indicators').append($('<li data-target="#myCarousel" data-slide-to="' + i + '"></li>'));
            var divSingle = $('<div class="item"></div>');
            divSingle.append($('<a href="/video-' + video.id + '.html"><img src="/showImg?isBig=true&video.view=' + video.view + '" alt="First slide"></a>'));
            divSingle.append($('<div class="carousel-caption">' + video.title + '</div>'));
            $('#carousel-inner').append(divSingle);
        }

    }
}

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
    a_img.append($('<img class="media-object img-rounded video-img" src="/showImg?video.view=' + video.view + '" alt="head">'));
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
