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
    <title><fmt:message key="title.search"/></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/search/search.css"/>
</head>
<body>
<header>
    <nav>
        <a href="${pageContext.request.contextPath}/library-servlet"> <fmt:message key="menu.main"/> </a>
        <c:if test="${role=='admin'}"><a href="${pageContext.request.contextPath}/library-servlet?action=load_debtors">
            <fmt:message key="menu.debtors"/> </a></c:if>
        <c:if test="${role=='user'}"><a href="${pageContext.request.contextPath}/library-servlet?action=take_book"><fmt:message key="menu.take_book"/></a></c:if>
        <a href="${pageContext.request.contextPath}/library-servlet?action=load_number"><fmt:message key="menu.items_number"/></a>
        <c:if test="${role=='guest' || role==''}"><a
                href="${pageContext.request.contextPath}/registration/registration.jsp"><fmt:message key="menu.sign_up"/></a></c:if>
        <c:if test="${role=='guest' || role==''}"><a
                href="${pageContext.request.contextPath}/log-in/log-in.jsp"><fmt:message key="menu.sign_in"/></a></c:if>
        <c:if test="${role!='guest' && role!=''}"><a
                href="${pageContext.request.contextPath}/library-servlet?action=log_out"><fmt:message key="menu.log_out"/></a></c:if>
    </nav>
</header>
<h2><fmt:message key="search.search_by_author"/></h2>
<form id="search" name="search" method="get" action="library-servlet">
    <input name="action" value="search_books" style="visibility: hidden"/>
</form>
<table class="table">
    <thead>
    <tr>
        <th><fmt:message key="search.author"/></th>
        <th><fmt:message key="search.title"/></th>
        <th><fmt:message key="search.number_items"/></th>
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
<script type="module" src="${pageContext.request.contextPath}/search/search.js"></script>
<script>lang = "${userLocale.language}"</script>
</body>
</html>

