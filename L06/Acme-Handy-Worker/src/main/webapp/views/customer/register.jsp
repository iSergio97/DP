<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<spring:message code="security.requiredFields" />
<br />
<br />

<!--  <article> -->

<div>
	<form:form modelAttribute="customer" method="POST">
		<!-- Campos ocultos -->
		<form:hidden path="id" />
		<form:hidden path="version" />
		<form:hidden path="messageBoxes" />
		<form:hidden path="userAccount" />


		<div>
			<form:label path="Name">
				<spring:message code="security.name" />
			</form:label>
			<form:input path="name" />
			<form:errors path="name" />
		</div>
		<br>

		<div>
			<form:label path="MiddleName">
				<spring:message code="security.middleName" />
			</form:label>
			<form:input path="middleName" />
			<form:errors path="middleName" />
		</div>
		<br>

		<div>
			<form:label path="Surname">
				<spring:message code="security.surname" />
			</form:label>
			<form:input path="surname" />
			<form:errors path="surname" />
		</div>
		<br>

		<div>
			<form:label path="Email">
				<spring:message code="security.email" />
			</form:label>
			<form:input path="email" />
			<form:errors path="email" />
		</div>
		<br>

		<div>
			<form:label path="phoneNumber">
				<spring:message code="security.phoneNumber" />
			</form:label>
			<form:input path="phoneNumber" />
			<form:errors path="phoneNumber" />
		</div>
		<br>

		<div>
			<form:label path="address">
				<spring:message code="security.address" />
			</form:label>
			<form:input path="address" />
			<form:errors path="address" />
		</div>
		<br>

		<div>
			<form:label path="photo">
				<spring:message code="security.photo" />
			</form:label>
			<form:input path="photo" />
			<form:errors path="photo" />
		</div>
		<br>

		<input type="submit" name="save"
			value="<spring:message code='security.send'/>" />
		<br>
		<input type="button" name="cancel"
			value="<spring:message code='security.cancel' />"
			onclick="javascript: relativeRedir('security/login.do');" />


	</form:form>
</div>