<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false" isErrorPage="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@include file="../layout/header.jsp"%>

<h1>마이페이지</h1>
    <div class="container">
        <c:choose>
            <c:when test="${principal.member.provider eq null}">
                <a href="/mypage/passwordCheck">회원 정보 수정</a>
                <a href="/mypage/deletePasswordCheck"> 회원탈퇴 </a>
            </c:when>
<%--            <c:when test=""></c:when>--%>
            <c:otherwise>
                <form id="" action="/member/updateForm" method="post">
                    <input type="hidden" name="member_idx" value="${principal.member.member_idx}">
                    <input type="hidden" name="userId" value="${principal.member.userId}">
                    <input type="hidden" name="password" value="${member.password}">
                    <button type="submit">oauth 회원 정보 수정</button>
                </form>
                <a href="/mypage/isDelete"> 회원탈퇴 </a>
            </c:otherwise>
        </c:choose>
<%--        <a href="/mypage/passwordCheck">oauth회원정보수정인증</a>--%>
    </div>

<script type="text/javascript">
    console.log("password >> "+ "${member.password}")
    console.log("userId >> "+ "${principal.member.userId}")

    $(document).ready(function () {

    });
</script>
<%@include file="../layout/footer.jsp"%>
