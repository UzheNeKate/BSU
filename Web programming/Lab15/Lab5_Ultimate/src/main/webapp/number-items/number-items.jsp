<%@ page import="com.example.lab5_ultimate.model.entity.BookEntity" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="mytaglib" uri="/WEB-INF/numberItemsTable.tld" %>
<fmt:requestEncoding value="UTF-8"/>
<fmt:setLocale value="${userLocale}"/>
<fmt:setBundle basename="locale"/>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title><fmt:message key="title.items_count"/></title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/number-items/number-items.css" />
</head>
<body>
<header>
  <nav>
    <a href="${pageContext.request.contextPath}/library-servlet">  <fmt:message key="menu.main"/> </a>
    <c:if test="${role=='admin'}"><a href="${pageContext.request.contextPath}/library-servlet?action=load_debtors">  <fmt:message key="menu.debtors"/>  </a></c:if>
    <c:if test="${role=='user'}"><a href="${pageContext.request.contextPath}/library-servlet?action=take_book"><fmt:message key="menu.take_book"/></a></c:if>
    <a href="${pageContext.request.contextPath}/library-servlet?action=load_search"><fmt:message key="menu.search_by_author"/></a>
    <c:if test="${role=='guest' || role==''}"><a href="${pageContext.request.contextPath}/registration/registration.jsp"><fmt:message key="menu.sign_up"/></a></c:if>
    <c:if test="${role=='guest' || role==''}"><a href="${pageContext.request.contextPath}/log-in/log-in.jsp"><fmt:message key="menu.sign_in"/></a></c:if>
    <c:if test="${role!='guest' && role!=''}"><a href="${pageContext.request.contextPath}/library-servlet?action=log_out"><fmt:message key="menu.log_out"/></a></c:if>
  </nav>
</header>
<h2>Поиск количества экземпляров:</h2>
<form id="number" name="number" method="get" action="library-servlet">
  <input name="action" value="get_number" style="visibility: hidden"/>
</form>
<%--<table class="table">--%>
<%--  <thead>--%>
<%--  <tr>--%>
<%--    <th><fmt:message key="number_items.author"/></th>--%>
<%--    <th><fmt:message key="number_items.title"/></th>--%>
<%--    <th><fmt:message key="number_items.number_items"/></th>--%>
<%--  </tr>--%>
<%--  </thead>--%>
<%--  <tbody>--%>
<%--  <c:forEach items="${allBooks}" var="book">--%>
<%--    <tr>--%>
<%--      <td>${book.getAuthor()}</td>--%>
<%--      <td>${book.getTitle()}</td>--%>
<%--      <td>${book.getNumOfItems()}</td>--%>
<%--    </tr>--%>
<%--  </c:forEach>--%>
<%--  </tbody>--%>
<%--</table>--%>
<mytaglib:NumberItemsTableTag/>
<script type="module" src="${pageContext.request.contextPath}/number-items/number-items.js"></script>
<script>lang = "${userLocale.language}"</script>
</body>
</html>

