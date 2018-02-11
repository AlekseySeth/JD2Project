<%--
  Created by a.shestovsky
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
    <title>Products</title>
</head>
<body>
    <div>
        <form action="${pageContext.request.contextPath}/products" method="post">
            <input type="text" name="title" placeholder="введите название продукта">
            <label for="category">Категория</label>
            <select name="category" id="category">
                <option value="all">--</option>
                <c:forEach var="category" items="${requestScope.categories}">
                    <option value="${category.id}" ${sessionScope.selectedCategory eq category.id ? "selected" : ""}>${category.name}</option>
                </c:forEach>
            </select>
            <label for="brand">Брэнд</label>
            <c:forEach var="brand" items="${requestScope.brands}">
                <input id="brand" name="brand" type="checkbox" value="${brand.id}" ${sessionScope.product.brand.id eq brand.id ? "selected" : ""}>${brand.name}
            </c:forEach>
            <br>
            <label for="page">Количество на странице</label>
            <select name="productsOnPage" id="page">
                <c:forEach var="qtyOnPage" items="${requestScope.pagesList}">
                    <option value="${qtyOnPage}" ${sessionScope.selectedProductsOnPage eq qtyOnPage ? "selected" : ""}>${qtyOnPage}</option>
                </c:forEach>
            </select>
            <label for="page">Страница</label>
            <input style="width: 50px" type="number" name="page" id="page" value="${sessionScope.selectedPage eq "" ? 1 : sessionScope.selectedPage}">
            <button type="submit">Поиск</button>
        </form>
    </div>
    <div>
        <table>
            <th>Id</th>
            <th>Наименование</th>
            <th>Цена</th>
            <th>Категория</th>
            <th>Брэнд</th>
            <c:forEach var="product" items="${sessionScope.products}">
                <tr>
                    <td>${product.id}</td>
                    <td>${product.title}</td>
                    <td>${product.price}</td>
                    <td>${product.category.name}</td>
                    <td>${product.brand.name}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>
