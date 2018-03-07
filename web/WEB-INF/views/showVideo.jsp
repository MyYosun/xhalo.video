<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>${video.title}</title>
    <link href="/css/video-js.min.css" rel="stylesheet">
    <script src="/js/video.min.js"></script>
    <link href="/css/xhalo-video.css" rel="stylesheet">
</head>
<body>
<jsp:include page="head.jsp"></jsp:include>
<div style="width: 100%;">
    <video id="example_video_1" class="video-js vjs-default-skin vjs-big-play-centered"
           controls preload="auto" style="width:100%!important;" height="600"
           data-setup='{"example_option":true}'>
        <source src="videoPlay?videoAddress=${video.address}" type='video/mp4' />
    </video>
</div>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-8 column">
            <div class="page-header">
                <h3>
                    ${video.title}<small>&nbsp;&nbsp;<span class="label label-info">${video.click}次观看</span></small>
                </h3><br/>
                <ul class="list-unstyled">
                    <li>
                        <p><fmt:formatDate type="date" dateStyle="long" value="${video.date}"/>发布</p>
                    </li>
                    <li class="li-right">
                        <a href="javascript:void(0);" onclick="likeVideo('likeBtn')" id="likeBtn" style="color:black;"><span class="glyphicon glyphicon-heart-empty"></span></a>&nbsp;&nbsp;
                        <span class="glyphicon glyphicon-share-alt" id="shareBtn" ></span>
                    </li>
                </ul>
            </div>
            <div class="media">
                <a class="media-left" href="#">
                    <img class="media-object img-circle" style="height:80px;width:80px;" src="/showImg?video.view=123qqqqcfcfba869d7cf8ed654a3aa676eebbd1.jpg"
                         alt="head">
                </a>
                <div class="media-body">
                    <h4 class="media-heading">
                        ${video.author.nickname}
                    </h4>
                    ${video.info}
                </div>
            </div>
            <hr>
            <div id="comment-content">
                <h4>
                    网友评论
                </h4>
                <ul class="list-unstyled">
                    <li>
                        <div class="media" style="margin-top:15px;">
                            <a class="media-left" href="#">
                                <img class="media-object img-circle" style="height:80px;width:80px;" src="/showImg?video.view=123qqqqcfcfba869d7cf8ed654a3aa676eebbd1.jpg"
                                     alt="head">
                            </a>
                            <div class="media-body">
                                <h4 class="media-heading">
                                    ${video.author.nickname}
                                </h4>
                                ${video.info}
                            </div>
                        </div>
                    </li>
                    <li>
                        <div class="media"  style="margin-top:15px;">
                            <a class="media-left" href="#">
                                <img class="media-object img-circle" style="height:80px;width:80px;" src="/showImg?video.view=123qqqqcfcfba869d7cf8ed654a3aa676eebbd1.jpg"
                                     alt="head">
                            </a>
                            <div class="media-body">
                                <h4 class="media-heading">
                                    ${video.author.nickname}
                                </h4>
                                ${video.info}
                            </div>
                        </div>
                    </li>
                    <li>
                        <div class="media" style="margin-top:15px;">
                            <a class="media-left" href="#">
                                <img class="media-object img-circle" style="height:80px;width:80px;" src="/showImg?video.view=123qqqqcfcfba869d7cf8ed654a3aa676eebbd1.jpg"
                                     alt="head">
                            </a>
                            <div class="media-body">
                                <h4 class="media-heading">
                                    ${video.author.nickname}
                                </h4>
                                ${video.info}
                            </div>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
        <div class="col-md-4 column">
            <h4 style="margin:20px 0 0 0;">
                热门视频
            </h4>
            <ul class="list-unstyled" id="popular-list">
                <!--下列列表由jquery动态生成，以下为单例示例-->
                <%--<li class="video-li">
                    <div class="media">
                        <a class="media-left" href="#">
                            <img class="media-object img-rounded video-img" src="/showImg?video.view=123qqqqcfcfba869d7cf8ed654a3aa676eebbd1.jpg"
                                 alt="head">
                        </a>
                        <div class="media-body">
                            <p class="media-heading video-title">
                                ${video.title}
                            </p>
                            <p>
                                <span class="video-info">${video.author.nickname}</span>
                                <br>
                                <span class="video-info">${video.click}次观看</span>
                            </p>
                        </div>
                    </div>
                </li>--%>
            </ul>
        </div>
    </div>
</div>

<jsp:include page="footer.jsp"></jsp:include>
</body>
<script src="/js/jquery.zclip.js"></script>
<script>
    $("#shareBtn").zclip({
        path: '/js/ZeroClipboard.swf',
        copy: function () {
            return window.document.location.href;
        },
        afterCopy: function () {
            toastr.info("链接已复制到剪切板!");
        }
    });
    getPopularList();
</script>
</html>
