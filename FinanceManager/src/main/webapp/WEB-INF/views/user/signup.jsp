<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fn" uri="jakarta.tags.functions"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<!doctype html>
<html lang="pt-BR">
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script></body>
<title>IFinance - Página de Cadastro</title>
</head>
<body>
	<div class="container">
		<div class="col-lg-6 offset-lg-3 col-sm-12">

			<c:if test="${signupErrorMessage != null}">
				<div class="alert alert-danger alert-dismissible fade show"
					 role="alert">
						${signupErrorMessage}
					<button type="button" class="btn-close" data-bs-dismiss="alert"
							aria-label="Close"></button>
				</div>
			</c:if>

			<form action="signup" method="post" id="form1">
				<h1 class="text-center">Cadastre-se</h1>

				<div class="mb-2">
					<label for="full-name">Nome completo*</label> <input type="text"
						name="full-name" id="full-name" class="form-control" minlength="3"
						maxlength="50" required="required"> <span id="0"></span>
				</div>

				<div class="mb-2">
					<label for="email">E-mail*</label> <input type="email"
					    name="email" id="email" class="form-control"
					    required="required"> <span id="1"></span>
				</div>

				<div class="mb-2">
					<label for="password">Senha*</label> <input type="password"
						name="password" id="password" class="form-control" minlength="3"
						maxlength="50" required="required"> <span id="2"></span>
				</div>

				<div class="mb-2">
					<label for="confirmPassword">Confirmação de Senha*</label> <input type="password"
						name="confirmPassword" id="confirmPassword" class="form-control" minlength="3"
						maxlength="50" required="required"> <span id="3"></span>
				</div>

				<div class="mb-2">
                    <label for="birth-date">Data de Nascimento*</label> <input type="date"
                        name="birth-date" id="birth-date" class="form-control"
                        required="required"> <span id="4"></span>
                </div>

				<div class="mb-2">
					<button type="submit" class="btn btn-primary">Salvar</button>
				</div>
			</form>
		</div>
	</div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script></body>
	<script src="../../../assets/js/signup.js"></script>
</body>
</html>