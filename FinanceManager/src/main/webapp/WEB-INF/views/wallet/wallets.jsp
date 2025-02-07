<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fn" uri="jakarta.tags.functions"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="pt-BR">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/boxicons@latest/css/boxicons.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/styles.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/components/navbar/navbar.css">
    <title>IFinance | Carteiras</title>
</head>

<body>

    <navbar-header username="${sessionScope.user.fullName}"></navbar-header>

    <main>

        <h1 class="mx-2 my-b text-header">Carteiras</h1>
        
        <div id="overview" class="row justify-content-center mb-3">

            <div class="card-overview mx-3 mt-3" style="background-color: #6f51f0;">
                <div class="icon"><i class='bx bxs-wallet-alt'></i></div>
                <h5>Saldo Total</h5>
                <p id="total">R$ 0,00</p>
            </div>

            <div class="card-overview mx-3 mt-3" style="background-color: #6f51f0;">
                <div class="icon"><i class='bx bxs-badge-dollar'></i></div>
                <h5>Maior Carteira</h5>
                <p id="most-expense-wallet"></p>
            </div>

            <div class="card-overview mx-3 mt-3" style="background-color: #6f51f0;">
                <div class="icon"><i class='bx bxs-bar-chart-square'></i></div>
                <h5>Transaçoes Mensais</h5>
                <p id="month-transactions">0</p>
            </div>
        </div>

        <div class="mx-2 mt-4">
            <table id="wallets-table" class="table table-striped table-hover overflow-x-scroll">
                <thead>
                    <tr class="text-center">
                        <th>Nome</th>
                        <th>Meta</th>
                        <th>Valor total</th>
                        <th>Ultima transação</th>
                        <th>Detalhes</th>
                    </tr>
                </thead>
                <tbody id="wallets-body">
                </tbody>
            </table>
        </div>

        <p id="no-records-message" class="text-center" style="display: none;">Nenhum registro financeiro cadastrado</p>

    </main>

    <div class="modal fade" id="detailsModal" tabindex="-1" aria-labelledby="detailsModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="modalTitle"></h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body" id="modalBody"></div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary text-nowrap" data-bs-dismiss="modal" id="create-wallet-transaction">Nova Movimentação</button>
                    <button type="button" class="btn btn-warning text-nowrap" data-bs-dismiss="modal" id="update-wallet">Editar</button>
                    <button type="button" class="btn btn-danger text-nowrap" data-bs-dismiss="modal" id="delete-wallet">Excluir</button>
                </div>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/components/navbar/navbar.js"></script>
    <script src="assets/js/wallets.js" type="module"></script>
</body>

</html>