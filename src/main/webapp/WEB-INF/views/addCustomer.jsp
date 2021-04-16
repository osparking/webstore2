<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>새 고객 정보</title>
<!-- <link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css"> -->
</head>
<body>
	<section class="container">
		<form:form method="POST" modelAttribute="newCustomer"
			class="form-horizontal">
			<fieldset>
				<legend>새 고객 정보</legend>
				<div class="form-group">
					<label class="control-label col-lg-2 col-lg-2" for="customerId">
						<spring:message code="addCustomer.form.customerId.label"/>
					</label>
					<div class="col-lg-10">
						<form:input id="customerId" path="customerId" type="text"
							class="form:input-large" value="C101" />
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-lg-2 col-lg-2" for="name">
						<spring:message code="addCustomer.form.name.label"/>
					</label>
					<div class="col-lg-10">
						<form:input id="name" path="name" type="text"
							class="form:input-large" value="홍길동" />
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-lg-2 col-lg-2" for="address">
						<spring:message code="addCustomer.form.address.label"/>
					</label>
					<div class="col-lg-10">
						<form:input id="address" path="address" type="text"
							class="form:input-large" value="서울시 동작구 6020" />
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-lg-2 col-lg-2" for="noOfOrdersMade">
						<spring:message code="addCustomer.form.noOfOrdersMade.label"/>
					</label>
					<div class="col-lg-10">
						<form:input id="noOfOrdersMade" path="noOfOrdersMade" type="text"
							class="form:input-large" value="14" />
					</div>
				</div>
				<div class="form-group">
					<div class="col-lg-offset-2 col-lg-10">
						<input type="submit" id="btnAdd" class="btn btn-primary"
							value=<spring:message code="addCustomer.form.btnAdd.label"/> />
					</div>
				</div>
			</fieldset>
		</form:form>
	</section>
</body>
</html>