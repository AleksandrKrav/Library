<%--
  Created by IntelliJ IDEA.
  User: Alexandr
  Date: 14.03.2016
  Time: 22:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Library</title>
    <link href="<c:url value="resources/css/bootstrap.min.css"/>" rel="stylesheet">
    <link href="<c:url value="resources/css/library.css"/>" rel="stylesheet">

    <script src="<c:url value="https://code.jquery.com/jquery-2.1.4.js"/>" type="text/javascript"></script>
    <script src="<c:url value="http://code.jquery.com/ui/1.10.4/jquery-ui.js"/>" type="text/javascript"></script>
    <script src="<c:url value="/pages/resources/js/validRegValues.js"/>" type="text/javascript"></script>

</head>
<body>
<jsp:include page="header.jsp"/>
<div class="container-fluid">
    <div class="row">
        <jsp:include page="menu.jsp"/>
        <div class="col-sm-8 col-sm-offset-3 col-md-8 col-md-offset-2 main">
            <div class="panel contact-panel panel-success">
                <div class="panel-heading">
                    <h3 class="panel-title">Registration to system</h3>
                </div>
                <div class="panel-body">
                    <div class="col-md-8">
                        <fieldset class="field-user">
                            <c:if test="${param.errorUserLogin != null}">
                                <div class="alert alert-danger">
                                    <p>Invalid login! Login has more than 3 symbols.</p>
                                </div>
                            </c:if>
                            <c:if test="${param.errorPass != null}">
                                <div class="alert alert-danger">
                                    <p>Invalid password! Password must be more than 5 symbols.</p>
                                </div>
                            </c:if>
                            <c:if test="${param.errorSameUserLogin != null}">
                                <div class="alert alert-danger">
                                    <p>System has user with same login!</p>
                                </div>
                            </c:if>

                            <form class="form-horizontal" action="/registration" method="POST" id="form-reg-user">
                                <div class="form-group reg-name-group">
                                    <label for="reg-name" class="col-md-4 control-label">Name</label>
                                    <div class="col-md-8">
                                        <input type="text" name="reg-name" class="form-control" id="reg-name"
                                               onblur="validateNonEmpty(this, document.getElementById('name_help'))"/>
                                        <span id="name_help"></span>
                                    </div>
                                </div>
                                <div class="form-group reg-login-group">
                                    <label for="reg-login" class="col-md-4 control-label">Login</label>
                                    <div class="col-md-8">
                                        <input type="text" name="reg-login" class="form-control" id="reg-login"
                                               <%--onblur="validLoginValue(this, document.getElementById('login_help'))--%>
                                               "/>
                                        <span id="login_help"></span>
                                    </div>
                                </div>
                                <div class="form-group reg-pass-group">
                                    <label for="reg-pass" class="col-md-4 control-label">Password</label>
                                    <div class="col-md-8">
                                        <input type="password" name="reg-pass" class="form-control" id="reg-pass"
                                               onblur="validateNonEmpty(this, document.getElementById('password_help'))
                                              "/>
                                        <span id="pass_help"></span>
                                    </div>
                                </div>
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                <div class="form-group pull-right">
                                    <div>
                                        <input id="but-reg"  name="registr" value="SignUp"
                                               class="btn btn-primary" onclick="checkEnter(this.form)">
                                    </div>
                                </div>
                            </form>
                        </fieldset>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
