<%@ page import="java.util.Objects" %><%--
  Created by IntelliJ IDEA.
  User: Alex Mikhalevich
  Date: 03.06.2022
  Time: 12:29  
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Страница удаления</title>
</head>
<body>
<div style="text-align: center;">

    <h2>Удаление книги ${sessionScope.bookToDelete.title}
    </h2>

    <form name="deleteBook" method="post" action="controller" autocomplete="off">
        <input name="id" type="hidden" required value="${sessionScope.bookToDelete.id}">
        <fieldset>
            <legend>Авторы</legend>
            <c:forEach var="authorExist" items="${sessionScope.bookToDelete.authors}">
                <label>
                    <input name="author" type="text" disabled
                           placeholder="${authorExist.firstName} ${authorExist.secondName} ${authorExist.surname}">
                </label><br>
            </c:forEach>
        </fieldset>
        <fieldset>
            <legend>Жанры</legend>
            <c:forEach var="genreExist" items="${sessionScope.bookToDelete.genres}">
                <label>
                    <input name="genre" type="text" disabled placeholder="${genreExist.name}">
                </label><br>
            </c:forEach>
        </fieldset>
        <fieldset>
            <legend>Издательство</legend>
            <c:if test="${sessionScope.bookToDelete.publisher != null}">
            <c:set var="publisher_id" scope="session" value="${sessionScope.bookToDelete.publisher.id}"/>
            <label>
                <input name="publisher" type="text" disabled placeholder="${sessionScope.bookToDelete.publisher.name}">
            </label><br>
            </c:if>
        </fieldset>
        <fieldset>
            <legend>Общая информация</legend>
            Название: <label>
            <input name="title" type="text" disabled placeholder="${sessionScope.bookToDelete.title}">
        </label>
            Язык: <label>
            <input name="language" type="text" disabled placeholder="${sessionScope.bookToDelete.language}">
        </label>
            Год издания: <label>
            <input name="year_of_publishing" type="text" disabled placeholder="${sessionScope.bookToDelete.yearOfPublishing}">
        </label>
            Дата поступления: <label>
            <input name="receipt_date" type="date" disabled value="${sessionScope.bookToDelete.receiptDate}">
        </label>
        </fieldset>
        <input name="action" type="hidden" value="delete_book">
        <button>Удалить</button>
    </form>

    <a href="books.jsp">К списку книг</a>

</div>
</body>
</html>
