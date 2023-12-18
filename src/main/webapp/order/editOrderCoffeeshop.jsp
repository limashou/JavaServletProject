<%--
  Created by IntelliJ IDEA.
  User: maksympol
  Date: 08/10/2023
  Time: 01:11
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Order</title>
    <style>
        button,
        input[type="button"],
        input[type="submit"] {
            background-color: lightgray;
            color: black;
            border: none;
            padding: 5px 10px;
            cursor: pointer;
            margin-right: 10px;
            font-size: 13px;
            border-radius: 10px;
        }

        button:focus,
        input[type="button"]:focus,
        input[type="submit"]:focus {
            outline: none;
            border-bottom: 2px solid blue;
        }

        label {
            font-weight: bold;
            margin-right: 10px;
        }

        input[type="text"],
        input[type="email"],
        select {
            width: 30%;
            padding: 5px;
            margin-bottom: 10px;
            border: 1px solid lightgray;
            border-radius: 15px;
        }

        select {
            appearance: none;
            background-position: right center;
            background-repeat: no-repeat;
            background-size: 15px;
            padding-right: 20px;
        }
    </style>
</head>
<body>
<h1>Edit Order</h1>
<c:if test="${selectedCoffeeshop ne null}">
<c:if test="${selectedOrderCoffeeshop ne null}">
    <form action="EditOrderCoffeeshopServlet" method="post">
        <input type="hidden" name="coffeeshopId" value="${selectedCoffeeshop.id}">
        <input type="hidden" name="customerId" value="${selectedOrderCoffeeshop.id}">
        <input type="hidden" name="orderId" value="${order.id}">
        <label for="order_date">Order Date:</label>
        <input type="text" id="order_date" name="order_date" value="${order.order_date}" required><br>
        <label>Items:</label>
        <c:forEach var="menu" items="${menuList}">
        <label>
            <input type="checkbox" name="selectedItems" value="${menu.id}">
                ${menu.items} - $${menu.cost}
        </label>
        </c:forEach>
        <label for="status">Status:</label>
        <select id="status" name="status" required>
            <option value="true" ${order.status ? 'selected' : ''}>Ready</option>
            <option value="false" ${!order.status ? 'selected' : ''}>Not ready</option>
        </select><br>
        <label for="payment_method">Payment Method:</label>
        <select id="payment_method" name="payment_method" required>
            <option value="true" ${order.payment_method ? 'selected' : ''}>Card</option>
            <option value="false" ${!order.payment_method ? 'selected' : ''}>Cash</option>
        </select><br>
        <input type="submit" value="Edit">
        <button onclick="window.location.href='orderCoffeeshop?coffeeshopId=${selectedCoffeeshop.id}&customerId=${selectedOrderCoffeeshop.id}'">Back</button>
    </form>
</c:if>
</c:if>
</body>
</html>
