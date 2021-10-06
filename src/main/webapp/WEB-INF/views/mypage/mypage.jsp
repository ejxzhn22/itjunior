<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false" isErrorPage="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@include file="../layout/header.jsp"%>
<div class="banner-section2">
    <div class="banner-write2">
        <span class="banner-title2">잇주멤버</span>
        <span class="banner-desc2">잇주멤버가 되어 <br>많은 정보를 얻어보세요😎</span>
    </div>
    <div class="banner-img2">
        <img src="${pageContext.request.contextPath}/image/banner-member.png" alt="banner img" class="banner-img2">
        <img src="${pageContext.request.contextPath}/image/icon-link.png" alt="" class="banner-link-img2">
    </div>
</div>
    <div class="mypage-section">
        <div class="liquid">
            <h1>회원정보 수정</h1>
            <h1>회원정보 수정</h1>
            <h1>회원정보 수정</h1>
            <h1>회원정보 수정</h1>
        </div>
        <c:choose>
            <c:when test="${principal.member.provider eq null}">
                <div class="mypage-update-btns">
                    <a href="/mypage/passwordCheck" class="mypage-update">회원정보 수정</a>
                    <a href="/job/mypage/scrapList" class="mypage-update">스크랩 확인</a>
                    <a href="/mypage/deletePasswordCheck" class="mypage-delete">회원탈퇴 </a>
                </div>
            </c:when>
<%--            <c:when test=""></c:when>--%>
            <c:otherwise>
                <form id="" action="/member/updateForm" method="post">
                    <input type="hidden" name="member_idx" value="${principal.member.member_idx}">
                    <input type="hidden" name="userId" value="${principal.member.userId}">
                    <input type="hidden" name="password" value="${member.password}">
                    <button type="submit" class="oauth-submit">oauth 회원정보 수정</button>
                </form>
                <a href="/mypage/isDelete" class="oauth-exit"> 회원탈퇴 </a>
            </c:otherwise>
        </c:choose>
<%--        <a href="/mypage/passwordCheck">oauth회원정보수정인증</a>--%>
        <a href="javascript:history.go(-1)" class="go-back">뒤로가기</a>
    </div>

<script type="text/javascript">
    console.log("password >> "+ "${member.password}")
    console.log("userId >> "+ "${principal.member.userId}")

    $(document).ready(function () {

    });
</script>
<%@include file="../layout/footer.jsp"%>
