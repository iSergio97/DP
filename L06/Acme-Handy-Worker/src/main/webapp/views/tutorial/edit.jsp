<%--
 * action-2.jsp
 *
 * Copyright (C) 2018 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<p>
	<spring:message code="tutorial.edit" />
</p>

<form:form modelAttribute="tutorial" action="tutorial/edit.do">

	<!-- Hidden fields -->
	<form:hidden path="id"/>
	<form:hidden path="version" />
	<form:hidden path="pictures" />
	<form:errors cssClass="error" path="pictures"></form:errors>
	<form:hidden path="sections" />
	<form:errors cssClass="error" path="sections"></form:errors>
	<form:hidden path="handyWorker" />
	<form:errors cssClass="error" path="handyWorker"></form:errors>
	<form:hidden path="sponsorship" /><form:errors cssClass="error" path="sponsorship"></form:errors>
	<form:hidden path="lastUpdated" />
	<form:errors cssClass="error" path="lastUpdated"></form:errors>

	<!-- Editable fields -->
	
<form:label path="title"><spring:message code="tutorial.title" /></form:label>
<form:input path="title" />
<form:errors cssClass="error" path="title"></form:errors>
<br/>

<form:label path="summary"><spring:message code="tutorial.summary" /></form:label>
<form:input path="summary" />
<form:errors cssClass="error" path="summary"></form:errors>
<br/>

	<!-- Control -->
<input type="submit" name="save" value="<spring:message code="tutorial.save" />" />
<input type="button" name="cancel" value ="<spring:message code="tutorial.cancel" />" onclick="javascript:relativeRedir('tutorial/handy-worker/list.do');"/>

</form:form>
