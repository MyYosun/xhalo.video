/**上传开始**/
function loadCategories() {
    $.ajax({
        type: "get",
        url: "getAllCategories",
        // contentType:"application/json",
        async: true,
        success: showUploadCategory
    });
}

function showUploadCategory(data) {
    var categoryList = eval('(' + data + ')');
    $.each(categoryList, function (k, category) {
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
    if (filePath == null || filePath == "" || filePath == undefined) {
        toastr.error("请选择视频");
        return;
    }
    if (videoTitle.length < 2 || videoTitle.length > 30) {
        toastr.error("上传视频标题的长度为2-30之间!");
        $('#video-title').focus();
        return;
    }
    if (videoTitle.trim() == "") {
        toastr.error("上传视频标题不能为空白!");
        $('#video-title').focus();
        return;
    }
    if (videoInfo.length > 200) {
        toastr.error("视频简介的长度应小于200!");
        $('#video-info').focus();
        return;
    }
    var extStart = filePath.lastIndexOf(".");
    var ext = filePath.substring(extStart, filePath.length).toUpperCase();
    if (ext != ".MP4") {
        toastr.error("上传视频格式不正确!");
        return;
    }
    var uploadBtn = $('#uploadBtn');
    $('#upload-form').ajaxSubmit({
        url: "uploadVideo",
        type: "post",
        beforeSubmit: function () {
            $('#myModal').modal({
                backdrop: "static",
                keyboard: false
            });
            uploadBtn.attr("disabled", "disabled");
        },
        xhr: function () { //用以显示上传进度
            var xhr = $.ajaxSettings.xhr();
            if (xhr.upload) {
                xhr.upload.addEventListener('progress', function (event) {
                    var percent = Math.floor(event.loaded / event.total * 100);
                    if (percent != 100)
                        $('#upload-percent').html(percent);
                    else
                        $('#upload-info').html("上传完成，正在转码...");
                }, false);
            }
            return xhr;
        },
        success: function (data) {
            $('#myModal').modal("hide");
            if (data == "formatError") {
                toastr.info("视频信息填写错误!");
            } else if (data == "uploadFail") {
                toastr.info("上传失败，请检查相应的视频信息和文件格式与大小!");
            } else {
                toastr.info("上传成功!");
            }
            $('#upload-form').resetForm();
            return false;
        }

    });
    return;
}

/**上传结束**/