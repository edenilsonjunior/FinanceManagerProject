"use strict";

const transactionSwitch = document.getElementById("transactionSwitch");
const transactionTypeInput = document.getElementById("transactionType");
const form = document.querySelector("form");

document.addEventListener("DOMContentLoaded", async () => {



    const url = window.location.href;
    const urlObj = new URL(url);
    const id = urlObj.searchParams.get("wallet-id");

    const form = document.getElementById("form");

    const wallet = form.querySelector("#wallet-id");
    wallet.value = id;

    function updateForm() {
        if (transactionSwitch.checked) {
            transactionTypeInput.value = "EXPENSE";
        } else {
            transactionTypeInput.value = "INCOME";
        }
    }

    transactionSwitch.addEventListener("change", updateForm);

    updateForm();
});