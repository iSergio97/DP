<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<spring:message code="security.requiredFields" />

<article>

	<header>
		<spring:message code="security.register" />
	</header>

	<div class="content">
		<form action="customer/register.do?register=true" method="post">
			<div>
				<label for="name"> <spring:message code="security.name" /></label>
				<input type="text" id="name" />
			</div>
			<br>

			<div>
				<label for="midleName"> <spring:message
						code="security.middleName" /></label> <input type="text" id="middleName">
			</div>
			<br>

			<div>
				<label for="surname"> <spring:message
						code="security.surname" /></label> <input type="text" id="surname">
			</div>
			<br>

			<div>
				<label for="email"> <spring:message code="security.email" /></label>
				<input type="email" id="email">
			</div>
			<br>

			<div>
				<label for="phoneNumber"> <spring:message
						code="security.phoneNumber" /></label> <input type="text"
					id="phoneNumber">
			</div>
			<br>

			<div>
				<label for="address"> <spring:message
						code="security.address" /></label> <input type="text" id="address">
			</div>
			<br>

		</form>

		<input type="submit" value="<spring:message code='security.send'/>" />
		<br>
	</div>
</article>