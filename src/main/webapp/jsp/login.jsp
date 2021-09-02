<%--
  Created by IntelliJ IDEA.
  User: Slizh Anton
  Date: 30.08.2021
  Time: 20:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setLocale value="ru_RU" scope="session"/>
<fmt:setBundle basename="prop.locale"/>
<html>
<head>
    <title><fmt:message key="login.title"/></title>
</head>
<body>
<form action="controller" method="get">
    <input type="hidden" name="command" value="login"/>
    <fmt:message key="login.email"/><input type="text" name="email"><br>
    <fmt:message key="login.password"/><input type="text" name="password"><br>
    <input type="submit" value="<fmt:message key="login.login-button"/>">
</form>


</body>
</html>
