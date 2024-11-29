<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fn" uri="jakarta.tags.functions"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="pt-BR">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/boxicons@latest/css/boxicons.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/styles.css">
    <title>IFinance - Cadastro de Registro financeiro</title>
</head>
<body>
    <form action="financialRecord" method="post" id="financialRecordForm">

        <c:choose>
            <c:when test="${userCategories != null}">
                <div class="mb-2">
                    <label for="categorySelect">Selecionar categoria</label>
                    <select class="form-select" name="categorySelect" id="categorySelect" required="required">
                        <option value="" selected>Selecione</option>
                        <c:forEach var="category" items="${userCategories}" varStatus="index">
                            <option value="${category.id}">${category.name} - ${category.id}</option>
                        </c:forEach>
                    </select>
                </div>
            </c:when>
        </c:choose>

            <input type="hidden" id="categoryId" name="categoryId" />
            <input type="hidden" id="transactionType" name="transactionType" value="${transactionType}" />

            <div class="mb-2">
                <label for="amount">Valor</label>
                <input type="number" step="0.01" name="amount" id="amount"
                       class="form-control" required="required"
                       value="">
            </div>

            <div class="mb-2">
                <label for="description">Observação*</label>
                <textarea name="description" id="description"
                          class="form-control mb-3"></textarea>
            </div>

            <div class="mb-2">
                <button type="submit" id="saveButton" class="btn btn-primary">Salvar</button>
            </div>

    </form>


    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/financialRecord.js"></script>
</body>
</html>