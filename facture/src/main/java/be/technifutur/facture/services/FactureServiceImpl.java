package be.technifutur.facture.services;

import be.technifutur.facture.model.Facture;
import be.technifutur.facture.rabbit.RabbitSender;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class FactureServiceImpl implements FactureService{

    List<Facture> factures = new ArrayList<>();
    private static FactureServiceImpl _instance;
    private final RabbitSender rabbitSender;

    public FactureServiceImpl(RabbitSender rabbitSender) {
        this.rabbitSender = rabbitSender;
    }

//    public static FactureServiceImpl getInstance()
//    {
//        if(_instance == null){
//            _instance = new FactureServiceImpl(new RabbitSender(new RabbitTemplate(), mapper));
//        }
//        return _instance;
//    }

    @Override
    public Facture createFacture(int nbrNuit, UUID reserv_ref) {
        Facture facture = new Facture();
        factures.add(facture);
        return facture;
    }

    @Override
    public List<Facture> getFactures() {
        return factures;
    }
}
