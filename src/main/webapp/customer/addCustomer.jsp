<%--
  Created by IntelliJ IDEA.
  User: maksympol
  Date: 13/10/2023
  Time: 22:35
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
    input[type="email"] {
      width: 30%;
      padding: 5px;
      margin-bottom: 10px;
      border: 1px solid lightgray;
      border-radius: 15px;
    }

  </style>
</head>
<body>
<h1>Add Order</h1>
<c:if test="${selectedCoffeeshop ne null}">
  <h2>Selected Coffeeshop: ${selectedCoffeeshop.name}  (${selectedCoffeeshop.address})</h2>
  <form action="AddCustomer" method="post">
    <input type="hidden" name="coffeeshopId" value="${selectedCoffeeshop.id}">
    <label for="name">Name:</label>
    <input type="text" id="name" name="name" required><br>
    <label for="email">Email:</label>
    <input type="text" id="email" name="email" ><br>
    <label for="phone">Phone:</label>
    <input type="text" id="phone" name="phone" ><br>
    <input type="submit" value="Add Customer">
    <button onclick="window.location.href='orderCoffeeshop?coffeeshopId=${selectedCoffeeshop.id}'">Back</button>
  </form>
</c:if>
</body>
</html>
