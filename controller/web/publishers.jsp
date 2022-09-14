<%--
  Created by IntelliJ IDEA.
  User: Alex Mikhalevich
  Date: 16.06.2022
  Time: 15:33  
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

    Добро пожаловать к Издательствам!
    <p>
    <form name="search" method="post" action="controller">
        <label>
            <input name="search_query" type="text" required placeholder="Поиск издательств">
        </label>
        <input name="action" type="hidden" value="find_publisher_by_name">
        <button>Искать</button>
    </form>

    <form name="sorting" method="post" action="controller">
        <label>
            <input type="radio" name="sorting" value="sort_by_name" checked>
        </label> По названию
        ${requestScope.errorSortPublishers}
        <c:forEach var="publisher" items="${sessionScope.publisherList}">
            <input name="publishers_ids" type="hidden" value="${publisher.id}">
        </c:forEach>
        <input name="action" type="hidden" value="sort_publishers">
        <button>Сортировать</button>
    </form>

    <table class="center">
        <p>
            <c:if test="${sessionScope.searchPublisherResults != null || sessionScope.searchPublisherResults != null && sessionScope.sortPublisherResults != null}">
                ${sessionScope.searchPublisherResults}
                ${requestScope.errorSearchPublisherResults}
            </c:if>
        </p>
        <caption><b>
            Список авторов
        </b></caption>
        <tr>
            <th>Название</th>
            <th>Страна</th>
            <th>Город</th>
            <th>Улица</th>
            <th>Дом</th>
            <th>Почтовый индекс</th>
            <th>Информация о книгах</th>
            <th>Информация об авторах</th>
            <th>Действия</th>
        </tr>
        <c:forEach var="publisher" items="${sessionScope.publisherList}">
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
                    <form name="infoBooks" method="post" action="controller">
                        <input name="publisher_id" type="hidden" value="${publisher.id}">
                        <input name="action" type="hidden" value="show_publisher_all_books">
                        <button>Просмотреть</button>
                    </form>
                </td>
                <td>
                    <form name="infoAuthors" method="post" action="controller">
                        <input name="publisher_id" type="hidden" value="${publisher.id}">
                        <input name="action" type="hidden" value="show_publisher_all_authors">
                        <button>Просмотреть</button>
                    </form>
                </td>
                <td>
                    <form name="delete" method="post" action="publisher-delete.jsp">
                        <input name="id" type="hidden" value="${publisher.id}">
                        <input name="name" type="hidden" value="${publisher.name}">
                        <input name="country" type="hidden" value="${publisher.address.country}">
                        <input name="city" type="hidden" value="${publisher.address.city}">
                        <input name="street" type="hidden" value="${publisher.address.street}">
                        <input name="house" type="hidden" value="${publisher.address.house}">
                        <input name="zipcode" type="hidden" value="${publisher.address.zipcode}">
                        <input name="action" type="hidden" value="publisher-delete.jsp">
                        <button>Удалить</button>
                    </form>
                    <form name="update" method="post" action="publisher-update.jsp">
                        <input name="id" type="hidden" value="${publisher.id}">
                        <input name="name" type="hidden" value="${publisher.name}">
                        <input name="country" type="hidden" value="${publisher.address.country}">
                        <input name="city" type="hidden" value="${publisher.address.city}">
                        <input name="street" type="hidden" value="${publisher.address.street}">
                        <input name="house" type="hidden" value="${publisher.address.house}">
                        <input name="zipcode" type="hidden" value="${publisher.address.zipcode}">
                        <input name="action" type="hidden" value="publisher-update.jsp">
                        <button>Редактировать</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
    <br>
    ${requestScope.successAddPublisher}
    ${requestScope.errorAddPublisher}
    ${requestScope.successUpdatePublisher}
    ${requestScope.errorUpdatePublisher}
    ${requestScope.successDeletePublisher}
    ${requestScope.errorDeletePublisher}
    <p>
        <a href="publisher-add.jsp">Добавить издательство</a>
    <p>
        <a href="index.jsp">На главную страницу</a>
    <p>
        <c:if test="${sessionScope.searchPublisherResults != null || sessionScope.searchPublisherResults != null && sessionScope.sortPublisherResults != null}">
        <a href="controller?action=find_all_publishers">Назад</a>
        </c:if>
</div>
</body>
</html>
