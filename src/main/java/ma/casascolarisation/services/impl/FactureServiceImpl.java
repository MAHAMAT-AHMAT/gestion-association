package ma.casascolarisation.services.impl;

import lombok.RequiredArgsConstructor;
import ma.casascolarisation.entities.Donateur;
import ma.casascolarisation.entities.Facture;
import ma.casascolarisation.entities.Transaction;
import ma.casascolarisation.repositories.DonateurRepo;
import ma.casascolarisation.repositories.FactureRepo;
import ma.casascolarisation.repositories.TransactionRepo;
import ma.casascolarisation.services.FactureService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FactureServiceImpl implements FactureService {

    private final FactureRepo factureRepo;
    private final TransactionRepo transactionRepo;
    private final DonateurRepo donateurRepo;

    @Override
    public List<Facture> findAll() {
        return factureRepo.findAll();
    }

    @Override
    public Facture findById(Long id) {
        return factureRepo.findById(id).orElse(null);
    }

    @Override
    public Facture save(Facture obj) {
        // Reload transaction from database if it exists
        if (obj.getTransaction() != null && obj.getTransaction().getId() != null) {
            Transaction transaction = transactionRepo.findById(obj.getTransaction().getId()).orElse(null);
            if (transaction != null) {
                obj.setTransaction(transaction);
            }
        }

        // Reload donateur from database if it exists
        if (obj.getDonateur() != null && obj.getDonateur().getId() != null) {
            Donateur donateur = donateurRepo.findById(obj.getDonateur().getId()).orElse(null);
            if (donateur != null) {
                obj.setDonateur(donateur);
            }
        }

        return factureRepo.save(obj);
    }

    @Override
    public void delete(Long id) {
        factureRepo.deleteById(id);
    }
}
