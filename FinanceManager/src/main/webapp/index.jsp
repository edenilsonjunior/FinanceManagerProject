<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fn" uri="jakarta.tags.functions"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<!DOCTYPE html>
<html lang="pt-BR">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/boxicons@latest/css/boxicons.min.css">
    <link rel="stylesheet" href="assets/css/styles.css">
    <link rel="stylesheet" href="components/navbar/navbar.css">
    <title>IFinance | DashBoard</title>
</head>

<body>

    <navbar-header username="${sessionScope.user.fullName}"></navbar-header>

    <main>

        <c:if test="${error != null}">
            <div id="error-message" class="alert alert-danger" role="alert">${error}</div>
        </c:if>

        <c:if test="${success != null}">
            <div id="success-message" class="alert alert-success" role="alert">${success}</div>
        </c:if>

        <h1 class="mx-2 text-header">DashBoard</h1>

        <div id="overview" class="row justify-content-center mb-3">

            <div class="card-overview saldo mx-3 my-3">
                <div class="icon">🏦</div>
                <h5>Saldo Atual</h5>
                <p id="current-balance">R$ 0,00</p>
            </div>

            <div class="card-overview receitas mx-3 my-3">
                <div class="icon">⬆️</div>
                <h5>Receitas</h5>
                <p id="total-income">R$ 0,00</p>
            </div>

            <div class="card-overview despesas mx-3 my-3">
                <div class="icon">⬇️</div>
                <h5>Despesas</h5>
                <p id="total-expense">R$ 0,00</p>
            </div>

        </div>

        <div class="row">

            <div class="col-6 ">
                <div class="col-12 d-flex flex-column align-items-start">
                    <h5 class="mb-3">Despesas por categoria</h5>
                    <div class="card shadow-sm p-3 w-100" style="border-radius: 20px; height: 300px;">
                        <div class="chart-container ">
                            <canvas id="expense-statistics-by-category"></canvas>
                        </div>
                        <div id="total-expenses-by-mounth" class="text-center mt-3" style="display: none;">
                            <h6 class="mb-0">R$ 0,00</h6>
                            <small class="text-muted">Total</small>
                        </div>
                    </div>
                </div>
            </div>

            <div class="col-6">
                <div class="col-12 d-flex flex-column align-items-start">
                    <h5 class="mb-3">Balanço Mensal</h5>
                    <div class="card shadow-sm p-3 w-100" style="border-radius: 20px; height: 300px;">
                        <div class="chart-container ">
                            <canvas id="monthly-balance"></canvas>
                        </div>
                        <div class="text-center ">
                            <a href="history" class="text-primary">VER MAIS</a>
                        </div>
                    </div>
                </div>
            </div>

        </div>

    </main>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <script src="assets/js/board.js" type="module"></script>
    <script src="components/navbar/navbar.js"></script>
</body>

</html>