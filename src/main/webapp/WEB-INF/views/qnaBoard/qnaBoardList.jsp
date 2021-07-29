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
        <form action="/qnaboards" method="post" class="board-search">
            <input type="hidden" name="currentPageNo" value="1">
            <input type="hidden" name="recordsPerPage" value="10">
            <select name="searchType" class="board-select">
                <option value="all">전체</option>
                <option value="writer">작성자</option>
                <option value="title">질문</option>
            </select>
            <input type="text" name="searchKeyword" class="board-input" autocomplete="off">
            <input type="submit" value="🔍" class="board-submit">
        </form>

        <ul class="board-table2">

            <c:forEach var="qna" items="${qnas}">
                <li>
                    <c:choose>
                        <c:when test="${qna.secret_yn == 'Y' and principal.member.nickname != 'root'}">
                            <a href="#" onclick="openMask(${qna.qna_idx});" class="qna-a openMask">
                        </c:when>
                        <c:otherwise>
                            <a href="#" onclick="goDetail(${qna.qna_idx},'${page.makeQueryString(page.currentPageNo)}');" class="qna-a openMask">
                        </c:otherwise>
                    </c:choose>

                        <div class="qna-div1">
                            <div class="title">
                                <c:choose>
                                    <c:when test="${qna.secret_yn == 'Y'}">
                                        <span class="qna-q"><i class="fas fa-lock"></i></span>
                                    </c:when>
                                    <c:otherwise>
                                        <span class="qna-q"><i class="fas fa-lock-open"></i></span>
                                    </c:otherwise>
                                </c:choose>

                                <span class="qna-q">Q.</span>
                                ${qna.title}
                            </div>
                            <div class="">${qna.writer} | ${qna.create_time}</div>
                        </div>
                        <div class="qna-div2">
                            <c:if test="${qna.reply_yn == 'Y'}">
                                <span class="success-badge badge">답변완료</span>
                            </c:if>
                            <c:if test="${qna.reply_yn == 'N'}">
                                <span class="wait-badge badge">답변대기</span>
                            </c:if>
                        </div>
                    </a>
                </li>
            </c:forEach>

        </ul>
    </div>
    <div class="board-footer">
        <c:if test="${page != null and page.paginationInfo.totalRecordCount > 0}">

            <ul class="board-paging" style="display: flex">
                <c:if test="${page.paginationInfo.hasPreviousPage}">
                    <li onclick="movePage('${page.makeQueryString(1)}')" >
                        <a href="javascript:void(0)"> << </a>
                    </li>
                    <li onclick="movePage('${page.makeQueryString(page.paginationInfo.firstPage - 1)}')" >
                        <a href="javascript:void(0)"> < </a>
                    </li>
                </c:if>
                <c:forEach begin="${page.paginationInfo.firstPage}" end="${page.paginationInfo.lastPage}" var="num">
                    <li onclick="movePage('${page.makeQueryString(num)}')" >
                        <a href="javascript:void(0)">${num}</a>
                    </li>
                </c:forEach>

                <c:if test="${page.paginationInfo.hasNextPage}">
                    <li onclick="movePage('${page.makeQueryString(page.paginationInfo.lastPage + 1)}')">
                        <a href="javascript:void(0)"> > </a>
                    </li>
                    <li onclick="movePage('${page.makeQueryString(page.paginationInfo.totalPageCount)}')" >
                        <a href="javascript:void(0)"> >> </a>
                    </li>
                </c:if>
            </ul>

        </c:if>
        <a class="board-writing" href="/qnaboards/form">질문하기</a>
    </div>
</div>
<!-- modal -->
<div id="mask"></div>
<form action="" method="post" class="window">
    <div class="modal-container">
        <input type="hidden" value="">
        <img src="image/icon-lock.png" alt="" class="icon-lock">
        <h2>작성 시 입력하신 비밀번호를 입력하세요.</h2>
        <input type="password" id="modal-pwd" name="secret_pwd" value="" class="modal-pw" maxlength="4" autofocus/>
        <div class="btns-container">
            <input id="modal-btn" onclick="pwdCheck(this.name,'${page.makeQueryString(page.currentPageNo)}')" type="button" value="확인">
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

    function openMask(qna_idx){
        // e.preventDefault();
        wrapWindowByMask();

        // 서브밋 클릭시 그냥 이동되게 테스트했습니다
        //location.href="qnaboards/"+qna_idx+"/detail";

        //console.log(e);
        console.log(qna_idx );

        $("#modal-btn").attr("name", qna_idx);
        $(".modal-pw").val("");
        $(".modal-pw").focus();
    }

    function pwdCheck(qna_idx, queryString) {

        console.log("qna",qna_idx);
        let data = {
            "pwd": $("#modal-pwd").val()
        }

        $.ajax({
            type:"post",
            url:"/qnaboards/pwdCheck/"+qna_idx,
            data:JSON.stringify(data),
            contentType:"application/json; utf-8",
            dataType:"json"
        }).done(res=>{
            console.log("성공", res);
            if(res){
                location.href="qnaboards/"+qna_idx+"/detail";
            }else{
                alert("비밀번호가 틀렸습니다.");
                $(".modal-pw").val("");
                $(".modal-pw").focus();
            }
        }).fail(fail=>{
            console.log("실패", fail);
        });
    }

    function goDetail(qna_idx, queryString){
        location.href="qnaboards/"+qna_idx+"/detail";
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

    function movePage(queryString) {
        location.href = "/boards" + queryString;
    }

</script>

