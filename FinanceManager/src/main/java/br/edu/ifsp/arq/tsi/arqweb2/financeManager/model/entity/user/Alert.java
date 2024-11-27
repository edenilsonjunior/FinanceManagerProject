package br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.entity.user;

import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Alert {

    private long id;
    private String message;
    private LocalDate alertDate;
    private boolean notified;

}
