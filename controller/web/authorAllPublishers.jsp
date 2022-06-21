<%--
  Created by IntelliJ IDEA.
  User: Alex Mikhalevich
  Date: 13.06.2022
  Time: 16:30  
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Информация о сотрудничестве автора с издательствами </title>
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
    <table class="center">
        <caption><b>
            Список издательств, сотрудничающих с автором <br>
            ${requestScope.author.firstName} ${requestScope.author.secondName} ${requestScope.author.surname}
        </b></caption>
        <tr>
            <th>Название</th>
            <th>Страна</th>
            <th>Город</th>
            <th>Улица</th>
            <th>Дом</th>
            <th>Почтовый индекс</th>
        </tr>
        <c:forEach var="publisher" items="${requestScope.author.publishers}">
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
            </tr>
        </c:forEach>
    </table>
    <p>
        <a href="authors.jsp">К списку авторов</a>

</div>
</body>
</html>
