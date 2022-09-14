<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <meta charset="UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <title></title>
</head>
<body>
<div>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.1/font/bootstrap-icons.css">
    <nav class="navbar navbar-expand-lg sticky-top navbar-light bg-light">
        <nav class="navbar navbar-light bg-light">
            <div class="container-fluid">
                <a class="navbar-brand" href="${pageContext.request.contextPath}/">
                    <img src="${pageContext.request.contextPath}/logo.jpg" alt="" width="100" height="100"
                         class="d-inline-block align-middle">
                    Библиотека Bookаньеры
                </a>
            </div>
        </nav>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link" href="controller?action=find_all_books">Книги</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="controller?action=find_all_authors">Авторы</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="controller?action=find_all_genres">Жанры</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="controller?action=find_all_publishers">Издательства</a>
                </li>
            </ul>
        </div>
    </nav>
</div>
</body>
</html>