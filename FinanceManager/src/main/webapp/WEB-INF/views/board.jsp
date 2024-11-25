<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Financial Records</title>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
        }
        th, td {
            padding: 10px;
            text-align: left;
            border: 1px solid #ddd;
        }
        th {
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>
    <h1>Financial Records</h1>
    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Category</th>
                <th>Amount</th>
                <th>Transaction Type</th>
                <th>Transaction Date</th>
                <th>Description</th>
            </tr>
        </thead>
        <tbody>
            <!-- Loop through financialRecords -->
            <c:forEach var="record" items="${financialRecords}">
                <tr>
                    <td>${record.id}</td>
                    <td>${record.category.name}</td>
                    <td>${record.amount}</td>
                    <td>${record.transactionType}</td>
                    <td>${record.transactionDate}</td>
                    <td>${record.description}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
