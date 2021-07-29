<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>


<!-- 푸터 -->
<div class="footer-section">
    <div class="footer-logo">
        <img src="${pageContext.request.contextPath}/image/itju-sum.png" alt="" class="itju-footer-logo">
        <span>상업적인 홈페이지가 아닌<br> <strong>포트폴리오/팀 프로젝트 페이지</strong>입니다.</span>
    </div>

    <div class="footer-team">
        <div class="profile">
            <%--이미지넣기--%>
        </div>
        <div class="profile-desc">
            <span>Back - 이재복</span>
            <div class="profile-link">
                <a href="https://github.com/ufonetcom" target="_blank">
                    <img src="${pageContext.request.contextPath}/image/git-jaebok.png" alt="git icon" class="git-icon">
                </a>
                <img src="${pageContext.request.contextPath}/image/mail-jaebok.png" alt="" class="mail-icon">
            </div>
        </div>
    </div>
    <div class="footer-team">
        <div class="profile">
            <%--이미지넣기--%>
        </div>
        <div class="profile-desc">
            <span>Back - 김수진</span>
            <div class="profile-link">
                <a href="https://github.com/ejxzhn22" target="_blank">
                    <img src="${pageContext.request.contextPath}/image/git-sujin.png" alt="git icon" class="git-icon">
                </a>
                <img src="${pageContext.request.contextPath}/image/mail-sujin.png" alt="" class="mail-icon">
            </div>
        </div>
    </div>
    <div class="footer-team">
        <div class="profile">
            <img src="${pageContext.request.contextPath}/image/jeseon-img.jpeg" class="profile-img">
        </div>
        <div class="profile-desc">
            <span>Front - 문제선</span>
            <div class="profile-link">
                <a href="https://github.com/moonjjj" target="_blank">
                    <img src="${pageContext.request.contextPath}/image/git-jeseon.png" alt="git icon" class="git-icon">
                </a>
                <img src="${pageContext.request.contextPath}/image/mail-jeseon.png" alt="" class="mail-icon">
            </div>
        </div>
    </div>
</div>
</body>

</html>

<script>

    window.addEventListener('scroll',e=>{
        if(window.scrollY>0){
            document.getElementById("header").style.background="#fff";
            document.getElementById("header").style.height="30px";
            document.querySelector(".logo-img").style.marginLeft="50px";
            document.querySelector(".logo-img").style.marginTop="-5px";
            document.querySelector(".logo-img").style.width="110px";
            document.querySelector(".logo-img").style.height="45px";
        }
        else if(window.scrollY==0){
            document.getElementById("header").style.height="53px";
            document.querySelector(".logo-img").style.marginLeft="0px";
            document.querySelector(".logo-img").style.width="180px";
            document.querySelector(".logo-img").style.height="70px";
        }
    });


</script>
