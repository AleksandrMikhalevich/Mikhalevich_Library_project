<%--
  Created by IntelliJ IDEA.
  User: Alex Mikhalevich
  Date: 16.06.2022
  Time: 14:40  
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page isELIgnored="false" %>
<html>
<head>
  <title>Страница редактирования</title>
</head>
<body class="d-flex flex-column min-vh-100">
<header>
  <%@include file="header.jsp" %>
</header>
<p>
<div style="text-align: center;">
  <h3>Редактирование жанра</h3>
</div>
<div class="container">
  <form name="updateGenre" method="post" action="controller" autocomplete="off">
    <fieldset>
      <legend>Информация</legend>
      <div class="mb-3">
        <label for="nameInput" class="form-label">Название</label>
        <input type="text" id="nameInput" name="name" class="form-control" required value="${param.name}">
      </div>
      <div class="mb-3">
        <label for="descriptionInput" class="form-label">Описание</label>
        <input type="text" id="descriptionInput" name="description" class="form-control"
               required value="${param.description}">
      </div>
    </fieldset>
    <input name="id" type="hidden" value="${param.id}">
    <input name="action" type="hidden" value="update_genre">
    <button type="submit" class="btn btn-outline-success">Сохранить</button>
  </form>
  <a href="genres.jsp">К списку жанров</a>
</div>
<div class="wrapper flex-grow-1">
</div>
<footer>
  <%@include file="footer.jsp" %>
</footer>
</body>
</html>

