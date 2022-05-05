package be.technifutur.api.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {

    private UUID reference;
    private LocalDate arrive;
    private LocalDate depart;
    private Status status;

    public static enum Status{
        DEMANDE,
        FACTURE
    }
}
