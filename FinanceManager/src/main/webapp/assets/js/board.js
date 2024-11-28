"use strict";

document.addEventListener("DOMContentLoaded", function (event) {

    const showNavbar = (toggleId, navId, bodyId, headerId) => {
        const toggle = document.getElementById(toggleId),
            nav = document.getElementById(navId),
            bodypd = document.getElementById(bodyId),
            headerpd = document.getElementById(headerId)

        // Validate that all variables exist
        if (toggle && nav && bodypd && headerpd) {
            toggle.addEventListener('click', () => {
                // show navbar
                nav.classList.toggle('show')
                // change icon
                toggle.classList.toggle('bx-x')
                // add padding to body
                bodypd.classList.toggle('body-pd')
                // add padding to header
                headerpd.classList.toggle('body-pd')
            })
        }
    }

    showNavbar('header-toggle', 'nav-bar', 'body-pd', 'header')

    /*===== LINK ACTIVE =====*/
    const linkColor = document.querySelectorAll('.nav_link')

    function colorLink() {
        if (linkColor) {
            linkColor.forEach(l => l.classList.remove('active'))
            this.classList.add('active')
        }
    }
    linkColor.forEach(l => l.addEventListener('click', colorLink))

    // Your code to run since DOM is loaded and ready
});


// load graphic

document.addEventListener("DOMContentLoaded", getExpenseStatisticsByCategory);

function getExpenseStatisticsByCategory() {
    // Dados das despesas por categoria
    const datalist = [
        { "category": "Alimentação", "amount": 300 },
        { "category": "Transporte", "amount": 150 },
        { "category": "Saúde", "amount": 100 },
        { "category": "Educação", "amount": 200 },
        { "category": "Lazer", "amount": 50 },
        { "category": "Outros", "amount": 70 }
    ];

    setChartDonut(datalist);
}

function setChartDonut(datalist) {
    var colors = ['#ff5733', '#ffc107', '#28a745', '#17a2b8', '#6f42c1', '#e83e8c']; // Cores para as categorias

    var chartData = {
        labels: datalist.map(data => data.category), // Categorias
        datasets: [
            {
                backgroundColor: colors.slice(0, datalist.length),
                borderWidth: 0,
                data: datalist.map(data => data.amount) // Valores das despesas
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

    const ctx = document.getElementById('chDonut1').getContext('2d');
    new Chart(ctx, {
        type: 'doughnut',
        data: chartData,
        options: chartOptions
    });

}


document.addEventListener("DOMContentLoaded", function () {
    const receitas = 6000; // Valor total de receitas
    const despesas = 5350; // Valor total de despesas
    const balanco = receitas - despesas; // Calcula o balanço

    // Configuração do gráfico de barras
    const barCtx = document.getElementById('barChart').getContext('2d');
    new Chart(barCtx, {
        type: 'bar',
        data: {
            labels: ['Receitas', 'Despesas'],
            datasets: [{
                data: [receitas, despesas],
                backgroundColor: ['#28a745', '#dc3545'], // Verde para receitas, vermelho para despesas
                borderRadius: 10,
                borderSkipped: false
            }]
        },
        options: {
            responsive: true,
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
});