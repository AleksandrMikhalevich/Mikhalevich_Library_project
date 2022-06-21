<%--
  Created by IntelliJ IDEA.
  User: Alex Mikhalevich
  Date: 28.05.2022
  Time: 19:04  
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Главная страница библиотеки</title>
</head>
<body>
<div style="text-align: center;">
    Приветствуем, пользователь!
    <p>
    <form name="library" method="post" action="controller">
        <input name="action" type="hidden" value="find_all_books">
        <button>Книги</button>
    </form>
    <p>
    <form name="library" method="post" action="controller">
        <input name="action" type="hidden" value="find_all_authors">
        <button>Авторы</button>
    </form>
    <p>
    <form name="library" method="post" action="controller">
        <input name="action" type="hidden" value="find_all_genres">
        <button>Жанры</button>
    </form>
    <p>
    <form name="library" method="post" action="controller">
        <input name="action" type="hidden" value="find_all_publishers">
        <button>Издательства</button>
    </form>
    ${requestScope.errorBookList}
    <br/>
    <a href="index.jsp">Назад</a>

</div>
</body>
</html>
