<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<a href="fix-up-task/show.do">Back</a>

<form:form action="fix-up-task/edit.do" modelAttribute="fixUpTask">

<form:hidden path="id"/>
<form:hidden path="version"/>



<form:label path="address"></form:label>
<form:input path="address" />
<form:errors cssClass="error" path="address"></form:errors>
<br/>

<form:label path="maximumPrice"></form:label>
<form:input path="maximumPrice" />
<form:errors cssClass="error" path="maximumPrice"></form:errors>
<br/>

<form:label path="timeLimit"></form:label>
<form:input path="timeLimit" />
<form:errors cssClass="error" path="timeLimit"></form:errors>
<br/>

<form:select id="warranties" path="warranty"> 
<form:options items="${warranty}" itemLabel="title" itemValue="id" /> 
<form:option value="${fixUpTask.warranty.id}" label="${fixUpTask.warranty.title}" /> 
</form:select>
<br/>

<form:label path="description"></form:label>
<form:input path="description" />
<form:errors cssClass="error" path="description"></form:errors>
<br/>

<input type="submit" name="submit" value="<spring:message code="fixUpTask.update" />" />

</form:form>