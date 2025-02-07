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
    <title>IFinance | Histórico Financeiro</title>
</head>

<body>

    <navbar-header username="${sessionScope.user.fullName}"></navbar-header>

    <main>

        <h1 class="mx-2 my-b text-header">Histórico de Registros Financeiros</h1>
        
        <div class="mx-2 mt-4">
            <table id="financial-records-table" class="table table-striped table-hover overflow-x-auto">
                <thead>
                    <tr class="text-center">
                        <th>Descrição</th>
                        <th>Valor</th>
                        <th>Data</th>
                        <th>Tipo</th>
                        <th>Categoria</th>
                        <th>Ação</th>
                    </tr>
                </thead>
                <tbody id="financial-records-body">
    
                </tbody>
            </table>
        </div>

        <p id="no-records-message" class="text-center" style="display: none;">Nenhum registro financeiro cadastrado</p>

    </main>


    <!-- Modal Financial Record -->
    <div class="modal fade" id="modalFinancialRecord" tabindex="-1" aria-labelledby="modalFinancialRecordLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fw-bold fs-5" id="modalFinancialRecordLabel">Remover Registro</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <p>Deseja <span class="fw-bold">Remover</span> este Registro?</p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Fechar</button>
                <button type="button" id="remove-task-btn" class="btn btn-danger btn-submit action-button" data-bs-dismiss="modal">Remover</button>
            </div>
            </div>
        </div>
    </div>


    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/components/navbar/navbar.js"></script>
    <script src="assets/js/history.js" type="module"></script>
</body>

</html>