<%--
  Created by IntelliJ IDEA.
  User: Slizh Anton
  Date: 18.08.2021
  Time: 22:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page isErrorPage="true" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html><title>Error Page</title>
<body>
Request from ${pageContext.errorData.requestURI} is failed
<br/>
Servlet name: ${pageContext.errorData.servletName}
<br/>
Status code: ${pageContext.errorData.statusCode}
<br/>
Exception: ${pageContext.exception}
<br/>
<hr/>
Message from exception: ${pageContext.exception.message}
</body>
</html>