<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Bootstrap Example</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>

<div class="container">
    <h2>Stacked form</h2>

    <form action="/boards/new" method="post">
        <select id="select" name="category" class="custom-select" onchange="changeSelect()">
            <option selected disabled>카테고리</option>
            <c:forEach var="category" items="${categories}">
                <option  id="${category.cate_no}" name="category" value="${category.cate_no}">[${category.name}]</option>
            </c:forEach>
        </select>
        <div class="form-group">
            <label for="title">Title:</label>
            <input type="text" class="form-control" id="title" placeholder="Enter title" name="title">
        </div>
        <div class="form-group">
            <label for="content">Content:</label>
            <textarea  class="form-control" rows="5" id="content" placeholder="Enter content" name="content"></textarea>
        </div>

        <button type="submit" class="btn btn-primary">Submit</button>
    </form>

</div>
<script>
    function changeSelect() {

        let select = document.getElementById("select");
        let selectText = select.options[select.selectedIndex].text;
        console.log(selectText);

        $('input[name=title]').attr('value', selectText);

    }
</script>
</body>
</html>
