<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<spring:message code="security.requiredFields" />

<!--  <article> -->

<%-- Añadir breakpoint en create --%>

<form:form commandName="customer" method="POST">
	<!-- Campos ocultos -->
	<form:hidden path="id" />
	<form:hidden path="version" />

	<!-- Comienzo datos del formulario -->
	<div>
		<spring:message code="security.name" />
		<form:input path="name" />
		<form:errors path="name" />
	</div>
	<br>

	<div>
		<spring:message code="security.middleName" />
		<form:input path="middleName" />
		<form:errors path="middleName" />
	</div>
	<br>

	<div>
		<spring:message code="security.surname" />
		<form:input path="surname" />
		<form:errors path="surname" />
	</div>
	<br>

	<div>
		<spring:message code="security.email" />
		<form:input path="email" />
		<form:errors path="email" />
	</div>
	<br>

	<div>
		<spring:message code="security.phoneNumber" />
		<form:input path="phoneNumber" />
		<form:errors path="phoneNumber" />
	</div>
	<br>

	<div>
		<spring:message code="security.address" />
		<form:input path="address" />
		<form:errors path="address" />
	</div>
	<br>

	<div>
		<spring:message code="security.photo" />
		<form:input path="photo" />
		<form:errors path="photo" />
	</div>
	<br>

	<br>
	<input type="submit" value="<spring:message code='security.send'/>" />
	<br>
	<input type="button" name="cancel"
		value="<spring:message code='security.cancel' />"
		onclick="javascript: relativeRedir('security/login.do');" />
</form:form>
