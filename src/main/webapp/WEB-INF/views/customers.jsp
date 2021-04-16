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
<title><spring:message code="customers.title.text"/></title>
</head>
<body>
	<section class="container">
		<div class="row">
			<c:forEach items="${customers}" var="customer">
				<div class="col-sm-6 col-md-3">
					<div class="thumbnail">
						<div class="caption">
							<h3>${customer.name}</h3>
							<p>${customer.phoneNumber}</p>
							<p>${customer.customerId}</p>
							<p>${customer.billingAddress.zipCode}&nbsp;
							${customer.billingAddress.wideCiDo}&nbsp;
							${customer.billingAddress.ciGoonGu}&nbsp;
							${customer.billingAddress.streetName}&nbsp;
							${customer.billingAddress.buildingNo}&nbsp;
							${customer.billingAddress.unitNo}</p>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</section>
</body>
</html>