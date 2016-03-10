<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Авторизация</title>
    <link href="css/StyleAdd2.css" rel="stylesheet" type="text/css">
</head>
<body>

<jsp:include page="Header.jsp"/>

<section class="container">
    <div class="login">
        <h1>Авторизация</h1>

        <div class = "error">${error}</div>

        <form method="post" action="login">
            <div><input type="text" name="username" pattern = ".{4,30}" value="${username}" placeholder="*Имя пользователя (не менее 4 символов)"></div>
            <div><input type="password" name="password" pattern = ".{4,15}" value="${password}" placeholder="*Пароль (не менее 4 символов)"></div>

            <div class="Stop"></div>

            <div>
                <div class="buttonRight"><input type="submit" name="Submit" value="Авторизация"></div>
            </div>

            <div class="Stop"></div>

        </form>
    </div>

</section>

</body>
</html>
