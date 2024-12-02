package br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.entity.financialRecord;


import br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.entity.user.User;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FinancialRecordCategory {

    private long id;
    private User user;
    private String name;

}
