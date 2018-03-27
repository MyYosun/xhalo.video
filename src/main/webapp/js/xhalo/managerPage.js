/*管理员界面开始*/
function deleteAdminVideo(id) {
    $.ajax({
        url: "adminDeleteVideo?id=" + id,
        type: "get",
        async: false,
        success: function (data) {
            $('#modal-confirm').modal('toggle');
            if (data == "deleteSuccess") {
                toastr.info("删除成功!");
                $('#adminVideo' + id).remove();
            }
            if (data == "deleteFail")
                toastr.error("删除失败!");
        }
    });
}

function showAdminVideoList(data) {
    var videoList = eval('(' + data + ')');
    for (var i = 0; i < videoList.length; i++) {
        var video = videoList[i];
        $('#upload-list').append(createLittleVideo(video, "admin", true));
    }
    if (videoList == null || videoList == "" || videoList.length < 12) {
        $('#load-btn').attr("onclick", "javascript:void(0);");
        $('#load-btn').addClass("disabled");
    }
    $('#videoPage').val(parseInt($('#videoPage').val()) + 1);
}

function adminGetUploadVideoList() {
    var pageNum = $('#videoPage').val();
    $.ajax({
        url: "adminGetVideos?pageSize=12&pageNum=" + pageNum,
        type: "get",
        success: showAdminVideoList,
        async: true
    });
}

function addCategoryAction() {
    $('#categoryInfoForm').ajaxSubmit({
        url: "adminAddCategory",
        type: "post",
        success: function (data) {
            if (data == "addSuccess")
                toastr.info("新增成功!");
            else
                toastr.info("新增失败!");
            $('#categoryInfoForm').resetForm();
        }
    });
}

function confirmDeleteUselessVideos() {
    $('#confirm-btn').attr("onclick", "deleteUselessVideos()");
}

function confirmRepairUselessVideos() {
    $('#confirm-btn').attr("onclick", "repairUselessVideos()");
}

function deleteUselessVideos() {
    $.ajax({
        url: "adminDeleteUselessVideos",
        type: "post",
        success: function (data) {
            if (data == "processSuccess") {
                toastr.info("处理完成!");
            } else {
                toastr.info("处理失败!");
            }
            $('#modal-confirm').modal('toggle');
            return;
        }
    });
    return;
}

function repairUselessVideos() {
    $.ajax({
        url: "adminRepairUselessVideos",
        type: "post",
        success: function (data) {
            if (data == "processSuccess") {
                toastr.info("处理完成!");
            } else {
                toastr.info("处理失败!");
            }
            $('#modal-confirm').modal('toggle');
            return;
        }
    });
    return;
}