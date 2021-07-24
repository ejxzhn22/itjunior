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
        <div class="liquid">
            <h1>FIND ID</h1>
            <h1>FIND ID</h1>
            <h1>FIND ID</h1>
            <h1>FIND ID</h1>
        </div>
        <div class="container" id="findId">
            <form id="passForm" method="post" action="/member/updateForm">
                <input type="hidden" name="member_idx" value="${principal.member.member_idx}">
                <input type="text" class="find-input1" placeholder="이름을 입력해주세요." id="name" name="name" autofocus autocomplete="off" required>
                <input type="email" class="find-input2" placeholder="이메일 주소를 입력해주세요." id="email" name="email" autocomplete="off" required>
                <div class="check-yn" id="check-yn"></div>
                <div class="find-btns-container">
                    <button type="button" onclick="nameAndEmailCheck()" class="btn btn-primary">아이디찾기</button>
                    <img src="${pageContext.request.contextPath}/image/right-arrow.png" alt="">
                    <!-- <a href="#" type="button">뒤로가기</a> -->
                </div>
                <a class="go-back" href="javascript:history.go(-1)">뒤로가기</a>
            </form>
        </div>
    </div>
</div>
<%@include file="../layout/footer.jsp"%>
<script type="text/javascript">

    function nameAndEmailCheck() {
        let passForm = $("#passForm");
        let member = {name: $("#name").val(), email: $("#email").val()};


        $.ajax({
            type: "POST",
            url: "/auth/member/find-id",
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(member),
            success: function (result){
                console.log(result);

                let htmls="";
                $("#findId").empty();
                htmls += '<span class="result-id">회원님의 아이디는<strong>'+result+'</strong>입니다</span>';
                htmls += '<a href="/auth/loginForm" class="go-login">로그인하러가기</a>';
                htmls += '<a href="/auth/member/find-password" class="go-pwfind">비밀번호찾기</a>';
                $("#findId").append(htmls);
            },
            error: function (e) {
                console.log(e+"에러");
                $("#check-yn").text("회원 아이디를 찾을 수 없습니다.");
                return false;

            }
        });
    }

</script>