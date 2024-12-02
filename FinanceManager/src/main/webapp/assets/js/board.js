"use strict";

import { submitGet } from './global.js';

const loadOverview = (data) => {

    var overview = data.overview;
    var totalIncome = overview.totalIncome;
    var totalExpense = overview.totalExpense;
    var currentBalance = overview.currentBalance;

    document.getElementById('total-income').textContent = `R$ ${totalIncome.toFixed(2)}`;
    document.getElementById('total-expense').textContent = `R$ ${totalExpense.toFixed(2)}`;
    document.getElementById('current-balance').textContent = `R$ ${currentBalance.toFixed(2)}`;
}

const loadExpenseStatisticsByCategory = (data) => {

    var categories = data.categories;
    var colors = ['#ff5733', '#ffc107', '#28a745', '#17a2b8', '#6f42c1', '#e83e8c']; 

    var chartData = {
        labels: categories.map(c => c.category), 
        datasets: [
            {
                backgroundColor: colors.slice(0, categories.length),
                borderWidth: 0,
                data: categories.map(c => c.amount) 
            }
        ]
    };

    const chartOptions = {
        responsive: true,
        maintainAspectRatio: false,
        plugins: {
            legend: {
                display: false
            },
            tooltip: {
                callbacks: {
                    label: function (context) {
                        const label = context.label || '';
                        const value = context.raw || 0;
                        return `${label}: R$ ${value.toFixed(2)}`;
                    }
                }
            }
        },
        cutout: '70%'
    };

    const ctx = document.getElementById('expense-statistics-by-category').getContext('2d');
    new Chart(ctx, {
        type: 'doughnut',
        data: chartData,
        options: chartOptions
    });

    const total = document.getElementById('total-expenses-by-mounth');
    const h6Element = total.querySelector('h6');

    h6Element.textContent = `R$ ${categories.reduce((acc, c) => acc + c.amount, 0).toFixed(2)}`;
}

const loadMonthlyBalance = (data) => {
    var monthlyBalance = data.monthlyBalance;

    const totalIncome = monthlyBalance.totalIncome;
    const totalExpense = monthlyBalance.totalExpense;
    const currentBalance = monthlyBalance.currentBalance;

    const barCtx = document.getElementById('monthly-balance').getContext('2d');
    new Chart(barCtx, {
        type: 'bar',
        data: {
            labels: ['Saldo Atual', 'Receitas', 'Despesas'],
            datasets: [{
                data: [currentBalance, totalIncome, totalExpense],
                backgroundColor: ['#0079f8', '#28a745', '#dc3545'],
                borderRadius: 10,
                borderSkipped: false
            }]
        },
        options: {
            responsive: true,
            maintainAspectRatio: false,
            plugins: {
                legend: { display: false }
            },
            scales: {
                x: {
                    grid: {
                        display: true,
                        color: 'rgba(0, 0, 0, 0.1)',
                        lineWidth: 1
                    }
                },
                y: {
                    beginAtZero: true,
                    grid: {
                        display: true,
                        color: 'rgba(0, 0, 0, 0.1)',
                        lineWidth: 1
                    }
                }
            }
        }
    });
}

const loadData = async () => {

    var data = await submitGet('/board');


    loadOverview(data);
    
    if(data.categories.length === 0) {
        document.getElementById('expense-statistics-by-category').parentElement.innerHTML = 
        `<div class="d-flex justify-content-center align-items-center text-center" style="height: 100%;">
            <div>
                <h5 class="text-muted">Nenhuma despesa cadastrada</h5>
                <h6 class="text-muted">Cadastre uma despesa para que o gráfico de despesas por categoria seja exibido.</h6>
            </div>
        </div>`;
    }
    else {
        document.getElementById('total-expenses-by-mounth').parentElement.style.display = 'block';
        loadExpenseStatisticsByCategory(data);
    }

    loadMonthlyBalance(data);
}

document.addEventListener("DOMContentLoaded", loadData);
