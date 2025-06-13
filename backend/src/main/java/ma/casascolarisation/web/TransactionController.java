package ma.casascolarisation.web;

import lombok.RequiredArgsConstructor;
import ma.casascolarisation.dto.TransactionDto;
import ma.casascolarisation.dto.TransactionMapper;
import ma.casascolarisation.entities.AnneeScolaire;
import ma.casascolarisation.entities.Don;
import ma.casascolarisation.entities.Transaction;
import ma.casascolarisation.services.AnneeScolaireService;
import ma.casascolarisation.services.DonService;
import ma.casascolarisation.services.TransactionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;
    private final DonService donService;
    private final AnneeScolaireService anneeScolaireService;

    @GetMapping
    public List<TransactionDto> findAll() {
        return transactionService.findAll()
                .stream()
                .map(TransactionMapper::toDto)
                .toList();
    }

    @GetMapping("/{id}")
    public TransactionDto findById(@PathVariable Long id) {
        Transaction entity = transactionService.findById(id);
        return TransactionMapper.toDto(entity);
    }

    @PostMapping
    public TransactionDto save(@RequestBody TransactionDto dto) {
        if (dto.getDon() == null || dto.getDon().getId() == null)
            throw new IllegalArgumentException("Champ don ou son id ne peut pas être null !");
        if (dto.getAnnee() == null || dto.getAnnee().getId() == null)
            throw new IllegalArgumentException("Champ annee ou son id ne peut pas être null !");
        Don don = donService.findEntityById(dto.getDon() != null ? dto.getDon().getId() : null);
        AnneeScolaire annee = anneeScolaireService.findEntityById(dto.getAnnee() != null ? dto.getAnnee().getId() : null);
        Transaction entity = TransactionMapper.toEntity(dto, don, annee);
        Transaction saved = transactionService.save(entity);
        return TransactionMapper.toDto(saved);
    }

    @PutMapping("/{id}")
    public TransactionDto update(@PathVariable Long id, @RequestBody TransactionDto dto) {
        Don don = donService.findEntityById(dto.getDon() != null ? dto.getDon().getId() : null);
        AnneeScolaire annee = anneeScolaireService.findEntityById(dto.getAnnee() != null ? dto.getAnnee().getId() : null);
        Transaction entity = TransactionMapper.toEntity(dto, don, annee);
        entity.setId(id);
        Transaction updated = transactionService.save(entity);
        return TransactionMapper.toDto(updated);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        transactionService.delete(id);
    }
}
