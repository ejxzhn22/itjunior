<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>
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
<div class="content-section">
    <div class="login-section">
        <h1>로그인</h1>
        <form action="/auth/loginProc" method="POST" class="login-form">
            <div class="id-section">
                <span>아이디</span>
                <input type="text" class="id-input" id="userId" name="userId">
            </div>
            <div class="pw-section">
                <span>비밀번호</span>
                <input type="password" class="pw-input" id="password" name="password">
            </div>
            <div class="member-bottom-section">
                <p><input type="checkbox" value="" id="check" name="saveId"><label for="check">정보 기억하기</label></p>
                <div>
                    <a href="/auth/member/find-id">ID 찾기</a>
                    &nbsp;&nbsp; / &nbsp;
                    <a href="/auth/member/find-password">PW 찾기</a>
                </div>
            </div>
            <input type="submit" value="로그인" class="login-submit">
        </form>
    </div>
    <div class="another-login-section">
        <div class="kakao-login" onclick="location.href='/oauth2/authorization/kakao';">
            <img src="${pageContext.request.contextPath}/image/icon-kakao.png" alt="">
            <span>카카오 로그인</span>
        </div>
        <div class="naver-login" onclick="location.href='/oauth2/authorization/naver';">
            <img src="${pageContext.request.contextPath}/image/icon-naver.png" alt="">
            <span>네이버 로그인</span>
        </div>
        <div class="google-login" onclick="location.href='/oauth2/authorization/google';">
            <img src="${pageContext.request.contextPath}/image/icon-google.png" alt="">
            <span>구글 로그인</span>
        </div>
    </div>
</div>

<%@include file="../layout/footer.jsp"%>