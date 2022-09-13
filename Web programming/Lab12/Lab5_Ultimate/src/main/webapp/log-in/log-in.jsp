<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:requestEncoding value="UTF-8"/>
<fmt:setLocale value="${userLocale}"/>
<fmt:setBundle basename="locale"/>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title><fmt:message key="title.log_in"/></title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/log-in/log-in.css" />
</head>
<body>
<header>
    <a href="${pageContext.request.contextPath}/library-servlet"> <fmt:message key="menu.main"/> </a>
</header>
<form id="login" name="login" action="${pageContext.request.contextPath}/library-servlet" method="post">
    <input name="action" value="login" style="visibility: hidden"/>
</form>
<c:if test="${failedAttempt}"><fmt:message key="login.incorrect_data"/></c:if>
</body>
<script type="module" src="${pageContext.request.contextPath}/log-in/log-in.js"></script>
<script>lang = "${userLocale.language}"</script>
</html>
