<%@ page language="java" pageEncoding="UTF-8" %>
<%@page contentType="text/html" %>
<!DOCTYPE html>

<html lang="en">
<head>
    <title>出错啦！</title>
    <meta name="copyright" content="Copyright">
    <link rel="stylesheet" href="/static/css/error/style.css"/>
    <link rel="stylesheet" href="/static/css/error/base.css"/>
</head>

<body>
<div id="errorpage">
    <div class="tfans_error">
        <div class="logo"></div>
        <div class="errortans clearfix">
            <div class="e404"></div>
            <p><b>出错啦！</b></p>
            <p>您访问的页面不存在</p>
            <div class="bt" >
                <a href="javascript:location.reload();">刷新试试</a>
                <a href="${pageContext.request.contextPath}/index">返回首页</a>
            </div>
        </div>
    </div>
</div>

</body>

</html>
