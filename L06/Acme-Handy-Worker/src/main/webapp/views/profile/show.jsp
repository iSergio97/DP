<%--
 * show.jsp
 *
 * Copyright (C) 2018 Universidad de Sevilla
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
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<p>
	<spring:message code="profile.show" />
</p>

<div>
	<spring:message code="profile.name" />
	:
	<jstl:out value="${actor.name}" />
	<br>

	<spring:message code="profile.middleName" />
	:
	<jstl:out value="${actor.middleName}" />
	<br>

	<spring:message code="profile.surname" />
	:
	<jstl:out value="${actor.surname}" />
	<br>

	<spring:message code="profile.photo" />
	:
	<jstl:out value="${actor.photo}" />
	<br>

	<spring:message code="profile.email" />
	:
	<jstl:out value="${actor.email}" />
	<br>

	<spring:message code="profile.phoneNumber" />
	:
	<jstl:out value="${actor.phoneNumber}" />
	<br>

	<spring:message code="profile.address" />
	:
	<jstl:out value="${actor.address}" />
	<br>
	<spring:message code="profile.isBanned" />
	?:
	<jstl:if test="${actor.isBanned == false }">
		<spring:message code='profile.isBannedTestNo' />
	</jstl:if>


	<jstl:if test="${actor.isBanned == true}">
		<spring:message code='profile.isBannedTestYes' />
	</jstl:if>
	<br>

		<a href="profile/edit.do">Editar perfil</a>

	<security:authorize access="hasRole('CUSTOMER')">
		<br>
		<a href="fixUpTask/customer/list.do">Fix-Up Tasks</a>
	</security:authorize>

	<security:authorize access="hasRole('SPONSOR')">
		<br>
		<a href="fixUpTask/customer/list.do">Fix-Up Tasks</a>
	</security:authorize>

	<security:authorize access="hasRole('HANDY_WORKER')">
		<br>
		<a href="fixUpTask/handyWorker/list.do">Fix-Up Tasks</a>
	</security:authorize>
</div>
