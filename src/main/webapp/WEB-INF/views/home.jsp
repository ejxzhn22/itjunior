<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<%@include file="layout/header.jsp"%>

<div class="content1-section">
    <img src="image/itju-main-card.png" alt="메인이미지" class="main-card-img">
    <span class="main-words">IT취업의 첫 걸음<br><p class="main-words-itju">잇주</p>가 함께합니다😀</span>
    <a href="#" class="main-join">JOIN 🙌</a>
    <img src="image/icon-downarrow.png" alt="" class="down-arrow" onclick="scrollDown();">
</div>
<img src="image/main-o.svg" alt="" class="main-o">
<img src="image/triangle.png" alt="" class="main-tri">
<div class="content2-section">
    <div class="spelling-it-container">
        <div class="spelling-i">
            <div class="spelling-container1">
                <img src="image/itjui.png" alt="I" class="itju-i spelling-img">
                <span>nformation</span>
            </div>
            <div class="desc-container desc-container1">
                <p>신입 IT취업 정보를 제공.</p>
                <hr>
                <span>대형 구인사이트인 사람인 API를 이용하여 신입 IT채용공고만 제공합니다. 그 뿐아니라 IT관련학원의 정보와, 취업관련하여 여러회원들의 정보를 얻을 수 있습니다.</span>
            </div>
        </div>
        <div class="spelling-t sa sa-right">
            <div class="spelling-container2">
                <img src="image/itjut.png" alt="T" class="itju-t spelling-img">
                <span>ruth</span>
            </div>
            <div class="desc-container">
                <p>믿을 수 있는 진실된 잇주</p>
                <hr>
                <span>고객님의 소중한 개인정보를 데이터베이스에 저장하는 과정에서 철저히 암호화하여 저장합니다.</span>
            </div>
        </div>
    </div>
    <div class="spelling-ju-container">
        <div class="spelling-j sa sa-right">
            <div class="spelling-container3">
                <img src="image/itjuj.png" alt="J" class="itju-j spelling-img">
                <span>unior</span>
            </div>
            <div class="desc-container">
                <p>신입의 눈높이에 맞추다.</p>
                <hr>
                <span>신입 웹개발자 3명이 개발에 참여한만큼, 같은 눈높이로 필요한 정보를 제공합니다.</span>
            </div>
        </div>
        <div class="spelling-u sa sa-right">
            <div class="spelling-container4">
                <img src="image/itjuu.png" alt="U" class="itju-u spelling-img">
                <span>seful</span>
            </div>
            <div class="desc-container">
                <p>사용에 용이하고 직관성.</p>
                <hr>
                <span>간편하고 눈에 익는 UI를 지향하고, 많은 정보를 직관성있게 보여드리기 위해  더욱 고심하겠습니다.</span>
            </div>
        </div>
    </div>
</div>
<div class="content3-section">
    <img src="image/itju-sum.png" alt="" class="itju-sum">
</div>
<%@include file="layout/footer.jsp"%>

<script>
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
    window.addEventListener('scroll', saFunc);

    function scrollDown(){
        console.log(window.innerHeight);
        window.scrollTo({
            top: window.innerHeight,
            left: 0,
            behavior: 'smooth'
        });
    }
</script>

