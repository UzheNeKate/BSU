<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Take</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/take-book/take-book.css" />
</head>
<body>
<header>
  <nav>
    <a href="${pageContext.request.contextPath}/library-servlet"> Главная </a>
    <c:if test="${role=='admin'}"><a href="${pageContext.request.contextPath}/library-servlet?action=load_debtors">  Должники  </a></c:if>
    <a href="${pageContext.request.contextPath}/library-servlet?action=load_search">Поиск по автору</a>
    <a href="${pageContext.request.contextPath}/library-servlet?action=load_number">Количество экземпляров</a>
    <c:if test="${role=='guest' || role==''}"><a href="${pageContext.request.contextPath}/registration/registration.jsp">Регистрация</a></c:if>
    <c:if test="${role=='guest' || role==''}"><a href="${pageContext.request.contextPath}/log-in/log-in.jsp">Войти</a></c:if>
    <c:if test="${role!='guest' && role!=''}"><a href="${pageContext.request.contextPath}/library-servlet?action=log_out">Выйти</a></c:if>
  </nav>
</header>
<form id="take" name="take" method="post" action="library-servlet">
  <input name="action" value="take-book" style="visibility: hidden"/>
</form>
</body>
<script type="module" src="${pageContext.request.contextPath}/take-book/take-book.js"></script>
</html>

