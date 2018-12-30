<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<a href="customer/browse.do">Back</a>

<jstl:out value="Address: ${fixUpTask.address}"/><br/>
<jstl:out value="Maximum Price: ${fixUpTask.maximumPrice}"/>
<jstl:out value="Time Limit: ${fixUpTask.timeLimit}"/>
<jstl:out value="Description: ${fixUpTask.description}"/>

<a href="fix-up-task/edit.do">Edit</a>
<a href="fix-up-task/delete.do">Delete</a>