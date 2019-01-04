<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<display:table name="fixUpTasks" id="row"
	requestURI="fixUpTask/handyWorker/list.do" pagesize="5"
	class="displaytag">

	<display:column property="customer.name" titleKey="fixUpTask.customer" />
	<display:column titleKey="fixUpTask.customer.surname"> <a href="customer.show.do?customerId=${row.id}"> <jstl:out value="${row.customer.surname}"/></a> </display:column>
	<display:column property="fixUpTaskCategory.name" titleKey="fixUpTaskCategory" />
	<display:column property="address" titleKey="fixUpTask.address" />
	<display:column property="timeLimit" titleKey="fixUpTask.timeLimit" />
	<display:column property="maximumPrice" titleKey="fixUpTask.maximumPrice" />



</display:table>