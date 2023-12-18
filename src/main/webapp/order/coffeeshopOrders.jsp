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

        .filter-table-container {
            display: flex;
            align-items: center;
            margin-right: 10px;
        }

        .checkbox-group {
            display: flex;
            flex-direction: column;
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
        #originalTableHTML {
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

        .highlighted-text {
            font-size: 20px;
        }

        .rate-button-form {
            border: none;
            background-color: transparent;
            cursor: pointer;
        }
    </style>

    <script>
        let selectedOrderCoffeeshopId = ${selectedOrderCoffeeshop.id};
        let selectedCoffeeshopId = ${selectedCoffeeshop.id};

        function resetTableOrder() {
            const table = document.querySelector('table');
            table.innerHTML = originalTableHTML;
            const currentURL = window.location.href;
            if (currentURL.includes('sort=by_total')) {
                const baseURL = currentURL.split('&')[0];
                window.location.href = baseURL;
            }
        }

        document.addEventListener('DOMContentLoaded', function () {
            const searchInput = document.getElementById('searchQuery');
            const searchTypeSelect = document.getElementById('searchType');
            const tableRows = document.querySelectorAll('table tr');
            const originalTable = document.getElementById('originalTableHTML');
            originalTableHTML = originalTable.innerHTML;

        });
        function applyFilters() {
            const statusFilters = Array.from(document.querySelectorAll('input[name="statusFilter"]:checked')).map(input => input.value);
            const paymentFilters = Array.from(document.querySelectorAll('input[name="paymentFilter"]:checked')).map(input => input.value);
            const tableRows = document.querySelectorAll('table tr');
            tableRows.forEach(function (row, index) {
                if (index === 0) return;
                const statusCellValue = row.cells[3].textContent.trim();
                const paymentCellValue = row.cells[4].textContent.trim();
                const matcheStatus = statusFilters.length === 0 || statusFilters.includes(statusCellValue);
                const matchesPayment = paymentFilters.length === 0 || paymentFilters.includes(paymentCellValue);

                if (matcheStatus && matchesPayment) {
                    row.style.display = '';
                } else {
                    row.style.display = 'none';
                }
            });
        }

        function clearFilters() {
            document.getElementById('filterForm').reset();
            applyFilters();
        }
    </script>
</head>
<body>
<h1>Coffeeshop Orders</h1>
<c:if test="${selectedCoffeeshop ne null}">
<c:if test="${selectedOrderCoffeeshop ne null}">
    <h2>Selected Coffeeshop: ${selectedOrderCoffeeshop.cust_name} </h2>
    <div class="container">
        <div class="button-container">
            <button onclick="window.location.href='AddOrderCoffeeshopServlet?coffeeshopId=${selectedCoffeeshop.id}&customerId=${selectedOrderCoffeeshop.id}'">Add Order</button>
            <input type='button' value='Reset Table' onclick='resetTableOrder()'>
            <button onclick="window.location.href='coffeeshop'">Coffeeshop Table</button>
            <button onclick="window.location.href='CustomerCoffeeshop?coffeeshopId=${selectedCoffeeshop.id}'">Customer ${selectedCoffeeshop.name}</button>
        </div>
        <div class="table-row">
            <div class="filter-table-container">
            <form action="orderCoffeeshop" method="post" id="filterForm">
                <div class="checkbox-group">
                    <b class="highlighted-text">Filters:</b>
                    <span>Status:</span>
                    <input type="checkbox" id="statusFilter1" name="statusFilter" value='Ready'>
                    <label for="statusFilter1">Ready</label>
                    <input type="checkbox" id="statusFilter2" name="statusFilter" value='Not ready'>
                    <label for="statusFilter2">Not ready</label>
                    <span>Payment method:</span>
                    <input type="checkbox" id="paymentFilter1" name="paymentFilter" value='Card'>
                    <label for="paymentFilter1">Card</label>
                    <input type="checkbox" id="paymentFilter2" name="paymentFilter" value='Cash'>
                    <label for="paymentFilter2">Cash</label>
                    <input type='button' value='Apply Filters' onclick='applyFilters();'>
                    <input type='reset' id = "reset" value='Clear Filters' onclick='clearFilters();'>
                </div>
            </form>
            </div>
            <div class="table-container">
                <table id="originalTableHTML" border="1">
                    <tr>
                        <th>Order Date</th>
                        <th>Items</th>
                        <th>Total Amount</th>
                        <th>Status</th>
                        <th>Payment Method</th>
                        <th colspan="2">Action</th>
                    </tr>
                    <c:forEach var="order" items="${orders}">
                        <tr>
                            <td><c:out value="${order.getOrder_date()}"/></td>
                            <td>
                                <c:forEach var="item" items="${order.items}">
                                    ${item.items}<br/>
                                </c:forEach>
                            </td>
                            <fmt:setLocale value="en_US"/>
                            <td><fmt:formatNumber value="${order.getTotal_amount()}" type="currency"/></td>
                            <td><c:out value="${order.status ? 'Ready' : 'Not ready'}"/></td>
                            <td><c:out value="${order.payment_method ? 'Card' : 'Cash'}"/></td>
                            <td>
                                <form action="DeleteOrderCoffeeshopServlet" method="post" class="rate-button-form">
                                    <input type='hidden' name='coffeeshopId' value='${selectedCoffeeshop.id}'>
                                    <input type='hidden' name='customerId' value='${selectedOrderCoffeeshop.id}'>
                                    <input type="hidden" name="orderId" value="${order.id}">
                                    <input type="submit" value="Delete" onclick="return confirm('Are you sure you want to delete this order?');" class="form-button">
                                </form>
                            </td>
                            <td>
                                <form action="EditOrderCoffeeshopServlet" method="get" class="rate-button-form">
                                    <input type='hidden' name='coffeeshopId' value='${selectedCoffeeshop.id}'>
                                    <input type='hidden' name='customerId' value='${selectedOrderCoffeeshop.id}'>
                                    <input type="hidden" name="orderId" value="${order.id}">
                                    <input type="submit" value="Edit" class="form-button">
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </div>
</c:if>
</c:if>
</body>
</html>