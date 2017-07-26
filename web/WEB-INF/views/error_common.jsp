<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: kss
  Date: 2017-07-26
  Time: 오후 6:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h4>${exception.getMessage()}</h4>
    <ul>
        <c:forEach items="${exception.getStackTrace()}" var="stack">
            <li>${stack.toString()}</li>
        </c:forEach>
    </ul>
</body>
</html>
