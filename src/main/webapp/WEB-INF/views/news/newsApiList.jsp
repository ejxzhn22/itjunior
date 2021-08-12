<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<%@include file="../layout/header.jsp"%>

<div class="content-section">
    <div class="job-section">

        <div class="job-list">
            <c:forEach items="${result}" var="news" varStatus="status">
                <div class="job-card" id="${status.index}">
                    <div class="job-card-box">
                        <div class="job-card1">
                            <span class="job-name"><a href="${news.link}" target="_blank">${news.title}</a></span>
                        </div>
                        <div class="job-card3">
                            <img class="slide" src="${pageContext.request.contextPath}/image/down-arrow.png" alt="" id="slide-${status.index}">
                        </div>
                    </div>

                    <div id="job-desc-box" class="job-desc-box">
                        <div class="job-info-news">
                            <p>${news.description}</p>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>


        <%-- 페이징 --%>
            <div class="board-footer" >
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

<script type="text/javascript">
    function movePage(queryString) {
        location.href = "/news" + queryString;
    }

    $(document).ready(function() {
        $(".job-desc-box").hide();
    });
    $(function() {
        $(".job-card").on("click", function(e) {
            let id = this.id;
            console.log("id", id);
            $(e.currentTarget.childNodes[3]).slideToggle('fast','linear',function(){

                if ( $(e.currentTarget.childNodes[3]).css('display') === 'none' ) {
                    $("#slide-"+id).css('transform','rotate(0deg)');
                    $(".job-card-box").css('border-bottom','none');
                    console.log("닫기");
                }
                else{
                    $("#slide-"+id).css('transform','rotate(180deg)');
                    $(".job-card-box").css('border-bottom','1px solid #d2d2d2');
                    console.log("열기");


                }

            });
        });
    });

</script>

<%@include file="../layout/footer.jsp"%>