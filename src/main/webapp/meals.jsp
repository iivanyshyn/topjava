<%@ page import="ru.javawebinar.topjava.util.TimeUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
    <title>Meal list</title>
    <style>
        .normal {color: green;}
        .exceed {color: red;}
    </style>
</head>
<body>
<h2><a href="index.html">Home</a></h2>
<h2>Meal list</h2>
<table border="1" cellpadding="8" cellspacing="0">
    <thead>
    <tr>
        <th>Date</th>
        <th>Description</th>
        <th>Calories</th>

    </tr>
    </thead>
    <tbody>
    <c:forEach items="${meals}" var="meal">
        <jsp:useBean id="meal" scope="page" type="ru.javawebinar.topjava.model.MealWithExceed"/>
        <tr class="${meal.exceed ? 'exceed' : 'normal'}">
            <%--<td><fmt:formatDate pattern="yyyy-MMM-dd" value="${meal.dateTime}" /></td>--%>
            <td><%=TimeUtil.toString(meal.getDateTime())%></td>
            <%--<td><c:out value="${meal.dateTime}" /></td>--%>
            <td><c:out value="${meal.description}" /></td>
            <td><c:out value="${meal.calories}" /></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
