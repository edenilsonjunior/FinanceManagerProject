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
    <title>IFinance | Login</title>
</head>

<body>

    <div class="d-flex">
        <i class='bx bx-wallet display-5 text-white me-2'></i>
        <h1 class="mx-2 my-b text-header text-white">IFinance</h1>
    </div>

    <div class="container-form">
        <form action="controller" method="post" class="form">

            <input type="hidden" name="context" value="users">
            <input type="hidden" name="action" value="login">

            <c:choose>
                <c:when test="${error != null}">
                    <div class="alert alert-danger alert-dismissible fade show" role="alert">
                        ${error}
                        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                    </div>
                </c:when>
            </c:choose>

            <div class="form-header">Login</div>

            <div class="form-field form-floating">
                <input type="email" name="email" id="email" placeholder="E-mail" required="required"
                    class="form-control">
                <label for="email">Email</label>
            </div>

            <div class="form-field form-floating">
                <input type="password" name="password" id="password" placeholder="Senha" required="required"
                    class="form-control">
                <label for="password">Senha</label>
            </div>

            <div class="form-submit">
                <button type="submit">Login</button>
            </div>

            <div id="form-redirect">
                <a href="signup">Não possui login?</a>
            </div>

    </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</body>

</html>