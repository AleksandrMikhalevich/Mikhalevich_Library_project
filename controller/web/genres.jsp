<%--
  Created by IntelliJ IDEA.
  User: Alex Mikhalevich
  Date: 16.06.2022
  Time: 13:07  
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Авторы</title>
</head>
<body class="d-flex flex-column min-vh-100">
<header>
    <%@include file="header.jsp" %>
</header>
<p>
<div style="text-align: center;">
    Добро пожаловать к Жанрам!
</div>
<p>
<div class="d-flex justify-content-center">
    <form name="search" method="post" action="controller">
        <div class="input-group">
            <input type="text" class="d-flex flex-row form-control" name="search_query"
                   aria-describedby="searchInput" aria-label="Search genres" placeholder="Поиск жанров">
            <input name="action" type="hidden" value="find_genre_by_name">
            <button class="d-flex flex-row btn btn-outline-success" type="submit" id="searchInput">Искать</button>
        </div>
    </form>
</div>
<div class="d-flex justify-content-center">
    <form name="sorting" method="post" action="controller">
        <div class="form-check form-check-inline">
            <input class="form-check-input" type="radio" name="sorting" id="inlineRadio1"
                   value="sort_by_name" checked="checked">
            <label class="form-check-label" for="inlineRadio1">По названию</label>
        </div>
        ${requestScope.errorSortGenres}
        <c:forEach var="genre" items="${sessionScope.genreList}">
            <input name="genres_ids" type="hidden" value="${genre.id}">
        </c:forEach>
        <input name="action" type="hidden" value="sort_genres">
        <button type="submit" class="btn btn-outline-secondary">Сортировать</button>
    </form>
</div>
<div class="w-90 p-3" style="text-align: center;">
    <p>
        <c:if test="${sessionScope.searchGenreResults != null || sessionScope.searchGenreResults != null && sessionScope.sortGenreResults != null}">
            ${sessionScope.searchGenreResults}
            ${requestScope.errorSearchGenreResults}
        </c:if>
    </p>
    <table class="table table-bordered table-hover" style="text-align: center;">
        <thead>
        <tr>
            <th scope="col">Название</th>
            <th scope="col">Описание</th>
            <th colspan="2">Действия</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="genre" items="${sessionScope.genreList}">
            <tr>
                <td>
                    <c:out value="${genre.name}"/>
                </td>
                <td>
                    <c:out value="${genre.description}"/>
                </td>
                <td>
                    <form name="delete" method="post" action="genre-delete.jsp">
                        <input name="id" type="hidden" value="${genre.id}">
                        <input name="name" type="hidden" value="${genre.name}">
                        <input name="description" type="hidden" value="${genre.description}">
                        <input name="action" type="hidden" value="genre-delete.jsp">
                        <button type="submit" class="btn btn-outline-danger">Удалить</button>
                    </form>
                </td>
                <td>
                    <form name="update" method="post" action="genre-update.jsp">
                        <input name="id" type="hidden" value="${genre.id}">
                        <input name="name" type="hidden" value="${genre.name}">
                        <input name="description" type="hidden" value="${genre.description}">
                        <input name="action" type="hidden" value="genre-update.jsp">
                        <button type="submit" class="btn btn-outline-warning">Редактировать</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <br>
    ${requestScope.successAddGenre}
    ${requestScope.errorAddGenre}
    ${requestScope.successUpdateGenre}
    ${requestScope.errorUpdateGenre}
    ${requestScope.successDeleteGenre}
    ${requestScope.errorDeleteGenre}
    <p>
        <a href="genre-add.jsp">Добавить жанр</a>
    <p>
        <a href="index.jsp">На главную страницу</a>
    <p>
        <c:if test="${sessionScope.searchGenreResults != null || sessionScope.searchGenreResults != null && sessionScope.sortGenreResults != null}">
        <a href="controller?action=find_all_genres">Назад</a>
        </c:if>
</div>
<div class="wrapper flex-grow-1">
</div>
<footer>
    <%@include file="footer.jsp" %>
</footer>
</body>
</html>
