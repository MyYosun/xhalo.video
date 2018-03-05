<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>${video.title}</title>
    <link href="css/video-js.min.css" rel="stylesheet">
    <script src="js/video.min.js"></script>
    <link href="css/xhalo-video.css" rel="stylesheet">
</head>
<body>
<jsp:include page="head.jsp"></jsp:include>
<div class="col-sm-12 col-md-12 col-lg-12">
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
                    ${video.title}<small>&nbsp;&nbsp;&nbsp;${video.click}次观看</small>
                </h3><br/>
                <ul class="list-unstyled">
                    <li>
                        <p><fmt:formatDate type="date" dateStyle="long" value="${video.date}"/>发布</p>
                    </li>
                    <li class="li-right">112223</li>
                </ul>
            </div>
            <p>

                是一个分布式的版本控制系统，最初由 <strong>Linus Torvalds</strong> 编写，用作Linux内核代码的管理。在推出后，Git在其它项目中也取得了很大成功，尤其是在 <small>Ruby</small> 社区中。
            </p> <button type="button" class="btn btn-default">按钮</button>
            <h3>
                h3. 这是一套可视化布局系统.
            </h3>
            <ul class="list-unstyled">
                <li>
                    Lorem ipsum dolor sit amet
                </li>
                <li>
                    Consectetur adipiscing elit
                </li>
                <li>
                    Integer molestie lorem at massa
                </li>
                <li>
                    Facilisis in pretium nisl aliquet
                </li>
                <li>
                    Nulla volutpat aliquam velit
                </li>
                <li>
                    Faucibus porta lacus fringilla vel
                </li>
                <li>
                    Aenean sit amet erat nunc
                </li>
                <li>
                    Eget porttitor lorem
                </li>
            </ul>
        </div>
        <div class="col-md-4 column">
            <h3>
                h3. 这是一套可视化布局系统.
            </h3>
            <ul class="list-unstyled">
                <li>
                    Lorem ipsum dolor sit amet
                </li>
                <li>
                    Consectetur adipiscing elit
                </li>
                <li>
                    Integer molestie lorem at massa
                </li>
                <li>
                    Facilisis in pretium nisl aliquet
                </li>
                <li>
                    Nulla volutpat aliquam velit
                </li>
                <li>
                    Faucibus porta lacus fringilla vel
                </li>
                <li>
                    Aenean sit amet erat nunc
                </li>
                <li>
                    Eget porttitor lorem
                </li>
            </ul>
        </div>
    </div>
</div>
</body>
</html>
