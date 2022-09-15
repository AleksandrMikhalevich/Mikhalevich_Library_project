<%--
  Created by IntelliJ IDEA.
  User: Alex Mikhalevich
  Date: 08.06.2022
  Time: 19:05  
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Выбор жанров</title>
</head>
<body class="d-flex flex-column min-vh-100">
<header>
    <%@include file="header.jsp" %>
</header>
<p>
<div style="text-align: center;">
    <h3>Добавление жанров к новой книге</h3>
</div>
<div class="w-90 p-3" style="text-align: center;">
    <form name="chooseGenres" method="post" action="controller">
        <table class="table table-bordered table-hover" style="text-align: center;">
            <thead>
            <tr>
                <th scope="col">Название</th>
                <th scope="col">Выбор</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="genre" items="${sessionScope.addedGenres}">
                <tr>
                    <td>
                        <c:out value="${genre.name}"/>
                    </td>
                    <td>
                        <div class="form-check d-flex justify-content-center">
                            <input class="form-check-input" type="checkbox" name="genre_ids" value="${genre.id}"
                                   id="flexCheckChecked">
                            <label class="form-check-label" for="flexCheckChecked"></label>
                        </div>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <input name="action" type="hidden" value="add_chosen_genres_for_added_book">
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
