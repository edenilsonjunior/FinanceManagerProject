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
    <title>IFinance - Cadastro de categoria</title>
</head>
<body>	

    <div class="forms-page-header">
        <i class='bx bx-wallet nav_logo-icon'></i>
        <h1>IFinance</h1>
    </div>

    <form action="create-category" method="post" class="form">

		<c:if test="${categoryErrorMessage != null}">
			<div class="alert alert-danger alert-dismissible fade show"
				 role="alert">
					${categoryErrorMessage}
				<button type="button" class="btn-close" data-bs-dismiss="alert"
						aria-label="Close"></button>
			</div>
		</c:if>

        <div class="form-header">Nova Categoria</div>
        
        <div class="form-field">
            <label for="name">Categoria:</label>
            <input type="text" name="name" id="name" placeholder="Digite o nome da categoria" required="required" class="form-control">
        </div>

        <div class="form-submit">
            <button type="submit">Criar</button>
        </div>

    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</body>
</html>
