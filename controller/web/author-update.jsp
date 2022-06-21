<%--
  Created by IntelliJ IDEA.
  User: Alex Mikhalevich
  Date: 15.06.2022
  Time: 20:52  
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

    <h2>Добавление автора</h2>
    <form name="updatePublishers" method="post" action="controller" autocomplete="off">
        <fieldset>
            <legend>Сотрудничество с издательствами</legend>
            <c:choose>
                <c:when test="${sessionScope.updatedChosenPublishersToAuthor != null}">
                    <c:forEach var="publisherToUpdate" items="${sessionScope.updatedChosenPublishersToAuthor}">
                        <label>
                            <input name="author" type="text" disabled
                                   placeholder="${publisherToUpdate.name}">
                        </label><br>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <c:forEach var="publisherExist" items="${sessionScope.author.publishers}">
                        <label>
                            <input name="author" type="text" disabled
                                   placeholder="${publisherExist.name}">
                        </label><br>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
            ${requestScope.errorChoosePublishers}
            ${requestScope.errorUpdateChosenPublishers}
            <input name="action" type="hidden" value="choose_publishers_for_updated_author">
            <button>Выбрать издательства</button>
        </fieldset>
    </form>

    <form name="updateAuthor" method="post" action="controller" autocomplete="off">
        <fieldset>
            <legend>Персональная информация</legend>
            <input name="id" type="hidden" required value="${sessionScope.author.id}">
            Фамилия: <label>
            <input name="surname" type="text" required value="${sessionScope.author.surname}">
        </label>
            Имя: <label>
            <input name="first_name" type="text" required value="${sessionScope.author.firstName}">
        </label>
            Отчество: <label>
            <input name="second_name" type="text" required value="${sessionScope.author.secondName}">
        </label>
            Страна: <label>
            <input name="country" type="text" required value="${sessionScope.author.country}">
        </label>
            <c:choose>
                <c:when test="${sessionScope.updatedChosenPublishersToAuthor != null}">
                    <c:forEach var="publisherToUpdate" items="${sessionScope.updatedChosenPublishersToAuthor}">
                        <label>
                            <input name="publishers_ids" type="hidden" value="${publisherToUpdate.id}">
                        </label>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <c:forEach var="publisherExist" items="${sessionScope.author.publishers}">
                        <label>
                            <input name="publishers_ids" type="hidden" value="${publisherExist.id}">
                        </label>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </fieldset>
        <input name="action" type="hidden" value="update_author">
        <button>Сохранить изменения</button>
    </form>

    <a href="authors.jsp">К списку авторов</a>

</div>
</body>
</html>
