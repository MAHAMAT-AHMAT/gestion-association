package ma.casascolarisation.web;

import lombok.RequiredArgsConstructor;
import ma.casascolarisation.entities.Facture;
import ma.casascolarisation.services.FactureService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/factures")
@RequiredArgsConstructor
public class FactureController {

    private final FactureService factureService;

    @GetMapping
    public List<Facture> findAll() {
        return factureService.findAll();
    }

    @GetMapping("/{id}")
    public Facture findById(@PathVariable Long id) {
        return factureService.findById(id);
    }

    @PostMapping
    public Facture save(@RequestBody Facture obj) {
        return factureService.save(obj);
    }

    @PutMapping("/{id}")
    public Facture update(@PathVariable Long id, @RequestBody Facture updated) {
        updated.setId(id);
        return factureService.save(updated);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        factureService.delete(id);
    }
}
