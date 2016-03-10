<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html" %>
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
        <h1>Редактирование</h1>

        <div class = "error">${error}</div>

        <form method="post" action="editContact">
            <div><input type="text" name="LastName" pattern = "\D[^0-9]{3,50}" value="${person.getLastName()}" placeholder="*Фамилия (3 - 50 символов, кроме цифр)"></div>
            <div><input type="text" name="FirstName" pattern = "\D[^0-9]{3,50}" value="${person.getFirstName()}" placeholder="*Имя (3 - 50 символов, кроме цифр)"></div>
            <div><input type="text" name="Patronymic" pattern = "\D[^0-9]{3,50}" value="${person.getPatronymic()}" placeholder="*Отчество (3 - 50 символов, кроме цифр)"></div>
            <div><input type="text" name="BirthDate" value="${person.getDate()}" placeholder="Дата рождения"></div>
            <div><textarea name="Address" pattern="{3,255}" placeholder="Адрес (до 255 символов)">${person.getAddress()}</textarea></div>


            <div id = "telephone" class="com">

            <c:forEach var="phone" items="${phones}">
                <div>
                    <input name="id" type = "hidden" value="${person.getId()}">
                </div>
                <div>
                    <input type="text" id="Phone${phones.indexOf(phone)+1}" name="Phone" value="${phone.getPhonenumber()}" pattern="[0-9]{3,17}" placeholder="*Номер телефона (3 - 17 цифр)">
                </div>

                <div class="typePhone">
                    <select name="TypePhone">
                        <c:if test="${phone.getType() == 'Мобильный'}">
                            <option>Мобильный</option>
                            <option>Домашний</option>
                            <option>Служебный</option>
                        </c:if>
                        <c:if test="${phone.getType() == 'Домашний'}">
                            <option>Домашний</option>
                            <option>Служебный</option>
                            <option>Мобильный</option>
                        </c:if>
                        <c:if test="${phone.getType() == 'Служебный'}">
                            <option>Служебный</option>
                            <option>Домашний</option>
                            <option>Мобильный</option>
                        </c:if>
                    </select>

                    <c:if test="${phones.indexOf(phone) == person.getPhone()}">
                        <label>Использовать по умолчанию <input type="radio" name = "PhoneRadio" value = "${phones.indexOf(phone)+1}" checked></label>
                    </c:if>

                    <c:if test="${phones.indexOf(phone) != person.getPhone()}">
                        <label>Использовать по умолчанию <input type="radio" name = "PhoneRadio" value = "${phones.indexOf(phone)+1}"></label>
                    </c:if>

                </div>

            </c:forEach>
            </div>

            <div id = "mail" class="com">

            <c:forEach var="email" items="${emails}">

                <div><input type="text" id = "Email${emails.indexOf(email)+1}" value="${email.getEmail()}"maxlength="50" name="Email" placeholder="*Электронная почта (не более 50 символов)"></div>

                <div class="typeEmail">
                    <select name="TypeEmail">
                        <c:if test="${email.getType() == 'Домашний'}">
                            <option>Служебный</option>
                        </c:if>
                        <c:if test="${email.getType() == 'Служебный'}">
                            <option>Домашний</option>
                        </c:if>
                    </select>

                    <c:if test="${emails.indexOf(email) == person.getEmail()}">
                        <label>Использовать по умолчанию <input  name = "EmailRadio" type="radio" value = "${emails.indexOf(email)+1}" checked></label>
                    </c:if>

                    <c:if test="${emails.indexOf(email) != person.getEmail()}">
                        <label>Использовать по умолчанию <input name = "EmailRadio" type="radio" value = "${emails.indexOf(email)+1}"></label>
                    </c:if>
                </div>
            </c:forEach>

            </div>

            <div id="Stop"></div>

            <div>
                <div class ="buttonLeft"><button type="button" id="buttonPhone1" onclick="createMobiles(${phones.size()})">Добавить телефон</button></div>
                <div class ="buttonLeft"><button type="button" id="buttonEmail1" onclick="createEmails(${emails.size()})">Добавить email</button></div>
                <div class="buttonRight"><input type="submit" name="Submit" value="Изменить контакт"></div>
            </div>

        </form>
    </div>

</section>

</body>

<script>
    var phones = 1;
    var emails = 1;

    function createEmails(variable)
    {
       if (variable >= emails)
       {
            emails = variable;
       }

        var element = document.getElementById('Email' + emails.toString(10));
        if (element.value == "")
        {
            return 0;
        }

        var parent = document.getElementById('mail');
        var input = document.createElement('input');
        var label = document.createElement('label');
        var inputradio = document.createElement('input');
        var select = document.createElement('select');

        select.options[select.options.length] = new Option("Домашний", "Домашний");
        select.options[select.options.length] = new Option("Служебный", "Служебный");
        select.name = 'TypeEmail';

        emails ++;
        input.type = 'text';
        input.id = 'Email' + emails.toString(10);
        input.name = 'Email';
        input.placeholder = 'Электронная почта (не более 50 символов)';
        input.maxlength='50';

        inputradio.type = 'radio';
        inputradio.name = 'EmailRadio';
        inputradio.value = emails.toString();

        parent.appendChild(input);
        parent.appendChild(select);
        label.innerHTML = "Использовать по умолчанию";
        label.appendChild(inputradio);
        parent.appendChild(label);
    }


    function createMobiles(variable)
    {
        if (variable >= phones)
        {
            phones = variable;
        }

        var element = document.getElementById('Phone' + phones.toString(10));
        if (element.value == "")
        {
            return 0;
        }

        var parent = document.getElementById('telephone');
        var input = document.createElement('input');
        var label = document.createElement('label');
        var inputradio = document.createElement('input');
        var select = document.createElement('select');

        select.options[select.options.length] = new Option("Мобильный", "Мобильный");
        select.options[select.options.length] = new Option("Домашний", "Домашний");
        select.options[select.options.length] = new Option("Служебный", "Служебный");
        select.name = 'TypePhone';

        phones ++;
        input.type = 'text';
        input.id = 'Phone' + phones.toString(10);
        input.name = 'Phone';
        input.pattern = '[0-9]{3,17}';
        input.placeholder = 'Номер телефона (3 - 17 цифр)';

        inputradio.type = 'radio';
        inputradio.name = 'PhoneRadio';
        inputradio.value = phones.toString();

        parent.appendChild(input);
        parent.appendChild(select);

        label.innerHTML = "Использовать по умолчанию ";
        label.appendChild(inputradio);

        parent.appendChild(label);
    }
</script>

</html>
