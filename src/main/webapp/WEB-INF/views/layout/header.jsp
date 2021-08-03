<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>

<sec:authorize access="isAuthenticated()">
    <sec:authentication property="principal" var="principal"/>
</sec:authorize>

<!DOCTYPE html>
<html id="html">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Spring Boot Application with JSP</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/default.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/board.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/member.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/job.css">
    <script src="${pageContext.request.contextPath}/js/summernote/summernote-lite.js"></script>
    <script src="${pageContext.request.contextPath}/js/summernote/lang/summernote-ko-KR.js"></script>
    <!-- 아이콘 -->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css" integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/summernote/summernote-lite.css">

</head>
<body>
<!-- 헤더 -->
<div class="header-section" id="header">
    <div class="logo-div">
        <a href="/">
            <img src="/image/itju-logo.png" alt="로고" class="logo-img">
        </a>
    </div>
    <div class="nav-div">
        <ul>
            <c:set var="uri" value="${requestScope['javax.servlet.forward.request_uri']}"/>
            <c:choose>
                <c:when test = "${uri eq '/jobs'}">
                    <li><a href="/job/list" class="select-a">채용공고</a></li>
                    <li><a href="#">IT News</a></li>
                    <li><a href="/boards">취업토론</a></li>
                    <li><a href="/qnaboards">Q&A</a></li>
                </c:when>
                <c:when test = "${uri eq '/boards'}">
                    <li><a href="/jobs">채용공고</a></li>
                    <li><a href="#">IT News</a></li>
                    <li><a href="/boards" class="select-a">취업토론</a></li>
                    <li><a href="/qnaboards">Q&A</a></li>
                </c:when>
                <c:when test = "${uri eq '/qnaboards'}">
                    <li><a href="/jobs">채용공고</a></li>
                    <li><a href="#">IT News</a></li>
                    <li><a href="/boards">취업토론</a></li>
                    <li><a href="/qnaboards" class="select-a">Q&A</a></li>
                </c:when>
                <c:otherwise>
                    <li><a href="/jobs">채용공고</a></li>
                    <li><a href="/news">IT News</a></li>
                    <li><a href="/boards">취업토론</a></li>
                    <li><a href="/qnaboards">Q&A</a></li>
                </c:otherwise>
          </c:choose>
        </ul>
    </div>

    <div class="member-div">
        <input type="hidden" id ="principalId" value="${principal.member.member_idx}"/>
        <input type="hidden" id ="principalNick" value="${principal.member.nickname}"/>

        <c:choose>
            <c:when test="${empty principal}">
                <a href="/auth/loginForm" class="login-btn">로그인</a>
                <a href="/auth/joinForm" class="join-btn">회원가입</a>
            </c:when>
            <c:otherwise>
                <a href="/mypage" class="mypage-btn">마이페이지</a>
                <c:choose>
                    <c:when test="${principal.member.provider == 'kakao'}">
                        <a href="https://kauth.kakao.com/oauth/logout?client_id=316b4f3202bb2e509e866b17e7ccc4c7&logout_redirect_uri=http://localhost:8000/logout">로그아웃</a>
                    </c:when>
                    <c:otherwise>
                        <a href="/logout" class="logout-btn">로그아웃</a>
                    </c:otherwise>
                </c:choose>
            </c:otherwise>
        </c:choose>

    </div>
    <img src="${pageContext.request.contextPath}/image/icon-mo-menu.png" class="icon-mo-menu">
</div>
<div class="mo-menu">
    <ul>
        <li><a href="/job/list">채용공고</a></li>
        <li><a href="#">IT News</a></li>
        <li><a href="/boards">취업토론</a></li>
        <li><a href="/qnaboards">Q&A</a></li>
    </ul>
</div>