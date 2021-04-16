<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 정보 입력</title>
<!-- <link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css"> -->
</head>
<body>
	<section>
		<div class="pull-right" style="padding-right: 50px">
			<a href="<c:url value="/market/products"/>">목록으로 </a>
		</div>		
	</section>
	<section class="container">
		<form:form method="POST" modelAttribute="newProduct"
			class="form-horizontal" enctype="multipart/form-data">
			<form:errors path="*" cssClass="alert alert-danger" element="div"/>
			<fieldset>
				<legend>신상품 정보 입력</legend>
				<div class="form-group">
					<label class="control-label col-lg-2 col-lg-2" for="productId">
						<spring:message code="addProduct.form.productId.label"/>
					</label>
					<div class="col-lg-10">
						<form:input id="productId" path="productId" type="text"
							class="form:input-large" value="P20" />
						<form:errors path="productId" cssClass="text-danger"/>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-lg-2 col-lg-2" for="name">
						<spring:message code="addProduct.form.productName.label"/>
					</label>
					<div class="col-lg-10">
						<form:input id="name" path="name" type="text"
							class="form:input-large" value="전기연장선" />
						<form:errors path="name" cssClass="text-danger"/>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-lg-2 col-lg-2" for="unitPrice">
						<spring:message code="addProduct.form.unitPrice.label"/>
					</label>
					<div class="col-lg-10">
						<form:input id="unitPrice" path="unitPrice" type="text"
							class="form:input-large" value="20000" />
						<form:errors path="unitPrice" cssClass="text-danger"/>							
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-lg-2 col-lg-2" for="manufacturer">
						<spring:message code="addProduct.form.manufacturer.label"/>
					</label>
					<div class="col-lg-10">
						<form:input id="manufacturer" path="manufacturer" type="text"
							class="form:input-large" value="국산" />
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-lg-2 col-lg-2" for="category">
						<spring:message code="addProduct.form.category.label"/>
					</label>
					<div class="col-lg-10">
						<form:input id="category" path="category" type="text"
							class="form:input-large" value="전기"/>
						<form:errors path="category" cssClass="text-danger" />
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-lg-2 col-lg-2" for="unitsInStock">
						<spring:message code="addProduct.form.unitsInStock.label"/>
					</label>
					<div class="col-lg-10">
						<form:input id="unitsInStock" path="unitsInStock" type="text"
							class="form:input-large" value="1"
							onkeypress="return event.charCode === 0 || /\d/.test(String.fromCharCode(event.charCode));" />
						<form:errors path="unitsInStock" cssClass="text-danger" />
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-lg-2" for="description">
						<spring:message code="addProduct.form.description.label"/>
					</label>
					<div class="col-lg-10">
						<form:textarea id="description" path="description" rows="2" />
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-lg-2" for="condition"> 
						<spring:message code="addProduct.form.condition.label"/>
					</label>
					<div class="col-lg-10">
						<form:radiobutton path="condition" value="New" checked="checked"/>
						New
						<form:radiobutton path="condition" value="Old" />
						Old
						<form:radiobutton path="condition" value="Refurbished" />
						Refurbished
					</div>
				</div>
				<!-- 상품 영상 입력 -->
				<div class="form-group">
					<label class="control-label col-lg-2" for="productImage"> 
						<spring:message
								code="addProduct.form.productImage.label" />
					</label>
					<div class="col-lg-1 0">
						<form:input id="productImage" path="productImage" type="file"
							class="form:input-large" />
						<form:errors path="productImage" cssClass="text-danger"/>
					</div>
				</div>
				<!-- 사용자 메뉴얼 -->
				<div class="form-group">
					<label class="control-label col-lg-2" for="pdfManual"> 
						<spring:message
								code="addProduct.form.pdfManual.label" />
					</label>
					<div class="col-lg-10">
						<form:input id="pdfManual" path="pdfManual" type="file"
							class="form:input-large" />
					</div>
				</div>

				<div class="form-group">
					<div class="col-lg-offset-2 col-lg-10">
						<input type="submit" id="btnAdd" class="btn btn-primary"
							value=<spring:message code="addProduct.form.btnAdd.label"/> />
						<a href="<spring:url value='/market/products'/>"
							class="btn btn-primary">
							<spring:message code="addProduct.form.btnCancel.label" /></a>
					</div>
				</div>
			</fieldset>
		</form:form>
	</section>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js">
	</script>
	<script type="text/javascript">
		$(document).ready(function() {
			document.getElementById('description').value 
				/* = "Works great. As good as new."; */
 				= "작동 잘됩니다. 보관만 했네요. 새상품이나 마찬가지입니다.";  
		});
	</script>

</body>
</html>