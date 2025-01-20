"use strict";

import { submitGet } from './global.js';

const categoryId = document.getElementById("categoryId");
const transactionSwitch = document.getElementById("transactionSwitch");
const transactionTypeInput = document.getElementById("transactionType");
const categoryField = categoryId.closest(".form-field");
const form = document.querySelector("form");

document.addEventListener("DOMContentLoaded", async () => {

    var data = await submitGet("/controller?context=categories&action=getByUser");

    data.forEach(category => {
            const option = document.createElement("option");
            option.value = category.id;
            option.textContent = category.name;
            categoryId.appendChild(option);
        }
    );

    // Initialize the form state
    function updateForm() {
        if (transactionSwitch.checked) {
            // Mark as Expense
            transactionTypeInput.value = "EXPENSE";
            categoryField.style.display = "block";
        } else {
            // Mark as Income
            transactionTypeInput.value = "INCOME";
            categoryField.style.display = "none";
        }
    }

    form.addEventListener("submit", (event) => {
        if (transactionSwitch.checked) {
            const selectedCategory = categoryId.value;

            if (!selectedCategory) {
                event.preventDefault();

                Swal.fire({
                    icon: 'error',
                    title: 'Categoria não selecionada',
                    text: 'Por favor, selecione uma categoria para registrar a despesa! Caso não exista, crie uma.',
                    confirmButtonColor: '#6f51f0',
                    confirmButtonText: 'Entendido',
                });
            }
        }
    });

    transactionSwitch.addEventListener("change", updateForm);

    updateForm();
});