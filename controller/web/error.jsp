<%--
  Created by IntelliJ IDEA.
  User: Alex Mikhalevich
  Date: 02.07.2022
  Time: 14:36  
--%>
<%@ page isErrorPage="true" contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Ошибка!</title>
</head>
<body class="d-flex flex-column min-vh-100">
<header>
    <%@include file="header.jsp" %>
</header>
<p>
Запрос из ${pageContext.errorData.requestURI} не удался.
<br/>
Сервлет: ${pageContext.errorData.servletName}
<br/>
Статус код: ${pageContext.errorData.statusCode}
<br/>
Исключение: ${pageContext.errorData.throwable}
<div class="wrapper flex-grow-1">
</div>
<footer>
    <%@include file="footer.jsp" %>
</footer>
</body>
</html>
