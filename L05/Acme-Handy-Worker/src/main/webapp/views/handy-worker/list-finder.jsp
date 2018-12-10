<%@page language="java" contentType="text/html; charset=ISO-8859-1"
        pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
          uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<display:table name="listFinder" id="listFinder" requestURI="/handy-worker/finder/show.do" pagesize="5" class="displaytag">
    <security:authorize access="hasRole('handy-worker')" >

        <display:column property="<spring:message code='handyWorker.finder />" titleKey="handyWorker.finder.keyWord" />
        <display:column property="<spring:message code='handyWorker.minPrice/>" titleKey="handyWorker.finder.minimumPrice" />
        <display:column property="<spring:message code='handyWorker.maxPrice />" titleKey="handyWorker.finder.maximumPrice" />
        <display:column property="<spring:message code='handyWorker.finder.minDate />" titleKey="handyWorker.finder.minimumDate" />
        <display:column property="<spring:message code='handyWorker.finder.maxDate />" titleKey="handyWorker.finder.maximumDate" />

        <a href="handy-worker/finder/edit.do?eventId="${row.id}"> <spring:message code="handyWorker.edit" /> </a>
    </security:authorize>
</display:table>