<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>

<%@include file="../layout/header.jsp"%>
<div class="banner-section3">
    <div class="banner-write">
        <span class="banner-title">채용공고</span>
        <span class="banner-desc3">IT 신입을 위한 <br>공고만 보여드립니다✍️</span>
    </div>
    <div class="banner-img3">
        <img src="${pageContext.request.contextPath}/image/job-img.png" alt="banner img" class="banner-img">
        <img src="${pageContext.request.contextPath}/image/banner-top.png" alt="" class="banner-o-img">
    </div>
</div>

<div class="content-section">
    <div class="job-section">
        <!-- <span>2021개의 취업정보가 검색되었습니다. </span> -->
        <form method="post" class="board-search">
            <select name="search-select" class="board-select">
                <option value="전체">전체</option>
                <option value="회사명">회사명</option>
                <option value="공고명">공고명</option>
            </select>
            <input type="text" name="board-search" class="board-input" autocomplete="off">
            <input type="submit" value="🔍" class="board-submit">
        </form>
        <div class="hot-category">
            <span class="hot-title">핫 카테고리🔥</span>
            <div class="category-class">
                <a href="#">프론트엔드</a>
                <a href="#">백엔드</a>
                <a href="#">서버개발</a>
                <a href="#">PCB</a>
                <a href="#">서버관리</a>
                <a href="#">통합망구축</a>
            </div>
        </div>

        <div class="job-list">
            <c:forEach var="item" items="${result.jobs.job}">
            <div class="job-card">
                <div class="job-card-box">
                    <div class="job-card1">
                        <span class="job-name">${item.company.detail.name}</span>
                        <div class="job-title">
                            <span>${item.position.title}</span>
                        </div>
                        <div class="job-cop">
                            <span>${item.position.location.name}</span>
                        </div>
                    </div>
                    <div class="job-card2">
                        <div class="card2-box1">
                            <span>기한</span>
                            <span>${item.expiration-date} 마감</span>
                            <span>${item.expiration-timestamp} 마감2</span>
                        </div>
                        <div class="card2-box2">
                            <span>근무형태</span>
                            <span>${item.position.job-type.name}</span>
                        </div>
                    </div>
                    <div class="job-card3">
                        <img src="${pageContext.request.contextPath}/image/down-arrow.png" alt="" id="slide">
                    </div>
                </div>

                <div id="job-desc-box">
                    API로 뿌려줄 정보들 여기에 작성하면됩니다
                </div>
            </div>
            </c:forEach>
        </div>
        <div class="board-footer">
            <div class="board-paging">
                <a href="#"> < </a>
                <a href="#">1</a>
                <a href="#">2</a>
                <a href="#">3</a>
                <a href="#">4</a>
                <a href="#">5</a>
                <a href="#"> > </a>
            </div>
        </div>
    </div>

</div>
<link rel="stylesheet" type="text/css" href="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.css"/>
<script type="text/javascript" src="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.min.js"></script>
<script>
    $(document).ready(function() {
        $("#job-desc-box").hide();
    });
    $('.category-class').slick({
        slidesToShow: 4,
        slidesToScroll: 1,
        autoplay: true,
        autoplaySpeed: 3000,
    });

    $(function() {
        $(".job-card").on("click", function() {
            $("#job-desc-box").slideToggle('fast','linear',function(){
                if ( $('#job-desc-box').css('display') === 'none' ) {
                    $("#slide").css('transform','rotate(0deg)');
                    $(".job-card-box").css('border-bottom','none');
                }
                else{
                    $("#slide").css('transform','rotate(180deg)');
                    $(".job-card-box").css('border-bottom','1px solid #d2d2d2');
                }

            });
        });
    });
    $('.category-class a').click(function(e){
        console.log(e);
        console.log(e.target.innerHTML);

        $('.board-input').val(e.target.innerHTML);
        // $('.board-submit').click();
    });

</script>
<%@include file="../layout/footer.jsp"%>