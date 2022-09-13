<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Log in</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/log-in/log-in.css" />
</head>
<body>
<header>
    <a href="${pageContext.request.contextPath}/library-servlet"> Главная </a>
</header>
<form id="login" name="login" action="${pageContext.request.contextPath}/library-servlet" method="post">
    <input name="action" value="login" style="visibility: hidden"/>
</form>
<c:if test="${failedAttempt}">Неверные данные, попробуйте снова</c:if>
</body>
<script type="module" src="${pageContext.request.contextPath}/log-in/log-in.js"></script>
</html>
