"use strict";

import {contextPath, submitGet } from './global.js';


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

    total.textContent = `R$ ${categories.reduce((acc, c) => acc + c.amount, 0).toFixed(2)}`;
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
                    grid: { display: false }
                },
                y: {
                    beginAtZero: true,
                    grid: { display: false }
                }
            }
        }
    });
}

const loadOverview = (data) => {

    var overview = data.overview;
    var totalIncome = overview.totalIncome;
    var totalExpense = overview.totalExpense;
    var currentBalance = overview.currentBalance;

    document.getElementById('total-income').textContent = `R$ ${totalIncome.toFixed(2)}`;
    document.getElementById('total-expense').textContent = `R$ ${totalExpense.toFixed(2)}`;
    document.getElementById('current-balance').textContent = `R$ ${currentBalance.toFixed(2)}`;
}


document.addEventListener("DOMContentLoaded", async () => {

    var data = await submitGet('/board');
    console.log(data);

    loadOverview(data);
    loadExpenseStatisticsByCategory(data);
    loadMonthlyBalance(data);
 });