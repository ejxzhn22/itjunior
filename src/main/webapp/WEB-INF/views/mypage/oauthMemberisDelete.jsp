<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@include file="../layout/header.jsp"%>
${principal.attributes}
    <h1>회원 탈퇴 안내</h1>
        ${principal.member.name}님! 정말로 탈퇴 하시겠습니까?
        <button onclick="memberOut()">탈퇴</button>


<%@include file="../layout/footer.jsp"%>
<script type="text/javascript">

    function memberOut() {
        $.ajax({
            type: "DELETE",
            url: "/member/${principal.member.member_idx}",
            contentType: "application/json; charset=utf-8",
            success: function (result){
                console.log(result);
                if (result === "success") {
                    alert("정상적으로 탈퇴가 되었습니다. 이용해주셔서 감사합니다.");
                    location.href="/logout";
                }else if (result === "fail"){
                    console.log("탈퇴 실패,,");
                    return false;
                }
            },
            error: function (e) {
                console.log(e+"에러");
            }
        });
    }

</script>