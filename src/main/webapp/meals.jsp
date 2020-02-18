<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Meals</h2>
<table border="1">
    <thead>
    <tr>
        <th>Дата/Время</th>
        <th>Описание</th>
        <th>Калории</th>
        <th>Редактировать</th>
        <th>Удалить</th>
    </tr>
    </thead>
    <tbody>
    <td><a href="meals?action=new">new meal</a></td>
    <c:forEach var="meal" items="${requestScope.meals}">
        <tr style="background-color:${!meal.excess ? "green" : "red"}">
            <td> ${meal.dateTime} </td>
            <td> ${meal.description} </td>
            <td> ${meal.calories} </td>
            <td><a href="meals?action=update&id=${meal.id}">update</a></td>
            <td><a href="meals?action=delete&id=${meal.id}">delete</a></td>
        </tr>

    </c:forEach>

    </tbody>

</table>


</body>
</html>
