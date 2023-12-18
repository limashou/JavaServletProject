<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Coffeeshop</title>
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
<h1>Edit Coffeeshop</h1>
<form action="EditCoffeeshopServlet" method="post">
    <input type="hidden" name="coffeeshopId" value="${selectedCoffeeshop.id}">
    <label for="name">Name:</label>
    <input type="text" id="name" name="name" value="${selectedCoffeeshop.name}" required><br>
    <label for="address">Address:</label>
    <input type="text" id="address" name="address" value="${selectedCoffeeshop.address}" required><br>
    <label for="rate">Rate:</label>
    <select id="rate" name="rate" required>
        <option value="1" ${selectedCoffeeshop.rate == 1 ? "selected" : ""}>1</option>
        <option value="2" ${selectedCoffeeshop.rate == 2 ? "selected" : ""}>2</option>
        <option value="3" ${selectedCoffeeshop.rate == 3 ? "selected" : ""}>3</option>
        <option value="4" ${selectedCoffeeshop.rate == 4 ? "selected" : ""}>4</option>
        <option value="5" ${selectedCoffeeshop.rate == 5 ? "selected" : ""}>5</option>
    </select><br>
    <label for="working_hours">Working Hours:</label>
    <select id="working_hours" name="working_hours" required>
        <option value="1" ${selectedCoffeeshop.working_hours == 1 ? "selected" : ""}>08:00-22:00</option>
        <option value="2" ${selectedCoffeeshop.working_hours == 2 ? "selected" : ""}>10:00-24:00</option>
        <option value="3" ${selectedCoffeeshop.working_hours == 3 ? "selected" : ""}>00:00-23:59</option>
    </select><br>
    <label for="email">Email:</label>
    <input type="email" id="email" name="email" value="${selectedCoffeeshop.email}" required><br>
    <input type="submit" value="Save">
    <button onclick="window.location.href='coffeeshop'">Back</button>

</form>
</body>
</html>
