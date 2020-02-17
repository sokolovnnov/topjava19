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

    <c:forEach var="mealTo" items="${requestScope.meals}">
        <tr style="background-color:${mealTo.excess ? "green" : "red"}">
            <td> ${mealTo.dateTime} </td>
            <td> ${mealTo.description} </td>
            <td> ${mealTo.calories} </td>
            <td><a href="meals?action=update&id=${mealTo.id}">update</a></td>
            <td><a href="meals?action=delete&id=${mealTo.id}">delete</a></td>
        </tr>

    </c:forEach>
    <td><a href="meals?action=new">new meal</a></td>
    <td>

    <form action="${pageContext.request.contextPath}/meals" method="post">
        <input type="submit" name="button" value="new"/>
    </form>
    </td>
    </tbody>

</table>


</body>
</html>
