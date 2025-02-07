<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fn" uri="jakarta.tags.functions"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<!DOCTYPE html>
<html lang="pt-BR">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/boxicons@latest/css/boxicons.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@11/dist/sweetalert2.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/styles.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/forms.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/components/navbar/navbar.css">
    <title>IFinance | Nova Movimentação</title>
</head>

<body>

    <navbar-header theme="secondary" username="${sessionScope.user.fullName}"></navbar-header>

    <div class="container-form">
        <form action="controller" method="POST" class="form">

            <div class="form-header">Movimentação</div>
    
            <input type="hidden" name="context" value="financial-record">
            <input type="hidden" name="action" value="create">
    
            <!--Error Message-->
            <c:if test="${error != null}">
                <div class="alert alert-danger alert-dismissible fade show" role="alert">
                    ${error}
                    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                </div>
            </c:if>
    
    
            <!--Switch from Expense to Income-->
            <div class="form-check form-switch">
                <label class="form-check-label" for="transactionSwitch">Marcar como Despesa</label>
                <input class="form-check-input" type="checkbox" id="transactionSwitch" />
            </div>
    
    
            <!--Categories-->
            <div class="form-field">
                <select name="categoryId" id="categoryId" class="form-select">
                    <option value="" selected disabled>Selecionar categoria</option>
                </select>
            </div>
    
            <!--Amount-->
            <div class="form-field">
                <input placeholder="Valor (R$)" type="number" step="0.01" name="amount" id="amount" required="required"
                    value="" class="form-control">
            </div>
    
            <!--Description-->
            <div class="form-field">
                <input type="text" name="description" id="description" placeholder="Descrição" required="required"
                    class="form-control">
            </div>
    
            <!--Transaction Type (hydden, from the previous page)-->
            <input type="hidden" id="transactionType" name="transactionType" value="${transactionType}" />
    
            <div class="form-submit">
                <button type="submit">Criar Movimentação</button>
            </div>
        </form>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <script src="${pageContext.request.contextPath}/components/navbar/navbar.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/financialRecord.js" type="module"></script>
</body>

</html>