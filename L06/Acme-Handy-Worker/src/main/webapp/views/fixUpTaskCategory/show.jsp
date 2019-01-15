<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<a href="fixUpTaskCategory/administrator/list.do"><spring:message code="fixUpTaskCategory.return" /></a>
<br/>
<jstl:out value="Name: ${fixUpTaskCategory.name}"/><br/>
<spring:message code="fixUpTaskCategory.fixUpTasks" />
	<jstl:forEach items="${fixUpTaskCategory.fixUpTasks}" var="tasks">
		<br>
		<jstl:out value="${task.ticker}" />
		</jstl:forEach>
<jstl:out value="Category Parent: ${fixUpTaskCategory.fixUpTaskCategoryParent.name}"/><br/>
<spring:message code="fixUpTaskCategory.fixUpTaskCategoryChildren" />
	<jstl:forEach items="${fixUpTaskCategory.fixUpTaskCategoryChildren}" var="children">
		<br>
		<jstl:out value="${children.name}" />
	</jstl:forEach>

<br/>
<a href="fixUpTaskCategory/administrator/edit.do?fixUpTaskCategoryId=${fixUpTaskCategory.id}">Edit</a>
<a href="fixUpTaskCategory/administrator/delete.do?fixUpTaskCategoryId=${fixUpTaskCategory.id}">Delete</a>