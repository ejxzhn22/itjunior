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
            <h1>FIND PW</h1>
            <h1>FIND PW</h1>
            <h1>FIND PW</h1>
            <h1>FIND PW</h1>
        </div>
        <div class="pw-find-desc">
            <span>입력하신 아이디와 비밀번호가 일치하면 <br>회원가입시 등록한 <strong>이메일로 인증번호가 전송</strong>됩니다</span>
        </div>
        <div class="container" id="findId">
            <form id="passForm">
                    <input type="hidden" name="member_idx" value="${principal.member.member_idx}">
                    <input type="text" class="find-input1" placeholder="ID를 입력해주세요." id="userId" name="userId" autofocus autocomplete="off" required>
                    <input type="email" class="find-input2" placeholder="이름을 입력해주세요." id="name" name="name" autocomplete="off" required>
                    <div class="check-yn" id="check-yn"></div>
                <div class="find-btns-container">
                    <button type="button"  onclick="IdAndNameCheck()" class="btn btn-primary">비밀번호찾기</button>
                    <img src="${pageContext.request.contextPath}/image/right-arrow.png" alt="">
                    <!-- <a href="#" type="button">뒤로가기</a> -->
                </div>
                <a class="go-back" href="javascript:history.go(-1)">뒤로가기</a>
            </form>
        </div>
    </div>
</div>
<%--<div class="content-section">--%>
<%--<h1>비밀번호 찾기</h1>--%>
<%--    <div class="container" id="findPwd">--%>
<%--        <div class="text-center">--%>
<%--            <h3>이메일로 비밀번호 찾기</h3>--%>
<%--            <p>아이디와 이름을 입력해 주세요.</p>--%>
<%--            <p>입력하신 아이디와 비밀번호가 일치하면 회원가입 당시 등록한 이메일로 인증번호가 전송됩니다.</p>--%>
<%--        </div>--%>
<%--        <div>--%>
<%--            <form id="passForm">--%>
<%--                <div>--%>
<%--                    <input type="hidden" name="member_idx" value="${principal.member.member_idx}">--%>
<%--                    <div class="form-group">--%>
<%--                        <label>UserId</label>--%>
<%--                        <input type="text" class="form-control" placeholder="아이디" id="userId" name="userId" autofocus required>--%>
<%--                    </div>--%>
<%--                    <div class="form-group">--%>
<%--                        <label>Name</label>--%>
<%--                        <input type="text" class="form-control" placeholder="이름" id="name" name="name" autofocus required>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--                <div>--%>
<%--                    <button onclick="history.back()">뒤로가기</button>--%>
<%--                    <button type="button" onclick="IdAndNameCheck()" class="btn btn-primary">다음</button>--%>
<%--                </div>--%>
<%--                <div>--%>
<%--                    <p>아이디를 모르시나요?</p><a href="/auth/member/find-id" type="button">아이디찾기</a>--%>
<%--                    <div class="check-yn" id="check-yn"></div>--%>
<%--                </div>--%>
<%--            </form>--%>
<%--        </div>--%>
<%--    </div>--%>

<%--    </div>--%>

<%@include file="../layout/footer.jsp"%>
<script type="text/javascript">

    function IdAndNameCheck() {
        let member = {userId: $("#userId").val(), name: $("#name").val()};

        $.ajax({
            type: "POST",
            url: "/auth/member/find-pw",
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(member),
            success: function (result){
                console.log(result);
                location.href="/auth/member/find-password-certification/"+result;
            },
            error: function (e) {
                console.log(e+"에러");
                $("#check-yn").text("회원 정보를 찾을 수 없습니다.");
                return false;

            }
        });
    }

</script>