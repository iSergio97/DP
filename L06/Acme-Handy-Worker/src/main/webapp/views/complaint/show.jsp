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

<a href="complaint/customer/list.do"><spring:message code="complaint.return" /></a><br/>


<p>
	<spring:message code="complaint.ticker" />
	<br>
	<jstl:out value=": ${complaint.ticker}" />
</p>

<p>
	<spring:message code="complaint.moment" />
	<br>
	<jstl:out value=": ${complaint.moment}" />
</p>

<p>
	<spring:message code="complaint.description" />
	<br>
	<jstl:out value=": ${complaint.description}" />
</p>

<p>
	<spring:message code="complaint.attachments" />
	<jstl:forEach items="${complaint.attachments}" var="attachment">
		<br>
		<a href="<jstl:out value="${attachment}" />">
			<jstl:out value=": ${attachment}" />
		</a>
	</jstl:forEach>
</p>

<p>
	<spring:message code="complaint.fixUpTask" />
	<br>
	<jstl:out value=": ${complaint.fixUpTask.ticker}" />
</p>

<p>
<spring:message code="complaint.reports"/>
<display:table name="reports" id="row">
	<display:column property="id" titleKey="report.id"/>
	<display:column property="moment" titleKey="report.moment" />
	<display:column property="description" titleKey="report.description" />
</display:table>
</p>
	