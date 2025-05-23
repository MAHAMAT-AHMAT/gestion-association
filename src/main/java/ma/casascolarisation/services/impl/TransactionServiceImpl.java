package ma.casascolarisation.services.impl;

import lombok.RequiredArgsConstructor;
import ma.casascolarisation.entities.Transaction;
import ma.casascolarisation.repositories.TransactionRepo;
import ma.casascolarisation.services.TransactionService;
import org.springframework.stereotype.Service;

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
        return transactionRepo.findById(id).orElse(null);
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