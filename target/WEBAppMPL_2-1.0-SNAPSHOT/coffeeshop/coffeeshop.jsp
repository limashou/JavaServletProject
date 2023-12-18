<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Coffeeshops</title>
    <style>
        .container {
            display: flex;
            flex-direction: column;
            align-items: center;
        }

        .search-container{
            text-align: left;
            margin-right: 70% ;
            margin-bottom: 10px;
        }
        .button-container{
            margin-right: 84%;
            margin-bottom: 10px;
            text-align: left;

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
        .search-container label, .search-container input{
            font-size: 18px;
            font-weight: bold;
        }
        .search-container select {
            font-size: 16px;
        }

        .button-container button, .button-container input[type="button"] {
            font-size: 16px;
            padding: 5px 15px;
        }
        button,
        input[type="button"], #reset {
            background-color: lightgray;
            color: black;
            border: none;
            padding: 5px 10px;
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

        #originalTable th {
            background-color: #f2f2f2;
        }
        .table-row {
            display: flex;
            align-items: flex-start;
            width: 100%;
        }

        .table-container {
            width: 80%;
        }
        .highlighted-text {
            font-size: 20px;
        }
        .rate-button-form {
            border: none;
            background-color: transparent;
            cursor: pointer;
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
                    const column = row.querySelector('td:nth-child(' + (searchType === 'name' ? '1' : '2') + ')');
                    if (column) {
                        const cellValue = column.textContent.toLowerCase();
                        if (cellValue.includes(searchValue)) {
                            row.style.display = '';
                        } else {
                            row.style.display = 'none';
                        }
                    }
                });
            });
        });

        let isSortAscending = true;
        function sortTableByName() {
            const table = document.querySelector('table');
            const rows = Array.from(table.querySelectorAll('tr'));
            const header = rows.shift();
            rows.sort((a, b) => {
                const nameA = a.cells[0].textContent.trim().toLowerCase();
                const nameB = b.cells[0].textContent.trim().toLowerCase();
                return nameA.localeCompare(nameB);
            });
            if (!isSortAscending) {
                rows.reverse();
            }
            table.innerHTML = '';
            table.appendChild(header);
            rows.forEach(row => table.appendChild(row));
            isSortAscending = !isSortAscending;
        }

        function resetTable() {
            const table = document.querySelector('table');
            table.innerHTML = originalTableHTML;
            isSortAscending = true;
            const currentURL = window.location.href;
            if (currentURL.includes('sort=by_rate')) {
                const baseURL = currentURL.split('?')[0];
                window.location.href = baseURL;
            }
        }

        const form = document.querySelector('form');
        form.addEventListener('submit', function (event) {
            event.preventDefault();
            window.location.href = 'coffeeshop';
        });

        function confirmDelete() {
            var result = confirm("Are you sure you want to delete this coffeeshop?");
            if (result) {
                return true;
            } else {
                return false;
            }
        }

        function applyFilters() {
            const rateFilters = Array.from(document.querySelectorAll('input[name="rateFilter"]:checked')).map(input => input.value);
            const workingHoursFilters = Array.from(document.querySelectorAll('input[name="workingHoursFilter"]:checked')).map(input => input.value);
            const tableRows = document.querySelectorAll('table tr');
            tableRows.forEach(function (row, index) {
                if (index === 0) return;
                const rateCellValue = row.cells[2].textContent.trim();
                const workingHoursCellValue = row.cells[3].getAttribute('data-working-hours');
                const matchesRate = rateFilters.length === 0 || rateFilters.includes(rateCellValue);
                const matchesWorkingHours = workingHoursFilters.length === 0 || workingHoursFilters.includes(workingHoursCellValue);
                if (matchesRate && matchesWorkingHours) {
                    row.style.display = '';
                } else {
                    row.style.display = 'none';
                }
            });
        }

        function clearFilters() {
            document.getElementById('filterForm').reset();
            applyFilters();
            resetTable()
        }
    </script>
</head>
<body>
<h1>Coffeeshops List</h1>
<div class="container">
    <div class="search-container">
        <form action='coffeeshop' method='post'>
            <select id='searchType' name='searchType' style='display: none;'>
                <option value='name'>Name</option>
            </select>
            <label for='searchQuery'>Search:</label>
            <input type='text' id='searchQuery' name='searchQuery'>
            <input type='submit' value='Search'>
        </form>
    </div>
    <div class="button-container">
        <button onclick="window.location.href='AddCoffeeshopServlet'">Add</button>
        <input type='button' value='Reset Table' onclick='resetTable();'>
    </div>
    <div class="table-row">
    <div class="filter-table-container">
        <form action='coffeeshop' method='post' id='filterForm'>
            <div class="checkbox-group">
                <b class="highlighted-text">Filters:</b>
                <span>Rate:</span>
                <label for='rateFilter1'><input type='checkbox' id='rateFilter1' name='rateFilter' value='1'>1</label>
                <label for='rateFilter2'><input type='checkbox' id='rateFilter2' name='rateFilter' value='2'>2</label>
                <label for='rateFilter3'><input type='checkbox' id='rateFilter3' name='rateFilter' value='3'>3</label>
                <label for='rateFilter4'><input type='checkbox' id='rateFilter4' name='rateFilter' value='4'>4</label>
                <label for='rateFilter5'><input type='checkbox' id='rateFilter5' name='rateFilter' value='5'>5</label>
                <span>Working Hours:</span>
                <label for='workingHoursFilter1'><input type='checkbox' id='workingHoursFilter1' name='workingHoursFilter' value='1'>08:00-22:00</label>
                <label for='workingHoursFilter2'><input type='checkbox' id='workingHoursFilter2' name='workingHoursFilter' value='2'>10:00-24:00</label>
                <label for='workingHoursFilter3'><input type='checkbox' id='workingHoursFilter3' name='workingHoursFilter' value='3'>00:00-23:59</label>
                <input type='button' value='Apply Filters' onclick='applyFilters();'>
                <input type='reset' id = "reset" value='Clear Filters' onclick='clearFilters();'>
            </div>
        </form>
    </div>
        <div class="table-container">
            <table id="originalTable" border="1">
                <tr>
                    <th><input type='submit' value='Name' onclick='sortTableByName();' style='border: none; background-color: transparent; cursor: pointer;' class="form-button"></th>
                    <th>Address</th>
                    <th><form action="coffeeshop" method="GET" class="rate-button-form">
                        <input type="hidden" name="sort"  value="by_rate">
                        <button class="form-button">Rate</button>
                    </form></th>
                    <th>Working Hours</th>
                    <th>Email</th>
                    <th colspan="4">Action</th>
                </tr>
                <c:forEach var="shop" items="${coffeeshops}">
                    <tr>
                        <td><c:out value="${shop.name}"></c:out></td>
                        <td><c:out value="${shop.address}"></c:out></td>
                        <td><c:out value="${shop.rate}"></c:out></td>
                        <c:choose>
                            <c:when test="${shop.working_hours eq 1}">
                                <td data-working-hours="1">08:00-22:00</td>
                            </c:when>
                            <c:when test="${shop.working_hours eq 2}">
                                <td data-working-hours="2">10:00-24:00</td>
                            </c:when>
                            <c:when test="${shop.working_hours eq 3}">
                                <td data-working-hours="3">00:00-23:59</td>
                            </c:when>
                        </c:choose>
                        <td><c:out value="${shop.email}"></c:out></td>
                        <td>
                            <form action="DeleteCoffeeshopServlet" method="post" class="rate-button-form">
                                <input type="hidden" name="coffeeshopId" value="${shop.id}">
                                <input type="submit" value="Delete" class="form-button" onclick="return confirmDelete();">
                            </form>
                        </td>
                        <td>
                            <form action="EditCoffeeshopServlet" method="get" class="rate-button-form">
                                <input type="hidden" name="coffeeshopId" value="${shop.id}">
                                <input type="submit" value="Edit" class="form-button">
                            </form>
                        </td>
                        <td>
                            <form action="CustomerCoffeeshop" method="get" class="rate-button-form">
                                <input type="hidden" name="coffeeshopId" value="${shop.id}">
                                <input type="submit" value="Customer" class="form-button">
                            </form>
                        </td>
                        <td>
                            <form action="Menu" method="get" class="rate-button-form">
                                <input type="hidden" name="coffeeshopId" value="${shop.id}">
                                <input type="submit" value="Menu" class="form-button">
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</div>
</body>
</html>
