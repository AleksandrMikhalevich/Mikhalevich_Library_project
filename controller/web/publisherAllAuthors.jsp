<%--
  Created by IntelliJ IDEA.
  User: Alex Mikhalevich
  Date: 16.06.2022
  Time: 17:23  
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Информация об авторах издательства</title>
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
            Список авторов издательства ${requestScope.publisher.name}
        </b></caption>
        <tr>
            <th>Имя</th>
            <th>Отчество</th>
            <th>Фамилия</th>
            <th>Страна</th>
        </tr>
        <c:forEach var="author" items="${requestScope.publisher.authors}">
            <tr>
                <td>
                    <c:out value="${author.firstName}"/>
                </td>
                <td>
                    <c:out value="${author.secondName}"/>
                </td>
                <td>
                    <c:out value="${author.surname}"/>
                </td>
                <td>
                    <c:out value="${author.country}"/>
                </td>
            </tr>
        </c:forEach>
    </table>
    <p>
        <a href="publishers.jsp">К списку издательств</a>

</div>
</body>
</html>
