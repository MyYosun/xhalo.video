<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>XHalo Video</title>
    <link rel="stylesheet" href="css/baguetteBox.min.css">
    <link rel="stylesheet" href="css/thumbnail-gallery.css">
    <style>
        #myCarousel {
            margin: 50px 0 0 0;
            height: 400px;
        }
        .carousel-inner img{
            margin: 0 auto;
            height: 400px!important;
            width: 80%;
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
            <img src="/showImg?isBig=true&video.view=123qqqqcfcfba869d7cf8ed654a3aa676eebbd1.jpg" alt="First slide">
            <div class="carousel-caption">标题 1</div>
        </div>
        <div class="item">
            <img src="/showImg?isBig=true&video.view=123qqqqcfcfba869d7cf8ed654a3aa676eebbd1.jpg" alt="Second slide">
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

<div class="container gallery-container">
    <h1>Latest Videos</h1>
    <p class="page-description text-center">享受用户们最新上传的视频吧!</p>
    <div class="tz-gallery">
        <div class="row" id="mostPopular">
            <!--使用jquery动态生成，下面为单例示例-->
            <%--<div class="col-sm-6 col-md-4">
                <div class="thumbnail">
                    <a class="lightBox" href="images/park.jpg">
                        <img src="/showImg?video.view=12313213cfcfba869d7cf8ed654a3aa676eebbd1.jpg" alt="First slide">
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
</body>
<script>
    $('#myCarousel').carousel('cycle');
    getRecommendVideos();
    getPopularVideos();
</script>
</html>
