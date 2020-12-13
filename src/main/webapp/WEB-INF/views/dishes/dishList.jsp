<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>

<petclinic:layout pageName="dishes">
	<h2>Dishes</h2>

	<table id="dishesTable" class="table table-striped">
		<thead>
			<tr>
				<th style="width: 120px;">Name</th>
				<th style="width: 120px;">Description</th>
				<th style="width: 120px">Picture</th>
				<th style="width: 120px">Price</th>
				<th style="width: 120px">Score</th>
				<th style="width: 120px">Allergens</th>
				<th style="width: 120px">Actions</th>

			</tr>
		</thead>
		<tbody>
			<c:forEach items="${dishes}" var="dish">
				<tr>
					<td><c:out value="${dish.name}" /></td>
					<td><c:out value="${dish.description}" /></td>
					<td><c:out value="${dish.picture}" /></td>
					<td><c:out value="${dish.price}" /></td>
					<td><c:out value="${dish.score}" /></td>
					<td><c:out value="${dish.allergens}" /></td>

					<td><spring:url value="/dishes/{dishId}" var="dishInfUrl">
							<spring:param name="dishId" value="${dish.id}" />
						</spring:url> <a href="${fn:escapeXml(dishInfUrl)}"><c:out value="Details" /></a>
						<br>
						
						 <spring:url value="/dishes/delete/{dishId}"
							var="dishDelUrl">
							<spring:param name="dishId" value="${dish.id}" />
						</spring:url> <a href="${fn:escapeXml(dishDelUrl)}"><c:out value="Delete" /></a>
					</td>



				</tr>
			</c:forEach>
		</tbody>
	</table>
	<form action="/dishes/new" method="get">
		<input type="submit" value="Add Dish" name="Submit" id="frm1_submit" />
	</form>
</petclinic:layout>
