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
<body>
Запрос из ${pageContext.errorData.requestURI} не удался.
<br/>
Сервлет: ${pageContext.errorData.servletName}
<br/>
Статус код: ${pageContext.errorData.statusCode}
<br/>
Исключение: ${pageContext.errorData.throwable}
</body>
</html>
