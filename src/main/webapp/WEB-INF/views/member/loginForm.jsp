<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Spring Boot Application with JSP</title>
</head>
<body>
<h1>로그인</h1>
    <div class="container">
        <form action="/auth/loginProc" method="post">
            <div class="form-group">
                <label>User Id</label>
                <input type="text" class="form-control" placeholder="ID" id="userId" name="userId">
            </div>
            <div class="form-group">
                <label>Password</label>
                <input type="password" class="form-control" placeholder="PASSWORD" id="password" name="password">
            </div>

            <button type="submit" class="btn btn-primary">로그인</button>
            <a href="/auth/joinForm">회원가입</a>
        </form>
    </div>

</body>

</html>