document.addEventListener("DOMContentLoaded", () => {
    const transactionSwitch = document.getElementById("transactionSwitch");
    const transactionTypeInput = document.getElementById("transactionType");
    const categorySelect = document.getElementById("categorySelect");
    const categoryField = categorySelect.closest(".form-field");
    const form = document.querySelector("form");

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
            const selectedCategory = categorySelect.value;

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