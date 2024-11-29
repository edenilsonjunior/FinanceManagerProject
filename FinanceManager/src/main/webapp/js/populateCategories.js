function populateCategories() {
    fetch('/FinanceManager/GetCategories')
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(categories => {
            const select = document.getElementById('category');
            categories.forEach(category => {
                const option = document.createElement('option');
                option.value = category.name;
                option.textContent = category.name;
                select.appendChild(option);
            });
        })
        .catch(error => console.error('Error fetching categories:', error));
}

document.addEventListener('DOMContentLoaded', populateCategories);