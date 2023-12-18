<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Error</title>
</head>
<body>
<h1>Error</h1>
<p>${errorMessage}</p>

<c:if test="${errorMessage eq 'Invalid order date format. Please use the HH:mm format for order date.'}">
  <a href='EditOrderCoffeeshopServlet'>Try again</a>
</c:if>

<c:if test="${errorMessage eq 'Coffeeshop not found with the specified id.'}">
  <a href="DeleteOrderCoffeeshopServlet"> Try again</a>
</c:if>
<c:if test="${errorMessage eq 'Coffeeshop have order u cant detele this.'}">
  <button onclick="window.location.href='coffeeshop'">Add</button>
</c:if>
<c:if test="${errorMessage eq 'Invalid data or order date format. Please use the HH:mm format for order date.'}">
  <a href="AddOrderCoffeeshopServlet"> Add</a>
</c:if>
</body>
</html>
