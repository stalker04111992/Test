<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Редактирование контакта</title>
    <link href="css/StyleAdd2.css" rel="stylesheet" type="text/css">
</head>

<body>

<jsp:include page="Header.jsp"/>

<section class="container">
    <div class="login">
        <h1>Выберите контакт</h1>

        <form method="get" action="editContact">
            <select name="Select">
                <c:forEach var="person" items="${persons}">
                    <option value="${person.getId()}">${person.getLastName()} ${person.getFirstName()} ${person.getPatronymic()}</option>
                </c:forEach>
            </select>
           <input class="submit2" type="submit" name="Submit" value="Редактировать">
        </form>
    </div>

</section>
</body>
</html>
