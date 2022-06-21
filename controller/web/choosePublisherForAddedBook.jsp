<%--
  Created by IntelliJ IDEA.
  User: Alex Mikhalevich
  Date: 09.06.2022
  Time: 14:02  
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Выбор издательства</title>
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
    <form name="choosePublisher" method="post" action="controller">
        Выбор издательства
        <table class="center">
            <caption><b>
                Список издательств
            </b></caption>
            <tr>
                <th>Название</th>
                <th>Страна</th>
                <th>Город</th>
                <th>Улица</th>
                <th>Дом</th>
                <th>Почтовый индекс</th>
                <th>Действия</th>
            </tr>
            <c:forEach var="publisher" items="${sessionScope.addedPublishers}">
                <tr>
                    <td>
                        <c:out value="${publisher.name}"/>
                    </td>
                    <td>
                        <c:out value="${publisher.address.country}"/>
                    </td>
                    <td>
                        <c:out value="${publisher.address.city}"/>
                    </td>
                    <td>
                        <c:out value="${publisher.address.street}"/>
                    </td>
                    <td>
                        <c:out value="${publisher.address.house}"/>
                    </td>
                    <td>
                        <c:out value="${publisher.address.zipcode}"/>
                    </td>
                    <td>
                        <label>
                            <input type="radio" name="publisher_id" value="${publisher.id}">
                        </label> Выбрать
                    </td>
                </tr>
            </c:forEach>
        </table>
        <input name="action" type="hidden" value="add_chosen_publisher_for_added_book">
        <button>Добавить</button>
    </form>

    <a href="book-add.jsp">Назад к странице добавления</a>

</div>
</body>
</html>