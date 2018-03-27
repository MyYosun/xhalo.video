<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>${video.title}</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="icon" href="/images/favicon.ico" type="image/x-icon"/>
    <link rel="shortcut icon" href="/images/favicon.ico" type="image/x-icon"/>
    <link rel="bookmark" href="/images/favicon.ico" type="image/x-icon"/>
    <script src="/js/vr/three.js"></script>
    <script src="/js/vr/CanvasRenderer.js"></script>
    <script src="/js/vr/Projector.js"></script>
    <script src="/js/vr/mxreality.js"></script>
    <link href="/css/xhalo-video.css" rel="stylesheet">
    <script src="/js/xhalo/showVideo.js"></script>
    <style>
        body {
            overflow: scroll !important
        }
    </style>
</head>
<body>
<jsp:include page="head.jsp"></jsp:include>
<input type="hidden" value="${video.address}" id="video-address"/>
<input type="hidden" value="${video.id}" id="videoId"/>
<div id="vr-video"></div>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-8 column">
            <div class="page-header">
                <h3>
                    ${video.title}
                    <small>&nbsp;&nbsp;<span class="label label-info">${video.click}次观看</span></small>
                </h3>
                <br/>
                <ul class="list-unstyled">
                    <li>
                        <p style="font-weight: 300;"><fmt:formatDate type="date" dateStyle="long"
                                                                     value="${video.date}"/>发布</p>
                    </li>
                    <li class="li-right">
                        <a href="javascript:void(0);" onclick="likeVideo('likeBtn')" id="likeBtn"
                           style="color:black;"><span class="glyphicon glyphicon-heart-empty"></span></a>&nbsp;&nbsp;
                        <span class="glyphicon glyphicon-share-alt" id="shareBtn"></span>
                    </li>
                </ul>
            </div>
            <div class="media">
                <a class="media-left" href="/author-${video.author.username}">
                    <img class="media-object img-circle" style="height:80px;width:80px;"
                         src="/showImg?isHead=true&view=${video.author.headImg}"
                         alt="head">
                </a>
                <div class="media-body">
                    <h4 class="media-heading">
                        ${video.author.nickname}
                    </h4>
                    <p style="font-weight: 300;"> ${video.info}</p>
                </div>
            </div>
            <hr>
            <div id="comment-content">
                <div class="media" style="margin-top:10px;">
                    <form role="form" id="comment-form">
                        <div class="form-group">
                            <label for="comment"><h4>发表你的评论</h4></label>
                            <textarea id="comment" name="content" class="form-control" rows="3"></textarea>
                        </div>
                        <input type="hidden" value="${video.id}" name="video.id"/>
                        <div class="form-group" id="comment-submit" style="display: none">
                            <button type="button" id="comment-btn" onclick="submitComment()" class="btn btn-default">
                                提交
                            </button>
                        </div>
                    </form>
                </div>
                <hr>
                <h4 style="margin: 15px 0 20px 0;">
                    网友评论
                </h4>
                <input type="hidden" value="1" id="comment-pageNum"/>
                <ul class="list-unstyled" id="comment-ul">
                    <!--使用jquery动态创建，下为单例-->
                    <%--<li>
                        <div class="media" style="margin-top:15px;">
                            <a class="media-left" href="#">
                                <img class="media-object img-circle small-img"
                                     src="/showImg?isHead=true&view=user.headImg">
                            </a>
                            <div class="media-body">
                                <h4 class="media-heading">
                                    user.nickname
                                </h4>
                                <p>content</p>
                            </div>
                        </div>
                    </li>--%>
                </ul>
                <div class="col-sm-12" style="text-align:center">
                    <a id="comment-load-btn" href="javascript:void(0);" onclick="getCommentList()"
                       class="button button-caution button-pill button-small">load more</a>
                </div>
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
                            <img class="media-object img-rounded video-img" src="/showImg?view=${video.view}"
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
    //初始化vr视频
    initVrVideo('/videoPlay?videoAddress=' + $('#video-address').val(), 'vr-video');
    //消除样式
    $('#head').removeClass("navbar-fixed-top");
    $('#vr-video').css("position", "relative");
    $('#comment').on("click", function () {
        $('#comment-submit').show(200);
    });
    if (!isMobile()) {
        $("#shareBtn").zclip({
            path: '/js/ZeroClipboard.swf',
            copy: function () {
                return window.document.location.href;
            },
            afterCopy: function () {
                toastr.info("链接已复制到剪切板!");
            }
        });
        $('#vr-video').css("height", "100%");
    } else {
        $("#shareBtn").on("click", function (e) {
            toastr.info("请通过浏览器自带的分享按钮分享!");
        });
        $('#vr-video').css("height", "80%");
    }

    window.addEventListener("onorientationchange" in window ? "orientationchange" : "resize", function () {
        if (window.orientation === 180 || window.orientation === 0) {
        }
        if (window.orientation === 90 || window.orientation === -90) {
            window.location.href = "/vr-view?path=" + $('#video-address').val();
        }
    }, false);

    getPopularList();
    modifyLikeBtn('likeBtn');
    getCommentList();
</script>
</html>
