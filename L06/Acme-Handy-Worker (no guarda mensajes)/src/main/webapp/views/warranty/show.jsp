<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<a href="warranty/list.do">Back</a>

<jstl:out value="Title: ${warranty.title}"/><br/>
<jstl:out value="Laws: ${warranty.laws}"/>
<jstl:out value="Terms: ${warranty.terms}"/>
<jstl:out value="Description: ${fixUpTask.description}"/>

<jstl:if test="${draft}">
<a href="warranty/save.do">Save</a>
</jstl:if>

<a href="warranty/edit.do">Edit</a>
<a href="warranty/delete.do">Delete</a>