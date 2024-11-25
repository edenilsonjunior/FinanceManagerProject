package br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.entity.user;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class Alert {

    private long id;
    private String message;
    private LocalDate alertDate;
    private boolean notified;

    public Alert(String message, LocalDate alertDate, boolean notified) {
        this.message = message;
        this.alertDate = alertDate;
        this.notified = notified;
    }
}
