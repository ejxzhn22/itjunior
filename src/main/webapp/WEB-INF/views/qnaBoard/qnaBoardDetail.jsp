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
<style>
    body {background: #f3f3f31a;}
</style>
<!-- 여기 복붙 -->
<div class="content-section">
    <div class="board-section">
        <div class="q-section">
            <div class="q-title">
                <span class="q-span">Q.</span>
                <span class="q-span-title">안녕하세요</span>
            </div>
            <div class="q-content">
                <span>제가 AD센서를 활용한 백엔드 개발자 양성과정을 신청하고 싶은데 교육시간이 8:30~17:00으로 제가 대학교에서 수업을 듣는 시간이랑 겹칩니다. 그래서 질문이 있는데 혹시 영상을 교육시간이 아닌 시간에도 교육영상을 시청할 수 있는지. 그리고 교육시간에 영상을 시청하지 않아도 교육을 이수한 것으로 처리받을 수 있는지가 궁금합니다.</span>
            </div>
            <div class="q-info">
                <span class="q-name">무무문</span>
                |
                <span class="q-date">2021.03.16 13:13</span>
            </div>
        </div>
        <div class="a-section">
            <div class="a-title">
                <span class="a-span">A.</span>
                <span class="a-span-title">반갑습니다 답변드립니다.</span>
            </div>
            <div class="a-content">
                <span>그건 맘대로 하면됩니다요</span>
            </div>
            <div class="a-info">
                <span class="a-name">상담원 김재복</span>
                |
                <span class="a-date">2021.03.16 13:13</span>
            </div>
        </div>
    </div>
    <div class="go-back">
        <a href="javascript:window.history.go(-1);">목록</a>
    </div>
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


