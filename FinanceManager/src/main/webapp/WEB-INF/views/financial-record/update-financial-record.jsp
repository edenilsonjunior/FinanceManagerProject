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
    <title>IFinance | Atualizar Registro</title>
</head>

<body>

    <navbar-header theme="secondary" username="${sessionScope.user.fullName}"></navbar-header>

    <div class="container-form">
        <form action="controller" method="post" id="update-financial-record" class="form">

            <input type="hidden" name="context" value="financial-record">
            <input type="hidden" name="action" value="update">
            <input type="hidden" id="financial-record-id" name="id">

            <div class="form-header">Atualizar</div>

            <!--Categories-->
            <div class="form-field">
                <select name="categoryId" id="categoryId" class="form-select">
                    <option value="" selected disabled>Selecionar categoria</option>
                </select>
            </div>

            <!--Amount-->
            <div class="form-field form-floating">
                <input placeholder="Valor (R$)" type="number" step="0.01" name="amount" id="amount" required="required"
                    value="" class="form-control">
                <label for="amount">Valor (R$)</label>
            </div>

            <!--Description-->
            <div class="form-field form-floating">
                <input type="text" name="description" id="description" placeholder="Descrição" required="required"
                    class="form-control">
                <label for="description">Descrição</label>
            </div>

            <div class="form-submit">
                <button type="submit">Atualizar Registro</button>
            </div>

        </form>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <script src="${pageContext.request.contextPath}/components/navbar/navbar.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/update-financial-record.js" type="module"></script>
</body>

</html>