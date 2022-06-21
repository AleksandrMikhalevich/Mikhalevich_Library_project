<%--
  Created by IntelliJ IDEA.
  User: Alex Mikhalevich
  Date: 13.06.2022
  Time: 15:22  
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Страница добавления</title>
</head>
<body>
<div style="text-align: center;">

    <h2>Добавление автора</h2>
    <form name="addPublishers" method="post" action="controller" autocomplete="off">
        <fieldset>
            <legend>Сотрудничество с издательствами</legend>
            <c:if test="${sessionScope.addedChosenPublishersToAuthor != null}">
                <c:forEach var="publisher" items="${sessionScope.addedChosenPublishersToAuthor}">
                    <label>
                        <input name="publisher" type="text" disabled
                               placeholder="${publisher.name}">
                    </label><br>
                </c:forEach>
            </c:if>
            ${requestScope.errorChoosePublishers}
            ${requestScope.errorAddChosenPublishers}
            <c:choose>
                <c:when test="${sessionScope.addedChosenPublishersToAuthor != null}">
                    <input name="action" type="hidden" value="choose_publishers_for_added_author">
                    <button>Изменить выбор издательств</button>
                </c:when>
                <c:otherwise>
                    <input name="action" type="hidden" value="choose_publishers_for_added_author">
                    <button>Выбрать издательства</button>
                </c:otherwise>
            </c:choose>
        </fieldset>
    </form>

    <form name="addAuthor" method="post" action="controller" autocomplete="off">
        <fieldset>
            <legend>Персональная информация</legend>
            Фамилия: <label>
            <input name="surname" type="text" required value="">
        </label>
            Имя: <label>
            <input name="first_name" type="text" required value="">
        </label>
            Отчество: <label>
            <input name="second_name" type="text" required value="">
        </label>
            Страна: <label>
            <input name="country" type="text" required value="">
        </label>
            <c:forEach var="publisher" items="${sessionScope.addedChosenPublishersToAuthor}">
                <label>
                    <input name="publishers_ids" type="hidden" value="${publisher.id}">
                </label>
            </c:forEach>
        </fieldset>
        <input name="action" type="hidden" value="add_author">
        <button>Сохранить</button>
    </form>

    <a href="authors.jsp">К списку авторов</a>

</div>
</body>
</html>
