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
    <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
            crossorigin="anonymous">
    <link rel="icon" href="img/tecnoif-logo.ico" type="image/x-icon">
    <link href="css/styles.css" rel="stylesheet">
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

    <!-- Option 1: Bootstrap Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    <script src="financialRecord.js"></script>
</body>
</html>