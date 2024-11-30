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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/forms.css">
    <title>IFinance - Novo Registro</title>
</head>
<body>	

    <div class="forms-page-header">
        <i class='bx bx-wallet nav_logo-icon'></i>
        <h1>IFinance</h1>
    </div>

    <form action="create-financial-record" method="post" class="form">

		<c:if test="${financialRecordErrorMessage != null}">
			<div class="alert alert-danger alert-dismissible fade show" role="alert">
                ${financialRecordErrorMessage}
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
			</div>
		</c:if>

        <div class="form-header">
            <c:if test="${userCategories == null}">Nova Receita</c:if>
            <c:if test="${userCategories != null}">Nova Despesa</c:if>    
        </div>

        <!--Categories-->
        <c:choose>
            <c:when test="${userCategories != null}">
                <div class="form-field">
                    <select name="categoryId" id="categorySelect" class="form-select">
                        <option value="" selected disabled>Selecione uma categoria</option>
                        <c:forEach var="category" items="${userCategories}" varStatus="index">
                            <option value="${category.id}">${category.name}</option>
                        </c:forEach>
                    </select>
                </div>
            </c:when>
        </c:choose>

        <!--Amount-->
        <div class="form-field">
            <input placeholder="valor do registro" type="number" step="0.5" name="amount" id="amount" required="required" value="" class="form-control">
        </div>
        
        <!--Description-->
        <div class="form-field">
            <input type="text" name="description" id="description" placeholder="Descrição" required="required" class="form-control">
        </div>
        
        
        <!--Transaction Type (hydden, from the previous page)--> 
        <input type="hidden" id="transactionType" name="transactionType" value="${transactionType}" />

        <div class="form-submit">
            <button type="submit">Criar</button>
        </div>
    </form>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</body>
</html>