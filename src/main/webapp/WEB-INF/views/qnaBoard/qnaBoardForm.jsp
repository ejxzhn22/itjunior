<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>

<%@include file="../layout/header.jsp"%>
<div class="banner-section">
    <div class="banner-write">
        <span class="banner-title">Q&A</span>
        <span class="banner-desc">잇주에 대해 <br>모두 물어보세요</span>
    </div>
    <div class="banner-img">
        <img src="image/banner-img.png" alt="banner img" class="banner-img">
        <img src="image/main-o.svg" alt="" class="banner-o-img">
    </div>
</div>
<div class="content-section">
    <form action="#" method="POST" class="write-form">

        <div class="write-content">
            <input type="text" class="write-input">
        </div>

        <div id="summernote"></div>

        <div class="btns-box">
            <input type="submit" value="작성하기" class="write-submit">
            <a href="/qnaboards" class="btn1">목록</a>
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
</script>


<%@include file="../layout/footer.jsp"%>


