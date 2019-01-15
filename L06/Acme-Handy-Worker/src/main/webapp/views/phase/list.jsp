<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>


<<jstl:out value="${workPlan.id}"></jstl:out>
<display:table name="phases" id="row"
	requestURI="phases/handyWorker/list.do" pagesize="5"
	class="displaytag">
	<display:column property="title" titleKey="phase.tle" />
	<display:column property="description" titleKey="phase.description" />
	<display:column property="startMoment" titleKey="phase.start" />
	<display:column property="endMoment" titleKey="phase.end" />
	<display:column> <a href="phase/handyWorker/edit.do?phaseId=${row.id}"> <jstl:out value="Edit"/></a> </display:column>
</display:table>

<a href="phase/handyWorker/create.do?handyWorkerId=${workPlan.id}">New</a>