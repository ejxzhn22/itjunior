<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<%@include file="layout/header.jsp"%>

<div class="content1-section">
    <img src="${pageContext.request.contextPath}/image/itju-main-card.png" alt="메인이미지" class="main-card-img">
<%--    <span class="main-words">IT취업의 첫 걸음<br><p class="main-words-itju">잇주</p>가 함께합니다😀</span>--%>
    <div class="main-words-div">
        <span class="main-words">IT취업의 첫 걸음</span>
        <span class="main-words2"><p class="main-words-itju">잇주</p>가 함께합니다😀</span>
    </div>

    <a href="/auth/joinForm" class="main-join">JOIN 🙌</a>
    <img src="${pageContext.request.contextPath}/image/icon-downarrow.png" alt="" class="down-arrow" onclick="scrollDown();">
</div>


<img src="${pageContext.request.contextPath}/image/main-o.svg" alt="" class="main-o">
<img src="${pageContext.request.contextPath}/image/triangle.png" alt="" class="main-tri">
<%--<div class="content2-section">--%>
<%--    <div class="spelling-it-container">--%>
<%--        <div class="spelling-i">--%>
<%--            <div class="spelling-container1">--%>
<%--                <img src="${pageContext.request.contextPath}/image/itjui.png" alt="I" class="itju-i spelling-img">--%>
<%--                <span>nformation</span>--%>
<%--            </div>--%>
<%--            <div class="desc-container desc-container1">--%>
<%--                <p>신입 IT취업 정보를 제공.</p>--%>
<%--                <hr>--%>
<%--                <span>대형 구인사이트인 사람인 API를 이용하여 신입 IT채용공고만 제공합니다. 그 뿐아니라 IT관련학원의 정보와, 취업관련하여 여러회원들의 정보를 얻을 수 있습니다.</span>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--        <div class="spelling-t sa sa-right">--%>
<%--            <div class="spelling-container2">--%>
<%--                <img src="${pageContext.request.contextPath}/image/itjut.png" alt="T" class="itju-t spelling-img">--%>
<%--                <span>ruth</span>--%>
<%--            </div>--%>
<%--            <div class="desc-container">--%>
<%--                <p>믿을 수 있는 진실된 잇주</p>--%>
<%--                <hr>--%>
<%--                <span>고객님의 소중한 개인정보를 데이터베이스에 저장하는 과정에서 철저히 암호화하여 저장합니다.</span>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--    <div class="spelling-ju-container">--%>
<%--        <div class="spelling-j sa sa-right">--%>
<%--            <div class="spelling-container3">--%>
<%--                <img src="${pageContext.request.contextPath}/image/itjuj.png" alt="J" class="itju-j spelling-img">--%>
<%--                <span>unior</span>--%>
<%--            </div>--%>
<%--            <div class="desc-container">--%>
<%--                <p>신입의 눈높이에 맞추다.</p>--%>
<%--                <hr>--%>
<%--                <span>신입 웹개발자 3명이 개발에 참여한만큼, 같은 눈높이로 필요한 정보를 제공합니다.</span>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--        <div class="spelling-u sa sa-right">--%>
<%--            <div class="spelling-container4">--%>
<%--                <img src="${pageContext.request.contextPath}/image/itjuu.png" alt="U" class="itju-u spelling-img">--%>
<%--                <span>seful</span>--%>
<%--            </div>--%>
<%--            <div class="desc-container">--%>
<%--                <p>사용에 용이하고 직관성.</p>--%>
<%--                <hr>--%>
<%--                <span>간편하고 눈에 익는 UI를 지향하고, 많은 정보를 직관성있게 보여드리기 위해  더욱 고심하겠습니다.</span>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--</div>--%>

<div class="content2-section">
<%--    <section class="itju-menu">--%>
<%--        <ul class="menu-ul">--%>
<%--            <li data-text="Information">--%>
<%--                <a href="#" class="menu-i">--%>
<%--                    <img src="${pageContext.request.contextPath}/image/itjui.png">--%>
<%--                </a>--%>
<%--            </li>--%>
<%--            <li data-text="Truly">--%>
<%--                <a href="#" class="menu-t">--%>
<%--                    <img src="${pageContext.request.contextPath}/image/itjut.png">--%>
<%--                </a>--%>
<%--            </li>--%>
<%--            <li data-text="Junior">--%>
<%--                <a href="#" class="menu-j">--%>
<%--                    <img src="${pageContext.request.contextPath}/image/itjuj.png">--%>
<%--                </a>--%>
<%--            </li>--%>
<%--            <li data-text="Useful">--%>
<%--                <a href="#" class="menu-u">--%>
<%--                    <img src="${pageContext.request.contextPath}/image/itjuu.png">--%>
<%--                </a>--%>
<%--            </li>--%>
<%--        </ul>--%>
<%--    </section>--%>
    <div class="content2-title">
        <div class="content2-line"></div>
        <span class="content2-desc1">ITJU를 만들면서</span>
        <span class="content2-desc2">모든게 어려운 초보 IT인들에게<br>도움이 되는 사이트를 만들고 싶습니다.</span>
    </div>
    <section>
        <span class="curve">
            <img src="${pageContext.request.contextPath}/image/curve.png">
        </span>
    </section>
    <div class="content2-card-box">
        <div class="c-card c-card-main">
            <p class="c-card-header">
                신입 IT취업자를 위한 사이트가 있으면 어떨까?<br>
                “신입 IT인의 눈높이로 만든 사이트”<br>
                뭐라쓰지

            </p>
            <div class="c-card-footer">
                <span class="card-footer-span" style="font-size:19px;">운영진</span>
            </div>
            <div class="ribbon ribbon-bottom-right"><span class="noto500">ITJU ⭐ ️</span></div>
        </div>
        <div class="c-card">
            <p class="c-card-header">
                잇주를 만들면서 느낀점or배운점 / 중점을 둔 부분 등 자유롭게 쓰기 (itju관련해서)
            </p>
            <div class="c-card-footer">
                <img src="${pageContext.request.contextPath}/image/jaebok-img.png" class="card-footer-img">
                <span class="card-footer-span">백엔드개발 이재복</span>
            </div>
        </div>
        <div class="c-card">
            <p class="c-card-header">
                잇주를 만들면서 느낀점or배운점 / 중점을 둔 부분 등 자유롭게 쓰기 (itju관련해서)
            </p>
            <div class="c-card-footer">
                <img src="${pageContext.request.contextPath}/image/sujin-img.png" class="card-footer-img">
                <span class="card-footer-span">백엔드개발 김수진</span>
            </div>
        </div>
        <div class="c-card">
            <p class="c-card-header">
                안녕하세요 신입 <strong>프론트엔드 개발자 문제선</strong>입니다.
                ITJU 프론트엔드 개발에 참여하여 기획 / 퍼블리싱을 맡아 진행하였습니다.
                <br>
                <br>
                <strong>느낀점/배운점</strong><br><br>
                독학하면서는 보이지 않았던 저의 부족한 부분을 알게되는 계기가 되었습니다.
                많은 웹사이트를 참고하고, 더 정형화된 코드를 짜보며 부족한 안목을 키우는데 큰 도움이 되었습니다.
                <br>
                <br>

                <strong>중점을 둔 부분</strong><br><br>
                매주 팀회의와 GIT코드관리를 통해 현업의 팀프로젝트 연습을 하는 것에 의의를 두며 진행하였습니다.
                개인프로젝트 진행할 때와는 다르게 어려운 부분도 많았지만 팀과 프로젝트를 완성했을 땐 그 희열이 배가 되었던 것 같습니다.
            </p>
            <div class="c-card-footer">
                <img src="${pageContext.request.contextPath}/image/jeseon-img.jpeg" class="card-footer-img">
                <span class="card-footer-span">프론트엔드개발 문제선</span>
            </div>
        </div>
<%--        <div class="c-card">--%>
<%--            <p class="c-card-header">--%>
<%--                잇주를 개발하면서--%>
<%--            </p>--%>
<%--            <div class="c-card-footer">--%>
<%--                <img src="${pageContext.request.contextPath}/image/jeseon-img.jpeg" class="card-footer-img">--%>
<%--                <span class="card-footer-span">프론트엔드개발 문제선</span>--%>
<%--            </div>--%>
<%--        </div>--%>
    </div>
</div>
<style>
    .footer-section{
        margin-top:0px !important;
    }
</style>
<%@include file="layout/footer.jsp"%>
<script src="${pageContext.request.contextPath}/css/vanilla-tilt.min.js"></script>
<script>
    function memberSuccessAlert() {
        let updateMemberResult = '${updateMsg}';
        let insertMemberResult = '${insertMsg}';
        if (updateMemberResult === 'success') {
            alert("회원 수정을 성공하였습니다.");
        }
        if (insertMemberResult === 'success') {
            alert("회원 가입을 축하드립니다!!");
        }
    }
    memberSuccessAlert();


    // Scroll Animation (sa, 스크롤 애니메이션)
    const saTriggerMargin = 300;
    const saElementList = document.querySelectorAll('.sa');

    const saFunc = function() {
        for (const element of saElementList) {
            if (!element.classList.contains('show')) {
                if (window.innerHeight > element.getBoundingClientRect().top + saTriggerMargin) {
                    element.classList.add('show');
                }
            }
        }
    }

    window.addEventListener('load', saFunc);
    // window.addEventListener('scroll', saFunc);

    function scrollDown(){
        console.log(window.innerHeight);
        window.scrollTo({
            top: window.innerHeight,
            left: 0,
            behavior: 'smooth'
        });
    }


    VanillaTilt.init(document.querySelectorAll(".menu-ul li a"),{
        max:30,
        speed:300,
        glare:true,
        "max-glare":1,
    });


    window.addEventListener('scroll',function(){
        const scroll1 = document.querySelector('.curve');

        const value = 1 + window.scrollY/-500;
        $('.curve').css("transform",`scaleY(${value})`);
    })

    $("li[data-text='Information']").click(function(){
        $(".desc-container1").css("display", "none");
    })
</script>

