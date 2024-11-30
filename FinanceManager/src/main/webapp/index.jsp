<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Financial Records</title>

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/boxicons@latest/css/boxicons.min.css">
    <link rel="stylesheet" href="assets/css/styles.css">
</head>

<body id="body-pd">

<jsp:include page="WEB-INF/include/navbar.jsp" />

<main class="bg-light">

    <h1 class="mx-2 my-5">DashBoard</h1>

    <div id="overview" class="row justify-content-center mb-5">

        <div class="card-overview saldo mx-3 my-3">
            <div class="icon">🏦</div>
            <h5>Saldo Atual</h5>
            <p id="current-balance">R$ 1.650,00</p>
        </div>

        <div class="card-overview receitas mx-3 my-3">
            <div class="icon">⬆️</div>
            <h5>Receitas</h5>
            <p id="total-income">R$ 6.000,00</p>
        </div>

        <div class="card-overview despesas mx-3 my-3">
            <div class="icon">⬇️</div>
            <h5>Despesas</h5>
            <p id="total-expense">R$ 5.350,00</p>
        </div>

        <div class="card-overview cartao mx-3 my-3">
            <div class="icon">💳</div>
            <h5>Cartão de Crédito</h5>
            <p id="total-credit-card">🚧 Em construção... 🚧</p>
        </div>
    </div>

    <div class="row">

        <!--Left side-->
        <div class="col-6 ">
            <div class="col-12 d-flex flex-column align-items-start"> 
                <h5 class="mb-3">Despesas por categoria</h5> 
                <div class="card shadow-sm p-3 w-100" style="border-radius: 20px; height: 300px;"> 
                    <div class="chart-container " >
                        <canvas id="expense-statistics-by-category"></canvas>
                    </div>
                    <div class="text-center mt-3">
                        <h6 id="total-expenses-by-mounth" class="mb-0"></h6>
                        <small class="text-muted" >Total</small>
                    </div>
                </div>
            </div>
        </div>

        <!--Right side-->
        <div class="col-6">
            <div class="col-12 d-flex flex-column align-items-start">
                <h5 class="mb-3">Balanço Mensal</h5>
                <div class="card shadow-sm p-3 w-100" style="border-radius: 20px; height: 300px;">
                    <div class="chart-container " >
                            <canvas id="monthly-balance"></canvas>
                        </div>
                    <div class="text-center ">
                        <a href="#" class="text-primary">VER MAIS</a>
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
</body>

</html>
