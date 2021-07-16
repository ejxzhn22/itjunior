<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<sec:authorize access="isAuthenticated()">
    <sec:authentication property="principal" var="principal"/>
</sec:authorize>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Spring Boot Application with JSP</title>
<%--    link--%>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
<%--    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>--%>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/default.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/board.css">
    <script src="${pageContext.request.contextPath}/js/summernote/summernote-lite.js"></script>
    <script src="${pageContext.request.contextPath}/js/summernote/lang/summernote-ko-KR.js"></script>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/summernote/summernote-lite.css">

</head>
<body>
<%--<h3>header</h3>--%>
<%--<c:choose>--%>
<%--    <c:when test="${empty principal}">--%>
<%--        <h2>로그인 정보 없음</h2>--%>
<%--        <li>--%>
<%--            <a href="/auth/loginForm">로그인</a>--%>
<%--            <a href="/auth/joinForm">회원가입</a>--%>
<%--        </li>--%>
<%--    </c:when>--%>
<%--    <c:otherwise>--%>
<%--        <h1>로그인 되었습니다..!</h1>--%>
<%--        ${principal}<br>--%>
<%--        <a href="/auth/member/form">마이페이지</a>--%>
<%--        <a href="/logout">로그아웃</a>--%>
<%--        <a href="/auth/updateForm">회원수정</a>--%>
<%--        <a href="/boards">게시판</a>--%>
<%--    </c:otherwise>--%>
<%--</c:choose>--%>

<!-- 헤더 -->
<div class="header-section" id="header">
    <div class="logo-div">
        <a href="/">
            <img src="/image/itju-logo.png" alt="로고" class="logo-img">
        </a>
    </div>
    <div class="nav-div">
        <ul>
            <li><a href="#">채용공고</a></li>
            <li><a href="#">관련학원</a></li>
            <li><a href="/boards">취업토론</a></li>
        </ul>
    </div>
    <div class="member-div">
        <a href="/auth/loginForm">login</a>
        <a href="/auth/joinForm">join</a>
    </div>
</div>