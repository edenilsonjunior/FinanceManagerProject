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
	<title>IFinance | SignUp</title>
</head>

<body>

    <div class="d-flex">
        <i class='bx bx-wallet display-5 text-white me-2'></i>
        <h1 class="mx-2 my-b text-header text-white">IFinance</h1>
    </div>

	<div class="container-form">
		<form action="controller" method="post" class="form" id="signup-form">

			<input type="hidden" name="context" value="users">
			<input type="hidden" name="action" value="signup">

			<c:if test="${error != null}">
				<div class="alert alert-danger alert-dismissible fade show" role="alert">
					${error}
					<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
				</div>
			</c:if>

			<div class="form-header">SignUp</div>

			<div class="form-field">
				<input type="text" name="full-name" id="full-name" placeholder="Nome" required="required"
					class="form-control">
				<span id="full-name-error" class="text-danger"></span>
			</div>

			<div class="form-field">
				<input type="date" name="birth-date" id="birth-date" required="required" class="form-control">
				<span id="birth-date-error" class="text-danger"></span>
			</div>

			<div class="form-field">
				<input type="email" name="email" id="email" placeholder="E-mail" required="required"
					class="form-control">
				<span id="email-error" class="text-danger"></span>
			</div>

			<div class="form-field">
				<input type="password" name="password" id="password" placeholder="Senha" required="required"
					class="form-control">
				<span id="password-error" class="text-danger"></span>
			</div>

			<div class="form-field">
				<input type="password" name="confirmPassword" id="confirmPassword" placeholder="Confirmação de Senha"
					required="required" class="form-control">
			</div>

			<div class="form-submit">
				<button type="submit">Cadastrar</button>
			</div>

	</div>
	</div>

	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/assets/js/signup.js"></script>
</body>

</html>