<%--
 * show.jsp
 *
 * Copyright (C) 2018 Nozotro
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<display:table name="messageBox.messages" id="message">
	<display:column titleKey="message.subject">
		<jstl:out value="${message.subject}" />
	</display:column>
	<display:column titleKey="message.sender">
		<jstl:out value="${message.sender.name}" />
		<jstl:out value=" " />
		<jstl:out value="${message.sender.middleName}" />
		<jstl:out value=" " />
		<jstl:out value="${message.sender.surname}" />
	</display:column>
	<display:column titleKey="message.priority">
		<jstl:choose>
			<jstl:when test = "${message.priority == 'LOW'}">
				<spring:message code="message.priority.LOW" />
			</jstl:when>
			<jstl:when test = "${message.priority == 'NEUTRAL'}">
				<spring:message code="message.priority.NEUTRAL" />
			</jstl:when>
			<jstl:when test = "${message.priority == 'HIGH'}">
				<spring:message code="message.priority.HIGH" />
			</jstl:when>
		</jstl:choose>
	</display:column>
	<display:column titleKey="message.options">
		<a href="message/show.do?id=<jstl:out value="${message.id}" />">
			<spring:message code="message.open" />
		</a>
	</display:column>
</display:table>

<br/>

<jstl:if test="${messageBox.name != 'InBox' && messageBox.name != 'OutBox' && messageBox.name != 'TrashBox' && messageBox.name != 'SpamBox'}">
	<a href="message-box/delete.do?name=<jstl:out value="${messageBox.name}" />">
		<spring:message code="messageBox.delete" />
	</a>
</jstl:if>

<!--
<br/> <br/>
<display:table name="messageBox.name" id="pepitorcaja">
	<jstl:forEach items="${messageBox.messages}" var="message">
		<jstl:out value = "${message.body}"/>
	</jstl:forEach>
</display:table>
  -->

