<%--
  Created by IntelliJ IDEA.
  User: maksympol
  Date: 01/10/2023
  Time: 01:26
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Order</title>
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
<h1>Add Order</h1>
<c:if test="${selectedCoffeeshop ne null}">
<c:if test="${selectedOrderCoffeeshop ne null}">
    <h2>Selected Coffeeshop: ${selectedOrderCoffeeshop.cust_name}</h2>
    <form action="AddOrderCoffeeshopServlet" method="post">
        <input type="hidden" name="coffeeshopId" value="${selectedCoffeeshop.id}">
        <input type="hidden" name="customerId" value="${selectedOrderCoffeeshop.id}">
        <label for="order_date">Order Date:</label>
        <input type="text" id="order_date" name="order_date" required><br>
        <label>Items:</label>
        <c:forEach var="menu" items="${menuList}">
            <label>
                <input type="checkbox" name="selectedItems" value="${menu.id}">
                    ${menu.items} - $${menu.cost}
            </label>
<%--            <input type="number" name="itemQuantities" value="0" min="0">--%>
        </c:forEach>
        <label for="status">Status:</label>
        <select id="status" name="status" required>
            <option value="true">Ready</option>
            <option value="false">Not Ready</option>
        </select><br>
        <label for="paymentMethod">Payment Method:</label>
        <select id="paymentMethod" name="paymentMethod" required>
            <option value="true">Card</option>
            <option value="false">Cash</option>
        </select><br>
        <input type="submit" value="Add Order">
        <button onclick="window.location.href='orderCoffeeshop?coffeeshopId=${selectedCoffeeshop.id}&customerId=${selectedOrderCoffeeshop.id}'">Back</button>
    </form>
</c:if>
</c:if>
</body>
</html>