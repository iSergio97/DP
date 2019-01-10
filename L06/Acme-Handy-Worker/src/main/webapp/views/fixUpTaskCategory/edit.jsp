<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>


<form:form action="fixUpTaskCategory/administrator/edit.do" modelAttribute="fixUpTaskCategory">

<input type="button" name="cancel" value ="<spring:message code="fixUpTaskCategory.cancel" />" onclick="javascript:RelativeRedir('fixUpTaskCategory/administrator/list.do');"/>
<form:hidden path="id"/>
<form:errors cssClass="error" path="id"></form:errors>
<br/>
<form:hidden path="version"/>
<form:errors cssClass="error" path="version"></form:errors>
<br/>
<form:hidden path="name"/>
<form:errors cssClass="error" path="name"></form:errors>
<br/>

<form:hidden path="fixUpTaskCategoryParent"/>
<form:errors cssClass="error" path="fixUpTaskCategoryParent"></form:errors>
<br/>




<form:label path="name">Name:</form:label>
<form:input path="name" />
<form:errors cssClass="error" path="name"></form:errors>
<br/>


<form:select id="parents" path="fixUpTaskCategory">
<form:options items="${parents.name}" itemLabel = "name" itemValue="id"/>
<form:option value="0" label="---"/>
</form:select>

<input type="submit" name="save" value="<spring:message code="fixUpTaskCategory.update" />" />

</form:form>