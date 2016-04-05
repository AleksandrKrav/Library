<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>


<div class="col-sm-3 col-md-2 sidebar">
    <ul class="nav nav-sidebar">
        <li><a href="/books"><i class="glyphicon glyphicon-book" id="logo"></i>&nbsp;Online Library</a></li>
        <li class="pull-left">
            <form class="form-horizontal" action="/findBook" method="POST" id="form-find-book">
                <div class="form-group find-book-name">
                    <label for="book-name" class="col-md-4 control-label">Book Name</label>
                    <div class="col-md-7">
                        <input type="text" name="book-name" class="form-control" id="book-name"/>
                    </div>
                </div>
                <div class="form-group find-author-name">
                    <label for="author-name" class="col-md-4 control-label">Author Name</label>
                    <div class="col-md-7">
                        <input type="text" name="author-name" class="form-control" id="author-name"/>
                    </div>
                </div>
                <div class="form-group genre-book-group">
                    <label for="genre-book"
                           class="col-md-4 control-label">Genre</label>
                    <div class="col-md-7">
                        <select id="genre-book" class="form-control"
                                name="genre-book">
                            <c:forEach items="${genres}" var="genre">
                                <option value="${genre.getId()}">${genre.getName()}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <div class="form-group center-pill">
                    <div>
                        <input id="but-find" type="submit" name="find" value="find"
                               class="btn btn-success">
                    </div>
                </div>
            </form>
        </li>
    </ul>
</div>
