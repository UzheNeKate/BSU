<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Number</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/number-items/number-items.css" />
</head>
<body>
<header>
  <nav>
    <a href="${pageContext.request.contextPath}/library-servlet"> Главная </a>
    <c:if test="${role=='admin'}"><a href="${pageContext.request.contextPath}/library-servlet?action=load_debtors">  Должники  </a></c:if>
    <c:if test="${role=='user'}"><a href="${pageContext.request.contextPath}/library-servlet?action=take_book">Взять книгу</a></c:if>
    <a href="${pageContext.request.contextPath}/library-servlet?action=load_search">Поиск по автору</a>
    <c:if test="${role=='guest' || role==''}"><a href="${pageContext.request.contextPath}/registration/registration.jsp">Регистрация</a></c:if>
    <c:if test="${role=='guest' || role==''}"><a href="${pageContext.request.contextPath}/log-in/log-in.jsp">Войти</a></c:if>
    <c:if test="${role!='guest' && role!=''}"><a href="${pageContext.request.contextPath}/library-servlet?action=log_out">Выйти</a></c:if>
  </nav>
</header>
<h2>Поиск количества экземпляров:</h2>
<form id="number" name="number" method="get" action="library-servlet">
  <input name="action" value="get_number" style="visibility: hidden"/>
</form>
<table class="table">
  <thead>
  <tr>
    <th>Автор</th>
    <th>Название</th>
    <th>Количество экземпляров</th>
  </tr>
  </thead>
  <tbody>
  <c:forEach items="${allBooks}" var="book">
    <tr>
      <td>${book.getAuthor()}</td>
      <td>${book.getTitle()}</td>
      <td>${book.getNumOfItems()}</td>
    </tr>
  </c:forEach>
  </tbody>
</table>
<script type="module" src="${pageContext.request.contextPath}/number-items/number-items.js"></script>
</body>
</html>

