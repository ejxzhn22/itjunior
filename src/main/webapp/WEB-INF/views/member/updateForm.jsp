<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Spring Boot Application with JSP</title>
</head>
<body>
<h1>회원가입</h1>
    <div class="container">
        <form action="/auth/member/${member.member_idx}" method="post">
            <input type="hidden" name="_method" value="put">
            <div class="form-group">
                <label>User Id</label>
                <input type="text" class="form-control" readonly="readonly" id="userId" name="userId" value="${member.userId}">
            </div>
            <div class="form-group">
                <label>Password</label>
                <input type="password" class="form-control" placeholder="PASSWORD" id="password" name="password">
            </div>
            <div class="form-group">
                <label>Email</label>
                <input type="email" class="form-control" placeholder="Email" id="email" name="email" value="${member.email}">
            </div>
            <div class="form-group">
                <label>Name</label>
                <input type="text" class="form-control" readonly="readonly" placeholder="Name" id="name" name="name" value="${member.name}">
            </div>
            <div class="form-group">
                <label>NickName</label>
                <input type="text" class="form-control" placeholder="NickName" id="nickname" name="nickname" value="${member.nickname}">
            </div>
            <button type="submit" class="btn btn-primary">회원가입</button>
        </form>
    </div>

</body>

</html>