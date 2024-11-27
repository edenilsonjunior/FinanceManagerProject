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

<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<link href="css/styles.css" rel="stylesheet">
<title>IFinance - Página de Login</title>
</head>
<body>

	<div class="container">
		<div class="col-lg-4 offset-lg-4 col-sm-12">
			<c:choose>
				<c:when test="${signupSuccessMessage != null}">
					<div class="alert alert-success alert-dismissible fade show"
						role="alert">
							${signupSuccessMessage}
						<button type="button" class="btn-close" data-bs-dismiss="alert"
							aria-label="Close"></button>
					</div>
				</c:when>
				<c:when test="${loginErrorMessage != null}">
					<div class="alert alert-danger alert-dismissible fade show"
						role="alert">
							${loginErrorMessage}
						<button type="button" class="btn-close" data-bs-dismiss="alert"
							aria-label="Close"></button>
					</div>
				</c:when>
			</c:choose>

			<form action="login" method="post">
					<h1 class="text-center">Login</h1>

					<div class="input-group mb-3">
						<span class="input-group-text">
							<img alt="Campo para informar o e-mail"
								src="img/envelope.svg" width="32"
								height="32">
						</span>
						<input type="email" name="email" id="email"
							placeholder="E-mail" required="required"
							class="form-control">
					</div>

					<div class="input-group mb-3">
						<span class="input-group-text">
							<img alt="Campo para informar o cpf"
								src="img/file-lock.svg" width="32"
								height="32">
						</span>
						<input type="password" name="password" id="password"
							placeholder="Senha" required="required"
							class="form-control">
					</div>

					<div class="mb-3">
						<button type="submit" class="btn btn-primary">Login</button>
					</div>

					<div class="mb-3">
						<a href="signup"
							id="link" class="btn btn-secondary">Cadastrar</a>
					</div>

			</form>
		</div>
	</div>

    <!-- Option 1: Bootstrap Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>
</html>