/**视频结果展示开始**/
//视频搜索
function getResultVideos(isReload) {
    $("#load-btn").button("loading");
    $("#load-btn").attr("onclick", "javascript:void(0)");
    var videoTitle = $('#video-title').val();
    var categoryId = $('#category-id').val();
    var pageNum = $('#pageNum').val();
    if (isReload) {
        $('#resultVideo').html("");
        pageNum = 1;
        $('#pageNum').val(1);
    }
    var option_duration = $('input[name="radio"]:checked').val();
    var option_order = $('input[name="radio_order"]:checked').val();
    var optionDuration = "all";
    var optionOrder = "date";
    switch (option_duration) {
        case "0" :
            optionDuration = "all";
            break;
        case "1" :
            optionDuration = "short";
            break;
        case "2" :
            optionDuration = "medium";
            break;
        case "3" :
            optionDuration = "long";
            break;
        case "4" :
            optionDuration = "other";
            break;
    }
    switch (option_order) {
        case "0" :
            optionOrder = "date";
            break;
        case "1" :
            optionOrder = "duration";
            break;
        case "2" :
            optionOrder = "click";
            break;
    }
    if (videoTitle != "" && videoTitle != undefined && videoTitle != null)
        var url = "getVideosByTitleAndPage?title=" + videoTitle;
    if (categoryId != "" && categoryId != undefined && categoryId != null)
        var url = "getVideosByCategoryAndPage?category.id=" + categoryId;
    url += "&pageNum=" + pageNum + "&optionDuration=" + optionDuration + "&optionOrder=" + optionOrder;
    $.ajax({
        type: "get",
        url: url,
        // contentType:"application/json;charset=utf-8",
        async: true,
        success: showResultVideos
    });
}

function showResultVideos(data) {
    var videoList = eval('(' + data + ')');
    for (var i = 0; i < videoList.length; i++) {
        var divTop = createNewVideoModel(videoList[i]);
        $('#resultVideo').append(divTop);
    }
    toastr.info("加载完成");
    $('#pageNum').val(parseInt($('#pageNum').val()) + 1);
    $("#load-btn").button("reset");
    if (videoList == null || videoList == "" || videoList.length < 9) {
        $("#load-btn").attr("onclick", "javascript:void(0);");
    }
    else {
        $("#load-btn").attr("onclick", "getResultVideos()");
    }
}

/**视频结果展示结束**/