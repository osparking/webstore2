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
<title>고객 목록-버전 2</title>
</head>
<body>
	<section class="container">
		<div class="row">
			<c:forEach items="${customers}" var="customer">
				<div class="col-sm-6 col-md-3">
					<div class="thumbnail">
						<div class="caption">
							<h3>${customer.name}</h3>
							<p>${customer.customerId}</p>
							<p>${customer.billingAddress.zipCode}</p>
							<p>${customer.billingAddress.wideCiDo}</p>
							<p>${customer.billingAddress.ciGoonGu}</p>
							<p>${customer.billingAddress.streetName}</p>
							<p>${customer.billingAddress.buildingNo}</p>
							<p>${customer.billingAddress.unitNo}</p>
							<p>${customer.phoneNumber}</p>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</section>
</body>
</html>