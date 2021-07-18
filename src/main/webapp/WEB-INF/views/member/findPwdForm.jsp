<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@include file="../layout/header.jsp"%>

<h1>비밀번호 찾기</h1>
    <div class="container" id="findPwd">
        <div class="text-center">
            <h3>이메일로 비밀번호 찾기</h3>
            <p>비밀번호를 찾고자하는 아이디와 이름을 입력해 주세요.</p>
            <p>입력하신 아이디와 비밀번호가 일치하면 회원가입 당시 등록한 이메일로 인증번호가 전송됩니다.</p>
        </div>
        <div>
            <form id="passForm">
                <div>
                    <input type="hidden" name="member_idx" value="${principal.member.member_idx}">
                    <div class="form-group">
                        <label>UserId</label>
                        <input type="text" class="form-control" placeholder="아이디" id="userId" name="userId" autofocus required>
                    </div>
                    <div class="form-group">
                        <label>Name</label>
                        <input type="text" class="form-control" placeholder="이름" id="name" name="name" autofocus required>
                    </div>
                </div>
                <div>
                    <button onclick="history.back()">뒤로가기</button>
                    <button type="button" onclick="IdAndNameCheck()" class="btn btn-primary">다음</button>
                </div>
                <div>
                    <p>아이디를 모르시나요?</p><a href="/auth/member/find-id" type="button">아이디찾기</a>
                    <div class="check-yn" id="check-yn"></div>
                </div>
            </form>
        </div>


    </div>

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