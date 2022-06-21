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
<body>
<div style="text-align: center;">

  <h2>Редактирование жанра</h2>
  <form name="updateGenre" method="post" action="controller" autocomplete="off">
    <fieldset>
      <legend>Информация</legend>
      Название: <label>
      <input name="name" type="text" required value="${param.name}">
    </label>
      Описание: <label>
      <input name="description" type="text" required value="${param.description}">
    </label>
    </fieldset>
    <input name="id" type="hidden" value="${param.id}">
    <input name="action" type="hidden" value="update_genre">
    <button>Сохранить изменения</button>
  </form>

  <a href="genres.jsp">К списку жанров</a>

</div>
</body>
</html>
