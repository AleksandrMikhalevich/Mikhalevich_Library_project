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
<body class="d-flex flex-column min-vh-100">
<header>
    <%@include file="header.jsp" %>
</header>
<p>
<div style="text-align: center;">
    <h4>Список издательств, сотрудничающих с
        автором ${requestScope.author.firstName} ${requestScope.author.secondName} ${requestScope.author.surname}</h4>
</div>
<div class="w-90 p-3" style="text-align: center;">
    <table class="table table-bordered table-hover" style="text-align: center;">
        <thead>
        <tr>
            <th scope="col">Название</th>
            <th scope="col">Страна</th>
            <th scope="col">Город</th>
            <th scope="col">Улица</th>
            <th scope="col">Дом</th>
            <th scope="col">Почтовый индекс</th>
        </tr>
        </thead>
        <tbody>
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
        </tbody>
    </table>
    <p>
        <a href="authors.jsp">К списку авторов</a>
</div>
<div class="wrapper flex-grow-1">
</div>
<footer>
    <%@include file="footer.jsp" %>
</footer>
</body>
</html>
