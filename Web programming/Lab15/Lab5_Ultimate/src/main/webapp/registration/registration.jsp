<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:requestEncoding value="UTF-8"/>
<fmt:setLocale value="${userLocale}"/>
<fmt:setBundle basename="locale"/>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title><fmt:message key="title.registration"/></title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/registration/registration.css" />
</head>
<body>
<header>
    <a href="${pageContext.request.contextPath}/library-servlet"> <fmt:message key="menu.main"/> </a>
</header>
<form id="registration" name="registration" action="${pageContext.request.contextPath}/library-servlet" method="post">
    <input name="action" value="registration" style="visibility: hidden"/>
</form>
<p>${errorMessage}</p>
</body>
<script type="module" src="${pageContext.request.contextPath}/registration/registration.js"></script>
<script>lang = "${userLocale.language}"</script>
</html>
