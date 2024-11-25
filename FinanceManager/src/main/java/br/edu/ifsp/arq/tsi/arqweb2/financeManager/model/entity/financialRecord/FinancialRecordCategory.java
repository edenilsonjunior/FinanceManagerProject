package br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.entity.financialRecord;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FinancialRecordCategory {

    private long id;
    private String name;

    public FinancialRecordCategory(long id, String name) {
        this.id = id;
        this.name = name;
    }

}
