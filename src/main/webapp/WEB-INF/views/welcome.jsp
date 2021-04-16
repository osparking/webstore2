<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01
Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width,
initial-scale=1 ">
<title>Welcome</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<%
	request.setCharacterEncoding("utf-8");
%>	
</head>
<body>
	<section>
	<div class="pull-right" style="padding-right: 50px">
		<a href="?language=ab">한글</a>|<a href="?language=en">English</a><br />
		<c:choose>
			<c:when test="${username == null}">
				<a href="<c:url value="/login" />">로그인</a>
			</c:when>
			<c:otherwise>
				환영합니다 "${username}"&nbsp;<a href="<c:url value="/logout" />">로그아웃</a>
			</c:otherwise>
		</c:choose>
	</div>
	</section>
	<div class="jumbotron">
		<h1>${greeting}</h1>
		<p>${tagline}</p>
	</div>
	<div>
	</div>
</body>
</html>