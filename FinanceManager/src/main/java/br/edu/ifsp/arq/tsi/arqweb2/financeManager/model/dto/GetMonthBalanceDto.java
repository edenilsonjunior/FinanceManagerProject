package br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.dto;

public record GetMonthBalanceDto(
    String monthYear,
    double totalIncome,
    double totalExpense,
    double currentBalance
) { }
