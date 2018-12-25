<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<a href="fixUpTask/customer/list.do"><spring:message code="fixUpTask.return" /></a>

<jstl:out value="Address: ${fixUpTask.address}"/><br/>
<jstl:out value="Maximum Price: ${fixUpTask.maximumPrice}"/><br/>
<jstl:out value="Time Limit: ${fixUpTask.timeLimit}"/><br/>
<jstl:out value="Description: ${fixUpTask.description}"/><br/>

<a href="fixUpTask/customer/edit.do?fixUpTaskId=${fixUpTask.id}">Edit</a>
<a href="fixUpTask/customer/delete.do?fixUpTaskId=${fixUpTask.id}">Delete</a>