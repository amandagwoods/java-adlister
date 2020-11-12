<%--
  Created by IntelliJ IDEA.
  User: Amanda
  Date: 11/12/20
  Time: 9:57 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
        <jsp:include page="/WEB-INF/partials/head.jsp">
            <jsp:param name="title" value="Register for our site!"/>
        </jsp:include>
</head>
<body>
<jsp:include page="/WEB-INF/partials/navbar.jsp";</jsp:include>

    <div class="container">
        <h1>Please fill in your information.</h1>
        <c:if test="${sessionScope.registerError !=null}">
            <div class="alert alert-danger" role="alert">
                ${requestScope.registerError}
            </div>
        </c:if>
        <form action="/register" method="post">
            <div class ="form-group">
                <label for="username">Usernaem</label>
                <input type="text" id="username" class="form-control" name="username">
            </div>
            <div class ="form-group">
                <label for="email">Email</label>
                <input type="text" id="email" class="form-control" name="email">
            </div>
            <div class ="form-group">
                <label for="password">Password</label>
                <input type="password" id="password" class="form-control" name="password">
            </div>
            <div class ="form-group">
                <label for="confirm">Confirm Password</label>
                <input type="password" id="confirm" class="form-control" name="confirm">
            </div>

            <input type="submit" class="btn btn-primary btn-block">
        </form>
    </div>
</body>
</html>
