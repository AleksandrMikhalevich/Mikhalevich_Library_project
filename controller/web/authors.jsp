<%--
  Created by IntelliJ IDEA.
  User: Alex Mikhalevich
  Date: 13.06.2022
  Time: 14:40  
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Авторы</title>
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

    Добро пожаловать к Авторам!
    <p>
    <form name="search" method="post" action="controller">
        <label>
            <input name="search_query" type="text" required placeholder="Поиск авторов">
        </label>
        <input name="action" type="hidden" value="find_author_by_name">
        <button>Искать</button>
    </form>

    <form name="sorting" method="post" action="controller">
        <label>
            <input type="radio" name="sorting" value="sort_by_surname" checked>
        </label> По фамилии
        ${requestScope.errorSortAuthors}
        <c:forEach var="author" items="${sessionScope.authorList}">
            <input name="authors_ids" type="hidden" value="${author.id}">
        </c:forEach>
        <input name="action" type="hidden" value="sort_authors">
        <button>Сортировать</button>
    </form>

    <table class="center">
        <p>
            <c:if test="${sessionScope.searchAuthorResults != null || sessionScope.searchAuthorResults != null && sessionScope.sortAuthorResults != null}">
                ${sessionScope.searchAuthorResults}
                ${requestScope.errorSearchAuthorResults}
            </c:if>
        </p>
        <caption><b>
            Список авторов
        </b></caption>
        <tr>
            <th>Фамилия</th>
            <th>Имя</th>
            <th>Отчество</th>
            <th>Страна</th>
            <th>Информация о книгах</th>
            <th>Информация о сотрудничестве с издательствами</th>
            <th>Действия</th>
        </tr>
        <c:forEach var="author" items="${sessionScope.authorList}">
            <tr>
                <td>
                    <c:out value="${author.surname}"/>
                </td>
                <td>
                    <c:out value="${author.firstName}"/>
                </td>
                <td>
                    <c:out value="${author.secondName}"/>
                </td>
                <td>
                    <c:out value="${author.country}"/>
                </td>
                <td>
                    <form name="infoBooks" method="post" action="controller">
                        <input name="id" type="hidden" value="${author.id}">
                        <input name="action" type="hidden" value="show_author_all_books">
                        <button>Просмотреть</button>
                    </form>
                </td>
                <td>
                    <form name="infoPublishers" method="post" action="controller">
                        <input name="id" type="hidden" value="${author.id}">
                        <input name="action" type="hidden" value="show_author_all_publishers">
                        <button>Просмотреть</button>
                    </form>
                </td>
                <td>
                    <form name="delete" method="post" action="controller">
                        <input name="id" type="hidden" value="${author.id}">
                        <input name="action" type="hidden" value="find_author_by_id_to_delete">
                        <button>Удалить</button>
                    </form>
                    <form name="update" method="post" action="controller">
                        <c:remove var="updatedChosenPublishersToAuthor"/>
                        <input name="id" type="hidden" value="${author.id}">
                        <input name="action" type="hidden" value="find_author_by_id_to_update">
                        <button>Редактировать</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
    <br>
    ${requestScope.successAddAuthor}
    ${requestScope.errorAddAuthor}
    ${requestScope.successUpdateAuthor}
    ${requestScope.errorUpdateAuthor}
    ${requestScope.successDeleteAuthor}
    ${requestScope.errorDeleteAuthor}
    ${requestScope.errorFindAuthorToDelete}
    ${requestScope.errorFindAuthorToUpdate}
    <p>
        <c:remove var="addedChosenPublishersToAuthor"/>
        <a href="author-add.jsp">Добавить автора</a>
    <p>
        <a href="main.jsp">На главную страницу</a>
    <p>
        <c:if test="${sessionScope.searchAuthorResults != null || sessionScope.searchAuthorResults != null && sessionScope.sortAuthorResults != null}">
        <a href="controller?action=find_all_authors">Назад</a>
        </c:if>
</div>
</body>
</html>