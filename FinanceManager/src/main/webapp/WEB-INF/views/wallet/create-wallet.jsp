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
    <title>IFinance - Nova Carteira</title>
</head>
<body>

    <div class="forms-page-header">
        <i class='bx bx-wallet nav_logo-icon'></i>
        <h1>IFinance</h1>
    </div>

    <form action="controller" method="post" class="form">

        <input type="hidden" name="context" value="wallet">
        <input type="hidden" name="action" value="create">

		<c:if test="${error != null}">
			<div class="alert alert-danger alert-dismissible fade show"
				 role="alert">
					${error}
				<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
			</div>
		</c:if>

        <div class="form-header">Nova Carteira</div>

        <div class="form-field">
            <label for="name">Nome:</label>
            <input type="text" name="name" id="name" placeholder="Digite o nome da carteira" required="required" class="form-control">
        </div>

        <!--Goal amount-->
        <div class="form-field">
            <label for="goal-amount">Meta:</label>
            <input placeholder="Digite sua meta:" type="number" step="0.01" name="goal-amount" id="goal-amount" required="required" value="" class="form-control">
        </div>
        
        <!--Description-->
        <div class="form-field">
            <label for="description">Descrição:</label>
            <input type="text" name="description" id="description" placeholder="Descrição" required="required" class="form-control">
        </div>

        <div class="form-submit">
            <button type="submit">Criar</button>
        </div>

    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</body>
</html>
