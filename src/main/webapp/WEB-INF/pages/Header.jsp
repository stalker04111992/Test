<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

    <head>
        <link href="css/style.css" rel="stylesheet" type="text/css">
    </head>

<body>
<div id="TopBar">

    <div id="logo">
        <a href="index"><img src="images/logo2.jpg" alt="" /></a>
    </div>

    <div id="menu">
        <div id="navigation">
            <ul>
                <li><a href="index">Главная</a></li>
                <li><a href="logout">Здравствуйте, ${auth.GetUsername()}</a></li>
                <li><a href="add">Добавить</a></li>
                <li><a href="edit">Редактировать</a></li>
                <li><a href="view">Просмотреть</a></li>
                <li class="last"><a href="logout">Выход</a></li>

            </ul>
        </div>
    </div>
</div>

</body>
</html>