"use strict";

import { submitGet } from './global.js';

const tableBody = document.getElementById('wallets-body');
const noRecordsMessage = document.getElementById('no-records-message');
const modalTitle = document.getElementById('modalTitle');
const modalBody = document.getElementById('modalBody');
const detailsModal = new bootstrap.Modal(document.getElementById('detailsModal'));

const loadData = async () => {
    try {
        var walletsData = await submitGet("/controller?context=wallets&action=wallets");

        if (walletsData.length === 0) {
            noRecordsMessage.style.display = 'block';
            return;
        }

        var overview = walletsData.overview;

        document.getElementById('total').textContent = overview.totalBalance;
        document.getElementById('most-expense-wallet').textContent = overview.highestBalanceWallet.value.name;
        document.getElementById('month-transactions').textContent = overview.monthlyTransactionsCount;

        walletsData.wallets.forEach(record => {
            const row = document.createElement('tr');
            row.classList.add("text-center");
            row.id = record.id;

            row.innerHTML = `
                <td>${record.name}</td>
                <td>R$ ${parseFloat(record.goalAmount).toFixed(2)}</td>
                <td>R$ ${parseFloat(record.currentBalance).toFixed(2)}</td>
                <td>${formatLastTransaction(record.lastTransaction)}</td>
                <td>
                    <a href="#" class="details-link" data-id="${record.id}"><i class='bx bx-info-circle display-6'></i></a>
                </td>
            `;
            tableBody.appendChild(row);
        });

        document.querySelectorAll('.details-link').forEach(link => {
            link.addEventListener('click', async (event) => {
                event.preventDefault();
                const walletId = event.currentTarget.getAttribute('data-id');
                await loadWalletDetails(walletId);
            });
        });

    } catch (error) {
        console.error('Erro:', error);
        alert('Não foi possível carregar os registros financeiros.');
    }
};

const formatLastTransaction = (lastTransaction) => {
    if (lastTransaction.value == null) {
        return 'Sem transações';
    }

    const { type, value } = lastTransaction.value;

    return type === 'INCOME' 
        ? `<span class="text-success">R$ ${parseFloat(value).toFixed(2)}</span>`
        : `<span class="text-danger">R$ ${parseFloat(value).toFixed(2)}</span>`;
};

const loadWalletDetails = async (walletId) => {
    try {
        const details = await submitGet(`/controller?context=wallets&action=details&wallet-id=${walletId}`);
        modalTitle.textContent = details.name;
        modalBody.innerHTML = `
            <p><strong>Meta:</strong> R$ ${details.goalAmount.toFixed(2)}</p>
            <p><strong>Saldo Atual:</strong> R$ ${details.currentBalance.toFixed(2)}</p>
            <p><strong>Descrição:</strong> ${details.description}</p>
            <h5>Transações:</h5>
            <div class="list-group ${details.transactions.length > 10 ? 'scrollable-list' : ''}">
                ${details.transactions.map(t => `
                    <div class="list-group-item">
                        <strong>${t.transactionDate}</strong> - 
                        <span class="${t.transactionType === 'INCOME' ? 'text-success' : 'text-danger'}">
                            R$ ${t.amount.toFixed(2)}
                        </span>
                    </div>
                `).join('')}
            </div>
        `;

        document.getElementById("create-wallet-transaction").addEventListener("click", function() {
            window.location.href = `create-wallet-transaction?wallet-id=${walletId}`;
        });
        
        document.getElementById("update-wallet").addEventListener("click", function() {
            window.location.href = `update-wallet?wallet-id=${walletId}`;
        });
        
        document.getElementById("delete-wallet").addEventListener("click", function() {
            window.location.href = `/controller?context=wallets&action=delete&wallet-id=${walletId}`;
        });

        detailsModal.show();
    } catch (error) {
        console.error('Erro ao carregar detalhes:', error);
        alert('Não foi possível carregar os detalhes da carteira.');
    }
};

document.addEventListener('DOMContentLoaded', loadData);
