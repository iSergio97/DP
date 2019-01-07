<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<a href="warranty/administrator/list.do">Back</a><br/>

<jstl:out value="Title: ${warranty.title}"/><br/>
<jstl:out value="Laws: ${warranty.applicableLaws}"/><br/>
<jstl:out value="Terms: ${warranty.terms}"/><br/>



<jstl:if test="${!warranty.draft}">
<a href="warranty/administrator/save.do?warrantyId=${warranty.id}">Save</a>
<a href="warranty/administrator/edit.do?warrantyId=${warranty.id}">Edit</a>
<a href="warranty/administrator/delete.do?warrantyId=${warranty.id}">Delete</a>
</jstl:if>