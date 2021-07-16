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
        <form method="post" class="board-search">
            <select name="search-select" class="board-select">
                <option value="제목">제목</option>
                <option value="제목+내용">제목+내용</option>
                <option value="글쓴이">글쓴이</option>
                <option value="닉네임">닉네임</option>
                <option value="카테고리">카테고리</option>
            </select>
            <input type="text" name="board-search" class="board-input" autocomplete="off">
            <input type="submit" value="🔍" class="board-submit">
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
                    <td><a href="/boards/${board.free_idx}">${board.title}</a></td>
                    <td>${board.writer}</td>
                    <td>${board.viewcnt}</td>
                    <td>${board.create_time}</td>
                </tr>
            </c:forEach>

        </table>

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
            <a class="board-writing" href="/boards/new">글쓰기</a>
        </div>
    </div>
</div>
<%@include file="../layout/footer.jsp"%>


