<%--
  Created by IntelliJ IDEA.
  User: maksympol
  Date: 13/10/2023
  Time: 22:12
  To change this template use File | Settings | File Templates.
--%>
<%--
  Created by IntelliJ IDEA.
  User: maksympol
  Date: 30/09/2023
  Time: 21:04
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Coffeeshop Orders</title>
    <style>
        .container {
            display: flex;
            flex-direction: column;
            align-items: center;
        }

        .search-container {
            text-align: left;
            margin-right: 70%;
            margin-bottom: 10px;
        }

        .button-container {
            margin-right: 75%;
            margin-bottom: 10px;
            text-align: left;
        }

        .table-row {
            display: flex;
            align-items: flex-start;
            width: 100%;
        }
        .checkbox-group label {
            display: block;
        }

        .search-container label,
        .search-container input {
            font-size: 18px;
            font-weight: bold;
        }

        .search-container select {
            font-size: 16px;
        }

        .button-container button,
        .button-container input[type="button"],
        .form-button {
            font-size: 12px;
            padding: 3px 8px;
        }

        .form-button {
            border: none;
            color: black;
            background-color: transparent;
            cursor: pointer;
            padding: 0;
            font: inherit;
            text-decoration: underline;
        }

        button,
        input[type="button"], #reset {
            background-color: lightgray;
            color: black;
            border: none;
            padding: 3px 5px;
            cursor: pointer;
            margin-right: 10px;
            border-radius: 5px;
        }
        #reset{margin-top: 5px}
        #originalTable {
            width: 100%;
            border-collapse: collapse;
        }

        #originalTable th,
        #originalTable td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }
        .table-container {
            width: 80%;
        }
        #originalTable th {
            background-color: #f2f2f2;
        }

        .rate-button-form {
            border: none;
            background-color: transparent;
            cursor: pointer;
        }
    </style>

    <script>
        document.addEventListener('DOMContentLoaded', function () {
            const searchInput = document.getElementById('searchQuery');
            const searchTypeSelect = document.getElementById('searchType');
            const tableRows = document.querySelectorAll('table tr');
            const originalTable = document.getElementById('originalTable');
            originalTableHTML = originalTable.innerHTML;
            searchInput.addEventListener('input', function () {
                const searchValue = searchInput.value.toLowerCase();
                const searchType = searchTypeSelect.value;
                tableRows.forEach(function (row, index) {
                    if (index === 0) return;
                    const column = row.querySelector('td:nth-child(' + (searchType === 'cust_name' ? '1' : '2') + ')');
                    if (column) {
                        const cellValue = column.textContent.toLowerCase();
                        if (cellValue.includes(searchValue) || searchValue.length === 0) {
                            row.style.display = '';
                        } else {
                            row.style.display = 'none';
                        }
                    }
                });
            });
        });
    </script>
</head>
<body>
<h1>Coffeeshop Customer</h1>
<c:if test="${selectedCoffeeshop ne null}">
    <h2>Selected Coffeeshop: ${selectedCoffeeshop.name}  (${selectedCoffeeshop.address})</h2>
    <div class="container">
        <div class="search-container">
            <form action='CustomerCoffeeshop' method='post' onsubmit="return false;">
                <select id='searchType' name='searchType' style='display: none;'>
                    <option value='cust_name' >Name</option>
                </select>
                <label for='searchQuery'>Search:</label>
                <input type='text' id='searchQuery' name='searchQuery'>
                <input type='submit' value='Search'>
            </form>
        </div>
        <div class="button-container">
            <button onclick="window.location.href='AddCustomer?coffeeshopId=${selectedCoffeeshop.id}'">Add Customer</button>
            <button onclick="window.location.href='coffeeshop'">Coffeeshop Table</button>
        </div>
        <div class="table-row">
            <div class="table-container">
                <table id="originalTable" border="1">
                    <tr>
                        <th>Name</th>
                        <th>Email</th>
                        <th>Phone</th>
                        <th colspan="3">Action</th>
                    </tr>
                    <c:forEach var="order" items="${customer}">
                        <tr>
                            <td><c:out value="${order.cust_name}"/></td>
                            <td><c:out value="${order.cust_email}"/></td>
                            <td><c:out value="${order.cust_phone}"/></td>
                            <td>
                                <form action="DeleteCustomer" method="post" class="rate-button-form">
                                    <input type='hidden' name='coffeeshopId' value='${selectedCoffeeshop.id}'>
                                    <input type="hidden" name="customerId" value="${order.id}">
                                    <input type="submit" value="Delete" onclick="return confirm('Are you sure you want to delete this order?');" class="form-button">
                                </form>
                            </td>
                            <td>
                                <form action="EditCustomer" method="get" class="rate-button-form">
                                    <input type='hidden' name='coffeeshopId' value='${selectedCoffeeshop.id}'>
                                    <input type="hidden" name="customerId" value="${order.id}">
                                    <input type="submit" value="Edit" class="form-button">
                                </form>
                            </td>
                            <td>
                            <form action="orderCoffeeshop" method="get" class="rate-button-form">
                                <input type='hidden' name='coffeeshopId' value='${selectedCoffeeshop.id}'>
                                <input type="hidden" name="customerId" value="${order.id}">
                                <input type="submit" value="Order" class="form-button">
                            </form>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </div>
</c:if>
</body>
</html>