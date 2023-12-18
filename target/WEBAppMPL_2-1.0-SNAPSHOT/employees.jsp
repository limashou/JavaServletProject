<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<!-- https://htmlbook.ru/content/tablitsa-s-ramkoy -->
<style type="text/css">
    TABLE {
        width: 500px; /* Ширина таблицы */
        border: 2px solid black; /* Рамка вокруг таблицы */
    }

    TD, TH {
        padding: 3px; /* Поля вокруг содержимого ячеек */
        border: 2px solid black; /* Рамка вокруг таблицы */
    }

    TH {
        text-align: left; /* Выравнивание по левому краю */
        background: white; /* Цвет фона */
        color: blue; /* Цвет текста */
    }
</style>
<head>
    <title>DDE Task1_4 - JSP+EL+JSTL</title>
</head>
<body>
<h1>Hello, JSP+EL+JSTL!</h1>
<br>
<table>
    <tr>
        <th>Name</th>
        <th>Birthday</th>
        <th>Gender</th>
        <th>Salary</th>
        <th>Prog Lang</th>
    </tr>
    <c:forEach var="empl" items="${mylist}">
        <tr>
            <td><c:out value="${empl.name}"></c:out></td>
            <td><c:out value="${empl.getBirthdayAsString()}"></c:out></td>
                <%-- Комірка із заливкою в залежності від полу --%>
            <c:if test="${empl.gender == true}">
                <td>male</td>
            </c:if>
            <c:if test="${empl.gender == false}">
                <td style="background:antiquewhite">female</td>
            </c:if>
                <%-- Комірка із заливкою в залежності від полу --%>
            <td>
                <fmt:setLocale value="uk_UA"/>
                <fmt:formatNumber value="${empl.salary}" type="currency"/>
            </td>
            <td><c:out value="${empl.programLanguage}"></c:out></td>
        </tr>
        <td>
            <form action="edit_employee" method="post">
                <input type="hidden" name="id_empl" value="${empl.id}">
                <input type="submit" value="Редагувати">
            </form>
        </td>
        <td>
            <form action="student_parents">
                <input type="hidden" name="id" value="${stud.id}">
                <input class="buttonfortable" type="submit" value="Батьки">
            </form>
        </td>
    </c:forEach>
</table>
<br>
<br>
<a href="employees">To START</a>
</body>
</html>