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
<style>
    table, th, td {
        border: 1px solid black
    }

    .center {
        margin-left: auto;
        margin-right: auto;
    }
</style>
<body>
<div style="text-align: center;">

    Добро пожаловать к Жанрам!
    <p>
    <form name="search" method="post" action="controller">
        <label>
            <input name="search_query" type="text" required placeholder="Поиск жанров">
        </label>
        <input name="action" type="hidden" value="find_genre_by_name">
        <button>Искать</button>
    </form>

    <form name="sorting" method="post" action="controller">
        <label>
            <input type="radio" name="sorting" value="sort_by_name" checked>
        </label> По названию
        ${requestScope.errorSortGenres}
        <input name="action" type="hidden" value="sort_genres">
        <button>Сортировать</button>
    </form>

    <table class="center">
        <p>
            ${requestScope.searchGenreResults}
            ${requestScope.errorSearchGenreResults}
        </p>
        <caption><b>
            Список жанров
        </b></caption>
        <tr>
            <th>Название</th>
            <th>Описание</th>
            <th>Действия</th>
        </tr>
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
                        <button>Удалить</button>
                    </form>
                    <form name="update" method="post" action="genre-update.jsp">
                        <input name="id" type="hidden" value="${genre.id}">
                        <input name="name" type="hidden" value="${genre.name}">
                        <input name="description" type="hidden" value="${genre.description}">
                        <input name="action" type="hidden" value="genre-update.jsp">
                        <button>Редактировать</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
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
        <a href="main.jsp">На главную страницу</a>
    <p>
        <c:if test="${requestScope.searchGenreResults !=null}">
        <a href="controller?action=find_all_genres">Назад</a>
        </c:if>
</div>
</body>
</html>
