<%--
  Created by IntelliJ IDEA.
  User: Alexandr
  Date: 17.03.2016
  Time: 11:54
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
        <div class="col-sm-8 col-sm-offset-3 col-md-8 col-md-offset-2 main">
            <div class="panel book-panel panel-success">
                <div class="panel-heading">
                    <h3 class="panel-title">Edit book</h3>
                </div>
                <div class="panel-body">
                    <div class="col-md-8">
                        <form class="form-horizontal" id="book-edit-form"
                              action="/editBook"
                        <%--enctype="multipart/form-data"--%>
                              method="post">
                            <div class="form-group edit-book-name-group">
                                <label for="book_name_edit"
                                       class="col-md-4 control-label">Book name</label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control"
                                           id="book_name_edit"
                                           name="book_name_edit"
                                           value="${book_name}"
                                           size="30" autofocus/>
                                </div>
                            </div>
                            <div class="form-group edit-author-name-group">
                                <label for="edit_author_name"
                                       class="col-md-4 control-label">Author name</label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control"
                                           id="edit_author_name"
                                           name="edit_author_name"
                                           value="${author}"
                                           size="30" autofocus/>
                                </div>
                            </div>
                            <div class="form-group edit-genre-book-group">
                                <label for="edit-genre-book"
                                       class="col-md-4 control-label">Genre</label>
                                <div class="col-md-8">
                                    <select id="edit-genre-book" class="form-control"
                                            name="edit-genre-book">
                                        <c:forEach items="${genres}" var="genre">
                                            <option value="${genre.getId()}">${genre.getName()}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group book-description-group">
                                <label for="edit-book-description"
                                       class="col-md-4 control-label">Description</label>
                                <div class="col-md-8">
                                                <textarea id="edit-book-description" class="form-control"
                                                          name="edit-book-description"
                                                          rows="3" cols="30">
                                                    ${description}
                                                </textarea>
                                </div>
                            </div>
                            <div class="form-group auth-pass-group pull-right">
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                <input id="edit-book-button" name="book-edit-button"
                                       type="submit"
                                       value="Edit"
                                       class="btn btn-primary"/>
                                <input id="remove-book-button" name="book-remove-button"
                                       type="submit"
                                       value="Remove"
                                       class="btn btn-default"/>
                            </div>
                    </div>
                </div>
            </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
