<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false" isErrorPage="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@include file="../layout/header.jsp"%>

<h1>회원가입</h1>
    <div class="container">
        <form action="/member" method="post">
<%--            <input type="hidden" name="_method" value="put">--%>
            <input type="hidden" id="originPwd" name="originPwd" value="${member.password}">
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
                <input type="text" class="form-control" placeholder="Name" id="name" name="name" value="${principal.member.name}">
            </div>
            <div class="form-group">
                <label>NickName</label>
                <input type="text" class="form-control" placeholder="NickName" id="nickname" name="nickname" value="${principal.member.nickname}">
            </div>
            <button type="submit" class="btn btn-primary">회원수정완료</button>
        </form>
    </div>

<script type="text/javascript">

    $(document).ready(function () {
        let uuidVal = "${member.password}";
        let originPwd = $("#originPwd");
        let strUserName = $("#name").val();
        console.log("member-password값"+originPwd.val());
        console.log("문자열 길이 : " + strUserName.length);
        console.log("uuid = "+uuidVal);
        if(strUserName.length > 21){
            alert("구글 아이디 최초 로그인 입니다. 회원수정 폼으로 이동하여 name(성명)과 nickname(활동이름)을 수정하여 주십시오.");
        }

    });
</script>
<%@include file="../layout/footer.jsp"%>
