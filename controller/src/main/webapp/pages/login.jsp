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

</head>
<body>
<jsp:include page="header.jsp"/>
<div class="container-fluid">
    <div class="row">
        <jsp:include page="menu.jsp"/>
        <div class="col-sm-8 col-sm-offset-3 col-md-8 col-md-offset-2 main">
            <div class="panel user-panel panel-success">
                <div class="panel-heading">
                    <h3 class="panel-title">Enter to system</h3>
                </div>
                <div class="panel-body">
                    <div class="col-md-6">
                        <fieldset class="field-user">
                            <c:if test="${param.error != null}">
                                <div class="alert alert-danger">
                                    <p>Invalid login or password.</p>
                                </div>
                            </c:if>
                            <c:if test="${param.logout != null}">
                                <div class="alert alert-success">
                                    <p>You have been logged out successfully.</p>
                                </div>
                            </c:if>

                            <form action="/j_spring_security_check" method="post" class="form-horizontal"
                                  id="form-auth-user">
                                <div class="form-group auth-login-group">
                                    <label for="inputLogin" class="col-md-4 control-label">Login</label>
                                    <div class="col-md-8">
                                        <input type="text" name="j_username" class="form-control" id="inputLogin" required/>
                                    </div>
                                </div>
                                <div class="form-group auth-pass-group">
                                    <label for="inputPassword" class="col-md-4 control-label">Password</label>
                                    <div class="col-md-8">
                                        <input type="password" name="j_password" class="form-control"
                                               id="inputPassword" required/>
                                    </div>
                                </div>
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                <div class="form-actions">
                                    <div class="col-md-6 pull-right">
                                        <input type="submit" name="SignIn"
                                               class="btn btn-block btn-primary" value="SignIn">
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
</div>
</body>
</html>
