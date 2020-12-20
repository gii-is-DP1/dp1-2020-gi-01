<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>

<petclinic:layout pageName="promotions">
	<h2>Edit Promotion</h2>
	<form:form modelAttribute="promotion" class="form-horizontal"
		action="/promotions/${promotion.id}/edit" id="add-owner-form">
		<div class="form-group has-feedback">
			<petclinic:inputField label="Title" name="title" />
			<petclinic:inputField label="Description" name="description" />
			<petclinic:inputField label="Code" name="code" />
			<petclinic:inputField label="Uses" name="uses" />
			<petclinic:inputField label="Start Date" name="startDate" />
			<petclinic:inputField label="End Date" name="endDate" />
		</div>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<button class="btn btn-default" type="submit">Edit Promotion</button>
			</div>
		</div>
	</form:form>
</petclinic:layout>