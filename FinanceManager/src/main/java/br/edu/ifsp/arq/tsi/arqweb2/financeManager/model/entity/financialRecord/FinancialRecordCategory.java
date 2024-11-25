package br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.entity.financialRecord;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FinancialRecordCategory {

    private String name;

    public FinancialRecordCategory(String name) {
        this.name = name;
    }
}
