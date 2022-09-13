<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>List</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/debtors/debtors.css" />
</head>
<body>
<header>
  <nav>
    <a href="${pageContext.request.contextPath}/library-servlet"> Главная </a>
    <a href="${pageContext.request.contextPath}/library-servlet?action=load_search">Поиск по автору</a>
    <a href="${pageContext.request.contextPath}/library-servlet?action=load_number">Количество экземпляров</a>
    <c:if test="${role=='guest' || role==''}"><a href="${pageContext.request.contextPath}/registration/registration.jsp">Регистрация</a></c:if>
    <c:if test="${role=='guest' || role==''}"><a href="${pageContext.request.contextPath}/log-in/log-in.jsp">Войти</a></c:if>
    <c:if test="${role!='guest' && role!=''}"><a href="${pageContext.request.contextPath}/library-servlet?action=log_out">Выйти</a></c:if>
  </nav>
</header>
<h2>Наши должники:</h2>
<table class="table">
  <thead>
  <tr>
    <th>Имя</th>
  </tr>
  </thead>
  <tbody>
  <c:forEach items="${debtors}" var="debtor">
    <tr>
      <td>${debtor.getName()}</td>
    </tr>
  </c:forEach>
  </tbody>
</table>
</body>
</html>
