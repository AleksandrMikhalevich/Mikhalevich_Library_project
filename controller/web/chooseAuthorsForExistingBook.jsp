<%--
  Created by IntelliJ IDEA.
  User: Alex Mikhalevich
  Date: 08.06.2022
  Time: 13:15  
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Выбор авторов</title>
</head>
<body class="d-flex flex-column min-vh-100">
<header>
    <%@include file="header.jsp" %>
</header>
<p>
<div style="text-align: center;">
    <h3>Изменение авторов существующей книги</h3>
</div>
<div class="w-90 p-3" style="text-align: center;">
    <form name="add" method="post" action="controller">
        <table class="table table-bordered table-hover" style="text-align: center;">
            <thead>
            <tr>
                <th scope="col">Имя</th>
                <th scope="col">Отчество</th>
                <th scope="col">Фамилия</th>
                <th scope="col">Страна</th>
                <th scope="col">Выбор</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="author" items="${sessionScope.changedAuthors}">
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
                    <td>
                        <div class="form-check d-flex justify-content-center">
                            <input class="form-check-input" type="checkbox" name="author_ids" value="${author.id}"
                                   id="flexCheckChecked">
                            <label class="form-check-label" for="flexCheckChecked"></label>
                        </div>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <input name="action" type="hidden" value="update_chosen_authors_for_existing_book">
        <button type="submit" class="btn btn-outline-success">Добавить</button>
    </form>
    <a href="book-update.jsp">Назад к странице редактирования</a>
</div>
<div class="wrapper flex-grow-1">
</div>
<footer>
    <%@include file="footer.jsp" %>
</footer>
</body>
</html>