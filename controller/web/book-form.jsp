<%--
Created by IntelliJ IDEA.
  User: Alex Mikhalevich
  Date: 03.06.2022
  Time: 19:56  
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

    <c:choose>
        <c:when test="${requestScope.edit != null}">
            <h2>Редактирование книги ${sessionScope.book.title}
            </h2>
            <form name="updateAuthors" method="post" action="controller" autocomplete="off">
                <fieldset>
                    <legend>Авторы</legend>
                    <c:choose>
                        <c:when test="${sessionScope.chosenAuthors != null && requestScope.marker != null}">
                            <c:forEach var="authorToChange" items="${sessionScope.chosenAuthors}">
                                <label>
                                    <input name="author" type="text" disabled
                                           placeholder="${authorToChange.firstName} ${authorToChange.secondName} ${authorToChange.surname}">
                                </label><br>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <c:forEach var="authorExist" items="${sessionScope.book.authors}">
                                <label>
                                    <input name="author" type="text" disabled
                                           placeholder="${authorExist.firstName} ${authorExist.secondName} ${authorExist.surname}">
                                </label><br>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
                    <input name="action" type="hidden" value="add_author_to_book">
                    <button>Выбрать авторов</button>
                </fieldset>
            </form>

            <form name="updateGenres" method="post" action="controller" autocomplete="off">
                <fieldset>
                    <legend>Жанры</legend>
                    <c:choose>
                        <c:when test="${sessionScope.chosenGenres != null && requestScope.marker != null}">
                            <c:forEach var="genre" items="${sessionScope.chosenGenres}">
                                <label>
                                    <input name="genre" type="text" disabled placeholder="${genre.name}">
                                </label><br>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <c:forEach var="genreExist" items="${sessionScope.book.genres}">
                                <label>
                                    <input name="genre" type="text" disabled placeholder="${genreExist.name}">
                                </label><br>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
                    <input name="action" type="hidden" value="add_genre_to_book">
                    <button>Выбрать жанры</button>
                </fieldset>
            </form>

            <form name="updatePublisher" method="post" action="controller" autocomplete="off">
                <fieldset>
                    <legend>Издательство</legend>
                    <c:choose>
                        <c:when test="${sessionScope.chosenPublisher != null && requestScope.marker != null}">
                            <c:set var="publisher_id" scope="session" value="${sessionScope.chosenPublisher.id}"/>
                            <c:set var="publisher_name" scope="session" value="${sessionScope.chosenPublisher.name}"/>
                            <label>
                                <input name="publisher" type="text" disabled placeholder="${publisher_name}">
                            </label><br>
                        </c:when>
                        <c:otherwise>
                            <c:set var="publisher_id" scope="session" value="${sessionScope.book.publisher.id}"/>
                            <label>
                                <input name="publisher" type="text" disabled
                                       placeholder="${sessionScope.book.publisher.name}">
                            </label><br>
                        </c:otherwise>
                    </c:choose>
                    <input name="action" type="hidden" value="add_publisher_to_book">
                    <button>Выбрать издательство</button>
                </fieldset>
            </form>

            <form name="updateBook" method="post" action="controller" autocomplete="off">
                <fieldset>
                    <input name="id" type="hidden" required value="${sessionScope.book.id}">
                    <legend>Общая информация</legend>
                    Название: <label>
                    <input name="title" type="text" required value="${sessionScope.book.title}">
                </label>
                    Язык: <label>
                    <input name="language" type="text" required value="${sessionScope.book.language}">
                </label>
                    Год издания: <label>
                    <input name="year_of_publishing" type="text" required value="${sessionScope.book.yearOfPublishing}">
                </label>
                    Дата поступления: <label>
                    <input name="receipt_date" type="date" required value="${sessionScope.book.receiptDate}">
                </label>
                    <c:choose>
                        <c:when test="${sessionScope.chosenAuthors != null}">
                            <c:forEach var="authorToChange" items="${sessionScope.chosenAuthors}">
                                <label>
                                    <input name="author_ids" type="hidden" value="${authorToChange.id}">
                                </label>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <c:forEach var="authorExist" items="${sessionScope.book.authors}">
                                <label>
                                    <input name="author_ids" type="hidden" value="${authorExist.id}">
                                </label>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
                    <c:choose>
                        <c:when test="${sessionScope.chosenGenres != null}">
                            <c:forEach var="genre" items="${sessionScope.chosenGenres}">
                                <label>
                                    <input name="genre_ids" type="hidden" value="${genre.id}">
                                </label>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <c:forEach var="genreExist" items="${sessionScope.book.genres}">
                                <label>
                                    <input name="genre_ids" type="hidden" value="${genreExist.id}">
                                </label>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
                    <label>
                        <input name="publisher_id" type="hidden" value="${publisher_id}">
                    </label>
                </fieldset>
                <input name="action" type="hidden" value="update_book">
                <button>Сохранить изменения</button>
            </form>
        </c:when>

        <c:when test="${param.id == null}">
            <h2>Добавление книги</h2>
            <form name="addAuthors" method="post" action="controller" autocomplete="off">
                <fieldset>
                    <legend>Авторы</legend>
                    <c:if test="${sessionScope.chosenAuthors != null && requestScope.marker != null}">
                        <c:forEach var="author" items="${sessionScope.chosenAuthors}">
                            <label>
                                <input name="author" type="text" disabled
                                       placeholder="${author.firstName} ${author.secondName} ${author.surname}">
                            </label><br>
                        </c:forEach>
                    </c:if>
                    <input name="action" type="hidden" value="add_author_to_book">
                    <button>Выбрать авторов</button>
                </fieldset>
            </form>

            <form name="addGenres" method="post" action="controller" autocomplete="off">
                <fieldset>
                    <legend>Жанры</legend>
                    <c:if test="${sessionScope.chosenGenres != null && requestScope.marker != null}">
                        <c:forEach var="genre" items="${sessionScope.chosenGenres}">
                            <label>
                                <input name="genre" type="text" disabled placeholder="${genre.name}">
                            </label><br>
                        </c:forEach>
                    </c:if>
                    <input name="action" type="hidden" value="add_genre_to_book">
                    <button>Выбрать жанры</button>
                </fieldset>
            </form>

            <form name="addPublisher" method="post" action="controller" autocomplete="off">
                <fieldset>
                    <legend>Издательство</legend>
                    <c:if test="${sessionScope.chosenPublisher != null && requestScope.marker != null}">
                        <c:set var="publisher_id" scope="session" value="${sessionScope.chosenPublisher.id}"/>
                        <c:set var="publisher_name" scope="session" value="${sessionScope.chosenPublisher.name}"/>
                        <label>
                            <input name="publisher" type="text" disabled placeholder="${publisher_name}">
                        </label><br>
                    </c:if>
                    <input name="action" type="hidden" value="add_publisher_to_book">
                    <button>Выбрать издательство</button>
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
                    <c:forEach var="author" items="${sessionScope.chosenAuthors}">
                        <label>
                            <input name="author_ids" type="hidden" value="${author.id}">
                        </label>
                    </c:forEach>
                    <c:forEach var="genre" items="${sessionScope.chosenGenres}">
                        <label>
                            <input name="genre_ids" type="hidden" value="${genre.id}">
                        </label>
                    </c:forEach>
                    <label>
                        <input name="publisher_id" type="hidden" value="${publisher_id}">
                    </label>
                </fieldset>
                <input name="action" type="hidden" value="add_book">
                <button>Сохранить изменения</button>
            </form>
        </c:when>
        <c:otherwise>
        </c:otherwise>
    </c:choose>

    <a href="books.jsp">К списку книг</a>

</div>
</body>
</html>



