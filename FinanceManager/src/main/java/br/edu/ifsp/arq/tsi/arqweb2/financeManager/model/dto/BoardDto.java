package br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.dto;

import java.util.List;
import java.util.Map;

public record BoardDto(
        Map<String, Double> overview,
        Map<String, Double> monthlyBalance,
        List<CategoryDto> categories
) { }
