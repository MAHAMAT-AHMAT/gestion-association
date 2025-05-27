package ma.casascolarisation.services.impl;

import lombok.RequiredArgsConstructor;
import ma.casascolarisation.entities.AnneeScolaire;
import ma.casascolarisation.entities.Don;
import ma.casascolarisation.entities.Transaction;
import ma.casascolarisation.repositories.AnneeScolaireRepo;
import ma.casascolarisation.repositories.DonRepo;
import ma.casascolarisation.repositories.TransactionRepo;
import ma.casascolarisation.services.TransactionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepo transactionRepo;
    private final DonRepo donRepo;
    private final AnneeScolaireRepo anneeScolaireRepo;

    @Override
    public List<Transaction> findAll() {
        return transactionRepo.findAll();
    }

    @Override
    public Transaction findById(Long id) {
        return transactionRepo.findById(id).orElse(null);
    }

    @Override
    public Transaction save(Transaction obj) {
        // Reload don from database if it exists
        if (obj.getDon() != null && obj.getDon().getId() != null) {
            Don don = donRepo.findById(obj.getDon().getId()).orElse(null);
            if (don != null) {
                obj.setDon(don);
            }
        }

        // Reload annee from database if it exists
        if (obj.getAnnee() != null && obj.getAnnee().getId() != null) {
            AnneeScolaire annee = anneeScolaireRepo.findById(obj.getAnnee().getId()).orElse(null);
            if (annee != null) {
                obj.setAnnee(annee);
            }
        }

        return transactionRepo.save(obj);
    }

    @Override
    public void delete(Long id) {
        transactionRepo.deleteById(id);
    }
}
