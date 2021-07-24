<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@include file="../layout/header.jsp"%>
<%--<h1>회원가입</h1>--%>
<%--    <div class="container">--%>
<%--        <form action="/auth/join" method="post">--%>
<%--            <div class="form-group">--%>
<%--                <label>User Id</label>--%>
<%--                <input type="text" class="form-control" placeholder="ID" id="userId" name="userId">--%>
<%--            </div>--%>
<%--            <div class="form-group">--%>
<%--                <label>Password</label>--%>
<%--                <input type="password" class="form-control" placeholder="PASSWORD" id="password" name="password">--%>
<%--            </div>--%>
<%--            <div class="form-group">--%>
<%--                <label>Email</label>--%>
<%--                <input type="email" class="form-control" placeholder="Email" id="email" name="email">--%>
<%--            </div>--%>
<%--            <div class="form-group">--%>
<%--                <label>Name</label>--%>
<%--                <input type="text" class="form-control" placeholder="Name" id="name" name="name">--%>
<%--            </div>--%>
<%--            <div class="form-group">--%>
<%--                <label>NickName</label>--%>
<%--                <input type="text" class="form-control" placeholder="NickName" id="nickname" name="nickname">--%>
<%--            </div>--%>
<%--            <button type="submit" class="btn btn-primary">회원가입</button>--%>
<%--        </form>--%>
<%--    </div>--%>


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
    <form id="join-form" class="join-section" action="/auth/join" method="post">
        <h1>회원가입</h1>

        <div class="join join-pw">
            <span>이름</span>
            <input type="text" class="join-pw-input" id="name" name="name" autocomplete="off" autofocus="on" required>
        </div>

        <div class="join join-id">
            <span>아이디</span>
            <input type="text" class="join-id-input" id="userId" name="userId" autocomplete="off" required>
            <span id="id-check"></span>
        </div>
        <div class="join join-pw">
            <span>비밀번호</span>
            <input type="password" class="join-pw-input" id="password" name="password" autocomplete="off" maxlength="12" required placeholder="8~12자 비밀번호를 사용해주세요.">
        </div>
        <div class="join join-pw-check">
            <span>비밀번호 확인</span>
            <input type="password" class="join-pw-check-input" id="password2" maxlength="12" autocomplete="off" required>
        </div>
        <div class="join join-pw">
            <span>이메일</span>
            <div class="join-box">
                <input type="text" class="join-pw-input" id="email" placeholder="@ 앞의 이메일을 입력해주세요." autocomplete="off" required>
                <select id="select-mail">
                    <option value="@naver.com">@naver.com</option>
                    <option value="@hanmail.net">@hanmail.net</option>
                    <option value="@daum.net">@daum.net</option>
                    <option value="@nate.com">@nate.com</option>
                    <option value="@hotmail.com">@hotmail.com</option>
                    <option value="@gmail.com">@gmail.com</option>
                    <option value="@icloud.com">@icloud.com</option>
                    <option value="">직접입력</option>
                </select>
                <input type="hidden" id="email-sum" name="email">
            </div>
            <span id="email-check"></span>
        </div>
        <div class="join join-pw">
            <span>닉네임</span>
            <input type="text" class="join-pw-input" id="nickname" name="nickname" autocomplete="off" required>
            <span id="nickname-check"></span>
        </div>
        <div class="join join-btns">
            <input type="submit" id="join-btn" value="회원가입" autocomplete="off" >
            <button onclick="history.go(-1);">뒤로가기</button>
        </div>
    </form>
</div>


<%@include file="../layout/footer.jsp"%>
<script>
    $(function(){
        $("#join-form").submit(function(){
            let email1 = $("#email").val();
            let email2 = $("#select-mail").val();

            $("#email-sum").val(email1+email2);


            if($("#password").val() !== $("#password2").val()){
                alert("비밀번호가 다릅니다.");
                $("#password").val("").focus();
                $("#password2").val("");
                return false;
            }else if ($("#password").val().length < 8) {
                alert("비밀번호는 8자 이상으로 설정해야 합니다.");
                $("#password").val("").focus();
                return false;
            }else if($.trim($("#password").val()) !== $("#password").val() || $.trim($("#email").val()) !== $("#email").val() || $.trim($("#userId").val()) !== $("#userId").val()){
                alert("공백은 입력이 불가능합니다.");
                return false;
            }
        })

        $("#userId").keyup(function() {
            $.ajax({
                url : "/auth/join/idCheck",
                type : "POST",
                data : {
                    id : $("#id").val()
                },
                success : function(result) {
                    if (result === "same") {
                        $("#id-check").html("중복된 아이디가 있습니다.");
                        $("#join-btn").attr("disabled", "disabled");
                    } else {
                        $("#id-check").html("");
                        $("#join-btn").removeAttr("disabled");
                    }
                },
            })
        });

        $("#email").keyup(function(){
            $.ajax({
                url : "/auth/join/emailCheck",
                type : "POST",
                data : {
                    email : $("#email").val()
                },
                success : function(result) {
                    console.log(result);
                    if (result === "same") {
                        $("#email-check").html("중복된 이메일이 있습니다.");
                    } else {
                        $("#email-check").html("사용가능한 이메일입니다.");
                    }
                },
            })
        });
        $("#nickname").keyup(function(){
            $.ajax({
                url : "/auth/join/nickNameCheck",
                type : "POST",
                data : {
                    email : $("#nickname").val()
                },
                success : function(result) {
                    console.log(result);
                    if (result == "same") {
                        $("#nickname-check").html("중복된 닉네임이 있습니다.");
                    } else {
                        $("#nickname-check").html("사용가능한 닉네임입니다.");
                    }
                },
            })
        });
    })
</script>