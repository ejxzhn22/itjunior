<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>

<%@include file="../layout/header.jsp"%>
<div class="banner-section">
    <div class="banner-write">
        <span class="banner-title">Q&A</span>
        <span class="banner-desc">잇주에 대해 <br>모두 물어보세요</span>
    </div>
    <div class="banner-img">
        <img src="${pageContext.request.contextPath}/image/banner-img.png" alt="banner img" class="banner-img">
        <img src="${pageContext.request.contextPath}/image/main-o.svg" alt="" class="banner-o-img">
    </div>
</div>

<div class="content-section">
    <div class="board-section">
        <form method="post" class="board-search">
            <select name="search-select" class="board-select">
                <option value="전체">전체</option>
                <option value="작성자">작성자</option>
                <option value="질문">질문</option>
            </select>
            <input type="text" name="board-search" class="board-input" autocomplete="off">
            <input type="submit" value="🔍" class="board-submit">
        </form>

        <ul class="board-table2">
            <li>
                <a href="#" onclick="openMask(1);" class="qna-a openMask">
                    <div class="qna-div1">
                        <div class="title">
                            <span class="qna-q">Q.</span>
                            요건 어떡하죠
                        </div>
                        <div class="">김수진 | 2021.07.08</div>
                    </div>
                    <div class="qna-div2">
                        <span class="success-badge badge">답변완료</span>
                    </div>
                </a>
            </li>
            <li>
                <a href="#" onclick="openMask(2);" class="qna-a openMask" value="2">
                    <div class="qna-div1">
                        <div class="title">
                            <span class="qna-q">Q.</span>
                            요건 어떡하죠
                        </div>
                        <div class="">김수진 | 2021.07.08</div>
                    </div>
                    <div class="qna-div2">
                        <span class="wait-badge badge">답변대기</span>
                    </div>
                </a>
            </li>
            <li>
                <a href="#" onclick="openMask(3);" class="qna-a openMask">
                    <div class="qna-div1">
                        <div class="title">
                            <span class="qna-q">Q.</span>
                            요건 어떡하죠
                        </div>
                        <div class="">김수진 | 2021.07.08</div>
                    </div>
                    <div class="qna-div2">
                        <span class="success-badge badge">답변완료</span>
                    </div>
                </a>
            </li>
        </ul>
        </div>
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
        <a class="board-writing" href="/qnaboards/form">질문하기</a>
    </div>
</div>
<!-- modal -->
<div id="mask"></div>
<form action="post" class="window">
    <div class="modal-container">
        <img src="image/icon-lock.png" alt="" class="icon-lock">
        <h2>작성 시 입력하신 비밀번호를 입력하세요.</h2>
        <input type="password" value="" class="modal-pw" maxlength="4" autofocus/>
        <div class="btns-container">
            <input type="submit" value="확인"
            <a href="#" class="close">닫기</a>
        </div>
    </div>
</form>

<%@include file="../layout/footer.jsp"%>

<script>
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

    function openMask(num){
        // e.preventDefault();
        wrapWindowByMask();
        // 서브밋 클릭시 그냥 이동되게 테스트했습니다
        location.href="qnaboards/detail";

        //console.log(e);
        console.log(num );

        $(".modal-pw").val("");
        $(".modal-pw").focus();
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

