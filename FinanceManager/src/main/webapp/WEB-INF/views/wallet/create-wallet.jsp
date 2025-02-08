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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/styles.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/forms.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/components/navbar/navbar.css">
    <title>IFinance | Nova Carteira</title>
</head>

<body>

    <navbar-header theme="secondary" username="${sessionScope.user.fullName}"></navbar-header>

    <div class="container-form">
        <form action="controller" method="post" class="form">

            <div class="form-header">Carteira</div>

            <input type="hidden" name="context" value="wallets">
            <input type="hidden" name="action" value="create">

            <c:if test="${error != null}">
                <div class="alert alert-danger alert-dismissible fade show" role="alert">
                    ${error}
                    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                </div>
            </c:if>

            <div class="form-field form-floating">
                <input type="text" name="name" id="name" placeholder="Categoria" required="required"
                    class="form-control">
                <label for="name">Nome</label>
            </div>

            <div class="form-field form-floating">
                <input placeholder="Meta (R$)" type="number" step="0.01" name="goal-amount" id="goal-amount" required="required"
                    value="" class="form-control">
                <label for="goal-amount">Meta (R$)</label>
            </div>

            <div class="form-field form-floating">
                <input type="text" name="description" id="description" placeholder="Descrição" required="required"
                    class="form-control">
                <label for="description">Descrição</label>
            </div>

            <div class="form-submit">
                <button type="submit">Criar Carteira</button>
            </div>
        </form>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/components/navbar/navbar.js"></script>
</body>

</html>