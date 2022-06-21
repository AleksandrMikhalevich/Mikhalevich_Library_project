<%--
  Created by IntelliJ IDEA.
  User: Alex Mikhalevich
  Date: 08.06.2022
  Time: 12:36  
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

    <h2>Добавление книги</h2>
    <form name="addAuthors" method="post" action="controller" autocomplete="off">
        <fieldset>
            <legend>Авторы</legend>
            <c:if test="${sessionScope.chosenAuthorsToAdd != null}">
                <c:forEach var="author" items="${sessionScope.chosenAuthorsToAdd}">
                    <label>
                        <input name="author" type="text" disabled
                               placeholder="${author.firstName} ${author.secondName} ${author.surname}">
                    </label><br>
                </c:forEach>
            </c:if>
            ${requestScope.errorChooseAuthors}
            ${requestScope.errorAddChosenAuthors}
            <c:if test="${sessionScope.chosenPublisherToAdd != null}">
                <label>
                    <input name="publisher_id" type="hidden" value="${sessionScope.chosenPublisherToAdd.id}">
                </label>
            </c:if>
            <c:choose>
                <c:when test="${sessionScope.chosenAuthorsToAdd != null}">
                    <input name="action" type="hidden" value="choose_authors_for_added_book">
                    <button>Изменить выбор авторов</button>
                </c:when>
                <c:otherwise>
                    <input name="action" type="hidden" value="choose_authors_for_added_book">
                    <button>Выбрать авторов</button>
                </c:otherwise>
            </c:choose>
        </fieldset>
    </form>

    <form name="addGenres" method="post" action="controller" autocomplete="off">
        <fieldset>
            <legend>Жанры</legend>
            <c:if test="${sessionScope.chosenGenresToAdd != null}">
                <c:forEach var="genre" items="${sessionScope.chosenGenresToAdd}">
                    <label>
                        <input name="genre" type="text" disabled placeholder="${genre.name}">
                    </label><br>
                </c:forEach>
            </c:if>
            ${requestScope.errorChooseGenres}
            ${requestScope.errorAddChosenGenres}
            <c:choose>
                <c:when test="${sessionScope.chosenGenresToAdd != null}">
                    <input name="action" type="hidden" value="choose_genres_for_added_book">
                    <button>Изменить выбор жанров</button>
                </c:when>
                <c:otherwise>
                    <input name="action" type="hidden" value="choose_genres_for_added_book">
                    <button>Выбрать жанры</button>
                </c:otherwise>
            </c:choose>
        </fieldset>
    </form>

    <form name="addPublisher" method="post" action="controller" autocomplete="off">
        <fieldset>
            <legend>Издательство</legend>
            <c:if test="${sessionScope.chosenPublisherToAdd != null}">
                <c:set var="publisher_id" scope="session" value="${sessionScope.chosenPublisherToAdd.id}"/>
                <c:set var="publisher_name" scope="session" value="${sessionScope.chosenPublisherToAdd.name}"/>
                <label>
                    <input name="publisher" type="text" disabled placeholder="${publisher_name}">
                </label><br>
            </c:if>
            ${requestScope.errorChoosePublisher}
            ${requestScope.errorAddChosenPublisher}
            <c:if test="${sessionScope.chosenAuthorsToAdd != null}">
                <c:forEach var="author" items="${sessionScope.chosenAuthorsToAdd}">
                    <label>
                        <input name="author_ids" type="hidden" value="${author.id}">
                    </label>
                </c:forEach>
            </c:if>
            <c:choose>
                <c:when test="${sessionScope.chosenPublisherToAdd != null}">
                    <input name="action" type="hidden" value="choose_publisher_for_added_book">
                    <button>Изменить выбор издательства</button>
                </c:when>
                <c:otherwise>
                    <input name="action" type="hidden" value="choose_publisher_for_added_book">
                    <button>Выбрать издательство</button>
                </c:otherwise>
            </c:choose>
        </fieldset>
    </form>

    <form name="addBook" method="post" action="controller" autocomplete="off">
        <fieldset>
            <legend>Общая информация</legend>
            Название: <label>
            <input name="title" type="text" required value="">
        </label>
            Язык: <label>
            <input name="language" type="text" required value="">
        </label>
            Год издания: <label>
            <input name="year_of_publishing" type="text" required placeholder="1900">
        </label>
            Дата поступления: <label>
            <input name="receipt_date" type="date" required value="">
        </label>
            <c:forEach var="author" items="${sessionScope.chosenAuthorsToAdd}">
                <label>
                    <input name="author_ids" type="hidden" value="${author.id}">
                </label>
            </c:forEach>
            <c:forEach var="genre" items="${sessionScope.chosenGenresToAdd}">
                <label>
                    <input name="genre_ids" type="hidden" value="${genre.id}">
                </label>
            </c:forEach>
            <label>
                <input name="publisher_id" type="hidden" value="${publisher_id}">
            </label>
        </fieldset>
        <input name="action" type="hidden" value="add_book">
        <button>Сохранить</button>
    </form>

    <a href="books.jsp">К списку книг</a>

</div>
</body>
</html>
