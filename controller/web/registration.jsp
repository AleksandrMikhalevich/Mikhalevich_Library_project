<%--
  Created by IntelliJ IDEA.
  User: Alex Mikhalevich
  Date: 30.09.2022
  Time: 12:33  
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Регистрация</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.1/font/bootstrap-icons.css">
</head>
<body class="d-flex flex-column min-vh-100">
<div class="container">
    <div class="row justify-content-center" style="margin-top:20px">
        <div class="col-md-4 mb-3">
            <form class="form-signin" action="controller" method="post">
                <fieldset>
                    <legend>Регистрация пользователя</legend>
                    <div class="form-group">
                        <label for="login">
                        </label><input type="text" name="login" id="login" class="form-control input-lg"
                                       placeholder="Логин" required="required"/>
                    </div>
                    <br>
                    <div class="form-group">
                        <label for="email">
                        </label><input type="email" name="email" id="email" class="form-control input-lg"
                                       placeholder="Электронная почта" required="required"/>
                    </div>
                    <br>
                    <div class="form-group">
                        <label for="password">
                        </label><input type="password" name="password" id="password" class="form-control input-lg"
                                       placeholder="Пароль" required="required"/>
                    </div>
                    <br>
                    <div class="form-group">
                        <label for="passwordConfirm">
                        </label><input type="password" name="password_confirm" id="passwordConfirm" class="form-control input-lg"
                                       placeholder="Повтор пароля" required="required"/>
                    </div>
                </fieldset>
                <br>
                <div>
                    <input name="action" type="hidden" value="add_user">
                    <button type="submit" class="btn btn-primary">Зарегистрироваться</button>
                </div>
                <br>
                <div class="text-center">
                        <a href="index.jsp">На главную</a>
                </div>
            </form>
        </div>
    </div>
</div>
<div style="text-align: center;">
    <p>
        ${requestScope.errorAddUser}
        ${requestScope.errorPassword}
        ${requestScope.errorLogin}
    </p>
</div>
<div class="wrapper flex-grow-1">
</div>
<footer>
    <%@include file="footer.jsp" %>
</footer>
</body>
</html>

