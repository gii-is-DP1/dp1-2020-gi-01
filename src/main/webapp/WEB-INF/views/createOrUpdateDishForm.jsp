<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>

<!DOCTYPE html>
<body>
	<h2>
		<c:if test="${dish['new']}">New </c:if>
		Dish
	</h2>
	<form:form modelAttribute="dish" class="form-horizontal"
		action="/dishes/save" id="add-owner-form">
		<div class="form-group has-feedback">
			<petclinic:inputField label="Name" name="name" />
			<petclinic:inputField label="Description" name="description" />
			<petclinic:inputField label="Picture" name="picture" />
			<petclinic:inputField label="Price" name="price" />
			<petclinic:inputField label="Score" name="score" />
			<petclinic:inputField label="Allergens" name="allergens" />
		</div>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<c:choose>
					<c:when test="${dish['new']}">
						<button class="btn btn-default" type="submit">Add Dish</button>
					</c:when>
					<c:otherwise>
						<button class="btn btn-default" type="submit">Update Dish</button>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</form:form>
</body>
</html>