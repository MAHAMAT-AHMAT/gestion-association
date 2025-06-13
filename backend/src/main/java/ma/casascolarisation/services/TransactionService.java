package ma.casascolarisation.services;

import ma.casascolarisation.entities.Transaction;

import java.util.List;

public interface TransactionService {
    List<Transaction> findAll();
    Transaction findById(Long id);
    Transaction save(Transaction obj);
    void delete(Long id);
}
