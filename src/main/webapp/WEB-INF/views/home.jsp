<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Spring Boot Application with JSP</title>
</head>
<body> Hello, Spring Boot App
<c:choose>
    <c:when test="${empty sessionScope.info}">
        <h2>로그인 정보 없음</h2>
        <li>
            <a href="/loginForm">로그인</a>
        </li>
    </c:when>
    <c:otherwise>
        <h1>${sessionScope.info.nickname}님 안녕하세요..!</h1>
    </c:otherwise>
</c:choose>



</body>

</html>
