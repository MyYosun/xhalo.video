<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${category.name}${video.title}</title>
    <link rel="stylesheet" href="/css/baguetteBox.min.css">
    <link rel="stylesheet" href="/css/thumbnail-gallery.css">
</head>
<style>
    .page-header ul {
        padding:0;
    }
    .page-header ul li {
        margin: 5px 0 0 0;
        list-style: none;
    }
    .option-btn {
        margin: 10px 0 0 0!important;
    }
</style>
<body>
<jsp:include page="head.jsp"></jsp:include>
<input type="hidden" value="${video.title}" id="video-title"/>
<input type="hidden" value="${category.id}" id="category-id"/>
<input type="hidden" value="1" id="pageNum"/>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="page-header">
                <h3>
                    ${category.name}${video.title} 的结果
                    <small style="margin-left:10px;"><span class="glyphicon glyphicon-list" data-toggle="collapse"
                                                           data-target="#options"></span> 筛选</small>
                </h3>
                <div id="options" class="collapse out">
                    <ul>
                        <li>
                            <div>
                                <span>时长:</span>
                                <input type="radio" checked="checked" class=" radiocheck" name="radio" value="0" id="radio" />
                                <label class=" full small left"  style="margin-left:10px!important;" for="radio">全部</label>
                                <input type="radio" class=" radiocheck" name="radio" value="1" id="radio0" />
                                <label class=" full small left"  for="radio0">0-10分钟</label>
                                <input type="radio" class=" radiocheck" name="radio" value="2" id="radio1" />
                                <label class=" full small left" for="radio1">10-30分钟</label>
                                <input type="radio" class=" radiocheck" name="radio" value="3" id="radio2" />
                                <label class=" full small left" for="radio2">30-90分钟</label>
                                <input type="radio" class=" radiocheck" name="radio" value="3" id="radio3" />
                                <label class=" full small left" for="radio3">大于90分钟</label>
                            </div>
                        </li>
                        <li>
                            <div>
                                <span>排序:</span>
                                <input type="radio" checked="checked" class=" radiocheck" name="radio_order" value="0" id="radio_order" />
                                <label class=" full small left"  style="margin-left:10px!important;" for="radio_order">发布时间</label>
                                <input type="radio" class=" radiocheck" name="radio_order" value="1" id="radio_order0" />
                                <label class=" full small left"  for="radio_order0">时长</label>
                                <input type="radio" class=" radiocheck" name="radio_order" value="2" id="radio_order1" />
                                <label class=" full small left" for="radio_order1">热度</label>
                            </div>
                        </li>
                        <li class="option-btn">
                            <a href="javascript:void(0)" onclick="getResultVideos(true)" class="button button-rounded button-tiny">筛选</a>
                        </li>
                    </ul>
                </div>
            </div>
            <div class="tz-gallery">
                <div class="row" id="resultVideo">
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
                <div class="col-sm-12" style="text-align:center">
                    <a id="load-btn" href="javascript:void(0);" onclick="getResultVideos()" class="button button-caution button-pill button-small">load more</a>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="footer.jsp"></jsp:include>
</body>
<script>
    $('#head').removeClass("navbar-fixed-top");
    getResultVideos();
</script>
</html>
