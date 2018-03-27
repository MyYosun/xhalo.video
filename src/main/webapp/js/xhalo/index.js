/**主页方法开始**/
function getLatestVideos() {
    $.ajax({
        type: "get",
        url: "getLatestVideos",
        // contentType:"application/json",
        async: true,
        success: showLatestVideos
    });
}

function showLatestVideos(data) {
    var videoList = eval('(' + data + ')');
    for (var i = 0; i < videoList.length; i++) {
        var divTop = createNewVideoModel(videoList[i]);
        $('#mostPopular').append(divTop);
    }
}

function getRecommendVideos() {
    $.ajax({
        type: "get",
        url: "getRecommendVideos",
        // contentType:"application/json",
        async: true,
        success: showRecommendVideos
    });
}

function showRecommendVideos(data) {
    var videoList = eval('(' + data + ')');
    var isBig = !isMobile();
    var imgHeight = "height:480px!important;";
    if (!isBig) {
        imgHeight = "height:300px!important;";
    }
    for (var i = 0; i < videoList.length; i++) {
        var video = videoList[i];
        if (i == 0) {
            $('#carousel-indicators').append($('<li data-target="#myCarousel" data-slide-to="' + i + '" class="active"></li>'));
            var divSingle = $('<div class="item active"></div>');
            divSingle.append($('<a href="/video-' + video.id + '.html"><img style="width:100%;' + imgHeight + '" src="/showImg?isBig=' + isBig + '&view=' + video.view + '" alt="First slide"></a>'));
            divSingle.append($('<div class="carousel-caption">' + video.title + '</div>'));
            $('#carousel-inner').append(divSingle);
        } else {
            $('#carousel-indicators').append($('<li data-target="#myCarousel" data-slide-to="' + i + '"></li>'));
            var divSingle = $('<div class="item"></div>');
            divSingle.append($('<a href="/video-' + video.id + '.html"><img style="width:100%;' + imgHeight + '" src="/showImg?isBig=' + isBig + '&view=' + video.view + '"></a>'));
            divSingle.append($('<div class="carousel-caption">' + video.title + '</div>'));
            $('#carousel-inner').append(divSingle);
        }
    }
}

/**主页方法结束**/