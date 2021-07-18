<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@include file="../layout/header.jsp"%>

<h1>비밀번호 변경</h1>
    <div class="container" id="findPwd">
        <div class="text-center">
            <h3>새로운 비밀번호를 입력해 주세요</h3>
        </div>
        <div>
            <form id="passForm">
                <div>
                    <input type="hidden" name="member_idx" value="${member_idx}">
                    <div class="form-group">
                        <label>비밀번호</label>
                        <input type="password" class="form-control" placeholder="영문대/소문자, 특수문자, 숫자 중 2가지 이상 조합하세요." id="password" name="password" autofocus required>
                    </div>
                    <div class="form-group">
                        <label>비밀번호확인</label>
                        <input type="password" class="form-control" placeholder="새 비밀번호 확인" id="passwordCk" autofocus required>
                    </div>
                </div>
                <div>
                    <button type="button" onclick="chagePassword()" class="btn btn-primary">확인</button>
                </div>
                <div>
                    <div class="check-yn" id="check-yn"></div>
                </div>
            </form>
        </div>


    </div>

<%@include file="../layout/footer.jsp"%>
<script type="text/javascript">

    function chagePassword() {

        let password = $("#password").val();
        let passwordCheck = $("#passwordCk").val();
        let member = {member_idx: ${member_idx}, password: $("#password").val()};

        console.log("member_idx = ",${member_idx});

        if (password === passwordCheck) {
            alert("비밀번호 확인 완료!!");
        }else {
            alert("비밀번호 확인 실패..");
            return false;
        }

        $.ajax({
            type: "PUT",
            url: "/auth/member/change-pw",
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(member),
            success: function (result){
                console.log(result);
                location.href="/auth/loginForm";
            },
            error: function (e) {
                console.log(e+"에러");
                return false;

            }
        });
    }

</script>