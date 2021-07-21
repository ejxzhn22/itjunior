<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>

<div class="container-fluid">

    <!-- 유효성 에러 테스트 -->
    <div class="text-center">
        <div class="error mx-auto" data-text="404">valid Exception</div>
        <p class="lead text-gray-800 mb-5">유효성 검사 실패. 입력을 확인하세요</p>
        <p class="text-gray-500 mb-0">It looks like you error type or parameters >> ${errorMap}</p>
    </div>

</div>