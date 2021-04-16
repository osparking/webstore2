<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page errorPage="error_id.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ID로 찾은 상품</title>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.5.5/angular.min.js">
</script>
<script src="/webstore/resources/js/controller.js"></script>
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
</head>
<body>
	<div class="pull-right" style="padding-right: 50px">
		<a href="?id=${product.productId}&lang=ko">한글</a>
		|<a href="?id=${product.productId}&lang=en">Eng</a>
		&nbsp; <a href="<c:url value="/logout"/>">로그아웃</a>
	</div>
	<c:if test="${product == null}">
		<%
			request.setAttribute("id: ", "${id}");
		RequestDispatcher dispatcher = request.getRequestDispatcher("error_id.jsp");
		dispatcher.forward(request, response);
		%>
	</c:if>
	<section>
		<div class="jumbotron">
			<div class="container">
				<h1>상품 검색 결과</h1>
			</div>
		</div>
	</section>
	<section class="container" ng-app="cartApp">
		<div class="row">
			<div class="col-md-5">
				<img src="<c:url value='/img/${product.productId}.png'/>" style="width:100%">
			</div>
			<div class="col-md-5">
				<h3>${product.name}</h3>
				<p>${product.description}</p>
				<p>
					<strong><spring:message code="product.page.title"/>
					: </strong> <span class="label label-warning">${product.productId}</span>
				</p>
				<p>
					<strong>제조사</strong> : ${product.manufacturer}
				</p>
				<p>
					<strong>상품범주</strong> : ${product.category}
				</p>
				<p>
					<strong>재고 수량</strong> : ${product.unitsInStockComma}
				</p>
				<p>
					<strong>단가(한화)</strong> : ₩${product.unitPriceComma}
				</p>
				<p>
					<strong> <a
						href="<c:url value='/pdf/${product.productId}.pdf '/>"> 메뉴얼
							내려받기(${product.productId}.pdf)</a>
					</strong> :
				</p>
								
				<p ng-controller="cartCtrl">>
					<a href="<spring:url value='/market/products'/>"
						class="btn btn-default"><span
						class="glyphicon-hand-left glyphicon"></span> 뒤로 가기</a> 
					<a href="#" class="btn btn-warning btn-large"
						ng-click="addToCart('${product.productId}')"> <span
						class="glyphicon-shopping-cart glyphicon"></span>
						<spring:message code="product.form.putOrder.link"/></a> 
					<a href="<spring:url value='/cart' />"
						class="btn btn-default"> <span
						class="glyphicon-hand-right glyphicon"></span>View Cart</a>
				</p>
			</div>
		</div>
	</section>
</body>
</html>