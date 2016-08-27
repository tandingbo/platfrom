<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>jquery.imgareaselect图像区域剪切</title>
    <meta id="i18n_pagename" content="index-common">
    <meta name="viewport" content="width=device-width">
    <meta name="keywords" content=""/>
    <meta name="description" content=""/>
    <link rel="stylesheet" href="static/imgareaselect/css/imgareaselect-default.css">
</head>
<body>
<div>
    <input type="file" style="display: none" id="upImg" onchange="changeImg(this)">
    <label for="upImg" id="preview">
        <img id="imghead" src="static/images/normal.jpg" width="198" height="198" alt="头像">
    </label>
</div>

<div class="boxFooter">
    <input type="hidden" name="x1" value="0">
    <input type="hidden" name="y1" value="0">
    <input type="hidden" name="x2" value="100">
    <input type="hidden" name="y2" value="100">
    <button name="confirm" id="subPhoto">确&nbsp;定</button>
    <div id="imgmsg"></div>
</div>
<script src="static/js/jquery.js"></script>
<!-- 加载js文件 -->
<script src="static/imgareaselect/scripts/jquery.imgareaselect.min.js"></script>
<script src="static/js/image.js"></script>
</body>
</html>