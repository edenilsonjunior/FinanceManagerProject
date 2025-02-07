"use strict";

import { submitGet } from './global.js';

document.addEventListener("DOMContentLoaded", async function () {
    
    const url = window.location.href;
    const urlObj = new URL(url);
    const id = urlObj.searchParams.get("id");

    const data = await submitGet(`/controller?context=financial-record&action=update-view&id=${id}`);

    const financialRecord = data.financialRecord;
    const categories = data.categories;

    const form = document.getElementById("update-financial-record");

    const categorySelect = form.querySelector("#categoryId");

    if (data.financialRecord.transactionType.trim().toUpperCase() === 'INCOME') {
        form.removeChild(categorySelect.parentElement);
    }

    categories.forEach(category => {
        const option = document.createElement("option");
        option.value = category.id;
        option.textContent = category.name;
        categorySelect.appendChild(option);
    });

    categorySelect.value = financialRecord.category.id;


    const amount = form.querySelector("#amount");
    amount.value = financialRecord.amount;

    const description = form.querySelector("#description");
    description.value = financialRecord.description;

    form.querySelector("#financial-record-id").value = financialRecord.id;
});