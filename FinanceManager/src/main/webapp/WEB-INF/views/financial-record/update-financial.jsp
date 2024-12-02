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
    <title>IFinance - Atualização de Despesa</title>
</head>
<body>

    <form action="update-financial-record" method="post">

        <div class="form-header">Atualização Despesa</div>

        <!--Categories-->
        <div class="form-field">

            <label for="categorySelect">Categoria:</label>
            <select name="categorySelect" id="categorySelect" class="form-select">
                <c:if test="${listCategories = null}">
                    <c:forEach var="category" items="${listCategories}" varStatus="index">
                        <option value="${category.id}"
                            <c:if test="${category.name == fr.category.name}">
                                selected="selected"
                            </c:if>
                        >${category.name}</option>
                    </c:forEach>  
                </c:if>
            </select>
        </div>

        <!--Amount-->
        <div class="form-field">
            <label for="amount">Valor:</label>
            <input placeholder="valor do registro" type="number" step="0.01" name="amount" id="amount" required="required" value="" class="form-control">
        </div>
        
        <!--Description-->
        <div class="form-field">
            <label for="description">Descrição:</label>
            <input type="text" name="description" id="description" placeholder="Descrição" required="required" class="form-control">
        </div>

        <div class="form-submit">
            <button type="submit">Atualizar</button>
        </div>

    </form>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script></body>
</html>
