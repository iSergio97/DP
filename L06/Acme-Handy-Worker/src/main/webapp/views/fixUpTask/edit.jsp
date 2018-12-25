<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>


<form:form action="fixUpTask/customer/edit.do" modelAttribute="fixUpTask">

<input type="button" name="cancel" value ="<spring:message code="fixUpTask.cancel" />" onclick="javascript:RelativeRedir('fixUpTask/customer/list.do');"/>
<form:hidden path="id"/>
<form:errors cssClass="error" path="id"></form:errors>
<br/>
<form:hidden path="version"/>
<form:errors cssClass="error" path="version"></form:errors>
<br/>
<form:hidden path="ticker"/>
<form:errors cssClass="error" path="ticker"></form:errors>
<br/>
<form:hidden path="moment"/>
<form:errors cssClass="error" path="moment"></form:errors>
<br/>
<form:hidden path="customer"/>
<form:errors cssClass="error" path="customer"></form:errors>
<br/>
<form:hidden path="applications"/>
<form:errors cssClass="error" path="applications"></form:errors>
<br/>
<form:hidden path="workPlan"/>
<form:errors cssClass="error" path="workPlan"></form:errors>
<br/>
<form:hidden path="complaints"/>
<form:errors cssClass="error" path="complaints"></form:errors>
<br/>









<form:label path="address">Address:</form:label>
<form:input path="address" />
<form:errors cssClass="error" path="address"></form:errors>
<br/>

<form:label path="maximumPrice">MaximumPrice</form:label>
<form:input path="maximumPrice" />
<form:errors cssClass="error" path="maximumPrice"></form:errors>
<br/>

<form:label path="timeLimit">TimeLimit</form:label>
<form:input path="timeLimit" />
<form:errors cssClass="error" path="timeLimit"></form:errors>
<br/>


<form:select id="warranties" path="warranty">
<form:options items="${warranties}" itemLabel = "title" itemValue="id"/>
<form:option value="0" label="---"/>
</form:select>

<form:select id="fixUpTaskCategories" path="fixUpTaskCategory">
<form:options items="${fixUpTaskCategories}" itemLabel = "name" itemValue="id"/>
<form:option value="0" label="---"/>
</form:select>


<form:label path="description">Description</form:label>
<form:input path="description" />
<form:errors cssClass="error" path="description"></form:errors>
<br/>

<input type="submit" name="save" value="<spring:message code="fixUpTask.update" />" />

</form:form>