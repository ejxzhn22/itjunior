<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>

<%@include file="../layout/header.jsp"%>

<div class="banner-section">
    <div class="banner-write">
        <span class="banner-title">취업토론</span>
        <span class="banner-desc">취업에 관련된 <br>정보들을 나눠봅시다✍️</span>
    </div>
    <div class="banner-img">
        <img src="/image/banner-img.png" alt="banner img" class="banner-img">
        <img src="/image/main-o.svg" alt="" class="banner-o-img">
    </div>
</div>
<div class="content-section">
    <div class="detail-header">
        <span>${board.cate_name}</span>
        <img src="/image/icon-minus.png" alt="">
        <h1>${board.title}</h1>
    </div>
    <span class="detail-date">${create_time}</span>
    <div class="detail-content-section">
        <img src="/image/itju-sum.png" alt="" class="itju-main-img">

        <span>${board.content}</span>
    </div>
    <div class="detail-content-recommend">
        <div class="recommend-box">
            <img src="/image/icon-up.png" alt="" id="likeIcon-${board.free_idx}" onclick="like(${board.free_idx})">
            <span class="up-count">${board.likecnt}</span>
        </div>
    </div>
    <div class="detail-content-bottom">

        <a href="/boards/${board.free_idx}/update" class="btn1 update">수정</a>
        <a href="/boards/${board.free_idx}/delete" class="btn1 delete">삭제</a>

        <a href="/boards" class="btn1">목록</a>
    </div>
    <div class="detail-reply-input-section">
        <div class="detail-reply-emoji">
            <span class="selectedd-emoji" id="selected">😀</span>
        </div>
        <form method="post" class="detail-reply">
            <textarea placeholder="댓글을 입력해주세요." class="detail-reply-input" cols="40" rows="40"></textarea>
            <div class="detail-reply-btns">
                <div class="emojis">
                    <span id="smile" class="emoji">😀</span>
                    <span id="love" class="emoji">😍</span>
                    <span id="suprise" class="emoji">🤭</span>
                    <span id="bad" class="emoji">🙁</span>
                    <span id="anger" class="emoji">😡</span>
                </div>
                <input type="submit" class="reply-submit" value="작성">
            </div>
        </form>
    </div>

    <div class="detail-reply-section">
        <div class="reply-top">
            <span class="reply-emoji">😀</span>
            <span class="reply-nick">nickname</span>
            <span class="reply-date">2021.03.03</span>
        </div>
        <div class="reply-content">
            <span>oh~that's hot</span><!--댓글 구역-->

            <div class="rereply-section">
                <img src="/image/icon-rereply.png" alt="" class="rereply-img">
                <span>oh i agree!</span>
                <div class="rereply-btns">
                    <a href="#" class="update">수정</a>
                    <a href="#" class="delete">삭제</a>
                </div>
            </div><!-- 답글 구역 -->
            <form class="rereply-form visible" action="" method="POST">
                <img src="/image/icon-rereply.png" alt="" style="width:20px;">
                <span>nickname</span>
                <textarea name="rereply" class="rereply-input" placeholder="답글을 입력해주세요."></textarea>
                <input type="submit" value="작성" class="btn1">
            </form>
        </div>
        <div class="reply-bottom">
            <div class="btns">
                <span class="rereply" onclick="rereply();">답글</span>
                <a href="#" class="update">수정</a>
                <a href="#" class="delete">삭제</a>
            </div>
            <div class="reply-eval">
                <span><img src="/image/icon-up.png" alt="">0</span>
                <span><img src="/image/icon-down.png" class="icon-down"alt="">0</span>
            </div>
        </div>
    </div>


</div>

<%@include file="../layout/footer.jsp"%>

<script type="text/javascript">
    function like(free_idx){
        console.log("idx: ",${board.free_idx})
        let likeIcon = $(`#likeIcon-`+free_idx);

        if(likeIcon.hasClass("far")) { //좋아요
            $.ajax({
                type:"post",
                url:`/boards/`+free_idx+`/likes`,
                dataType:"json"
            }).done(res=>{
                let likeContStr = $("#likeCount").text();
                let likeCount = Number(likeContStr) +1;

                $("#likeCount").text(likeCount);

                likeIcon.addClass("fas");
                likeIcon.addClass("active");
                likeIcon.removeClass("far");
            }).fail(error=>{
                console.log("오류", error);
            });
        } else{ // 좋아요 취소
            $.ajax({
                type: "delete",
                url:`/boards/`+free_idx+`/likes`,
                dataType:"json"
            }).done(res=> {

                let likeContStr = $("#likeCount").text();
                let likeCount = Number(likeContStr) - 1;

                $("#likeCount").text(likeCount);

                likeIcon.removeClass("fas");
                likeIcon.removeClass("active");
                likeIcon.addClass("far");
            }).fail(error=>{
                console.log("오류", error);
            });

        }
    }



    var select = document.getElementById("selected");
    var choose = document.getElementsByClassName("emoji");

    for(var i=0; i<choose.length; i++){
        choose[i].addEventListener("click",clickEmoji);
    }
    function clickEmoji(e){
        var a=e.target.innerHTML;
        select.innerText=a;
    }

    var rereplyForm = document.querySelector(".rereply-form");

    function rereply(){
        rereplyForm.classList.toggle("visible");

    }
    window.addEventListener('scroll',e=>{
        if(window.scrollY>0){
            document.getElementById("header").style.background="#fff";
            document.getElementById("header").style.height="30px";
            document.querySelector(".logo-img").style.marginLeft="50px";
            document.querySelector(".logo-img").style.marginTop="-5px";
            document.querySelector(".logo-img").style.width="110px";
            document.querySelector(".logo-img").style.height="45px";
        }
        else if(window.scrollY==0){
            document.getElementById("header").style.height="53px";
            document.querySelector(".logo-img").style.marginLeft="0px";
            document.querySelector(".logo-img").style.width="180px";
            document.querySelector(".logo-img").style.height="70px";
        }
    });
</script>

