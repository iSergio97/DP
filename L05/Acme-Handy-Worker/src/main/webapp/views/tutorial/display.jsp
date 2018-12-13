<%--
 * action-2.jsp
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
	<spring:message code="tutorial.display" />
</p>

<p>
	<spring:message code="application.moment" />
	<br>
	<jstl:out value="${application.moment}" />
</p>
<p>
	<spring:message code="application.status" />
	<br>
	<jstl:if test = "${application.status == 'PENDING'}">
		<spring:message code="application.status.pending" />
	</jstl:if>
</p>
<p>
	<spring:message code="application.offeredprice" />
	<br>
	<jstl:out value="${application.offeredPrice}" />
</p>
<p>
	<spring:message code="application.customer" />
	<br>
	<jstl:out value="${application.fixUpTask.customer.name}" />
</p>
<p>
	<spring:message code="application.handyworker" />
	<br>
	<jstl:out value="${application.handyWorker.name}" />
</p>
<p>
	<spring:message code="application.fixuptask" />
	<br>
	<jstl:out value="${application.fixUpTask.description}" />
</p>
<p>
	<spring:message code="application.comments" />
	<jstl:forEach items="${application.comments}" var="comment">
		<br>
		<jstl:out value="${comment}" />
	</jstl:forEach>
</p>

<form action="application/addcomment.do" method="POST">
	<input type="hidden" name="applicationid" value="<jstl:out value="${application.id}" />">
	<p>
		<spring:message code="application.addcomment" />
		<br><input type="text" name="applicationcomment">
	</p>
	<input type="submit" name="save" value="<spring:message code="application.save" />" />
</form>
