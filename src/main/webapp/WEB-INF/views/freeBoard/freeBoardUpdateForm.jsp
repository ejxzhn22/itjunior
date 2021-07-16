<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>

<%@include file="../layout/header.jsp"%>

<div class="banner-section">
    <div class="banner-write">
        <span class="banner-title">취업토론</span>
        <span class="banner-desc">취업에 관련된 <br>정보들을 나눠봅시다✍️</span>
    </div>
    <div class="banner-img">
        <img src="${pageContext.request.contextPath}/image/banner-img.png" alt="banner img" class="banner-img">
        <img src="${pageContext.request.contextPath}/image/main-o.svg" alt="" class="banner-o-img">
    </div>
</div>

<div class="content-section">
    <form action="/boards/${board.free_idx}/update" method="POST" class="write-form">

        <div class="write-content">

    <select id="select" name="category" class="board-category-select">
        <option selected disabled>${board.cate_name}</option>

    </select>
            <input type="text" class="write-input" id="title" name="title" value="${board.title}">
        </div>

        <div id="summernote" name="content">${board.content}</div>

        <div class="btns-box">
            <input type="submit" value="작성하기" class="write-submit">
            <a href="/boards" class="btn1">목록</a>
        </div>
    </form>

</div>
<script>

    $(document).ready(function() {
        $('#summernote').summernote({
            height: 300,                 // 에디터 높이
            minHeight: null,             // 최소 높이
            maxHeight: null,             // 최대 높이
            focus: true,                  // 에디터 로딩후 포커스를 맞출지 여부
            lang: "ko-KR",					// 한글 설정
            placeholder: '최대 2048자까지 쓸 수 있습니다'	//placeholder 설정

        });
    });

    function changeSelect() {

        let select = document.getElementById("select");
        let selectText = select.options[select.selectedIndex].text;
        console.log(selectText);

        $('input[name=title]').attr('value', selectText);

    }
</script>

<%@include file="../layout/footer.jsp"%>

