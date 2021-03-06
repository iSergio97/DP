<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>


<display:table name="draftedWarranties" id="row"
	requestURI="warranties/browse.do" pagesize="5"
	class="displaytag">
	<display:column property="terms" titleKey="warranty.terms" />
	<display:column property="terms" titleKey="warranty.laws" />
	<display:column> <a href="warranty.show.do?warrantyId=${row.id}"> <jstl:out value="Show"/></a> </display:column>
</display:table>

<display:table name="warranties" id="row"
	requestURI="warranties/browse.do" pagesize="5"
	class="displaytag">
	<display:column property="terms" titleKey="warranty.terms" />
	<display:column property="terms" titleKey="warranty.laws" />
</display:table>

<a href="warranty/new.do">New</a>