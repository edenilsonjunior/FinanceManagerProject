<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Update Financial Record</title>
</head>
<body>
    <h1>Update Financial Record</h1>
    <form action="UpdateFinancialRecord" method="post">
        <input type="hidden" name="id" value="${financialRecord.id}" />

        <label for="category">Category:</label>
        <select name="categoryName" id="category"></select>
        <br/>

        <label for="amount">Amount:</label>
        <input type="text" id="amount" name="amount" value="${financialRecord.amount}" required />
        <br/>

        <label for="transactionType">Transaction Type:</label>
        <input type="text" id="transactionType" name="transactionType" value="${financialRecord.transactionType}" required />
        <br/>

        <label for="transactionDate">Transaction Date:</label>
        <input type="date" id="transactionDate" name="transactionDate" value="${financialRecord.transactionDate}" required />
        <br/>

        <label for="description">Description:</label>
        <textarea id="description" name="description" required>${financialRecord.description}</textarea>
        <br/>

        <button type="submit">Update</button>
    </form>

<script src="js/populateCategories.js"></script>
</body>
</html>