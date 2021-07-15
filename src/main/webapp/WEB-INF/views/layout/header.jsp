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
</head>
<body>
<h3>이곳은 header</h3>
<c:choose>
    <c:when test="${empty principal}">
        <h2>로그인 정보 없음</h2>
        <li>
            <a href="/auth/loginForm">로그인</a>
            <a href="/auth/joinForm">회원가입</a>
        </li>
    </c:when>
    <c:otherwise>
        <h1>로그인 되었습니다..!</h1>
        ${principal}<br>
        <a href="/auth/member/form">마이페이지</a>
        <a href="/logout">로그아웃</a>
        <a href="/auth/updateForm">회원수정</a>
    </c:otherwise>
</c:choose>
