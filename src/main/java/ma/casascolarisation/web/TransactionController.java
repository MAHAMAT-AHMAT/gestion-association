package ma.casascolarisation.web;

import lombok.RequiredArgsConstructor;
import ma.casascolarisation.entities.Transaction;
import ma.casascolarisation.services.TransactionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @GetMapping
    public List<Transaction> findAll() {
        return transactionService.findAll();
    }

    @GetMapping("/{id}")
    public Transaction findById(@PathVariable Long id) {
        return transactionService.findById(id);
    }

    @PostMapping
    public Transaction save(@RequestBody Transaction obj) {
        return transactionService.save(obj);
    }

    @PutMapping("/{id}")
    public Transaction update(@PathVariable Long id, @RequestBody Transaction updated) {
        updated.setId(id);
        return transactionService.save(updated);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        transactionService.delete(id);
    }
}
