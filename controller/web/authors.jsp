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
<body class="d-flex flex-column min-vh-100">
<header>
    <%@include file="header.jsp" %>
</header>
<p>
<div style="text-align: center;">
    Добро пожаловать к Авторам!
</div>
<p>
<div class="d-flex justify-content-center">
    <form name="search" method="post" action="controller">
        <div class="input-group">
            <input type="text" class="d-flex flex-row form-control" name="search_query"
                   aria-describedby="searchInput" aria-label="Search authors" placeholder="Поиск авторов">
            <input name="action" type="hidden" value="find_author_by_name">
            <button class="d-flex flex-row btn btn-outline-success" type="submit" id="searchInput">Искать</button>
        </div>
    </form>
</div>
<div class="d-flex justify-content-center">
    <form name="sorting" method="post" action="controller">
        <div class="form-check form-check-inline">
            <input class="form-check-input" type="radio" name="sorting" id="inlineRadio1"
                   value="sort_by_surname" checked="checked">
            <label class="form-check-label" for="inlineRadio1">По фамилии</label>
        </div>
        ${requestScope.errorSortAuthors}
        <c:forEach var="author" items="${sessionScope.authorList}">
            <input name="authors_ids" type="hidden" value="${author.id}">
        </c:forEach>
        <input name="action" type="hidden" value="sort_authors">
        <button type="submit" class="btn btn-outline-secondary">Сортировать</button>
    </form>
</div>
<div class="w-90 p-3" style="text-align: center;">
    <p>
        <c:if test="${sessionScope.searchAuthorResults != null || sessionScope.searchAuthorResults != null && sessionScope.sortAuthorResults != null}">
            ${sessionScope.searchAuthorResults}
            ${requestScope.errorSearchAuthorResults}
        </c:if>
    </p>
    <table class="table table-bordered table-hover" style="text-align: center;">
        <thead>
        <tr>
            <c:if test="${sessionScope.get('user').getRole().getName() eq 'ADMIN'}">
                <th scope="col">Id</th>
            </c:if>
            <th scope="col">Фамилия</th>
            <th scope="col">Имя</th>
            <th scope="col">Отчество</th>
            <th scope="col">Страна</th>
            <th scope="col">Информация о книгах</th>
            <th scope="col">Информация о сотрудничестве с издательствами</th>
            <c:if test="${sessionScope.get('user').getRole().getName() eq 'ADMIN'}">
                <th colspan="2">Действия</th>
            </c:if>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="author" items="${sessionScope.authorList}">
            <tr>
                <c:if test="${sessionScope.get('user').getRole().getName() eq 'ADMIN'}">
                    <td>
                        <c:out value="${author.id}"/>
                    </td>
                </c:if>
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
                        <button type="submit" class="btn btn-outline-info">Просмотреть</button>
                    </form>
                </td>
                <td>
                    <form name="infoPublishers" method="post" action="controller">
                        <input name="id" type="hidden" value="${author.id}">
                        <input name="action" type="hidden" value="show_author_all_publishers">
                        <button type="submit" class="btn btn-outline-info">Просмотреть</button>
                    </form>
                </td>
                <c:if test="${sessionScope.get('user').getRole().getName() eq 'ADMIN'}">
                    <td>
                        <form name="delete" method="post" action="controller">
                            <input name="id" type="hidden" value="${author.id}">
                            <input name="action" type="hidden" value="find_author_by_id_to_delete">
                            <button type="submit" class="btn btn-outline-danger">Удалить</button>
                        </form>
                    </td>
                    <td>
                        <form name="update" method="post" action="controller">
                            <c:remove var="updatedChosenPublishersToAuthor"/>
                            <input name="id" type="hidden" value="${author.id}">
                            <input name="action" type="hidden" value="find_author_by_id_to_update">
                            <button type="submit" class="btn btn-outline-warning">Редактировать</button>
                        </form>
                    </td>
                </c:if>
            </tr>
        </c:forEach>
        </tbody>
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
    <c:if test="${sessionScope.get('user').getRole().getName() eq 'ADMIN'}">
    <p>
        <a <c:remove var="addedChosenPublishersToAuthor"/>
                href="author-add.jsp">Добавить автора</a>
    <p>
    </c:if>
        <a href="index.jsp">На главную страницу</a>
    <p>
    <c:if test="${sessionScope.searchAuthorResults != null || sessionScope.searchAuthorResults != null && sessionScope.sortAuthorResults != null}">
        <a href="controller?action=find_all_authors">Назад</a>
    </c:if>
</div>
<div class="wrapper flex-grow-1">
</div>
<footer>
    <%@include file="footer.jsp" %>
</footer>
</body>
</html>