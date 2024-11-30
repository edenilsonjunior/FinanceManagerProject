<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fn" uri="jakarta.tags.functions"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<!doctype html>
<html lang="pt-BR">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/boxicons@latest/css/boxicons.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/styles.css">
    <title>IFinance - Atualização de Despesa</title>
</head>
<body>
    <form action="update-financial-record" method="post">
        <h1 class="text-center">Atualização Despesa</h1>

        <c:choose>
            <c:when test="${listCategories != null}">
                <div class="mb-2">
                    <label for="categorySelect">Selecionar categoria</label>
                    <select class="form-select" name="categorySelect" id="categorySelect" required="required">
                        <c:forEach var="category" items="${listCategories}" varStatus="index">
                            <option
                                <c:if test="${category.name == fr.category.name}">
                                    selected="selected"
                                </c:if>
                                >${category.name}</option>
                        </c:forEach>
                    </select>
                </div>
            </c:when>
        </c:choose>


        <div class="mb-2">
            <label for="amount">Amount</label>
            <input type="text" name="amount" id="amount" class="form-control mb-3" required="required" value="${fr.amount}">
        </div>

        <div class="mb-2">
            <label for="description">Description</label>
            <input type="text" name="description" id="description" class="form-control mb-3" required="required" value="${fr.description}">
        </div>

        <div class="mb-3">
            <button type="submit" class="btn btn-primary">Atualizar</button>
        </div>
    </form>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script></body>
</html>
