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
                <span class="q-date"><fmt:parseDate value="${board.create_time}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both" />
                                <fmt:formatDate value="${parsedDateTime}" pattern="yyyy.MM.dd HH:mm"/></span>
            </div>
        </div>

        <!--답변-->
        <c:if test="${board.reply_yn == 'Y'}">
            <div class="a-section">
                <div class="a-title">
                    <span class="a-span">A.</span>
                    <span class="a-span-title">${board.answer_title}</span>
                </div>
                <div class="a-content">
                    <span>${board.answer_content}</span>
                </div>
                <div class="a-info">
                    <span class="a-name">${board.answer_writer}</span>
                    |
                    <span class="a-date"><fmt:parseDate value="${board.answer_create_time}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both" />
                                <fmt:formatDate value="${parsedDateTime}" pattern="yyyy.MM.dd HH:mm"/></span>
                </div>
            </div>
        </c:if>

        <!---------------------->

    </div>
    <div class="go-back">
        <c:if test="${principal.member.nickname == 'root' and board.reply_yn == 'N'}">
            <a href="#" onclick="openMask(${board.qna_idx});" class="qna-a openMask answer-btn">답변등록</a>
        </c:if>
        <a href="javascript:window.history.go(-1);">목록</a>
    </div>

    <!-- modal -->
    <div id="mask"></div>
    <form action="/qnaboards/${board.qna_idx}/answer" method="post" class="window">
        <div class="modal-answer-container">
            <div class="answer-content-container">
                <input type="hidden" value="">
                <img src="image/icon-lock.png" alt="" class="icon-lock">
                <h3>답변입력</h3>
                <input type="text" id="modal-title" name="answer_title" class="modal-title" placeholder="제목입력" autofocus/>
                <textarea id="modal-content" name="answer_content" class="modal-content" placeholder="내용입력" ></textarea>
            </div>
            <div class="answer-btns-container">
                <button type="submit" id="modal-btn" value="답변 작성">답변 작성</button>
                <a href="#" class="close">닫기</a>
            </div>
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

    function wrapWindowByMask(){

        //화면의 높이와 너비를 구한다.
        var maskHeight = $(document).height();
        var maskWidth = $(window).width();

        //마스크의 높이와 너비를 화면 것으로 만들어 전체 화면을 채운다.
        $("#mask").css({"width":maskWidth,"height":maskHeight});

        //애니메이션 효과 - 일단 0초동안 까맣게 됐다가 60% 불투명도로 간다.

        $("#mask").fadeIn(0);
        $("#mask").fadeTo("slow",0.6);

        //윈도우 같은 거 띄운다.
        $(".window").show();

    }

    function openMask(qna_idx){
        // e.preventDefault();
        wrapWindowByMask();

        //console.log(e);
        console.log(qna_idx );

        $("#modal-btn").attr("name", qna_idx);
        $(".modal-title").val("");
        $(".modal-title").focus();
    }

    $(document).ready(function(){
        // //검은 막 띄우기
        // $(".openMask").click(function(e){
        //     e.preventDefault();
        //     wrapWindowByMask();
        //     console.log(e);
        // });

        //닫기 버튼을 눌렀을 때
        $(".window .close").click(function (e) {
            //링크 기본동작은 작동하지 않도록 한다.
            e.preventDefault();
            $("#mask, .window").hide();
        });

        //검은 막을 눌렀을 때
        $("#mask").click(function () {
            $(this).hide();
            $(".window").hide();

        });

    });
</script>


<%@include file="../layout/footer.jsp"%>


