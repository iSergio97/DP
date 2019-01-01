<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>


<form:form modelAttribute="domainMessage" method="POST">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="date" />
	<form:hidden path="messageBoxes" />
	<form:hidden path="sender"/>
	
	<spring:message code="message.sender"/>:<jstl:out value="${domainMessage.sender.userAccount.username}"/>

	<div id="priority" class="priority">
		<form:label path="priority">
			<spring:message code="message.priority" />
		</form:label>
		<form:select path="priority">
			<form:option value="HIGH" />
			<form:option value="NEUTRAL" />
			<form:option value="LOW" />
		</form:select>
		<form:errors path="priority"/>
	</div>

	<div id="recipients" class="recipients">
		<form:label path="recipients">
			<spring:message code="message.recipients" />
		</form:label>
		<form:select path="recipients" multiple="true">
			<form:options items="${actors}" itemValue="id" itemLabel="userAccount.username"/>
		</form:select>
		<form:errors path="recipients"/>
	</div>

	<div id="subject" class="subject">
		<form:label path="subject">
			<spring:message code="message.subject" />
		</form:label>
		<form:input path="subject" />
		<form:errors path="subject"/>
	</div>

	<div id="tags" class="tags">
		<form:label path="body">
			<spring:message code="message.tags" />
		</form:label>
		<form:input path="tags" />
		<form:errors path="tags" />
	</div>

	<div id="body" class="body">
		<form:label path="body">
			<spring:message code="message.body" />
		</form:label>
		<form:input path="body" />
		<form:errors path="body"/>
	</div>
	
	<input type="submit" name="save"
			value="<spring:message code='security.send'/>" />
		<br>
		<input type="button" name="cancel"
			value="<spring:message code='security.cancel' />"
			onclick="javascript: relativeRedir('security/login.do');" />

</form:form>