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
  <title><fmt:message key="title.take_book"/></title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/take-book/take-book.css" />
</head>
<body>
<header>
  <nav>
    <a href="${pageContext.request.contextPath}/library-servlet"> <fmt:message key="menu.main"/> </a>
    <c:if test="${role=='admin'}"><a href="${pageContext.request.contextPath}/library-servlet?action=load_debtors">  <fmt:message key="menu.debtors"/>  </a></c:if>
    <a href="${pageContext.request.contextPath}/library-servlet?action=load_search"><fmt:message key="menu.search_by_author"/></a>
    <a href="${pageContext.request.contextPath}/library-servlet?action=load_number"><fmt:message key="menu.items_number"/></a>
    <c:if test="${role=='guest' || role==''}"><a href="${pageContext.request.contextPath}/registration/registration.jsp"><fmt:message key="menu.sign_up"/></a></c:if>
    <c:if test="${role=='guest' || role==''}"><a href="${pageContext.request.contextPath}/log-in/log-in.jsp"><fmt:message key="menu.sign_in"/></a></c:if>
    <c:if test="${role!='guest' && role!=''}"><a href="${pageContext.request.contextPath}/library-servlet?action=log_out"><fmt:message key="menu.log_out"/></a></c:if>
  </nav>
</header>
<form id="take" name="take" method="post" action="library-servlet">
  <input name="action" value="take-book" style="visibility: hidden"/>
</form>
</body>
<script type="module" src="${pageContext.request.contextPath}/take-book/take-book.js"></script>
<script>lang = "${userLocale.language}"</script>
</html>

