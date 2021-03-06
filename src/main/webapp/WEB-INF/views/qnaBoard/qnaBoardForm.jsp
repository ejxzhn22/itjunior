<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>

<%@include file="../layout/header.jsp"%>
<div class="banner-section">
    <div class="banner-write">
        <span class="banner-title">Q&A</span>
        <span class="banner-desc">잇주에 대해 <br>모두 물어보세요🤔</span>
    </div>
    <div class="banner-img">
        <img src="${pageContext.request.contextPath}/image/banner-img.png" alt="banner img" class="banner-img">
        <img src="${pageContext.request.contextPath}/image/main-o.svg" alt="" class="banner-o-img">
    </div>
</div>
<div class="content-section">
    <form action="/qnaboards/form" method="POST" class="write-form">

        <div class="write-content">
            <input type="text" name="title" class="write-input2" placeholder="잇주에 대해 모두 물어보세요 🤔">

        </div>

        <div>
            <textarea id="summernote" name="content"></textarea>
        </div>

        <div>
            <input type="checkbox" name="secret_yn" id="secret_yn"> <label for="secret_yn">비밀글</label>
            <input type="password" name="secret_pwd" id="secret_pwd" placeholder="비밀번호를 입력하세요">
        </div>
        <div class="btns-box">
            <input type="submit" value="작성하기" class="write-submit">
            <a href="/qnaboards" class="btn1">목록</a>
        </div>
    </form>

</div>
<script>
    $(document).ready(function() {
        $('#summernote').summernote({
            name: "content",
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


