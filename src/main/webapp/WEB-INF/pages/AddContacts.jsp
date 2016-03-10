<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Добавление контакта</title>
    <link href="css/StyleAdd2.css" rel="stylesheet" type="text/css">
</head>

<body>

<jsp:include page="Header.jsp"/>

<section class="container">
    <div class="login">
        <h1>Новый контакт</h1>

        <div class = "error">${error}</div>

        <form method="post" action="add">
            <div><input type="text" name="LastName" pattern = "\D[^0-9]{3,50}" value="${lastName}" placeholder="*Фамилия (3 - 50 символов, кроме цифр)"></div>
            <div><input type="text" name="FirstName" pattern = "\D[^0-9]{3,50}" value="${firstName}" placeholder="*Имя (3 - 50 символов, кроме цифр)"></div>
            <div><input type="text" name="Patronymic" pattern = "\D[^0-9]{3,50}" value="${patronymic}" placeholder="*Отчество (3 - 50 символов, кроме цифр)"></div>
            <div><input type="text" name="BirthDate" pattern="{\D[^0-9]{2}+(-)+\D[^0-9]{2}+(-)+\D[^0-9]{4}}" value="${birthDate}" placeholder="Дата рождения"></div>
            <div><textarea name="Address" value="${address}" pattern="{3,255}" placeholder="Адрес (до 255 символов)"></textarea></div>

            <div id = "telephone" class="com">
                <div>
                    <input type="text" id="Phone1" name="Phone" pattern="[0-9]{3,17}" placeholder="*Номер телефона (3 - 17 цифр)">
                </div>

                <div id="typePhone1">

                    <select name="TypePhone">
                    <option>Мобильный</option>
                    <option>Домашний</option>
                    <option>Служебный</option>
                    </select>

                    <label>Использовать по умолчанию <input type="radio" name = "PhoneRadio" value = "1" checked></label>
                </div>
            </div>

            <div id = "mail" class="com">
                <div><input type="text" id = "Email1" maxlength="50" name="Email" placeholder="*Электронная почта (не более 50 символов)"></div>

                <div id="typeEmail1">
                    <select name="TypeEmail">
                        <option>Домашний</option>
                        <option>Служебный</option>
                    </select>

                    <label>Использовать по умолчанию <input type="radio" name = "EmailRadio" value = "1" checked></label>
                </div>
            </div>

            <div class="Stop"></div>

            <div>
                <div class ="buttonLeft"><button type="button" id="buttonPhone1" onclick="createMobiles()">Добавить телефон</button></div>
                <div class ="buttonLeft"><button type="button" id="buttonEmail1" onclick="createEmails()">Добавить email</button></div>
                <div class="buttonRight"><input type="submit" name="Submit" value="Добавить контакт"></div>
            </div>

            <div class="Stop"></div>

        </form>
    </div>

</section>



</body>

<script>
    var phones = 1;
    var emails = 1;

    function createEmails()
    {
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


    function createMobiles()
    {
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
