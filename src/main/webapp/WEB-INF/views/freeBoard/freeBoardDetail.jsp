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
            <span class="selectedd-emoji" id="selected" name="emoji">😀</span>
        </div>
        <form method="post" class="detail-reply">
            <textarea placeholder="댓글을 입력해주세요." id="parent_content" name="content" class="detail-reply-input" cols="40" rows="40"></textarea>
            <div class="detail-reply-btns">
                <div class="emojis">
                    <span id="smile" class="emoji">😀</span>
                    <span id="love" class="emoji">😍</span>
                    <span id="suprise" class="emoji">🤭</span>
                    <span id="bad" class="emoji">🙁</span>
                    <span id="anger" class="emoji">😡</span>
                </div>
                <input onclick="insertParent()" class="reply-submit" value="작성">
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


    <!--==================== 댓글 ==================-->
    <!-- 댓글 리스트 -->
    function replyList() {
        let listhtml = "";
        let relisthtml = "";

        $.ajax({
            type:"get",
            url:`/replies/${board.free_idx}`,
            dataType:"json"
        }).done(res=>{
                console.log(res);
            res.forEach(function (i){

                console.log("for문 : " ,i);
                console.log("깊이 : " ,i.depth);

                let content= i.content;
                let create_time= i.create_time;
                let depth= i.depth;
                let emoji= i.emoji;
                let free_idx= i.free_idx;
                let member_idx= i.member_idx;
                let parent_idx= i.parent_idx;
                let reply_idx= i.reply_idx;
                let reply_order= i.reply_order;
                let writer= i.writer;
                let nickname = $("#principalNick").val();
                let principalId = $("#principalId").val();

                listhtml = "<div id='reply-total-container-"+reply_idx+"'>";
                if( depth === 0){ // 부모 댓글
                    listhtml+="<div class='reply-top' id='reply-top-"+reply_idx+"'>";
                    listhtml+= "<span class='reply-emoji'>"+emoji+"</span>";
                    listhtml+="<span class='reply-nick'>"+writer+"</span>";
                    listhtml+="<span class='reply-date'>"+create_time+"</span>";
                    listhtml+="</div>";


                    <!--댓글 구역-->
                        listhtml+="<div class='reply-content' id='reply-content-"+reply_idx+"'>";
                    listhtml+="<span id='parent-content-"+reply_idx+"'>"+content+"</span>";


                    <!-- 답글 들어갈 곳 -->
                    listhtml+="<div  id='rereply-container-"+reply_idx+"' >";

                    listhtml+="</div>";

                    <!-- ======= -->
                    <!-- 답글 쓰는 곳 -->
                    listhtml+="<form class='rereply-form visible' id='rereply-form-"+reply_idx+"' action='' method='POST'>";
                    listhtml+="<img src='/image/icon-rereply.png' alt='' style='width:20px;'>";
                    listhtml+="<span>"+nickname+"</span>";
                    listhtml+="<textarea  id='child-content-"+reply_idx+"' name='rereply' class='rereply-input' placeholder='답글을 입력해주세요.'></textarea>";
                    listhtml+="<input onclick='insertChild("+reply_idx+")' value='작성' class='btn1'>";
                    listhtml+="</form>";
                    <!-- ======================= -->


                        listhtml+="</div>";

                    <!-- 부모 버튼 -->
                    listhtml+="<div class='reply-bottom' id='rereply-bottom-"+reply_idx+"'>";
                    listhtml+="<div class='btns'>";

                    console.log("member",member_idx);
                    console.log("principal",principalId);

                    listhtml+="<span class='rereply' onclick='rereply("+reply_idx+");'>답글</span>";

                    if(member_idx == principalId){
                        listhtml+=" <a href='javascript:void(0)' onclick='deleteParent("+reply_idx+")' class='delete'>삭제</a>";
                    }

                    listhtml+=" </div>";

                    <!-- 부모댓글 추천 비추천 -->
                    listhtml+="<div class='reply-eval' id='reply-eval-"+reply_idx+"'>";
                        listhtml+="<span><i onclick='parentLike("+reply_idx+")' id='parent-like"+reply_idx+"' class='far fa-thumbs-up'></i>0</span>";
                        listhtml+="<span><i id='parent-hate"+reply_idx+"' class='far fa-thumbs-down'></i>0</span>";
                    listhtml+="</div>";
                    listhtml+="</div>";




                    console.log("여기까지");
                    //let container = document.getElementById("reply-container");  이렇게 가져로면 .html 쓸수 없음
                    //container.html(listhtml);
                     //$("#reply-container").html(listhtml);

                    listhtml+="</div>";

                    console.log(listhtml);
                    $("#reply-container").append(listhtml);

                }else if(depth === 1){ // 대댓글
                    listhtml+="<div class='rereply-section' id='rereply-section'>";

                    listhtml += "<img src='/image/icon-rereply.png' alt='' class='rereply-img'>";
                    listhtml += "<span>"+content+"</span>";
                    listhtml += "<span>"+writer+"</span>";
                    listhtml += "<span class='reply-date'>"+create_time+"</span>";
                    listhtml += "<div class='rereply-btns'>";
                    if(member_idx == principalId){
                        listhtml += "<a href='javascript:void(0)' onclick='deleteChild("+reply_idx+")' class='delete'>삭제</a>";
                    }
                    listhtml += "</div>";

                    listhtml+="</div>";

                    console.log(listhtml);
                   $('#rereply-container-'+i.parent_idx).append(listhtml);

                    //console.log("id", id);

                    //document.getElementById("rereply-section-"+parent_idx).innerHTML = listhtml;
                  //$("#reply-section").html(listhtml);

                    //let textnode = document.createTextNode(listhtml);

                   // let id = "rereply-container-"+parent_idx;
                   //  let container = document.getElementById(id);
                   //  container.innerHTML=listhtml;

                }


            } // for end

            );




        }).fail(error=>{
            console.log("오류",error);
        });
    }

    replyList();


    <!-- 부모댓글 추가 -->
    function insertParent() {

        const emoji = $("#selected").text();
        const content = $("#parent_content").val();
        const member_idx = $("#principalId").val();
        const writer = $("#principalNick").val();
        const nickname = $("#principalNick").val();
        let listhtml = "";

        console.log("내용", content);
        console.log("이모지", emoji);

        let data = {
            emoji : emoji,
            content : content,
            member_idx : member_idx,
            writer : writer
        };

        $.ajax({
            type:"post",
            url:`/replies/${board.free_idx}/insertParent`,
            data:JSON.stringify(data),
            contentType:"application/json; charset=utf-8",
            dataType:"json"
        }).done(res=>{

            console.log("성공",res);

            listhtml = "<div id='reply-total-container-"+res.reply_idx+"'>";

            listhtml+="<div class='reply-top' id='reply-top-"+res.reply_idx+"'>";
            listhtml+= "<span class='reply-emoji'>"+res.emoji+"</span>";
            listhtml+="<span class='reply-nick'>"+res.writer+"</span>";
            listhtml+="<span class='reply-date'>"+res.create_time+"</span>";
            listhtml+="</div>";


            <!--댓글 구역-->
            listhtml+="<div class='reply-content' id='reply-content-"+res.reply_idx+"'>";
            listhtml+="<span id='parent-content-"+res.reply_idx+"'>"+res.content+"</span>";


            <!-- 답글 들어갈 곳 -->
            listhtml+="<div  id='rereply-container-"+res.reply_idx+"' >";

            listhtml+="</div>";

            <!-- ======= -->
            <!-- 답글 쓰는 곳 -->
            listhtml+="<form class='rereply-form visible' id='rereply-form-"+res.reply_idx+"' action='' method='POST'>";
            listhtml+="<img src='/image/icon-rereply.png' alt='' style='width:20px;'>";
            listhtml+="<span>"+nickname+"</span>";
            listhtml+="<textarea id='child-content-"+res.reply_idx+"' name='content' class='rereply-input' placeholder='답글을 입력해주세요.'></textarea>";
            listhtml+="<input onclick='insertChild("+res.reply_idx+")' value='작성' class='btn1'>";
            listhtml+="</form>";
            <!-- ======================= -->


            listhtml+="</div>";

            <!-- 부모 버튼 -->
            listhtml+="<div class='reply-bottom' id='rereply-bottom-"+res.reply_idx+"'>";
            listhtml+="<div class='btns'>";
            listhtml+="<span class='rereply' onclick='rereply("+res.reply_idx+");'>답글</span>";
            listhtml+=" <a href='javascript:void(0)' onclick='deleteParent("+res.reply_idx+")' class='delete'>삭제</a>";
            listhtml+=" </div>";
            <!--================ -->

            <!-- 부모댓글 추천 비추천 -->
            listhtml+="<div class='reply-eval' id='reply-eval-"+res.reply_idx+"'>";
            listhtml+="<span><i onclick='parentLike("+res.reply_idx+")' id='parent-like"+res.reply_idx+"' class='far fa-thumbs-up'></i>0</span>";
            listhtml+="<span><i id='parent-hate"+res.reply_idx+"' class='far fa-thumbs-down'></i>0</span>";
            listhtml+="</div>";
            listhtml+="</div>";
            <!--================ -->

            listhtml += "</div>";

            console.log("댓글 ", listhtml);
            $("#reply-container").append(listhtml);
            $("#parent_content").val("");
            $("#selected").text("😀");
            console.log("여기까지 왓지");

        }).fail(error=>{
            console.log("오류", error);
        });


    }
    <!-- 대댓글 추가 -->
    function insertChild(parent_idx){

        const member_idx = $("#principalId").val();
        const writer = $("#principalNick").val();
        const content = $("#child-content-" +parent_idx).val();

        let listhtml = "";

        let data = {
            parent_idx : parent_idx,
            member_idx : member_idx,
            writer : writer,
            content : content
        }
        console.log(data);

        $.ajax({
            type:"post",
            url:`/replies/${board.free_idx}/insertChild`,
            data:JSON.stringify(data),
            contentType:"application/json; charset=utf-8",
            dataType:"json"
        }).done(res=>{
            console.log("성공", res);

            listhtml = "<div id='reply-total-container-"+res.reply_idx+"'>";

            listhtml+="<div class='rereply-section' id='rereply-section'>";

            listhtml += "<img src='/image/icon-rereply.png' alt='' class='rereply-img'>";
            listhtml += "<span>"+res.content+"</span>";
            listhtml += "<span>"+res.writer+"</span>";
            listhtml += "<span class='reply-date'>"+res.create_time+"</span>";
            listhtml += "<div class='rereply-btns'>";
            listhtml += "<a href='javascript:void(0)' onclick='deleteChild("+res.reply_idx+")' class='delete'>삭제</a>";
            listhtml += "</div>";

            listhtml+="</div>";

            listhtml += "</div>";

            console.log(listhtml);
            $('#rereply-container-'+res.parent_idx).append(listhtml);

            $("#child-content-" +parent_idx).val("");

        }).fail(error =>{
            console.log("오류", error);

        })
    }




    <!-- 부모댓글 삭제 (대댓글도 전부 삭제)-->
    function deleteParent(reply_idx){
        $.ajax({
           type:"get",
            url:"/replies/"+reply_idx+"/deleteParent",
            dataType:"json"

        }).done(res=>{
            console.log("성공",res);
            let container = $("#reply-total-container-"+reply_idx);
            container.css("display","none");
        }).fail(error=>{
            console.log("오류",error);
        });

    }

    <!-- 대댓글 삭제 -->
    function deleteChild(reply_idx){

        $.ajax({
            type:"get",
            url:"/replies/"+reply_idx+"/deleteChild",
            dataType:"json"

        }).done(res=>{
            console.log("성공",res);
            let container = $("#reply-total-container-"+reply_idx);
            container.css("display","none");
        }).fail(error=>{
            console.log("오류",error);
        });

    }

    <!-- 댓글 좋아요하기 -->
    function parentLike(reply_idx) {
        let likeIcon = $(`#parent-like-`+reply_idx);

        if(likeIcon.hasClass("far")) { //좋아요
            $.ajax({
                type:"post",
                url:`/replies/`+reply_idx+`/likes`,
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
        } else { // 좋아요 취소
            $.ajax({
                type: "delete",
                url: `/boards/` + free_idx + `/likes`,
                dataType: "json"
            }).done(res => {

                let likeContStr = $("#likeCount").text();
                let likeCount = Number(likeContStr) - 1;

                $("#likeCount").text(likeCount);

                likeIcon.removeClass("fas");
                likeIcon.removeClass("active");
                likeIcon.addClass("far");
            }).fail(error => {
                console.log("오류", error);
            });
        }
    }



    <!-- 댓글 싫어요 -->

</script>

