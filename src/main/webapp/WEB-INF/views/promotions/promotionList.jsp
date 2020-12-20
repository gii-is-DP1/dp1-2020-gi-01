<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>

<petclinic:layout pageName="promotions">
	<h2>Promotions</h2>

	<table id="promoTable" class="table table-striped">
		<thead>
			<tr>
				<th style="width: 120px;">Title</th>
				<th style="width: 120px;">Description</th>
				<th style="width: 120px">Code</th>
				<th style="width: 120px">Uses</th>
				<th style="width: 120px">Start Date</th>
				<th style="width: 120px">End Date</th>
				<th style="width: 120px">Actions</th>

			</tr>
		</thead>
		<tbody>
			<c:forEach items="${promotions}" var="promotion">
				<tr>
					<td><c:out value="${promotion.title}" /></td>
					<td><c:out value="${promotion.description}" /></td>
					<td><c:out value="${promotion.code}" /></td>
					<td><c:out value="${promotion.uses}" /></td>
					<td><c:out value="${promotion.startDate}" /></td>
					<td><c:out value="${promotion.endDate}" /></td>

					<td><spring:url value="/promotions/{promotionId}"
							var="promoInfUrl">
							<spring:param name="promotionId" value="${promotion.id}" />
						</spring:url> <a href="${fn:escapeXml(promoInfUrl)}"><c:out value="Details" /></a>
						<br> 
						
						<spring:url value="/promotions/delete/{promotionId}"
							var="promoDelUrl">
							<spring:param name="promotionId" value="${promotion.id}" />
						</spring:url> <a href="${fn:escapeXml(promoDelUrl)}"><c:out value="Delete" /></a>
					</td>



				</tr>
			</c:forEach>
		</tbody>
	</table>
	<form action="/promotions/new" method="get">
		<input type="submit" value="Add Promotion" name="Submit"
			id="frm1_submit" />
	</form>
</petclinic:layout>
