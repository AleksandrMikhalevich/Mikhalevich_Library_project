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
    <title>Информация о книгах издательства</title>
</head>
<body class="d-flex flex-column min-vh-100">
<header>
    <%@include file="header.jsp" %>
</header>
<p>
<div style="text-align: center;">
    <h4>Список книг издательства ${requestScope.publisher.name}</h4>
</div>
<div class="w-90 p-3" style="text-align: center;">
    <table class="table table-bordered table-hover" style="text-align: center;">
        <thead>
        <tr>
            <th scope="col">Название</th>
            <th scope="col">Язык</th>
            <th scope="col">Год издания</th>
            <th scope="col">Дата поступления</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="book" items="${requestScope.publisher.books}">
            <tr>
                <td>
                    <c:out value="${book.title}"/>
                </td>
                <td>
                    <c:out value="${book.language}"/>
                </td>
                <td>
                    <c:out value="${book.yearOfPublishing}"/>
                </td>
                <td>
                    <c:out value="${book.receiptDate}"/>
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