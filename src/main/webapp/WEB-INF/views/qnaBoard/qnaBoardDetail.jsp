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
                <span class="q-span-title">${board.title}</span>
            </div>
            <div class="q-content">
                <span>${board.content}</span>
            </div>
            <div class="q-info">
                <span class="q-name">${board.writer}</span>
                |
                <span class="q-date">${board.create_time}</span>
            </div>
        </div>

        <!--답변-->
        <c:if test="${board.reply_yn == 'Y'}">
            <div class="a-section">
                <div class="a-title">
                    <span class="a-span">A.</span>
                    <span class="a-span-title">${baord.answer_title}</span>
                </div>
                <div class="a-content">
                    <span>${baord.answer_content}</span>
                </div>
                <div class="a-info">
                    <span class="a-name">${baord.answer_writer}</span>
                    |
                    <span class="a-date">${baord.answer_create_time}</span>
                </div>
            </div>
        </c:if>

        <!---------------------->


    </div>
    <div class="go-back">
        <c:if test="${principal.member.nickname == 'root' and board.reply_yn == 'N'}">
            <a href="">답변등록</a>
        </c:if>
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


