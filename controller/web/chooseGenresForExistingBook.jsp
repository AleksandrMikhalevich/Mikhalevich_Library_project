<%--
  Created by IntelliJ IDEA.
  User: Alex Mikhalevich
  Date: 08.06.2022
  Time: 19:05  
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Выбор авторов</title>
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
    <form name="add" method="post" action="controller">
        Выбор жанров
        <table class="center">
            <caption><b>
                Список жанров
            </b></caption>
            <tr>
                <th>Название</th>
                <th>Действия</th>
            </tr>
            <c:forEach var="genre" items="${sessionScope.changedGenres}">
                <tr>
                    <td>
                        <c:out value="${genre.name}"/>
                    </td>
                    <td>
                        <label>
                            <input type="checkbox" name="genre_ids" value="${genre.id}">
                        </label> Выбрать

                    </td>
                </tr>
            </c:forEach>
        </table>
        <input name="action" type="hidden" value="update_chosen_genres_for_existing_book">
        <button>Добавить</button>
    </form>

    <a href="book-update.jsp">Назад к странице редактирования</a>

</div>
</body>
</html>
