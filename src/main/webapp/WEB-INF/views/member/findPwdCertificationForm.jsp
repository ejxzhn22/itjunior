<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@include file="../layout/header.jsp"%>

<h1>비밀번호 찾기</h1>
    <div class="container" id="findPwd">
        <div class="text-center">
            <h3>이메일로 인증하기</h3>
            <p>가입시 등록한 이메일로 인증번호가 발송되었습니다.</p>
        </div>
        <div>
            <form id="passForm">
                <div>
                    <p>가입시 등록한 이메일 입니다.</p>
                    <br>
                    <h4>${email}</h4>
                </div>
                <div>
                    <h4>인증번호 입력</h4>
                    <p>위 메일로 수신된 이메일의 인증번호를 입력해주세요.</p>
                    <input type="text" id="inputAuthNum" placeholder="인증번호 입력">
                </div>
                <div>
                    <div id="check-yn"></div>
                    <button type="button" onclick="emailCertification()" class="btn btn-primary">다음</button>
                </div>
                <div>
                    <p>아이디를 모르시나요?</p><a href="/auth/member/find-id">아이디찾기</a>
                </div>
            </form>
        </div>


    </div>

<%@include file="../layout/footer.jsp"%>
<script type="text/javascript">

    function emailCertification() {
        let memberIdx = ${member.member_idx};
        let sendNum = ${certificationNum};
        let inputNum = $("#inputAuthNum").val();
        console.log(sendNum);
        console.log(inputNum);
        if (sendNum == inputNum) {
            alert("인증번호 일치")
            location.href="/auth/member/change-pw/"+${member.member_idx};
            return false;
        }else {
            $(".check-yn").text("잘못된 인증번호입니다. 다시 인증해주세요.");
            return false;
        }
    }

</script>