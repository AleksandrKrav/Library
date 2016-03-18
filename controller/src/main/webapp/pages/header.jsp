<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="/books"><i class="glyphicon glyphicon-book" id="logo"></i>&nbsp;Online Library</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav navbar-right">
                <sec:authorize access="isAuthenticated()" var="isAuthenticated">
                    <c:url value="/j_spring_security_logout" var="logoutUrl"/>
                    <form id="logout" action="${logoutUrl}" method="post">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    </form>
                    <c:if test="${pageContext.request.userPrincipal.name != null}">
                        <li><a href="/profile">Profile</a></li>
                        <li id="logoutUser"><a href="javascript:document.getElementById('logout').submit()">SignOut</a>
                        </li>
                    </c:if>
                </sec:authorize>
                <sec:authorize access="!isAuthenticated()">
                    <li id="loginUser"><a href="/pages/login.jsp">SignIn</a></li>
                    <li id="registUser"><a href="/pages/registration.jsp">SignUp</a></li>
                </sec:authorize>
            </ul>
        </div>
    </div>
</nav>
