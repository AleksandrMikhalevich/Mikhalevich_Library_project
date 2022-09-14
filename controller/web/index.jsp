<%--
  Created by IntelliJ IDEA.
  User: Alex Mikhalevich
  Date: 28.05.2022
  Time: 19:04
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Главная страница библиотеки</title>
</head>
<body class="d-flex flex-column min-vh-100">
<header>
    <%@include file="header.jsp"%>
</header>
<p>
<div style="text-align: center;">
    Вас приветствует библиотека Bookаньеры!
</div>
<div class="wrapper flex-grow-1">
</div>
<footer>
    <%@include file="footer.jsp"%>
</footer>
</body>
</html>
