<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@include file="../layout/header.jsp"%>

<h1>회원가입</h1>
    <div class="container">
        <form action="/auth/join" method="post">
            <div class="form-group">
                <label>User Id</label>
                <input type="text" class="form-control" placeholder="ID" id="userId" name="userId">
            </div>
            <div class="form-group">
                <label>Password</label>
                <input type="password" class="form-control" placeholder="PASSWORD" id="password" name="password">
            </div>
            <div class="form-group">
                <label>Email</label>
                <input type="email" class="form-control" placeholder="Email" id="email" name="email">
            </div>
            <div class="form-group">
                <label>Name</label>
                <input type="text" class="form-control" placeholder="Name" id="name" name="name">
            </div>
            <div class="form-group">
                <label>NickName</label>
                <input type="text" class="form-control" placeholder="NickName" id="nickname" name="nickname">
            </div>
            <button type="submit" class="btn btn-primary">회원가입</button>
        </form>
    </div>


<%@include file="../layout/footer.jsp"%>