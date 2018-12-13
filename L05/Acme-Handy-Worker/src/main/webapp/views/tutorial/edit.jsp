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

<form:form modelAttribute="tutorial" action="tutorial/handy-worker/edit.do">

	<!-- Hidden fields -->
	<form:hidden path="id"/>
	<form:hidden path="version" />
	<form:hidden path="pictures" />

	<!-- Editable fields -->
	<acme:textbox code="tutorial.title" path="title" placeholder="<spring:message code='tutorial.title'/>"/>
	<acme:textbox code="tutorial.summary" path="summary" placeholder="<spring:message code='tutorial.summary'/>"/>

	<!-- Control -->
	<acme:submit name="save" code="tutorial.save"/>
	<acme:submit name="delete" code="tutorial.delete"/>
	<acme:cancel code="tutorial.cancel" url="/tutorial/handy-worker/list.do"/>

</form:form>
