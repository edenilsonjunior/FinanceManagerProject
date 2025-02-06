<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fn" uri="jakarta.tags.functions"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="pt-BR">
    <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/boxicons@latest/css/boxicons.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/styles.css">
    <title>IFinance - Todas as carteiras</title>
</head>

<body id="body-pd">
    
    <jsp:include page="/navbar" />
    <main class="bg-light">
        <h1 class="mx-2 my-5">Todas as suas carteiras</h1>
        <table id="wallets-table" class="table table-bordered table-striped table-hover table-responsive">
            <thead>
                <tr>
                    <th>Id</th>
                    <th>Nome</th>
                    <th>Meta</th>
                    <th>Balança atual</th>
                    <th>Descrição</th>
                    <th>Ver detalhes</th>
                    <th>Editar</th>
                    <th>Excluir</th>
                </tr>
            </thead>
            <tbody id="wallets-body">

            </tbody>
        </table>
        <p id="no-wallets-message" class="text-center" style="display: none;">Nenhuma carteira cadastrada.</p>
    </main>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="assets/js/navbar.js" type="module"></script>
    <script src="assets/js/history.js" type="module"></script>
</body>
</html>