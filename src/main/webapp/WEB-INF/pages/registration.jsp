<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Регистрация</title>
    <link href="css/StyleAdd2.css" rel="stylesheet" type="text/css">
</head>
<body>

<jsp:include page="Header.jsp"/>

<section class="container">
    <div class="login">
        <h1>Регистрация</h1>

        <div class = "error">${error}</div>

        <form method="post" action="registration">
            <div><input type="text" name="username" pattern = "{4,30}" value="${username}" placeholder="*Имя пользователя"></div>
            <div><input type="password" name="password" pattern = "{4,15}" value="${password}" placeholder="*Пароль"></div>
            <div><input type="password" name="password2" pattern = "{4,15}" value="${password2}" placeholder="*Повторите пароль"></div>
            <div><input type="email" name="email" pattern = "{4,30}" value="${email}" placeholder="*Email"></div>

            <div class="Stop"></div>

            <div>
                 <div class="buttonRight"><input type="submit" name="Submit" value="Регистрация"></div>
            </div>

            <div class="Stop"></div>

        </form>
    </div>

</section>

</body>
</html>
