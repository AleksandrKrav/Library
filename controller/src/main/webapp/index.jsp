<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Online Library</title>
    <!-- Bootstrap core CSS -->
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css" rel="stylesheet">
    <link href="pages/resources/css/library.css" rel="stylesheet"/>
    <%--<script type="text/javascript" src="pages/resources/js/cookie.js"/>--%>
    <script type="text/javascript" src="pages/resources/js/editBook.js"/>
    <script type="text/javascript">
        var userName;

        function greetUser() {
            userName = readCookie("alex");
            if (userName)
                alert("hello " + userName + ", I missed you.");
            else
                alert("hello, i am your pet rock.")
        }

        function touchList() {
            if (userName) {
                alert("Thank you my" + userName);
            } else {
                userName = prompt("What is your name?", "Enter your name here.");
                if (userName) {
                    alert("it's good to meet you." + userName);
                    writeCookie("alex", userName, 5 * 365);
                }
            }
        }
    </script>

    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
</head>

<body>
<jsp:include page="pages/header.jsp"/>
<div class="container-fluid">
    <div class="row">
        <jsp:include page="pages/menu.jsp"/>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <h1 id="listBooks" class="page-header">
                <b id="titleListBooks" onclick="touchList()" style="cursor: pointer">List of books</b>
                <sec:authorize access="hasRole('ROLE_ADMIN') and isAuthenticated()">
                    <input id="book-add" type="button"
                           value="Add book"
                           class="btn btn-primary pull-right" data-toggle="modal"
                           data-target="#myModal3"/>
                </sec:authorize>
            </h1>
            <table class="table">
                <thead>
                <tr>
                    <th>Book name</th>
                    <th>Author</th>
                    <th>Genre</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${books}" var="book" varStatus="index">
                    <tr>
                        <td id="bookName">
                                ${book.getName()}
                        </td>
                        <td id="auhtorName">
                                ${book.getAuthor().getName()}
                        </td>
                        <td id="genreName">
                                ${book.getGenre().getName()}
                        </td>
                        <td>
                            <sec:authorize access="hasRole('ROLE_ADMIN') and isAuthenticated()">
                                <button id="edit_book" onclick="doGetRequest('${book.getId()}')" type="button"
                                        class="btn btn-primary pull-right"
                                        data-toggle="modal"
                                        data-target="#myModal4"/>
                                <%--<a type="button" class="btn btn-default" href="editBook?id=${book.getId()}"--%>
                                <%--name="book-edit-id">Edit book</a>--%>
                            </sec:authorize>
                            <sec:authorize access="hasRole('ROLE_USER') and isAuthenticated()">
                                <form method="post" action="/books" id="form-add-user">
                                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                    <input id="book_id" type="hidden" name="book_id" value="${book.getId()}"/>
                                    <input type="submit" name="add_book" class="btn btn-default" value="Add book"/>
                                </form>
                            </sec:authorize>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

            <form class="form-horizontal" id="book-edit-form"
                  method="post">
                  <%--enctype="multipart/form-data">--%>
                <div class="modal fade " id="myModal4">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <div class="modal-header">
                                    <button class="close" type="button"
                                            data-dismiss="modal">×
                                    </button>
                                    <h3 id="myModalLabel4">Add book</h3>
                                </div>
                                <div class="modal-body">
                                    <div class="form-group book-name-group">
                                        <label for="book_edit_name"
                                               class="col-md-4 control-label">Book name</label>
                                        <div class="col-md-8">
                                            <input type="text" class="form-control"
                                                   id="book_edit_name"
                                                   name="book_edit_name"
                                                   size="30" autofocus/>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group author-name-group">
                                    <label for="author_edit_name"
                                           class="col-md-4 control-label">Author name</label>
                                    <div class="col-md-8">
                                        <input type="text" class="form-control"
                                               id="author_edit_name"
                                               name="author_edit_name"
                                               value
                                               size="30" autofocus/>
                                    </div>
                                </div>
                                <div class="form-group genre-book-group">
                                    <label for="genre_edit_book"
                                           class="col-md-4 control-label">Genre</label>
                                    <div class="col-md-8">
                                        <select id="genre_edit_book" class="form-control"
                                                name="genre_edit_book">
                                            <c:forEach items="${genres}" var="genre">
                                                <option value="${genre.getId()}">${genre.getName()}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group book-description-group">
                                    <label for="book_edit_description"
                                           class="col-md-4 control-label">Description</label>
                                    <div class="col-md-8">
            <textarea id="book_edit_description" class="form-control"
                      name="book_edit_description"
                      rows="3" cols="30">
            </textarea>
                                    </div>
                                </div>
                                <%--<div class="form-group">--%>
                                    <%--<label for="file" class="col-md-4 control-label file-label">Choose book</label>--%>
                                    <%--<div class="col-md-8">--%>
                                        <%--<input id="file" type="file" name="uploadFile"/>--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                            </div>
                            <div class="modal-footer">
                                <div class="modal-footer">
                                    <input id="book_edit_button"
                                           name="book-edit-button"
                                           type="button"
                                           value="Refresh"
                                           onclick="doRefresh()"
                                           class="btn btn-primary"/>
                                    <input id="book_cancel" type="button"
                                           onclick="doRefresh()"
                                           value="Delete" class="btn btn-danger"
                                           data-dismiss="modal"/>

                                </div>
                            </div>
                        </div>
                    </div>
                </div>

            </form>


            <form class="form-horizontal" id="book-add-form"
                  action="./addBook?${_csrf.parameterName}=${_csrf.token}"
                  enctype="multipart/form-data"
                  method="post">
                <div class="modal fade " id="myModal3">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <div class="modal-header">
                                    <button class="close" type="button"
                                            data-dismiss="modal">×
                                    </button>
                                    <h3 id="myModalLabel3">Add book</h3>
                                </div>
                                <div class="modal-body">
                                    <div class="form-group book-name-group">
                                        <label for="book_name"
                                               class="col-md-4 control-label">Book name</label>
                                        <div class="col-md-8">
                                            <input type="text" class="form-control"
                                                   id="book_name"
                                                   name="book_name"
                                                   value
                                                   size="30" autofocus/>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group author-name-group">
                                    <label for="author_name"
                                           class="col-md-4 control-label">Author name</label>
                                    <div class="col-md-8">
                                        <input type="text" class="form-control"
                                               id="author_name"
                                               name="author_name"
                                               value
                                               size="30" autofocus/>
                                    </div>
                                </div>
                                <div class="form-group genre-book-group">
                                    <label for="genre-book"
                                           class="col-md-4 control-label">Genre</label>
                                    <div class="col-md-8">
                                        <select id="genre-book" class="form-control"
                                                name="genre-book">
                                            <c:forEach items="${genres}" var="genre">
                                                <option value="${genre.getId()}">${genre.getName()}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group book-description-group">
                                    <label for="book-description"
                                           class="col-md-4 control-label">Description</label>
                                    <div class="col-md-8">
                                                <textarea id="book-description" class="form-control"
                                                          name="book-description"
                                                          rows="3" cols="30">
                                                </textarea>
                                    </div>
                                </div>
                                <%--<div class="form-group">--%>
                                    <%--<label for="file" class="col-md-4 control-label file-label">Choose book</label>--%>
                                    <%--<div class="col-md-8">--%>
                                        <%--<input id="file" type="file" name="uploadFile"/>--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                            </div>
                            <div class="modal-footer">
                                <div class="modal-footer">
                                    <input id="book-add-button" name="book-add-button"
                                           type="submit"
                                           value="Add"
                                           class="btn btn-primary"/>
                                    <input id="book-cancel" type="reset"
                                           value="Cancel" class="btn btn-danger"
                                           data-dismiss="modal"/>

                                </div>
                            </div>
                        </div>
                    </div>
                </div>

            </form>
        </div>
    </div>
</div>
</div>

<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

</body>
</html>
