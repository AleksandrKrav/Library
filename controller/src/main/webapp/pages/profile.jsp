<%--
  Created by IntelliJ IDEA.
  User: Alexandr
  Date: 16.03.2016
  Time: 8:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Library</title>
    <link href="<c:url value="/pages/resources/css/bootstrap.min.css"/>" rel="stylesheet">
    <link href="<c:url value="/pages/resources/css/library.css"/>" rel="stylesheet">

    <script src="<c:url value="https://code.jquery.com/jquery-2.1.4.js"/>" type="text/javascript"></script>
    <script src="<c:url value="http://code.jquery.com/ui/1.10.4/jquery-ui.js"/>" type="text/javascript"></script>

</head>
<body>
<jsp:include page="header.jsp"/>
<div class="container-fluid">
    <div class="row">
        <jsp:include page="menu.jsp"/>
        <div class="col-sm-7 col-sm-offset-3 col-md-8 col-md-offset-2 main">
            <div class="panel user-panel panel-success">
                <div class="panel-heading ">
                    <h3 class="panel-title">${user_name}</h3>
                </div>
                <div class="panel-body ">
                    <div class="col-md-12">
                        <fieldset class="field-user">
                            <legend>Your books</legend>
                        </fieldset>
                        <table class="table">
                            <thead>
                            <tr>
                                <th>Book name</th>
                                <th>Author</th>
                                <th>Genre</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${books}" var="book">
                                <tr>
                                    <td>
                                            ${book.getName()}
                                    </td>
                                    <td>
                                            ${book.getAuthor().getName()}
                                    </td>
                                    <td>
                                            ${book.getGenre().getName()}
                                    </td>
                                    <td>
                                            ${book.getDescription()}
                                    </td>
                                    <td>
                                        <form method="get" action="/profile">
                                            <input id="book_id" type="hidden" name="book_id" value="${book.getId()}"/>
                                            <input type="submit" name="download" value="Download"
                                                   class="btn btn-default"/>
                                        </form>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>

