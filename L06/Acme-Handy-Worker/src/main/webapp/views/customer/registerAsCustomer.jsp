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
		<form action="customer/register.do?register=true" method="POST">
		<input type="hidden" id="id"/>
		<input type="hidden" id="version"/>
			<div>
				<label for="name"> <spring:message
						code="security.name" /><spring:message
						code="security.required" /></label> <input type="text" id="name" />
			</div>
			<br>

			<div>
				<label for="midleName"> <spring:message
						code="security.middleName" /></label> <input type="text" id="middleName">
			</div>
			<br>

			<div>
				<label for="surname"> <spring:message
						code="security.surname" /><spring:message
						code="security.required" /></label> <input type="text" id="surname">
			</div>
			<br>

			<div>
				<label for="email"> <spring:message
						code="security.email" /><spring:message
						code="security.required" /></label> <input type="email" id="email">
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

			<div>
				<label for="photo"> <spring:message
						code="security.photo" /></label> <input type="URL" id="photo">
			</div>
			<br>
			
			<div>
				<label for="username"> <spring:message
						code="security.username" /><spring:message
						code="security.required" /></label> <input type="text" id="username">
			</div>
			<br>
			
			<div>
				<label for="password"> <spring:message
						code="security.password" /><spring:message
						code="security.required" /></label> <input type="password" id="password">
			</div>
			<br> <input type="submit"
				value="<spring:message code='security.send'/>" /> <br> <input
				type="button" name="cancel"
				value="<spring:message code='security.cancel' />"
				onclick="javascript: relativeRedir('security/login.do');" />

		</form>
	</div>
</article>