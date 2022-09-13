<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Registration</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/registration/registration.css" />
</head>
<body>
<header>
    <a href="${pageContext.request.contextPath}/library-servlet"> Главная </a>
</header>
<form id="registration" name="registration" action="${pageContext.request.contextPath}/library-servlet" method="post">
    <input name="action" value="registration" style="visibility: hidden"/>
</form>
<p>${errorMessage}</p>
</body>
<script type="module" src="${pageContext.request.contextPath}/registration/registration.js"></script>
</html>
