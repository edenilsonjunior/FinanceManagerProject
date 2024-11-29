const button = document.getElementById("saveButton");

button.addEventListener("click", function() {
    const categorySelect = document.getElementById("categorySelect").value;
    document.getElementById("categoryId").value = categorySelect.substring(categorySelect.indexOf('-') + 1).trim();
});