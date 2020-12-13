<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>

<petclinic:layout pageName="dishInfo">
	<h2>Dish Info</h2>

	<form:form modelAttribute="dish" class="form-horizontal"
		id="add-owner-form">
		<div class="form-group has-feedback">
			<petclinic:inputField label="Name" name="name" readonly="true" />
			<petclinic:inputField label="Description" name="description"
				readonly="true" />
			<petclinic:inputField label="Picture" name="picture" readonly="true" />
			<petclinic:inputField label="Price" name="price" readonly="true" />
			<petclinic:inputField label="Score" name="score" readonly="true" />
			<petclinic:inputField label="Allergens" name="allergens"
				readonly="true" />
		</div>
	</form:form>

	<spring:url value="{dishId}/edit" var="editDishUrl">
		<spring:param name="dishId" value="${dish.id}" />
	</spring:url>
	<a href="${fn:escapeXml(editDishUrl)}" class="btn btn-default">Edit
		Dish</a>

</petclinic:layout>
