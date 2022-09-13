<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:requestEncoding value="UTF-8"/>
<fmt:setLocale value="${userLocale}"/>
<fmt:setBundle basename="locale"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title><fmt:message key="app.title"/></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/chat/chat.css">
    <link crossorigin="anonymous" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/chat/chat.js"></script>
</head>
<body>
<header>
    <nav>
        <a href="${pageContext.request.contextPath}/library-servlet"> <fmt:message key="menu.main"/> </a>
    </nav>
</header>
<h1><fmt:message key="chatPage.title"/></h1>

<input type="hidden" id="senderId" value="${senderId}">
<input type="hidden" id="role" value="${role}">
<div>
    <c:if test="${role == 'admin'}">
        <p>
            <fmt:message key="chatPage.adminInfo"/>
        </p>
    </c:if>
    <textArea id="chatWindow" rows="10" style="width: 44em;margin: 15px" readonly></textArea>
</div>
<div>
    <input type="text" id="chatInput" style="width: 40em;margin: 15px"/>
    <input id="sendBtn" type="button" class="btn btn-primary" onclick="sendMessage()" value="<fmt:message key="button.send"/>">
</div>


<script>lang = "${userLocale.language}"</script>

</body>
</html>
