<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>VR VIEW (Beta)</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="icon" href="/images/favicon.ico" type="image/x-icon"/>
    <link rel="shortcut icon" href="/images/favicon.ico" type="image/x-icon"/>
    <link rel="bookmark" href="/images/favicon.ico" type="image/x-icon"/>
    <script src="/js/vr/three.js"></script>
    <script src="/js/vr/CanvasRenderer.js"></script>
    <script src="/js/vr/Projector.js"></script>
    <script src="/js/vr/mxreality.js"></script>
    <script src="/js/xhalo-video.js"></script>
</head>
<body>
<div id="vr-video"></div>
</body>
<script>
    var url = "";
    if (!getQueryString("isUrl"))
        url = '/videoPlay?videoAddress=' + getQueryString("path");
    else
        url = getQueryString("path");
    initVrVideo(url, 'vr-video');
</script>
</html>
