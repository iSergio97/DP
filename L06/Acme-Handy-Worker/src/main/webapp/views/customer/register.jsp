<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<spring:message code="security.requiredFields" />

<!--  <article> -->

	<%-- Añadir breakpoint en create --%>

	<form:form commandName="customer" method="POST">
	<form:hidden path="id" />
	<form:hidden path="version" />
	<spring:message code="security.name"/>
	<form:input path="name"/>
	</form:form> 