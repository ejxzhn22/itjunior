<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@include file="../layout/header.jsp"%>
${principal.attributes}
<c:choose>
    <c:when test="${principal.member.provider eq null}">
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
                    <input type="password" class="form-control" placeholder="PASSWORD" autofocus="on" autocomplete="off" id="password" name="password">
                </div>
                <div class="check-yn" id="check-yn"></div>
                <a href="#" type="button">뒤로가기</a>
                <button type="button" onclick="passwordCheck()" class="btn btn-primary">인증하기</button>
            </form>
        </div>
    </c:when>
<%--    <c:otherwise>--%>
<%--        <h1>회원님의 안전한 정보를 위해 인증 확인을 해주세요.</h1>--%>
<%--        <form id="oauthPassForm" method="post" action="/member/updateForm">--%>
<%--            <c:choose>--%>
<%--                <c:when test="${principal.member.provider eq 'google'}">--%>
<%--                    <div class="container">--%>
<%--                        <input type="hidden" name="userId" value="${principal.member.userId}"/>--%>
<%--                        <input type="hidden" name="password" value="${member.password}"/>--%>
<%--&lt;%&ndash;                        <input type="hidden" name="auth" value="y"/>&ndash;%&gt;--%>
<%--                        <div class="google-login">--%>
<%--                            <img src="${pageContext.request.contextPath}/image/icon-google.png" alt="">--%>
<%--                            <span>구글 로그인</span>--%>
<%--                            <button type="button" onclick="oauthCheck()" class="btn btn-primary">인증하기</button>--%>
<%--                            <a href="/oauth2/authorization/google">인증하기2</a>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                </c:when>--%>
<%--                <c:when test="${principal.member.provider eq 'kakao'}">--%>
<%--                    <div class="container">--%>
<%--                            <button type="button" onclick="" class="btn btn-primary">인증하기</button>--%>
<%--                    </div>--%>
<%--                </c:when>--%>
<%--                <c:when test="${principal.member.provider eq 'naver'}">--%>
<%--                    <div class="container">--%>
<%--                            <button type="button" onclick="" class="btn btn-primary">인증하기</button>--%>
<%--                    </div>--%>
<%--                </c:when>--%>
<%--            </c:choose>--%>
<%--        </form>--%>
<%--    </c:otherwise>--%>
</c:choose>



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