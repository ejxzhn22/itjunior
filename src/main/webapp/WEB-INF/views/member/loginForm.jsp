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
<%--@elvariable id="params" type="com.spring.itjunior.domain.Member"--%>
    <form:form class="form-signin" modelAttribute="params" name="form" id="form" role="form" method="post" action="/login">
        <div class="text-center mb-4">
            <h1 class="h3 mb-3 font-weight-normal">ITJunior</h1>
        </div>
        <div class="form-label-group">
            <form:input path="userId" id="uid" class="form-control" placeholder="User ID" required="" autofocus="" />
                <label for="uid" class="sr-only">ufozx</label>
        </div>
        <div class="form-label-group">
            <form:password path="password" id="pwd" class="form-control" placeholder="User Password" required="" />
            <label for="pwd" class="sr-only">1234</label>
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="submit">login</button>
        <span style="font-size:11pt;">Sign up</span>
    </form:form> <!-- login form {e} -->

</body>

</html>