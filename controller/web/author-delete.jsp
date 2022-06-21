<%--
  Created by IntelliJ IDEA.
  User: Alex Mikhalevich
  Date: 16.06.2022
  Time: 12:32  
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

    <h2>Удаление автора</h2>
    <form name="deleteAuthor" method="post" action="controller" autocomplete="off">
        <fieldset>
            <legend>Сотрудничество с издательствами</legend>
            <c:forEach var="publisherExist" items="${sessionScope.authorToDelete.publishers}">
                <label>
                    <input name="author" type="text" disabled
                           placeholder="${publisherExist.name}">
                </label><br>
            </c:forEach>
        </fieldset>
        <fieldset>
            <legend>Персональная информация</legend>
            Фамилия: <label>
            <input name="surname" type="text" disabled value="${sessionScope.authorToDelete.surname}">
        </label>
            Имя: <label>
            <input name="first_name" type="text" disabled value="${sessionScope.authorToDelete.firstName}">
        </label>
            Отчество: <label>
            <input name="second_name" type="text" disabled value="${sessionScope.authorToDelete.secondName}">
        </label>
            Страна: <label>
            <input name="country" type="text" disabled value="${sessionScope.authorToDelete.country}">
        </label>
        </fieldset>
        <input name="id" type="hidden" value="${sessionScope.authorToDelete.id}">
        <input name="action" type="hidden" value="delete_author">
        <p>
            Вниманию администратора!<br>
            Удаление автора из базы данных библиотеки приведет к удалению всех книг данного автора.
        </p>
        <button>Удалить</button>
    </form>

    <a href="authors.jsp">К списку авторов</a>

</div>
</body>
</html>
