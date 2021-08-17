<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>

<%@include file="../layout/header.jsp"%>
<div class="banner-section3">
    <div class="banner-write">
        <span class="banner-title">스크랩</span>
        <span class="banner-desc3">스크랩 한 공고를 <br>확인하실 수 있습니다.✍️</span>
    </div>
    <div class="banner-img3">
        <img src="${pageContext.request.contextPath}/image/job-img.png" alt="banner img" class="banner-img3">
        <img src="${pageContext.request.contextPath}/image/banner-top.png" alt="" class="banner-o-img">
    </div>
</div>

<div class="content-section">
    <div class="job-section">
        <!-- <span>2021개의 취업정보가 검색되었습니다. </span> -->
        <div class="job-list">
            <c:forEach var="item" items="${result}">
                <div class="job-card job-card-${item.job_idx}" id="${item.job_idx}">
                    <div class="job-card-box">
                        <div class="job-card1">
                            <span class="job-name" id="company-name-${item.job_idx}"><c:out value="${item.company_name}"/></span>
                            <div class="job-title">
                                <span id="title-${item.job_idx}"><c:out value="${item.title}"/></span>
                            </div>
                            <div class="job-cop">
                                <span id="location-${item.job_idx}"><c:out value="${item.location}"/></span>
                            </div>
                        </div>
                        <div class="job-card2">
                            <div class="card2-box2">
                                <span>근무형태</span>
                                <span id="job-type-${item.job_idx}"><c:out value="${item.job_type}"/></span>
                            </div>
                            <div class="card2-box1">
                                <c:choose>
                                    <c:when test="${item.active eq '0'}">
                                        <span>기한</span>
                                        <span style="color: #d01212">접수 마감</span>
                                    </c:when>
                                    <c:otherwise>
                                        <span>기한</span>
                                        <c:if test="${item.close_type eq '접수마감일'}">
                                            <fmt:parseDate value="${item.expiration_date}" var="parseDate" pattern="yyyy-MM-dd'T'HH:mm" type="both"/>
                                            <fmt:formatDate value="${parseDate}" var="formatDate" pattern="MM/dd HH:mm"/>
                                            <c:out value="${formatDate}"/> 마감
                                            <input type="hidden" id="close-type-${item.job_idx}" value="${item.close_type}">
                                        </c:if>
                                        <c:if test="${item.close_type ne '접수마감일'}">
                                            <span><c:out value="${item.close_type}"/></span> 마감
                                            <input type="hidden" id="close-type-${item.job_idx}" value="${item.close_type}">
                                        </c:if>
                                        <input type="hidden" id="expiration-date-${item.job_idx}" value="${item.expiration_date}">
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </div>
                        <div class="job-card3">
                            <img src="${pageContext.request.contextPath}/image/down-arrow.png" alt="" class="slide" id="slide-${item.job_idx}">
                        </div>
                    </div>

                    <div id="job-desc-box" class="job-desc-box">
                        <div class="job-info">
                            <div>
                                <span>직종</span> :
                                <span id="job-category-${item.job_idx}"><c:out value="${item.category}"/></span>
                            </div>
                            <div>
                                <span>경력</span> :
                                <span id="experience-level-${item.job_idx}"><c:out value="${item.experience_level}"/></span>
                            </div>
                            <div>
                                <span>최종학력</span> :
                                <span id="required-education-level-${item.job_idx}"><c:out value="${item.required_education_level}"/></span>
                            </div>
                            <div>
                                <span>연봉</span> :
                                <span id="salary-${item.job_idx}"><c:out value="${item.salary}"/></span>
                            </div>
                            <div>
                                <span>지원자 수</span> :
                                <span id="apply-cnt-${item.job_idx}"><c:out value="${item.applycnt}"/></span>
                            </div>

                            <input id="active-${item.job_idx}" type="hidden" value="${item.active}">
                            <c:if test="${item.active eq '0'}">
                                <div>
                                    <span>상태</span>
                                    <span> : 마감된 공고입니다.</span>
                                </div>
                            </c:if>
                        </div>
                        <div>
                            <i style="color: darkred" class="fas fa-trash-alt" id="scrap-button-${item.job_idx}" onclick="scrap(${item.job_idx},event)">삭제</i>
                        </div>
                        <div class="job-link">
                            <a href="${item.url}" target="_blank" class="join-btn">채용상세정보</a>
                            <a href="${item.company_href}" target="_blank" class="join-btn">기업상세정보</a>
                            <input id="url-${item.job_idx}" type="hidden" value="${item.url}">
                            <input id="company-href-${item.job_idx}" type="hidden" value="${item.company_href}">
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
        location.href = "/job/mypage/scrapList" + queryString;
    }

    function scrap(job_idx,event) {
        event.stopPropagation();
        console.log("job_idx >>> " + job_idx);

        //스크랩 삭제
        $.ajax({
            url: "/job/scrap/"+job_idx,
            type: "DELETE",
            success: function(result) {
                console.log("result >> "+result);
                if (result === "success") {
                    console.log("스크랩 취소 성공");
                    alert("선택하신 공고가 삭제되었습니다.");
                    $(".job-card-"+job_idx).remove();
                    return false;
                }
            },
            error: function (error) {
                console.log(error.status);
                console.log("서버호출 에러.");
            }
        });

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
                    $("#slide-"+job_idx).css('transform','rotate(0deg)');
                    $(".job-card-box").css('border-bottom','none');
                    console.log("닫기");
                }
                else{
                    console.log("company-name >>> "+$("#company-name-"+job_idx).text());
                    $("#slide-"+job_idx).css('transform','rotate(180deg)');
                    $(".job-card-box").css('border-bottom','1px solid #d2d2d2');
                    console.log("열기");
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