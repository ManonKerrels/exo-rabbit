package be.technifutur.facture.services;

import be.technifutur.facture.model.Facture;

import java.util.List;
import java.util.UUID;

public interface FactureService {

    Facture createFacture(int nbrNuit, UUID reserv_ref);

    List<Facture> getFactures();
}
