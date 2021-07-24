<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>

<%@include file="../layout/header.jsp"%>
<div class="banner-section">
    <div class="banner-write">
        <span class="banner-title">취업토론</span>
        <span class="banner-desc">취업에 관련된 <br>정보들을 나눠봅시다✍️</span>
    </div>
    <div class="banner-img">
        <img src="image/banner-img.png" alt="banner img" class="banner-img">
        <img src="image/main-o.svg" alt="" class="banner-o-img">
    </div>
</div>
<div class="content-section">
    <div class="board-section">
        <form method="post" action="/boards" class="board-search">
            <input type="hidden" name="currentPageNo" value="1">
            <select name="searchType" class="board-select">
                <option value="title">제목</option>
                <option value="content">내용</option>
                <option value="all">제목+내용</option>
                <option value="writer">작성자</option>
            </select>
            <input type="text" name="searchKeyword" class="board-input" autocomplete="off">
            <input type="submit"  value="🔍" class="board-submit">
        </form>

        <div class="board-search2">
            <select name="board-category" class="board-category-select">
                <option value="전체보기">전체보기</option>
                <option value="면접후기">면접후기</option>
                <option value="취업후기">취업후기</option>
                <option value="잡담">잡담</option>
                <option value="질문">질문</option>
            </select>
            <select name="board-filter" class="board-filter">
                <option value="10">10개씩 보기</option>
                <option value="25">25개씩 보기</option>
                <option value="50">50개씩 보기</option>
            </select>
        </div>

        <table class="board-table">
            <tr>
                <td>글번호</td>
                <td>제목</td>
                <td>작성자</td>
                <td>조회수</td>
                <td>작성일</td>

            </tr>
            <c:forEach var="board" items="${boards}">
                <tr>
                    <td>${board.free_idx}</td>
                    <td><a href="/boards/${board.free_idx}${page.makeQueryString(page.currentPageNo)}">${board.title}</a></td>
                    <td>${board.writer}</td>
                    <td>${board.viewcnt}</td>
                    <td>${board.create_time}</td>
                </tr>
            </c:forEach>

        </table>


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
            <a class="board-writing" href="/boards/new">글쓰기</a>
        </div>
    </div>
</div>

<script type="text/javascript">
    function movePage(queryString) {
        location.href = "/boards" + queryString;
    }
</script>

<%@include file="../layout/footer.jsp"%>


