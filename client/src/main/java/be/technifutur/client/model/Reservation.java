package be.technifutur.client.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {

    private UUID reference;
    private Date arrive;
    private Date depart;
    private Status status;

    public Reservation(Date arrive, Date depart) {
    }

    public enum Status{
        DEMANDE,
        FACTURE
    }
}
