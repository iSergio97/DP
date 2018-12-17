<%--
 * action-1.jsp
 *
 * Copyright (C) 2018 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<p>
	<spring:message code="application.received" />
</p>

<display:table name="applications" id="application">
	<display:column property="moment" titleKey="application.moment" />
	<display:column titleKey="application.status">
		<jstl:choose>
			<jstl:when test = "${application.status == 'PENDING'}">
				<spring:message code="application.status.pending" />
			</jstl:when>
			<jstl:when test = "${application.status == 'ACCEPTED'}">
				<spring:message code="application.status.accepted" />
			</jstl:when>
			<jstl:when test = "${application.status == 'REJECTED'}">
				<spring:message code="application.status.rejected" />
			</jstl:when>
			<jstl:otherwise>
			</jstl:otherwise>
		</jstl:choose>
	</display:column>
	<display:column property="offeredPrice" titleKey="application.offeredprice" />
	<display:column titleKey="application.handyworker">
		<div>
			<jstl:out value="${application.handyWorker.name}" />
		</div>
	</display:column>
	<display:column titleKey="application.fixuptask">
		<div>
			<jstl:out value="${application.fixUpTask.description}" />
		</div>
	</display:column>
	<display:column titleKey="application.options">
		<div>
			<a href="application/display.do?id=${application.id}">
				<spring:message code="application.open" />
			</a>
			<jstl:if test = "${application.status == 'PENDING'}">
				<form action="application/customer.do" method="POST">
					<input type="hidden" name="applicationid" value="<jstl:out value="${application.id}" />">
					<input type="submit" name="accept" value='<spring:message code="application.accept" />' />
					<input type="submit" name="reject" value='<spring:message code="application.reject" />' />
				</form>
			</jstl:if>
		</div>
	</display:column>
</display:table>
