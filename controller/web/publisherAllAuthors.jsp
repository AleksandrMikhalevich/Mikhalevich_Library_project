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
<body class="d-flex flex-column min-vh-100">
<header>
    <%@include file="header.jsp" %>
</header>
<p>
<div style="text-align: center;">
    <h4>Список авторов издательства ${requestScope.publisher.name}</h4>
</div>
<div class="w-90 p-3" style="text-align: center;">
    <table class="table table-bordered table-hover" style="text-align: center;">
        <thead>
        <tr>
            <th scope="col">Имя</th>
            <th scope="col">Отчество</th>
            <th scope="col">Фамилия</th>
            <th scope="col">Страна</th>
        </tr>
        </thead>
        <tbody>
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
        </tbody>
    </table>
    <p>
        <a href="publishers.jsp">К списку издательств</a>
</div>
<div class="wrapper flex-grow-1">
</div>
<footer>
    <%@include file="footer.jsp" %>
</footer>
</body>
</html>
