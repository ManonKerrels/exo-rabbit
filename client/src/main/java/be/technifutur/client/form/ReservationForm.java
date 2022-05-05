package be.technifutur.client.form;

import be.technifutur.client.model.Reservation;
import lombok.Data;

import java.util.Date;

@Data
public class ReservationForm {

    private Date arrive;
    private Date depart;

    public Reservation map() {
        return new Reservation(arrive, depart);
    }
}
