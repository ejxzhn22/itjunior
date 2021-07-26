<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>

<%@include file="../layout/header.jsp"%>

<div class="banner-section">
    <div class="banner-write">
        <span class="banner-title">취업토론</span>
        <span class="banner-desc">취업에 관련된 <br>정보들을 나눠봅시다✍️</span>
    </div>
    <div class="banner-img">
        <img src="${pageContext.request.contextPath}/image/banner-img.png" alt="banner img" class="banner-img">
        <img src="${pageContext.request.contextPath}/image/main-o.svg" alt="" class="banner-o-img">
    </div>
</div>

<div class="content-section">
    <form action="/boards/new" method="POST" class="write-form">

        <div class="write-content">
            <select id="select" name="category" class="board-category-select" onchange="changeSelect()" required>
                <option selected disabled>카테고리</option>
                <c:forEach var="category" items="${categories}">
                    <option  id="${category.cate_no}" name="category" value="${category.cate_no}">${category.name} </option>
                </c:forEach>
            </select>
            <input type="text" class="cate-input" id="cate_name"  name="cate_name" readonly>
            <input type="text" class="write-input" id="title" placeholder="Enter title" name="title" required>
        </div>

        <textarea id="summernote" placeholder="Enter content" name="content"></textarea>

        <div class="btns-box">
            <input type="submit" value="작성하기" class="write-submit">
            <a href="/boards" class="btn1">목록</a>
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

    function changeSelect() {

        let select = document.getElementById("select");
        let selectText = select.options[select.selectedIndex].text;
        console.log(selectText);

        $('input[name=cate_name]').attr('value', "["+selectText+"]");

    }
</script>

<%@include file="../layout/footer.jsp"%>

