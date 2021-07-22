<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false" isErrorPage="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@include file="../layout/header.jsp"%>

<h1>회원가입</h1>
    <div class="container">
        <form action="/member" method="post">
<%--            <input type="hidden" name="_method" value="put">--%>
            <input type="hidden" name="originPwd" value="${member.password}">
            <input type="hidden" name="member_idx" value="${principal.member.member_idx}">
            <div class="form-group">
                <label>User Id</label>
                <input type="text" class="form-control" readonly="readonly" id="userId" name="userId" value="${principal.member.userId}">
            </div>
            <div class="form-group">
                <label>Password</label>
                <input type="password" class="form-control" placeholder="PASSWORD" id="password" name="password">
            </div>
            <div class="form-group">
                <label>Email</label>
                <input type="email" class="form-control" placeholder="Email" id="email" name="email" value="${principal.member.email}">
            </div>
            <div class="form-group">
                <label>Name</label>
                <input type="text" class="form-control" readonly="readonly" placeholder="Name" id="name" name="name" value="${principal.member.name}">
            </div>
            <div class="form-group">
                <label>NickName</label>
                <input type="text" class="form-control" placeholder="NickName" id="nickname" name="nickname" value="${principal.member.nickname}">
            </div>
            <button type="submit" class="btn btn-primary">회원수정완료</button>
        </form>
    </div>

<%@include file="../layout/footer.jsp"%>
