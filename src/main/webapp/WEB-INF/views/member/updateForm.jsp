<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false" isErrorPage="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@include file="../layout/header.jsp"%>

<h1>회원정보 수정</h1>
    <div class="content-section">
        <form id="join-form" class="join-section" action="/member" method="post">
            <input type="hidden" id="originPwd" name="originPwd" value="${member.password}">
            <input type="hidden" name="member_idx" value="${principal.member.member_idx}">
            <div class="join join-id">
                <span>아이디</span>
                <input type="text" class="join-id-input" id="userId" name="userId" value="${principal.member.userId}" autocomplete="off" disabled>
                <span id="id-check"></span>
            </div>
            <div class="join join-pw">
                <span>비밀번호</span>
                <input type="password" class="join-pw-input" id="password" name="password" autocomplete="off" maxlength="16" disabled required placeholder="영문대/소문자, 숫자, 특수문자 중 2가지 이상조합. 8자이상 16이하.">
                <button type="button" id="passwordChange">변경</button>
            </div>
<%--            <div class="join join-pw-check">--%>
<%--                <span>비밀번호 확인<button type="button" id="publicPw2">눈깔</button></span>--%>
<%--                <input type="password" class="join-pw-check-input" id="password2" maxlength="16" autocomplete="off" required>--%>
<%--                <span id="password-check2"></span>--%>
<%--            </div>--%>
            <div class="join join-pw">
                <span>이름</span>
                <input type="text" class="join-pw-input" id="name" name="name" value="${principal.member.name}" autocomplete="off" placeholder="사용자 성함을 기재해 주세요." maxlength="20" autofocus="on" required>
                <span id="name-check"></span>
            </div>
            <div class="join join-pw">
                <span>이메일</span>
                <div class="join-box">
                    <input type="text" class="join-pw-input" id="email" value="${principal.member.email}" autocomplete="off" required>
                    <select id="select-mail">
                        <option value="">직접입력</option>
                        <option value="@naver.com">@naver.com</option>
                        <option value="@hanmail.net">@hanmail.net</option>
                        <option value="@daum.net">@daum.net</option>
                        <option value="@nate.com">@nate.com</option>
                        <option value="@hotmail.com">@hotmail.com</option>
                        <option value="@gmail.com">@gmail.com</option>
                        <option value="@icloud.com">@icloud.com</option>
                    </select>
                    <input type="hidden" id="email-sum" name="email" value="${principal.member.email}">
                </div>
                <span id="email-check"></span>
            </div>
            <div class="join join-pw">
                <span>닉네임</span>
                <input type="text" class="join-pw-input" id="nickname" name="nickname" value="${principal.member.nickname}" placeholder="활동 닉네임을 기재해 주세요." autocomplete="off" maxlength="20" required>
                <span id="nickname-check"></span>
            </div>
            <div class="join join-btns">
                <input type="submit" id="join-btn" value="회원수정" autocomplete="off" >
                <button onclick="history.go(-1);">뒤로가기</button>
            </div>
        </form>
    </div>
<%@include file="../layout/footer.jsp"%>
<script type="text/javascript">
    <%--console.log("isfirst = "+${principal.isFirstOauthLogin});--%>

    $(document).ready(function () {

        $("#join-form").submit(function(){
            if($.trim($("#password").val()) !== $("#password").val() || $.trim($("#email").val()) !== $("#email").val() || $.trim($("#userId").val()) !== $("#userId").val()){
                alert("공백은 입력이 불가능합니다.");
                return false;
            }else {
                alert("회원정보 수정 성공하였습니다.");
            }
        });

        $("#password").keyup(function() {
            let pwdRegexp = RegExp(/^(?=.*[a-zA-Z0-9])(?=.*[a-zA-Z!@#$%^&*])(?=.*[0-9~!@#$%^&*()_+=|?/,.<>;:'"{}]).{8,16}$/);
            let passwordVal = $("#password").val();

            let speTest = pwdRegexp.test(passwordVal);

            if (passwordVal.length < 8 || passwordVal.length > 16) {
                $("#password-check").html("비밀번호 8 ~ 16 자리를 입력해 주세요.");
                $("#join-btn").attr("disabled", "disabled");
                return false;
            }else if (!speTest) {
                $("#password-check").html("영문 대/소문자, 숫자, 특수기호 중 2가지 이상을 사용해 주세요.");
                $("#join-btn").attr("disabled", "disabled");
                return false;
            }else if(/(\w)\1\1\1/.test(passwordVal)){
                $("#password-check").html("같은 문자나 숫자를 네번이상 사용하실 수 없습니다.");
                $("#join-btn").attr("disabled", "disabled");
                return false;
            }else {
                $("#password-check").html("사용 가능합니다.");
                $("#join-btn").removeAttr("disabled");
            }
        });

        $("#password2").keyup(function() {
            if($("#password").val() !== $("#password2").val()){
                $("#password-check2").html("비밀번호가 다릅니다.");
                $("#join-btn").attr("disabled", "disabled");
                return false;
            }else {
                $("#password-check2").html("사용 가능합니다.");
                $("#join-btn").removeAttr("disabled");
            }

        });

        $("#name").keyup(function() {
            let numRegExp = RegExp(/^[a-zA-Z가-힣]{1,20}$/);
            let nameVal = $("#name").val();
            let numTest = numRegExp.test(nameVal);
            if(!numTest){
                $("#name-check").html("숫자와 특수문자는 사용할 수 없습니다. 최대 20자");
                $("#join-btn").attr("disabled", "disabled");
                return false;
            }else {
                $("#name-check").html("사용 가능합니다.");
                $("#join-btn").removeAttr("disabled");
            }
        });


        $("#email").keyup(function(){
            let email1 = $("#email").val();
            $("#email-sum").val(email1);

            console.log("email : "+$("#email-sum").val());
            let emailRegexp = RegExp(/^[a-z0-9_+.-]+@([a-z0-9-]+\.)+[a-z0-9]{2,4}$/);
            let emailTest = emailRegexp.test($("#email-sum").val());
            $.ajax({
                url : "/auth/join/emailCheck",
                type: "POST",
                contentType: "application/json; charset=utf-8",
                data : JSON.stringify({
                    email : $("#email-sum").val()
                }),
                success : function(result) {
                    console.log(result);
                    if (result === "same") {
                        $("#email-check").html("중복된 이메일이 있습니다.");
                        $("#join-btn").attr("disabled", "disabled");
                    } else if (!emailTest) {
                        $("#email-check").html("올바른 이메일 형식이 아닙니다.");
                        $("#join-btn").attr("disabled", "disabled");
                    } else if (!$("#email").val()) {
                        $("#email-check").html("이메일 정보는 필수 입니다.");
                        $("#join-btn").attr("disabled", "disabled");
                    } else {
                        $("#email-check").html("사용가능한 이메일입니다.");
                        $("#join-btn").removeAttr("disabled");
                    }
                },
                error: function (error) {
                    console.log(error.status);
                    console.log("서버호출 에러.");
                }
            })
        });

        //email 옵션을 선택할 때
        $("#select-mail").change(function (){
            let email1 = $("#email").val();
            let email2 = $("#select-mail").val();

            if (email2 != "") {
                console.log("직접입력 아닐때로 넘어옴")
                $("#email").val(email1+email2);
                $("#email").attr("disabled",true);
            }else if (email2 == "") {
                $("#email").attr("disabled", false);
            }
            console.log("옵션 체크 했을때 ajax로 넘어갈 email1 값 >>> "+$("#email").val());

            $.ajax({
                url : "/auth/email/direct/"+$("#email").val(),
                type: "GET",
                success : function(result) {
                    console.log("ajax호출 후 중복 결과 >>> " + result["result"]);
                    console.log("ajax호출 후 split 된 값 >>> " + result["email"]);
                    $("#email").val(result["email"]+email2);
                    $("#email-sum").val(result["email"]+email2);
                    if (result["result"] === "same") {
                        $("#email-check").html("중복된 이메일이 있습니다.");
                        $("#join-btn").attr("disabled", "disabled");
                    }else {
                        $("#email-check").html("사용가능한 이메일입니다.");
                        $("#join-btn").removeAttr("disabled");
                    }
                    console.log("최종 email 합한 값 >>> "+$("#email").val());
                },
                error: function (error) {
                    console.log(error.status);
                    console.log("서버호출 에러.");
                }
            });
        });

        $("#nickname").keyup(function(){
            let nicknameRegexp = RegExp(/^[a-zA-Z가-힣0-9]{1,20}$/);
            let nicknameTest = nicknameRegexp.test($("#nickname").val());
            $.ajax({
                url : "/auth/join/nickNameCheck",
                type : "POST",
                contentType: "application/json; charset=utf-8",
                data : JSON.stringify({
                    email : $("#nickname").val()
                }),
                success : function(result) {
                    console.log(result);
                    if (result == "same") {
                        $("#nickname-check").html("중복된 닉네임이 있습니다.");
                        $("#join-btn").attr("disabled", "disabled");
                    }else if (!nicknameTest) {
                        $("#nickname-check").html("영문,한글,숫자 사용 가능. 1 ~ 20자.");
                        $("#join-btn").attr("disabled", "disabled");
                    } else {
                        $("#nickname-check").html("사용가능한 닉네임입니다.");
                        $("#join-btn").removeAttr("disabled");
                    }
                },
                error: function (error) {
                    console.log(error.status);
                    console.log("서버호출 에러.");
                }
            })
        });

        $("#publicPw").on("click",function (){
            $("#password").toggleClass("active");
            if ($("#password").hasClass("active")) {
                $("#password").attr("type","text");
            }else {
                $("#password").attr("type","password");
            }
        });

        $("#publicPw2").on("click",function (){
            $("#password2").toggleClass("active");
            if ($("#password2").hasClass("active")) {
                $("#password2").attr("type","text");
            }else {
                $("#password2").attr("type","password");
            }
        });

    });
</script>
