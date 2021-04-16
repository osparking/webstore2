<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
<title><spring:message code="products.title.text"/></title>
</head>
<body>
	<section>
		<div class="pull-right" style="padding-right: 50px">
			<a href="<c:url value="add"/>">상품 추가</a>
		</div>		
	</section>
	<section class="container">
		<div class="row">
			<c:forEach items="${products}" var="product">
				<div class="col-sm-6 col-md-3" style="height:450px">
					<div class="thumbnail" style="height:100%">
						<img src="<c:url value='/img/${product.productId}.png'/>"
							alt="상품 사진" style="width:60%">
						<div class="caption">
							<h3>${product.name}</h3>
							<p>${product.description}</p>
							<p>₩${product.unitPriceComma}</p>
							<p><spring:message code="products.unitsInStockComma.label"/>: ${product.unitsInStockComma}</p>
							<p>
								<a href="<spring:url value="/market/product?id=${product.productId}"/>"
									class="btn btn-primary">
									<span class="glyphicon-info-sign glyphicon"></span>
									<spring:message code="products.anchor.text"/>
								</a>
							</p>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>

	</section>
</body>
</html>