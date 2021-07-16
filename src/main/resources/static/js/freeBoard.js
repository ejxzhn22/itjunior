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

function test(){
    console.log("test")
}
test();




















































































































































































































































