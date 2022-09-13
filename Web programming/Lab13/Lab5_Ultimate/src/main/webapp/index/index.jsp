<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <title>Библиотека</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/index/index.css"/>
</head>
<body>
<header>
    <nav>
        <c:if test="${role=='user'}"><a href="${pageContext.request.contextPath}/library-servlet?action=take_book">Взять книгу</a></c:if>
        <c:if test="${role=='admin'}"><a href="${pageContext.request.contextPath}/library-servlet?action=load_debtors">
            Должники </a></c:if>
        <a href="${pageContext.request.contextPath}/library-servlet?action=load_search">Поиск по автору</a>
        <a href="${pageContext.request.contextPath}/library-servlet?action=load_number">Количество экземпляров</a>
        <c:if test="${role=='guest' || role==''}"><a href="${pageContext.request.contextPath}/registration/registration.jsp">Регистрация</a></c:if>
        <c:if test="${role=='guest' || role==''}"><a href="${pageContext.request.contextPath}/log-in/log-in.jsp">Войти</a></c:if>
        <c:if test="${role!='guest' && role!=''}"><a href="${pageContext.request.contextPath}/library-servlet?action=log_out">Выйти</a></c:if>
    </nav>
</header>
<div class="info-block">
    <h2>БИБЛИОТЕКА</h2>
    <p>
        Добро пожаловать, дорогой читатель!
        <br/>
        <br/>
        Мы рады приветствовать тебя в нашей библиотеке. Здесь ты сможешь найти
        много захватывающих художественных произведений, а также изучить что-то
        новое с помощью научной литературы.
        <br/>
        <br/>
        Если у тебя возникнут какие-то трудности, ты всегда можешь позвонить или
        написать нам ❤️
        <br/>
        <br/>
        <strong>Контакты:</strong>
        <br/>
        Телефончик: +375-(44)-864-53-84
        <time>(с 10:00 до 16:00)</time>
        <br/>
        Почтовый ящичек: library@puppy.com
        <br/>
        <br/>
        <strong>Мы работаем по адресу:</strong>
        <br/>
        г. Минск, ул. Чюрлениса, д.1
        <br/>
        <time>с понедельника по пятницу с 10 до 19.</time>
        <br/>
        <br/>
        Приятного чтения!
    </p>
    <img src="${pageContext.request.contextPath}/index/dog.jpg" alt="dog"/>
</div>
</body>
</html>
