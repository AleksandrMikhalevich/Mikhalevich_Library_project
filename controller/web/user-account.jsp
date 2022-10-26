<%--
  Created by IntelliJ IDEA.
  User: Alex Mikhalevich
  Date: 04.10.2022
  Time: 19:18  
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Профиль пользователя</title>
</head>
<body class="d-flex flex-column min-vh-100">
<header>
    <%@include file="header.jsp"%>
</header>
<div class="w-90 p-3">
    <form action="controller" method="post">
        <button type="button" class="btn btn-outline-danger" data-bs-toggle="modal"
                data-bs-target="#deleteUserModal"
                id="data-bs-target='#deleteUserModal' + ${sessionScope.user.id}">
            Удалить
        </button>
        <div class="modal modal-delete" id="deleteUserModal" tabindex="-1"
             aria-labelledby="deleteUserLabel"
             aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="deleteUserLabel">Подтверждение удаления
                            пользователя</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal"
                                aria-label="Закрыть"></button>
                    </div>
                    <div class="modal-body">
                        <p>Вы действительно хотите удалить свой аккаунт?</p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">
                            Отмена
                        </button>
                        <button type="submit" class="btn btn-danger">Удалить</button>
                        <input name="id" type="hidden" value="${sessionScope.user.id}">
                        <input name="action" type="hidden" value="delete_user">
                    </div>
                </div>
            </div>
        </div>
    </form>
</div>
<div class="container">
    <div class="row justify-content-center" style="margin-top:20px">
        <div class="col-md-4 mb-3">
            <form class="form-signin" action="controller" method="post">
                <fieldset>
                    <legend>Редактирование профиля пользователя ${sessionScope.user.login}</legend>
                    <div class="form-group">
                        <label for="login">
                        </label><input type="text" name="login" id="login" class="form-control input-lg"
                                       disabled placeholder="${sessionScope.user.login}"/>
                    </div>
                    <br>
                    <div class="form-group">
                        <label for="email">
                        </label><input type="email" name="email" id="email" class="form-control input-lg"
                                       value="${sessionScope.user.email}" required="required"/>
                    </div>
                    <br>
                    <div class="form-group">
                        <label for="password">
                        </label><input type="password" name="password" id="password" class="form-control input-lg"
                                       placeholder="Новый пароль" required="required"/>
                    </div>
                    <br>
                    <div class="form-group">
                        <label for="passwordConfirm">
                        </label><input type="password" name="password_confirm" id="passwordConfirm" class="form-control input-lg"
                                       placeholder="Повтор нового пароля" required="required"/>
                    </div>
                </fieldset>
                <br>
                <div>
                    <input name="id" type="hidden" value="${sessionScope.user.id}">
                    <input name="action" type="hidden" value="update_user">
                    <button type="submit" class="btn btn-primary">Сохранить</button>
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
        ${requestScope.errorUpdateUser}
        ${requestScope.errorPassword}
        ${requestScope.errorDeleteUser}
    </p>
</div>
<div class="wrapper flex-grow-1">
</div>
<footer>
    <%@include file="footer.jsp" %>
</footer>
</body>
</html>

