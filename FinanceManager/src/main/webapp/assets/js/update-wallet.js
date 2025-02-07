"use strict";

import { submitGet } from './global.js';

document.addEventListener("DOMContentLoaded", async function () {
    
    const url = window.location.href;
    const urlObj = new URL(url);
    const id = urlObj.searchParams.get("wallet-id");

    const data = await submitGet(`/controller?context=wallets&action=update-view&wallet-id=${id}`);

    const form = document.getElementById("update-wallet");

    const wallet = form.querySelector("#wallet-id");
    wallet.value = data.wallet.id;

    const name = form.querySelector("#name");
    name.value = data.wallet.name;

    const description = form.querySelector("#description");
    description.value = data.wallet.description;

    const goalAmount = form.querySelector("#goal-amount");
    goalAmount.value = data.wallet.goalAmount;

    form.querySelector("#financial-record-id").value = financialRecord.id;
});