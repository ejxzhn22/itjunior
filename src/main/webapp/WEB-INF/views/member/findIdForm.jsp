<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@include file="../layout/header.jsp"%>

<h1>아이디찾기!!</h1>
    <div class="container" id="findId">
        <form id="passForm" method="post" action="/member/updateForm">
            <input type="hidden" name="member_idx" value="${principal.member.member_idx}">
            <div class="form-group">
                <label>Name</label>
                <input type="text" class="form-control" placeholder="이름" id="name" name="name" autofocus required>
            </div>
            <div class="form-group">
                <label>Email</label>
                <input type="email" class="form-control" placeholder="가입한 이메일 주소" id="email" name="email" autofocus required>
            </div>
            <div class="check-yn" id="check-yn"></div>
            <a href="#" type="button">뒤로가기</a>
            <button type="button" onclick="nameAndEmailCheck()" class="btn btn-primary">아이디찾기</button>
        </form>
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
                htmls += '<h1>회원님의 아이디는'+result+'입니다</h1>';
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