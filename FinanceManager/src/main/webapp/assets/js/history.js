"use strict";

import { submitGet } from './global.js';

const tableBody = document.getElementById('financial-records-body');
const noRecordsMessage = document.getElementById('no-records-message');

const loadData = async() => {
    try {

        var financialRecords = await submitGet("/controller?context=financial-record&action=history");

        if (financialRecords.length === 0) {
            noRecordsMessage.style.display = 'block';
            return;
        }

        financialRecords.forEach(record => {
            const row = document.createElement('tr');
            row.classList.add("text-center"); 
            row.id = record.id;

            row.innerHTML = `
                <td>${truncateDescription(record.description)}</td>
                <td>R$ ${parseFloat(record.amount).toFixed(2)}</td>
                <td>${formatDate(record.transactionDate)}</td>
                <td>${formatTransactionType(record.transactionType)}</td>
                <td>${formatCategoryName(record.categoryName)}</td>
                <td>
                    <a href="update-financial-record?id=${record.id}"><i class='bx bx-edit text-warning display-6'></i></a>
                    <a data-bs-toggle="modal" data-bs-target="#modalFinancialRecord" style="cursor: pointer;"><i class='bx bx-trash text-danger display-6'></i></a>
                </td>
            `;
            tableBody.appendChild(row);
        });

    } catch (error) {
        console.error('Erro:', error);
        alert('Não foi possível carregar os registros financeiros.');
    }
}

const modalFinancialRecord = document.querySelector("#modalFinancialRecord");

modalFinancialRecord.addEventListener("shown.bs.modal", (eventModal) => {
    let btn = eventModal.target.querySelector(".action-button");
    btn.onclick = () => {
        const id = eventModal.relatedTarget.closest("tr").id;
        window.location.href = `controller?context=financial-record&action=delete&id=${id}`;
    };
});

const formatCategoryName = (categoryName) => {
    if (categoryName.trim() === '') {
        return '--';
    }
    return categoryName;
}

const formatTransactionType = (transactionType) => {
    if (transactionType.trim().toUpperCase() === 'EXPENSE') {
        return "<i class='bx bxs-down-arrow-circle text-danger display-6'></i>";
    }
    return "<i class='bx bxs-up-arrow-circle text-success display-6'></i>";
}

const formatDate = (dateString) => {
    const [year, month, day] = dateString.split('-');
    return `${day}/${month}/${year}`;
}

const truncateDescription = (description) => {
    if (description.length > 45) {
        return `${description.substring(0, 45)}... <a href="#" onclick="alert('${description}')">Ler Mais</a>`;
    }
    return description;
}

document.addEventListener('DOMContentLoaded', loadData);