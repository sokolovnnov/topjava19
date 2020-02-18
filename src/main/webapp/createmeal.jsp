<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<b>New</b>


<jsp:useBean id="meal" type="ru.javawebinar.topjava.model.Meal" scope="request"/>
    <form method="post" action="meals">
    <input type="hidden" name="id" value="${meal.id}">
    <input type="text" name="description" value="${meal.description}" size="40">
    <input type="number" name="calories" value="${meal.calories}">
    <input type="datetime-local" name="dateTime" value="${meal.dateTime}">
    <button type="submit">OK</button>
        <button type="button" onclick="window.history.back()">Reset</button>
    </form>
    </body>
</html>