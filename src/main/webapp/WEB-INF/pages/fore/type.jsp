<%@page contentType="text/html" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1"/>
    <meta name="description" content=""/>
    <meta name="author" content=""/>
    <!--[if IE]>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <![endif]-->
    <title>博客分类</title>
    <!-- BOOTSTRAP CORE STYLE -->
    <link href="static/assets/css/bootstrap.css" rel="stylesheet"/>
    <!-- FONT AWESOME ICON STYLE -->
    <link href="static/assets/css/font-awesome.css" rel="stylesheet"/>
    <!-- CUSTOM STYLE CSS -->
    <link href="static/assets/css/style.css" rel="stylesheet"/>
</head>
<body>
<div id="header">
    <div class="overlay">
        <div class="container">
            <div class="row">
                <div class="col-md-4 logo-div">
                    <div class="logo-inner text-center">
                        <div class="logo-name">
                            <a href="index.html">
                                <img src="static/assets/img/luffy.jpg" class="img-circle"/>
                            </a>
                        </div>

                    </div>

                </div>
                <div class="col-md-8 header-text-top " id="about">


                    <h1>欢迎来到BlackStar的博客.</h1>
                    simpleness and sureness -- all I want for every day.<br/>
                    <h2><strong>Who I am ? </strong></h2>
                    <i>I am Your Great DADDY. </i>

                </div>
            </div>
        </div>
    </div>
</div>
<!--END HEADER SECTION-->
<div class="info-sec">
    <div class="container">
        <div class="row">
            <div class="col-md-10">
                Say hello to me at <strong>751317151</strong>@qq.com
            </div>
            <div class="col-md-2">
                <div class="social-link">
                    <a href="https://github.com/751317151" class="btn btn-default btn-xs">
                        <i class="fa fa-2x">G</i></a>
                </div>

            </div>

        </div>
    </div>
</div>
<!--END INFO SECTION-->
<div class="container">

    <div class="row">

        <div class="col-md-8 ">
            <c:if test="${empty pageInfo.blogList}">
                Null Message for more blog.
            </c:if>
            <c:if test="${!empty pageInfo}">
                <c:forEach items="${pageInfo.blogList}" var="blog">
                    <div class="blog-post">
                        <h2>${blog.title}</h2>
                        <h4>Posted by <a href="${pageContext.request.contextPath}/about">BlackStar</a> ${blog.createTime} </h4>
                        <p>${blog.summary} </p>
                        <a href="${pageContext.request.contextPath}/blog?id=${blog.id}"
                           class="btn btn-default btn-lg ">Read
                            More <i class="fa fa-angle-right"></i></a>
                    </div>
                </c:forEach>
            </c:if>
            <br/>
            <nav>
                <ul class="pagination">
                    <c:if test="${pageInfo.currentPage != 1 && !empty pageInfo.blogList}">
                        <li>
                            <a href="${pageContext.request.contextPath}/blogType"
                               aria-label="Previous">
                                <span aria-hidden="true">首页</span>
                            </a>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/blogType?blogTypeId=${blogTypeId}&currentPage=${pageInfo.currentPage-1}"
                               aria-label="Previous">
                                <span aria-hidden="true">&laquo;</span>
                            </a>
                        </li>
                    </c:if>

                    <c:if test="${!empty pageInfo.blogList}">

                        <c:if test="${pageInfo.totalPage<=5}">
                            <c:set var="beginPage" value="1" />
                            <c:set var="endPage" value="${pageInfo.totalPage}" />
                        </c:if>
                        <c:if test="${pageInfo.totalPage>5}">
                            <c:set var="beginPage" value="${pageInfo.currentPage -2}" />
                            <c:set var="endPage" value="${pageInfo.currentPage +2}" />

                            <c:if test="${beginPage < 1}">
                                <c:set var="beginPage" value="1" />
                                <c:set var="endPage" value="5" />
                            </c:if>
                            <c:if test="${endPage > pageInfo.totalPage}">
                                <c:set var="beginPage" value="${pageInfo.totalPage -4}" />
                                <c:set var="endPage" value="${pageInfo.totalPage}" />
                            </c:if>
                        </c:if>

                        <c:forEach begin="${beginPage}" end="${endPage}" var="i">
                            <c:if test="${pageInfo.currentPage == i}">
                                <li>
                                    <a style="background-color: black;color: white;" href="${pageContext.request.contextPath}/blogType?blogTypeId=${blogTypeId}&currentPage=${i}">${i}</a>
                                </li>
                            </c:if>
                            <c:if test="${pageInfo.currentPage != i}">
                                <li>
                                    <a href="${pageContext.request.contextPath}/blogType?blogTypeId=${blogTypeId}&currentPage=${i}">${i}</a>
                                </li>
                            </c:if>
                        </c:forEach>
                    </c:if>

                    <c:if test="${pageInfo.currentPage != pageInfo.totalPage && !empty pageInfo.blogList}">
                        <li>
                            <a href="${pageContext.request.contextPath }/blogType?blogTypeId=${blogTypeId}&currentPage=${pageInfo.currentPage+1}"
                               aria-label="Next">
                                <span aria-hidden="true">&raquo;</span>
                            </a>
                        </li>
                        <li>
                            <a href="href="${pageContext.request.contextPath }/blogType?blogTypeId=${blogTypeId}&currentPage=${pageInfo.totalPage}"
                               aria-label="Next">
                                <span aria-hidden="true">末页</span>
                            </a>
                        </li>
                    </c:if>
                    <c:if test="${!empty pageInfo.blogList}">
                        <p>共${pageInfo.totalPage}页</p>
                    </c:if>
                </ul>
            </nav>
        </div>
        <div class="col-md-1"></div>
        <div class="col-md-3" style="padding-top: 30px;">
            <div class="row">
                <ul class="list-group">
                    <li class="list-group-item">
                        <strong>
                            <a href="${pageContext.request.contextPath}/index">主页</a>&nbsp;&nbsp;&nbsp;>&nbsp;&nbsp;&nbsp;博客分类
                        </strong>
                    </li>
                    <c:forEach items="${blogTypeList}" var="blogType">
                        <li class="list-group-item">
                            <a href="${pageContext.request.contextPath}/blogType?blogTypeId=${blogType.id}">${blogType.typeName}</a>
                        </li>
                    </c:forEach>
                </ul>
            </div>
        </div>

    </div>

</div>

<!--END HOME PAGE SECTION-->
<div id="footer" style=" background: #121d1f;padding: 30px 0 25px 0;">
    <div class="container text-center">
        <div class="fnav">
            <p style="color: #fff;font-size: 13px;">Copyright © 2020 blackstar.All Right Reserved</p>
            <a target="_blank" href="http://www.beian.gov.cn/portal/registerSystemInfo?recordcode=42028102000161" style="display:inline-block;"><img src="static/img/eba.png" style="float:left;"/><p style="float:left;line-height:20px;margin: 0px 0px 0px 5px; color:#fff;font-size: 13px;">鄂公网安备 42028102000161号</p></a>
            <a target="_blank" href="http://www.beian.miit.gov.cn/" style="display:inline-block;"><p style="float:left;line-height:20px;margin: 0px 0px 0px 5px; color:#fff;font-size: 13px;">鄂ICP备19017946</p></a>
        </div>
    </div>
</div>

<script src="static/lib/jquery/jquery.js"></script>
<script src="static/js/heart_click.js"></script>
<script src="static/js/su.js"></script>
<script src="static/js/leaf.js"></script>

</body>
</html>
