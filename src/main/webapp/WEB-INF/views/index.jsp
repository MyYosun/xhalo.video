<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>XHalo Video</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="icon" href="/images/favicon.ico" type="image/x-icon"/>
    <link rel="shortcut icon" href="/images/favicon.ico" type="image/x-icon"/>
    <link rel="bookmark" href="/images/favicon.ico" type="image/x-icon"/>
    <link rel="stylesheet" href="/css/baguetteBox.min.css">
    <link rel="stylesheet" href="/css/thumbnail-gallery.css">
    <script src="/js/xhalo/index.js"></script>
    <style>
        #myCarousel {
            margin: 50px 0 0 0;
        }

       /* .lightBox img {
            height:250px!important;
            width:100%;
        }

        .caption {
            height:100px!important;
        }

        .tz-gallery .caption .video-info{
            height:40px!important;
        }

        .tz-gallery .caption .video-title{
            height:20px!important;
        }*/

        .content h1 {
            color: #f17c67;
            text-shadow: 1px 1px 1px white;
            font-size: 50px;
        }

        .banner {
            width:90%;
            margin-left:5%;
            height: 250px;
            position: relative;
            margin-top: 20px;
            overflow: hidden;
            background: #222;
            border-radius: 4px 4px 4px 4px;
        }

        .banner img {
            position: absolute;
            left: 50%;
            top: -300px;
            margin-left: -1000px;
            width: 2000px;
            vertical-align: middle;
            border: 0;
        }

        .banner span {
            position: absolute;
            top: 0;
            left: 0;
            right: 0;
            bottom: 0;
            text-align: center;
            color: #fff;
            text-shadow: 1px 1px 1px #333;
            font-size: 50px;
            line-height: 250px;
        }
    </style>
</head>
<body>
<jsp:include page="head.jsp"></jsp:include>
<div id="myCarousel" class="carousel slide">
    <!-- 轮播（Carousel）指标 -->
    <ol class="carousel-indicators" id="carousel-indicators">
        <!--使用jquery动态生成，下面为单例示例-->
        <%--<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
        <li data-target="#myCarousel" data-slide-to="1"></li>--%>
    </ol>
    <!-- 轮播（Carousel）项目 -->
    <div class="carousel-inner" id="carousel-inner">
        <!--使用jquery动态生成，下面为单例示例-->
        <%--<div class="item active">
            <img src="/showImg?isBig=true&view=123qqqqcfcfba869d7cf8ed654a3aa676eebbd1.jpg" alt="First slide">
            <div class="carousel-caption">标题 1</div>
        </div>
        <div class="item">
            <img src="/showImg?isBig=true&view=123qqqqcfcfba869d7cf8ed654a3aa676eebbd1.jpg" alt="Second slide">
            <div class="carousel-caption">标题 2</div>
        </div>--%>
    </div>
    <!-- 轮播（Carousel）导航 -->
    <a class="carousel-control left" href="#myCarousel"
       data-slide="prev"><span class="glyphicon glyphicon-chevron-left"> </span>
    </a>
    <a class="carousel-control right" href="#myCarousel"
       data-slide="next"><span class="glyphicon glyphicon-chevron-right"> </span>
    </a>
</div>
<div>
    <div class="container gallery-container content" id="content">
        <div>
            <h1>Latest Videos</h1>
            <p class="page-description text-center">享受用户们最新上传的视频吧!</p>
            <div class="tz-gallery">
                <div class="row" id="latestVideos">
                    <!--使用jquery动态生成，下面为单例示例-->
                    <%--<div class="col-sm-6 col-md-4">
                        <div class="thumbnail">
                            <a class="lightBox" href="images/park.jpg">
                                <img src="/showImg?view=12313213cfcfba869d7cf8ed654a3aa676eebbd1.jpg" alt="First slide">
                            </a>
                            <div class="caption">
                                <div class="video-title">
                                <h3><span class="badge">50views</span>蠟筆小新 [949] 就是想吃聖代 & 單身出任真揪心</h3>
                                </div>
                                <div class="video-info">
                                <p>这是一个简洁的简介，来测试一下看起来怎么样，可能有点丑，没事，我过会会调动</p>
                                </div>
                                <ul>
                                    <li><span class="glyphicon glyphicon-user"></span><span><a>Yosun</a></span></li>
                                    <li class="right-list"><span class="glyphicon glyphicon-time"></span><span>5:03</span></li>
                                </ul>
                            </div>
                        </div>
                    </div>--%>
                </div>
            </div>
        </div>
    </div>

    <div class="banner">
        <img alt="Recommend" data-stellar-ratio="1.3"
             src="/images/recommend-index.jpg">
        <span>Recommend Videos</span>
    </div>
    <div class="container gallery-container content">
        <div>
            <p class="page-description text-center">推荐给你的视频!</p>
            <div class="tz-gallery">
                <div class="row" id="suggestVideos">
                </div>
            </div>
        </div>
    </div>

    <div class="banner">
        <img alt="VR" data-stellar-ratio="1.3"
             src="/images/vr-index.jpg">
        <span>VR Videos</span>
    </div>
    <div class="container gallery-container content">
        <div>
            <p class="page-description text-center">享受VR视频吧!</p>
            <div class="tz-gallery">
                <div class="row" id="vrVideos">
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="footer.jsp"></jsp:include>
</body>
<script>
    $('#myCarousel').carousel('cycle');
    getIndex();
    if (getQueryString("loginSuccess")) {
        toastr.info("登录成功");
    }
    if (getQueryString("logout"))
        toastr.info("登出成功");
</script>
</html>
