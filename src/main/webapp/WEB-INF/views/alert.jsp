<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<%@include file="layout/header.jsp"%>
<script type="text/javascript">
    let message = "${msg}";
    let url = "${url}"

    alert(message);
    location.href = url;
</script>

<%@include file="layout/footer.jsp"%>


