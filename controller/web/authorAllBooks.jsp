<%--
  Created by IntelliJ IDEA.
  User: Alex Mikhalevich
  Date: 13.06.2022
  Time: 15:53  
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Информация о книгах автора</title>
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
            Список книг автора <br>
            ${requestScope.author.firstName} ${requestScope.author.secondName} ${requestScope.author.surname}
        </b></caption>
        <tr>
            <th>Название</th>
            <th>Язык</th>
            <th>Год издания</th>
            <th>Дата поступления</th>
        </tr>
        <c:forEach var="book" items="${requestScope.author.books}">
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
    </table>
    <p>
        <a href="authors.jsp">К списку авторов</a>

</div>
</body>
</html>
