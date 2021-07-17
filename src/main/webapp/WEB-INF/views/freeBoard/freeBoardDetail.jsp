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
        <c:choose>
            <c:when test="${principal.member.member_idx != board.member_idx}">
                <c:choose>
                    <c:when test="${board.likeState}">
                        <i class="fas fa-thumbs-up" id="likeIcon-${board.free_idx}" onclick="like(${board.free_idx})"></i>
                    </c:when>
                    <c:otherwise>
                        <i class="far fa-thumbs-up" id="likeIcon-${board.free_idx}" onclick="like(${board.free_idx})"></i>
                    </c:otherwise>
                </c:choose>
            </c:when>
        </c:choose>
            <span class="up-count" id="likeCount">${board.likecnt}</span>
        </div>
    </div>
    <div class="detail-content-bottom">

        <c:choose>
            <c:when test="${principal.member.member_idx == board.member_idx}">
                <a href="/boards/${board.free_idx}/update" class="btn1 update">수정</a>
                <a href="/boards/${board.free_idx}/delete" class="btn1 delete">삭제</a>
            </c:when>
        </c:choose>


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

    <!-- ====================== 댓글 ========================== -->
        <div class="detail-reply-section" id="reply-container">


        </div>

    <!-- =========================================== -->

</div>

<%@include file="../layout/footer.jsp"%>

<script type="text/javascript">
    <!-- 글 추천 -->
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
                type:"delete",
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


    // 댓글 이모지
    var select = document.getElementById("selected");
    var choose = document.getElementsByClassName("emoji");

    for(var i=0; i<choose.length; i++){
        choose[i].addEventListener("click",clickEmoji);
    }
    function clickEmoji(e){
        var a=e.target.innerHTML;
        select.innerText=a;
    }



    function rereply(reply_idx){
        const rereplyForm = document.querySelector("#rereply-form-"+reply_idx);

        rereplyForm.classList.toggle("visible");

    }


    <!-- 댓글 -->
    let listhtml = "";
    function replyList() {

        $.ajax({
            type:"get",
            url:`/replies/${board.free_idx}`,
            dataType:"json"
        }).done(res=>{
            console.log(res);

            for(const i in res){
                console.log("for문 : " ,i);
                console.log("깊이 : " ,res[i].depth);

                let content= res[i].content;
                let create_time= res[i].create_time;
                let depth= res[i].depth;
                let emoji= res[i].emoji;
                let free_idx= res[i].free_idx;
                let member_idx= res[i].member_idx;
                let parent_idx= res[i].parent_idx;
                let reply_idx= res[i].reply_idx;
                let reply_order= res[i].reply_order;
                let writer= res[i].writer;
                let nickname = $("#principalNick").val();

                listhtml = "<div id='reply-total-container-"+reply_idx+"'>";

                if( depth === 0){ // 부모 댓글
                    listhtml+="<div class='reply-top' id='reply-top-"+reply_idx+"'>";
                    listhtml+= "<span class='reply-emoji'>"+emoji+"</span>";
                    listhtml+="<span class='reply-nick'>"+writer+"</span>";
                    listhtml+="<span class='reply-date'>"+create_time+"</span>";
                    listhtml+="</div>";
                    <!--댓글 구역-->
                        listhtml+="<div class='reply-content' id='reply-content-"+reply_idx+"'>";
                    listhtml+="<span>"+content+"</span>";
                    listhtml+="<div class='reply-bottom' id='rereply-bottom-"+reply_idx+"'>";
                    listhtml+="<div class='btns'>";
                    listhtml+="<span class='rereply' onclick='rereply("+reply_idx+");'>답글</span>";
                    listhtml+="<a href='#' class='update'>수정</a>";
                    listhtml+=" <a href='#' class='delete'>삭제</a>";
                    listhtml+=" </div>";

                    <!-- 부모댓글 추천 비추천 -->
                    listhtml+="<div class='reply-eval' id='reply-eval-"+reply_idx+"'>";
                    listhtml+="<span><img src='/image/icon-up.png' alt=''>0</span>";
                    listhtml+="<span><img src='/image/icon-down.png' class='icon-down' alt=''>0</span>";
                    listhtml+="</div>";
                    listhtml+="</div>";

                        listhtml+="</div>";
                    <!-- 답글 들어갈 곳 -->


                    <!-- ======= -->

                    listhtml+="<form class='rereply-form visible' id='rereply-form-"+reply_idx+"' action='' method='POST'>";
                    listhtml+="<img src='/image/icon-rereply.png' alt='' style='width:20px;'>";
                    listhtml+="<span>"+nickname+"</span>";
                    listhtml+="<textarea name='rereply' class='rereply-input' placeholder='답글을 입력해주세요.'></textarea>";
                    listhtml+="<input type='submit' value='작성' class='btn1'>";
                    listhtml+="</form>";




                    console.log("여기까지");
                    //let container = document.getElementById("reply-container");  이렇게 가져로면 .html 쓸수 없음
                    //container.html(listhtml);
                     //$("#reply-container").html(listhtml);

                }else if(depth === 1){ // 대댓글
                    listhtml+="<div class='rereply-section' id='rereply-section'>";

                    listhtml += "<img src='/image/icon-rereply.png' alt='' class='rereply-img'>";
                    listhtml += "<span>"+content+"</span>";
                    listhtml += "<div class='rereply-btns'>";
                    listhtml += "<a href='#' class='update'>수정</a>";
                    listhtml += "<a href='#' class='delete'>삭제</a>";
                    listhtml += "</div>";

                    listhtml+="</div>";

                    console.log("들어와유?");
                   // let id = "rereply-section-"+parent_idx;
                    //console.log("id", id);

                    //document.getElementById("rereply-section-"+parent_idx).innerHTML = listhtml;
                  //$("#reply-section").html(listhtml);
                }

                listhtml+="</div>";
                console.log("끝");
                console.log(listhtml);
            }

            $("#reply-container").append(listhtml);



        }).fail(error=>{
            console.log("오류",error);
        });
    }

    replyList();
</script>

