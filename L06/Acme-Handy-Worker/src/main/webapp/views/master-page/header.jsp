<%--
 * header.jsp
 *
 * Copyright (C) 2018 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
	
<div id="escImg" class="escImg">
<div>
	<a href="#"><img src="images/logo.png"
		alt="Acme Handy Worker Co., Inc." height="70%" width="50%"/></a>
</div>

<div>
	<ul id="jMenu">
		<!-- Do not forget the "fNiv" class for the first level links !! -->
		<security:authorize access="hasRole('ADMIN')">
			<li><a class="fNiv"><spring:message
						code="master.page.administrator" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="administrator/systemconfiguration.do"><spring:message
								code="master.page.administrator.systemconfiguration" /></a></li>
					<li><a href="administrator/dashboard.do"><spring:message
								code="master.page.administrator.dashboard" /></a></li>
					<li><a href="administrator/registeradmin.do"><spring:message
								code="master.page.administrator.register.administrator" /></a></li>
					<li><a href="administrator/registerreferee.do"><spring:message
								code="master.page.administrator.register.referee" /></a></li>
					<li><a href="administrator/suspicious.do"><spring:message
								code="master.page.administrator.suspicious" /></a></li>
				</ul>
				</li>
		</security:authorize>

		<security:authorize access="hasRole('CUSTOMER')">
			<li><a class="fNiv"><spring:message
						code="master.page.customer" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="application/customer.do"><spring:message
								code="master.page.customer.application" /></a></li>
				</ul></li>
		</security:authorize>

		<security:authorize access="hasRole('HANDY_WORKER')">
			<li><a class="fNiv"><spring:message
						code="master.page.handyWorker" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="application/handyworker.do"><spring:message
								code="master.page.handyWorker.application" /></a></li>
				</ul></li>
		</security:authorize>

		<security:authorize access="isAnonymous()">
			<li><a class="fNiv" href="security/login.do"><spring:message
						code="master.page.login" /></a></li>
		</security:authorize>

		<security:authorize access="isAnonymous()">
			<li><a class="fNiv" href="customer/register.do"><spring:message
						code="master.page.register" /></a></li>
		</security:authorize>

		<security:authorize access="isAuthenticated()">
			<li><a class="fNiv"> <spring:message
						code="master.page.profile" /> (<security:authentication
						property="principal.username" />)
			</a>
				<ul>
					<li class="arrow"></li>
					<li><a href="profile/show.do"><spring:message
								code="master.page.profile.show" /></a></li>
					<li><a href="profile/edit.do?id=<security:authentication property="principal.id" />"><spring:message
								code="master.page.profile.edit" /></a></li>
					<li><a href="profile/action-3.do"><spring:message
								code="master.page.profile.action.3" /></a></li>
					<li><a href="j_spring_security_logout"><spring:message
								code="master.page.logout" /> </a></li>
				</ul></li>
		</security:authorize>
		
		<security:authorize access="isAuthenticated()">
			<li><a class="fNiv"> <spring:message
						code="master.page.messages" />
			</a><ul>
					<li class="arrow"></li>
					<li><a href="message/sendMessage.do"><spring:message
								code="master.page.messages.send" /></a></li>
					<li><a href="message/showBox.do"><spring:message
								code="master.page.messages.box" /></a></li>
				</ul></li>
		</security:authorize>
	</ul>
</div>

<div>
	<a href="?language=en">en</a> | <a href="?language=es">es</a>
</div>

</div>