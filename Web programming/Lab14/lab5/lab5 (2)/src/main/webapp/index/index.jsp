<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:requestEncoding value="UTF-8"/>
<fmt:setLocale value="${userLocale}"/>
<fmt:setBundle basename="locale"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title><fmt:message key="app.title"/></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/index/index.css"/>
</head>
<body>
<header>
    <nav>
        <c:if test="${role=='user'}"><a
                href="${pageContext.request.contextPath}/library-servlet?action=take_book"><fmt:message
                key="menu.take_book"/></a></c:if>
        <c:if test="${role=='admin'}"><a href="${pageContext.request.contextPath}/library-servlet?action=load_debtors">
            <fmt:message key="menu.debtors"/> </a></c:if>
        <a href="${pageContext.request.contextPath}/library-servlet?action=load_search"><fmt:message
                key="menu.search_by_author"/></a>
        <a href="${pageContext.request.contextPath}/library-servlet?action=load_number"><fmt:message
                key="menu.items_number"/></a>
        <c:if test="${role!='guest' && role!=''}">
            <a href="${pageContext.request.contextPath}/library-servlet?action=chat"><fmt:message
                    key="menu.chat"/></a>
        </c:if>
        <c:if test="${role=='guest' || role==''}"><a
                href="${pageContext.request.contextPath}/registration/registration.jsp"><fmt:message
                key="menu.sign_up"/></a></c:if>
        <c:if test="${role=='guest' || role==''}"><a
                href="${pageContext.request.contextPath}/log-in/log-in.jsp"><fmt:message key="menu.sign_in"/></a></c:if>
        <c:if test="${role!='guest' && role!=''}"><a
                href="${pageContext.request.contextPath}/library-servlet?action=log_out"><fmt:message
                key="menu.log_out"/></a></c:if>
    </nav>
</header>
<form>
    <select name='lang' onchange='this.form.submit()'>
        <c:choose>
        <c:when test="${userLocale.language == 'ru'}">
        <option value='ru' selected>Русский
        <option value='en'>English
            </c:when>
            <c:otherwise>
        <option value='ru'>Русский
        <option value='en' selected>English
            </c:otherwise>
            </c:choose>
    </select>
</form>
<div class="info-block">
    <h2><fmt:message key="app.title"/></h2>
    <p>
        <fmt:message key="index.welcome"/>
        <br/>
        <br/>
        <fmt:message key="index.introduction"/>
        <br/>
        <br/>
        <fmt:message key="index.troubles"/>️
        <br/>
        <br/>
        <strong><fmt:message key="index.contacts_header"/></strong>
        <br/>
        <fmt:message key="index.phone"/>
        <time><fmt:message key="index.time"/></time>
        <br/>
        <fmt:message key="index.mail"/>
        <br/>
        <br/>
        <strong><fmt:message key="index.address_work"/></strong>
        <br/>
        <fmt:message key="index.address"/>
        <br/>
        <time><fmt:message key="index.days"/></time>
        <br/>
        <br/>
        <fmt:message key="index.have_nice_read"/>
    </p>
    <img src="${pageContext.request.contextPath}/index/dog.jpg" alt="dog"/>
</div>
</body>
</html>
