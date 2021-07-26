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
            <input type="text" class="join-id-input" id="userId" name="userId" placeholder="아이디 입력(4~50자)" autocomplete="off" required>
            <span id="id-check"></span>
        </div>
        <div class="join join-pw">
            <span>비밀번호</span>
            <input type="password" class="join-pw-input" id="password" name="password" autocomplete="off" maxlength="12" required placeholder="영문대/소문자, 숫자, 특수문자 중 2가지 이상조합. 8자이상 16이하.">
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
            let idRegexp = RegExp(/^[a-z0-9_]{4,50}$/);
            let userIdTest = idRegexp.test($("#userId").val());
            // let userIdVal = $("#userId").val();
            $.ajax({
                url: "/auth/join/idCheck",
                type: "POST",
                contentType: "application/json; charset=utf-8",
                data: JSON.stringify({
                    userId : $("#userId").val()
                }),
                success: function(result) {
                    if (result === "same") {
                        $("#id-check").html("중복된 아이디가 있습니다.");
                        $("#join-btn").attr("disabled", "disabled");
                    } else if (!userIdTest) {
                        $("#id-check").html("영문소문자,숫자,특수기호(_)만 사용 가능. 4자이상 50자이하.");
                        $("#join-btn").attr("disabled", "disabled");
                    } else {
                        $("#id-check").html("사용 가능합니다.");
                        $("#join-btn").removeAttr("disabled");
                    }
                },
                error: function (error) {
                    console.log(error.status);
                    console.log("서버호출 에러.");
                }
            })
        });

        $("#email").keyup(function(){
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
                    } else {
                        $("#email-check").html("사용가능한 이메일입니다.");
                        $("#join-btn").removeAttr("disabled");
                    }
                },
            })
        });
        $("#nickname").keyup(function(){
            $.ajax({
                url : "/auth/join/nickNameCheck",
                type : "POST",
                data : JSON.stringify({
                    email : $("#nickname").val()
                }),
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