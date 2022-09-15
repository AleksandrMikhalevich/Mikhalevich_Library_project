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
    <title>Издательства</title>
</head>
<body class="d-flex flex-column min-vh-100">
<header>
    <%@include file="header.jsp" %>
</header>
<p>
<div style="text-align: center;">
    Добро пожаловать к Издательствам!
</div>
<p>
<div class="d-flex justify-content-center">
    <form name="search" method="post" action="controller">
        <div class="input-group">
            <input type="text" class="d-flex flex-row form-control" name="search_query"
                   aria-describedby="searchInput" aria-label="Search publishers" placeholder="Поиск издательств">
            <input name="action" type="hidden" value="find_publisher_by_name">
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
        ${requestScope.errorSortPublishers}
        <c:forEach var="publisher" items="${sessionScope.publisherList}">
            <input name="publishers_ids" type="hidden" value="${publisher.id}">
        </c:forEach>
        <input name="action" type="hidden" value="sort_publishers">
        <button type="submit" class="btn btn-outline-secondary">Сортировать</button>
    </form>
</div>
<div class="w-90 p-3" style="text-align: center;">
    <p>
        <c:if test="${sessionScope.searchPublisherResults != null || sessionScope.searchPublisherResults != null && sessionScope.sortPublisherResults != null}">
            ${sessionScope.searchPublisherResults}
            ${requestScope.errorSearchPublisherResults}
        </c:if>
    </p>
    <table class="table table-bordered table-hover" style="text-align: center;">
        <thead>
        <tr>
            <th scope="col">Название</th>
            <th scope="col">Страна</th>
            <th scope="col">Город</th>
            <th scope="col">Улица</th>
            <th scope="col">Дом</th>
            <th scope="col">Почтовый индекс</th>
            <th scope="col">Информация о книгах</th>
            <th scope="col">Информация об авторах</th>
            <th colspan="2">Действия</th>
        </tr>
        </thead>
        <tbody>
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
                        <button type="submit" class="btn btn-outline-info">Просмотреть</button>
                    </form>
                </td>
                <td>
                    <form name="infoAuthors" method="post" action="controller">
                        <input name="publisher_id" type="hidden" value="${publisher.id}">
                        <input name="action" type="hidden" value="show_publisher_all_authors">
                        <button type="submit" class="btn btn-outline-info">Просмотреть</button>
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
                        <button type="submit" class="btn btn-outline-danger">Удалить</button>
                    </form>
                </td>
                <td>
                    <form name="update" method="post" action="publisher-update.jsp">
                        <input name="id" type="hidden" value="${publisher.id}">
                        <input name="name" type="hidden" value="${publisher.name}">
                        <input name="country" type="hidden" value="${publisher.address.country}">
                        <input name="city" type="hidden" value="${publisher.address.city}">
                        <input name="street" type="hidden" value="${publisher.address.street}">
                        <input name="house" type="hidden" value="${publisher.address.house}">
                        <input name="zipcode" type="hidden" value="${publisher.address.zipcode}">
                        <input name="action" type="hidden" value="publisher-update.jsp">
                        <button type="submit" class="btn btn-outline-warning">Редактировать</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
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
