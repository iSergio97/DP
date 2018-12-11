<%--
 * action-2.jsp
 *
 * Copyright (C) 2017 Universidad de Sevilla
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

<

<security:authorize access="hasRole('administrator')">

<form:form action="configuration/administrator/save" modelAttribute="configuration">
<form:label path="filterCache">
<spring:message code = "administrator.cache1"/>
</form:label>
<form:input path="filterCache" [placeholder = <spring:message code = "administrator.cache2" />]/>
<input type="submit" name="filterCache" value=<spring:message code = "administrator.save"/> />
</form:form>

</security:authorize>
