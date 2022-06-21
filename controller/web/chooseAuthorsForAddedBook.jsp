<%--
  Created by IntelliJ IDEA.
  User: Alex Mikhalevich
  Date: 08.06.2022
  Time: 13:15  
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
    <form name="chooseAuthors" method="post" action="controller">
        Выбор авторов
        <table class="center">
            <caption><b>
                Список авторов
            </b></caption>
            <tr>
                <th>Имя</th>
                <th>Отчество</th>
                <th>Фамилия</th>
                <th>Страна</th>
                <th>Действия</th>
            </tr>
            <c:forEach var="author" items="${sessionScope.addedAuthors}">
                <tr>
                    <td>
                        <c:out value="${author.firstName}"/>
                    </td>
                    <td>
                        <c:out value="${author.secondName}"/>
                    </td>
                    <td>
                        <c:out value="${author.surname}"/>
                        <c:set var="author_surname" scope="session" value="${author.surname}"/>
                    </td>
                    <td>
                        <c:out value="${author.country}"/>
                    </td>
                    <td>
                        <label>
                            <input type="checkbox" name="author_ids" value="${author.id}">
                        </label> Выбрать

                    </td>
                </tr>
            </c:forEach>
        </table>
        <input name="action" type="hidden" value="add_chosen_authors_for_added_book">
        <button>Добавить</button>
    </form>

    <a href="book-add.jsp">Назад к странице добавления</a>

</div>
</body>
</html>