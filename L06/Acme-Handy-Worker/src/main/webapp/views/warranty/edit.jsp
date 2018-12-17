<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<a href="warranty/show.do">Back</a>

<form:form action="warranty/new.do" modelAttribute="warranty">

<form:hidden path="id"/>
<form:hidden path="version"/>



<form:label path="title"></form:label>
<form:input path="title" />
<form:errors cssClass="error" path="title"></form:errors>
<br/>

<form:label path="laws"></form:label>
<form:input path="laws" />
<form:errors cssClass="error" path="laws"></form:errors>
<br/>

<form:label path="terms"></form:label>
<form:input path="terms" />
<form:errors cssClass="error" path="terms"></form:errors>
<br/>


<input type="submit" name="submit" value="<spring:message code="warranty.update" />" />

</form:form>