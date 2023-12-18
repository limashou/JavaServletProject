<%--
  Created by IntelliJ IDEA.
  User: maksympol
  Date: 13/10/2023
  Time: 22:42
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
<h1>Edit Order</h1>
<c:if test="${selectedCoffeeshop ne null}">
    <form action="EditCustomer" method="post">
        <input type="hidden" name="coffeeshopId" value="${selectedCoffeeshop.id}">
        <input type="hidden" name="customerId" value="${customer.id}">
        <label for="name">Name:</label>
        <input type="text" id="name" name="name" value="${customer.cust_name}" required><br>
        <label for="email">Order Date:</label>
        <input type="text" id="email" name="email" value="${customer.cust_email}" ><br>
        <label for="phone">Phone:</label>
        <input type="text" id="phone" name="phone" value="${customer.cust_phone}" ><br>
        <input type="submit" value="Edit">
        <button onclick="window.location.href='CustomerCoffeeshop?coffeeshopId=${selectedCoffeeshop.id}'">Back</button>
    </form>
</c:if>
</body>
</html>

