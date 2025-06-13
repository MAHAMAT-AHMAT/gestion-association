package ma.casascolarisation.services.impl;

import lombok.RequiredArgsConstructor;
import ma.casascolarisation.entities.Transaction;
import ma.casascolarisation.repositories.TransactionRepo;
import ma.casascolarisation.services.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepo transactionRepo;

    @Override
    public List<Transaction> findAll() {
        return transactionRepo.findAll();
    }

    @Override
    public Transaction findById(Long id) {
        return transactionRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Transaction introuvable pour l'id " + id));
    }

    @Override
    public Transaction save(Transaction obj) {
        return transactionRepo.save(obj);
    }

    @Override
    public void delete(Long id) {
        transactionRepo.deleteById(id);
    }
}