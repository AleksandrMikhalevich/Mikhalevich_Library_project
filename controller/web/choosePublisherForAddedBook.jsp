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
<body class="d-flex flex-column min-vh-100">
<header>
    <%@include file="header.jsp" %>
</header>
<p>
<div style="text-align: center;">
    <h3>Добавление издательства к новой книге</h3>
</div>
<div class="w-90 p-3" style="text-align: center;">
    <form name="choosePublisher" method="post" action="controller">
        <table class="table table-bordered table-hover" style="text-align: center;">
            <thead>
            <tr>
                <th scope="col">Название</th>
                <th scope="col">Страна</th>
                <th scope="col">Город</th>
                <th scope="col">Улица</th>
                <th scope="col">Дом</th>
                <th scope="col">Почтовый индекс</th>
                <th scope="col">Выбор</th>
            </tr>
            </thead>
            <tbody>
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
                        <div class="form-check d-flex justify-content-center">
                            <input class="form-check-input" type="radio" name="publisher_id" value="${publisher.id}"
                                   id="flexCheckChecked">
                            <label class="form-check-label" for="flexCheckChecked"></label>
                        </div>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <input name="action" type="hidden" value="add_chosen_publisher_for_added_book">
        <button type="submit" class="btn btn-outline-success">Добавить</button>
    </form>
    <a href="book-add.jsp">Назад к странице добавления</a>
</div>
<div class="wrapper flex-grow-1">
</div>
<footer>
    <%@include file="footer.jsp" %>
</footer>
</body>
</html>