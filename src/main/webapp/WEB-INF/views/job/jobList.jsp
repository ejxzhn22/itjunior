<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>

<%@include file="../layout/header.jsp"%>
<div class="banner-section3">
    <div class="banner-write">
        <span class="banner-title">채용공고</span>
        <span class="banner-desc3">IT 신입을 위한 <br>공고만 보여드립니다✍️</span>
    </div>
    <div class="banner-img3">
        <img src="${pageContext.request.contextPath}/image/job-img.png" alt="banner img" class="banner-img3">
        <img src="${pageContext.request.contextPath}/image/banner-top.png" alt="" class="banner-o-img">
    </div>
</div>

<div class="content-section">
    <div class="job-section">
        <!-- <span>2021개의 취업정보가 검색되었습니다. </span> -->
        <form method="get" action="/job/list" class="board-search">
            <select name="search-select" class="board-select">
                <option value="전체">전체</option>
                <option value="회사명">회사명</option>
                <option value="공고명">공고명</option>
            </select>
            <input type="text" name="searchKeyword" class="board-input" autocomplete="off">
            <input type="submit" value="🔍" class="board-submit">
        </form>
        <div class="hot-category">
            <span class="hot-title">핫 카테고리🔥</span>
            <div class="category-class">
            <c:forEach items="${categories}" var="cate">
                <a href="/job/list?searchKeyword=${cate.name}" name="searchKeyword">${cate.name}</a>
            </c:forEach>
            </div>
        </div>

        <div class="job-list">
            <c:forEach var="item" items="${result.jobs.job}">
                <div class="job-card" id="${item.id}">
                    <div class="job-card-box">
                        <div class="job-card1">
                            <span class="job-name" id="company-name-${item.id}"><c:out value="${item.company.detail.name}"/></span>
                            <div class="job-title">
                                <span id="title-${item.id}"><c:out value="${item.position.title}"/></span>
                            </div>
                            <div class="job-cop">
                                <span id="location-${item.id}"><c:out value="${item.position.location.name}"/></span>
                            </div>
                        </div>
                        <div class="job-card2">
                            <div class="card2-box2">
                                <span>근무형태</span>
                                <span id="job-type-${item.id}"><c:out value="${item.position['job-type'].name}"/></span>
                            </div>
                            <div class="card2-box1">
                                <span>기한</span>
                                <c:if test="${item['close-type'].name eq '접수마감일'}">
                                    <fmt:parseDate value="${item['expiration-date']}" var="parseDate" pattern="yyyy-MM-dd'T'HH:mm:ssZ"/>
                                    <fmt:formatDate value="${parseDate}" var="formatDate" pattern="MM/dd HH:mm"/>
                                    <c:out value="${formatDate}"/> 마감
                                    <input type="hidden" id="close-type-${item.id}" value="${item['close-type'].name}">
                                </c:if>
                                <c:if test="${item['close-type'].name ne '접수마감일'}">
                                    <span><c:out value="${item['close-type'].name}"/></span> 마감
                                    <input type="hidden" id="close-type-${item.id}" value="${item['close-type'].name}">
                                </c:if>
                                <input type="hidden" id="expiration-date-${item.id}" value="${item['expiration-date']}">
                            </div>
                        </div>
                        <div class="job-card3">
                            <img src="${pageContext.request.contextPath}/image/down-arrow.png" alt="" id="slide">
                        </div>
                    </div>

                    <div id="job-desc-box" class="job-desc-box">
                        <div class="job-info">
                            <div>
                                <span>직종</span> :
                                <span id="job-category-${item.id}"><c:out value="${item.position['job-category'].name}"/></span>
                            </div>
                            <div>
                                <span>경력</span> :
                                <span id="experience-level-${item.id}"><c:out value="${item.position['experience-level'].name}"/></span>
                            </div>
                            <div>
                                <span>최종학력</span> :
                                <span id="required-education-level-${item.id}"><c:out value="${item.position['required-education-level'].name}"/></span>
                            </div>
                            <div>
                                <span>연봉</span> :
                                <span id="salary-${item.id}"><c:out value="${item.salary.name}"/></span>
                            </div>
                            <div>
                                <span>지원자 수</span> :
                                <span id="apply-cnt-${item.id}"><c:out value="${item['apply-cnt']}"/></span>
                            </div>

                            <input id="active-${item.id}" type="hidden" value="${item['active']}">
                            <c:if test="${item['active'] eq '0'}">
                                <div>
                                    <span>상태</span>
                                    <span> : 마감된 공고입니다.</span>
                                </div>
                            </c:if>
                        </div>
                        <div>
                            <i class="far fa-star" id="scrap-button-${item.id}" onclick="scrap(${item.id})">스크랩</i>
                        </div>
                        <div class="job-link">
                            <a href="${item.url}" target="_blank" class="join-btn">채용상세정보</a>
                            <a href="${item.company.detail.href}" target="_blank" class="join-btn">기업상세정보</a>
                            <input id="url-${item.id}" type="hidden" value="${item.url}">
                            <input id="company-href-${item.id}" type="hidden" value="${item.company.detail.href}">
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
        <div class="board-footer">
            <c:if test="${page != null and page.paginationInfo.totalRecordCount > 0}">
                <ul class="board-paging" style="display: flex">
                    <c:if test="${page.paginationInfo.hasPreviousPage}">
                        <li onclick="movePage('${page.makeQueryString(1)}')" >
                            <a href="javascript:void(0)"> << </a>
                        </li>
                        <li onclick="movePage('${page.makeQueryString(page.paginationInfo.firstPage - 1)}')" >
                            <a href="javascript:void(0)"> < </a>
                        </li>
                    </c:if>
                    <c:forEach begin="${page.paginationInfo.firstPage}" end="${page.paginationInfo.lastPage}" var="num">
                        <li onclick="movePage('${page.makeQueryString(num)}')" >
                            <a href="javascript:void(0)">${num}</a>
                        </li>
                    </c:forEach>

                    <c:if test="${page.paginationInfo.hasNextPage}">
                        <li onclick="movePage('${page.makeQueryString(page.paginationInfo.lastPage + 1)}')">
                            <a href="javascript:void(0)"> > </a>
                        </li>
                        <li onclick="movePage('${page.makeQueryString(page.paginationInfo.totalPageCount)}')" >
                            <a href="javascript:void(0)"> >> </a>
                        </li>
                    </c:if>
                </ul>
            </c:if>
        </div>
    </div>

</div>
<link rel="stylesheet" type="text/css" href="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.css"/>
<script type="text/javascript" src="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.min.js"></script>
<script>
    function movePage(queryString) {
        location.href = "/job/list" + queryString;
    }

    function scrap(job_idx) {
        let scrapIcon = $("#scrap-button-"+job_idx);
        console.log("job_idx >>> " + job_idx);

        if(${principal.member eq null}){
            alert("로그인 후 사용하십시오.");
            location.href = "/auth/loginForm";
        }
        let totalData = JSON.stringify({
            job_idx : job_idx,
            company_name : $("#company-name-"+job_idx).text(),
            title : $("#title-"+job_idx).text(),
            location : $("#location-"+job_idx).text(),
            job_type : $("#job-type-"+job_idx).text(),
            expiration_date : $("#expiration-date-"+job_idx).val(),
            close_type : $("#close-type-"+job_idx).val(),
            category : $("#job-category-"+job_idx).text(),
            experience_level : $("#experience-level-"+job_idx).text(),
            required_education_level : $("#required-education-level-"+job_idx).text(),
            salary : $("#salary-"+job_idx).text(),
            applycnt : $("#apply-cnt-"+job_idx).text(),
            active : $("#active-"+job_idx).val(),
            url : $("#url-"+job_idx).val(),
            company_href : $("#company-href-"+job_idx).val()
        });
        if(scrapIcon.hasClass("far")){

            $('#scrap-button-'+job_idx).addClass('fas');
            $('#scrap-button-'+job_idx).addClass('active');
            $('#scrap-button-'+job_idx).removeClass('far');
            $('#scrap-button-'+job_idx).text("스크랩취소");
            $.ajax({
                url: "/job/scrap/"+job_idx,
                type: "POST",
                contentType: "application/json; charset=utf-8",
                data: totalData,
                success: function(result) {
                    console.log("result >> "+result);
                    if (result === "success") {
                        console.log("스크랩 성공");
                    }
                },
                error: function (error) {
                    console.log(error.status);
                    console.log("서버호출 에러.");
                }
            })
        } else {
            $.ajax({
                url: "/job/scrap/"+job_idx,
                type: "DELETE",
                success: function(result) {
                    console.log("result >> "+result);
                    if (result === "success") {
                        console.log("스크랩 취소 성공");
                        $('#scrap-button-'+job_idx).removeClass('fas');
                        $('#scrap-button-'+job_idx).removeClass('active');
                        $('#scrap-button-'+job_idx).addClass('far');
                        $('#scrap-button-'+job_idx).text("스크랩");
                        return false;
                    }
                },
                error: function (error) {
                    console.log(error.status);
                    console.log("서버호출 에러.");
                }
            })
        }
    }

    $(document).ready(function() {
        $(".job-desc-box").hide();
    });
    $('.category-class').slick({
        slidesToShow: 4,
        slidesToScroll: 1,
        autoplay: true,
        autoplaySpeed: 3000,
    });

    $(function() {
        $(".job-card").on("click", function(e) {
            let job_idx = this.id;
            $(e.currentTarget.childNodes[3]).slideToggle('fast','linear',function(){
                console.log("job-card-push job-idx>>> "+job_idx);
                if ( $(e.currentTarget.childNodes[3]).css('display') === 'none' ) {
                    $("#slide").css('transform','rotate(0deg)');
                    $(".job-card-box").css('border-bottom','none');
                    console.log("닫기");
                }
                else{
                    console.log("company-name >>> "+$("#company-name-"+job_idx).text());
                    $("#slide").css('transform','rotate(180deg)');
                    $(".job-card-box").css('border-bottom','1px solid #d2d2d2');
                    console.log("열기");

                    $.ajax({
                        url: "/job/isScrap/"+job_idx,
                        type: "GET",
                        success: function(result) {
                            if (result.isScrap) {
                                console.log("이미 스크랩한 공고");
                                $('#scrap-button-'+job_idx).addClass('fas');
                                $('#scrap-button-'+job_idx).addClass('active');
                                $('#scrap-button-'+job_idx).removeClass('far');
                                $('#scrap-button-'+job_idx).text("스크랩취소");
                                return false;
                            }
                            console.log("스크랩 없음.")
                        },
                        error: function (error) {
                            console.log(error.status);
                            console.log("서버호출 에러.");
                        }
                    })

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