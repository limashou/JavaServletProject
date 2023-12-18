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
    <title>Coffeeshop Menu</title>
</head>
<body>
<h1>Coffeeshop Customer</h1>
<c:if test="${selectedCoffeeshop ne null}">
    <h2>Selected Coffeeshop: ${selectedCoffeeshop.name}  (${selectedCoffeeshop.address})</h2>
    <div class="container">
        <div class="table-row">
            <div class="table-container">
                <table id="originalTableHTML" border="1">
                    <tr>
                        <th>Items</th>
                        <th>Cost</th>
                    </tr>
                    <c:forEach var="order" items="${customer}">
                        <tr>
                            <td><c:out value="${order.items}"/></td>
                            <td><c:out value="${order.cost}"/></td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </div>
</c:if>
</body>
</html>