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
		<form method="POST">
			<input type="hidden" name="id" value="<jstl:out value="${customer.id}" />">
			<input type="hidden" name="version" value="<jstl:out value="${customer.version}" />" />
			<div>
				<label for="name">
					<spring:message code="security.name" />
					<spring:message code="security.required" />
				</label>
				<input type="text" name="name" value="<jstl:out value="${customer.name}" />" />
			</div>
			<br>
			<div>
				<label for="midleName">
					<spring:message code="security.middleName" />
				</label>
				<input type="text" name="middleName" value="<jstl:out value="${customer.middleName}" />" />
			</div>
			<br>
			<div>
				<label for="surname">
					<spring:message code="security.surname" />
					<spring:message code="security.required" />
				</label>
				<input type="text" name="surname" value="<jstl:out value="${customer.surname}" />" />
			</div>
			<br>
			<div>
				<label for="email">
					<spring:message code="security.email" />
					<spring:message code="security.required" />
				</label>
				<input type="text" name="email" value="<jstl:out value="${customer.email}" />" />
			</div>
			<br>
			<div>
				<label for="phoneNumber">
					<spring:message code="security.phoneNumber" />
				</label>
				<input type="text" name="phoneNumber" value="<jstl:out value="${customer.phoneNumber}" />" />
			</div>
			<br>
			<div>
				<label for="address">
					<spring:message code="security.address" />
				</label>
				<input type="text" name="address" value="<jstl:out value="${customer.address}" />" />
			</div>
			<br>
			<div>
				<label for="photo">
					<spring:message code="security.photo" />
				</label>
				<input type="text" name="photo" value="<jstl:out value="${customer.photo}" />" />
			</div>
			<br>
			<div>
				<label for="username">
					<spring:message code="security.username" />
					<spring:message code="security.required" />
				</label>
				<input type="text" name="username" value="<jstl:out value="${customer.userAccount.username}" />" />
			</div>
			<br>
			<div>
				<label for="password">
					<spring:message code="security.password" />
					<spring:message code="security.required" />
				</label>
				<input type="password" name="password" value="" />
			</div>
			<br>
			<input type="submit" value="<spring:message code='security.send'/>" />
			<br>
			<input type="button" name="cancel" value="<spring:message code='security.cancel' />" onclick="javascript: relativeRedir('security/login.do');" />
		</form>
	</div>

</article>
