<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Просмотр контактов</title>
   <!-- <script language="JavaScript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.5.0/jquery.min.js"></script>
  -->  <link href="css/table.css" rel="stylesheet" type="text/css">

    <!--<script src="js/jquery.js"/>-->
</head>
<body>
    <jsp:include page="Header.jsp"/>

    <table id="table11" class="simple-little-table" cellspacing="0">
        <tr>
            <th>№</th>
            <th>Фамилия</th>
            <th>Имя</th>
            <th>Отчество</th>
            <th>Дата рождения</th>
            <th class = "address">Адрес</th>
            <th>Тип телефона</th>
            <th>Номер телефона</th>
            <th>Тип почтового ящика</th>
            <th>Электронный адрес</th>
        </tr>


        <c:forEach var="person" items="${persons}">
            <tr>
                <td>${i = persons.indexOf(person)+1}</td>
                <td>${person.getLastName()}</td>
                <td>${person.getFirstName()}</td>
                <td>${person.getPatronymic()}</td>
                <td>${person.getDate()}</td>
                <td>${person.getAddress()}</td>
                <td>${phones.get(i-1).getType()}</td>
                <td>${phones.get(i-1).getPhonenumber()}</td>
                <td>${emails.get(i-1).getType()}</td>
                <td>${emails.get(i-1).getEmail()}</td>
            </tr>
        </c:forEach>



    </table>



</body>
</html>
