<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Bootstrap Example</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css" integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>

<div class="container">
    <h2>Detail</h2>

        <div class="form-group">
            <label for="title">Title:</label>
            <input type="text" class="form-control" id="title" value="${board.title}" name="title" readonly>
        </div>
        <div class="form-group">
            <label for="content">Content:</label>
            <textarea  class="form-control" rows="5" id="content"  name="content" readonly>${board.content}</textarea>
        </div>
        <div class="form-group d-flex justify-content-around">
            <div class="view">
                <h2><i class="far fa-grin-hearts"></i></h2>
                <h2>${board.viewcnt}</h2>
            </div>
            <div class="like">
                <h2><i id="likeIcon-${board.free_idx}" class="far fa-thumbs-up" onclick="like(${board.free_idx}"></i></h2>
                <h2 id="likeCount">${board.likecnt}</h2>
            </div>
        </div>


</div>
<script>
    function like(free_idx){
        let likeIcon = $(`#likeIcon-${free_idx}`);

        if(likeIcon.hasClass("far")) { //좋아요
            $.ajax({
                type:"post",
                url:`/boards/${free_idx}/likes`,
                dataType:"json"
            }).done(res=>{
                let likeContStr = $(`#likeCount`).text();
                let likeCount = Number(likeContStr) +1;

                $(`#likeCount`).text(likeCount);

                likeIcon.addClass("fas");
                likeIcon.addClass("active");
                likeIcon.removeClass("far");
            }).fail(error=>{
                console.log("오류", error);
            });
        } else{ // 좋아요 취소
            $.ajax({
                type: "delete",
                url:`/boards/${free_idx}/likes`,
                dataType:"json"
            }).done(res=> {

                let likeContStr = $(`#likeCount`).text();
                let likeCount = Number(likeCountStr) - 1;

                $(`#likeCount`).text(likeCount);

                likeIcon.removeClass("fas");
                likeIcon.removeClass("active");
                likeIcon.addClass("far");
            }).fail(error=>{
                console.log("오류", error);
            });

        }
    }
</script>
</body>
</html>
