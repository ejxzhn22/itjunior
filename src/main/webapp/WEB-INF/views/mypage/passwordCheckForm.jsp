<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@include file="../layout/header.jsp"%>

<h1>회원님의 안전한 정보를 위해 비밀번호를 확인 해주세요.</h1>
    <div class="container">
        <form id="passForm" method="post" action="/member/updateForm">
            <input type="hidden" name="member_idx" value="${principal.member.member_idx}">
            <div class="form-group">
                <label>User Id</label>
                <input type="text" class="form-control" readonly="readonly" id="userId" name="userId" value="${principal.member.userId}">
            </div>
            <div class="form-group">
                <label>Password</label>
                <input type="password" class="form-control" placeholder="PASSWORD" id="password" name="password">
            </div>
            <div class="check-yn" id="check-yn"></div>
            <a href="#" type="button">뒤로가기</a>
            <button type="button" onclick="passwordCheck()" class="btn btn-primary">인증하기</button>
        </form>
    </div>

<%@include file="../layout/footer.jsp"%>
<script type="text/javascript">

    function passwordCheck() {
        let passForm = $("#passForm");
        let pass = {password: $("#password").val()};

        $.ajax({
            type: "POST",
            url: "/mypage/passwordCheck",
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(pass),
            success: function (result){
                console.log(result);
                if (result === "checkSuccess") {
                    passForm.submit();
                }else if (result === "fail"){
                    $("#check-yn").text("비밀번호를 정확하게 입력해주세요.");
                    return false;
                }
            },
            error: function (e) {
                console.log(e+"에러");
            }
        });
    }

</script>