<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<%@include file="../layout/header.jsp"%>


    <h1>news</h1>


    <c:forEach items="${result}" var="news">
        <div>
            <h3><a href="${news.link}">${news.title}</a></h3>
            <span> ${news.description}</span>

        </div>
        <hr/>
    </c:forEach>

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

<script type="text/javascript">
    function movePage(queryString) {
        location.href = "/news" + queryString;
    }
</script>

<%@include file="../layout/footer.jsp"%>