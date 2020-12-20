<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>

<petclinic:layout pageName="promotionInfo">
	<h2>Promotion Info</h2>

	<form:form modelAttribute="promotion" class="form-horizontal"
		id="add-owner-form">
		<div class="form-group has-feedback">
			<petclinic:inputField label="Title" name="title" readonly="true" />
			<petclinic:inputField label="Description" name="description"
				readonly="true" />
			<petclinic:inputField label="Code" name="code" readonly="true" />
			<petclinic:inputField label="Uses" name="uses" readonly="true" />
			<petclinic:inputField label="Start Date" name="startDate" readonly="true" />
			<petclinic:inputField label="End Date" name="endDate"
				readonly="true" />

		</div>
	</form:form>

	<spring:url value="{promotionId}/edit" var="editPromoUrl">
		<spring:param name="promotionId" value="${promotion.id}" />
	</spring:url>
	<a href="${fn:escapeXml(editPromoUrl)}" class="btn btn-default">Edit
		Promotion</a>

</petclinic:layout>
