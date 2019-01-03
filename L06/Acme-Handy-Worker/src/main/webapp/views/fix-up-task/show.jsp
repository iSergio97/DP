<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<a href="fixUpTask/customer/list.do"><spring:message code="fixUpTask.return" /></a><br/>

<spring:message code="fixUpTask.address" />
<jstl:out value=": ${fixUpTask.address}"/><br/>
<spring:message code="fixUpTask.maximumPrice" />
<jstl:out value=": ${fixUpTask.maximumPrice}"/><br/>
<spring:message code="fixUpTask.timeLimit" />
<jstl:out value=": ${fixUpTask.timeLimit}"/><br/>
<spring:message code="fixUpTask.description" />
<jstl:out value=": ${fixUpTask.description}"/><br/>

<a href="fixUpTask/customer/edit.do?fixUpTaskId=${fixUpTask.id}"><spring:message code="fixUpTask.edit" /></a>
<a href="fixUpTask/customer/delete.do?fixUpTaskId=${fixUpTask.id}"><spring:message code="fixUpTask.delete" /></a>