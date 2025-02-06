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

            row.innerHTML = `
                <td>${record.id}</td>
                <td>${record.transactionType}</td>
                <td>R$ ${parseFloat(record.amount).toFixed(2)}</td>
                <td>${formatDate(record.transactionDate)}</td>
                <td>${record.categoryName}</td>
                <td>${truncateDescription(record.description)}</td>
                <td><a href="update-financial-record?id=${record.id}" class="btn btn-warning btn-sm">Editar</a></td>
                <td><a href="controller?context=financial-record&action=delete&id=${record.id}" class="btn btn-danger btn-sm">Excluir</button></td>
            `;
            tableBody.appendChild(row);
        });
    } catch (error) {
        console.error('Erro:', error);
        alert('Não foi possível carregar os registros financeiros.');
    }
}

const formatDate =(dateString)=> {
    const [year, month, day] = dateString.split('-');
    return `${day}/${month}/${year}`;
}

const truncateDescription = (description) =>{
    if (description.length > 45) {
        return `${description.substring(0, 45)}... <a href="#" onclick="alert('${description}')">Ler Mais</a>`;
    }
    return description;
}

function deleteRecord(id) {
    alert(`Excluir registro com ID: ${id}`);
}

document.addEventListener('DOMContentLoaded', loadData);