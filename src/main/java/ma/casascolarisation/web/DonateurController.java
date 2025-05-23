package ma.casascolarisation.web;

import lombok.RequiredArgsConstructor;
import ma.casascolarisation.entities.Donateur;
import ma.casascolarisation.services.DonateurService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/donateurs")
@RequiredArgsConstructor
public class DonateurController {

    private final DonateurService donateurService;

    @GetMapping
    public List<Donateur> findAll() {
        return donateurService.findAll();
    }

    @GetMapping("/{id}")
    public Donateur findById(@PathVariable Long id) {
        return donateurService.findById(id);
    }

    @PostMapping
    public Donateur save(@RequestBody Donateur obj) {
        return donateurService.save(obj);
    }

    @PutMapping("/{id}")
    public Donateur update(@PathVariable Long id, @RequestBody Donateur updated) {
        updated.setId(id);
        return donateurService.save(updated);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        donateurService.delete(id);
    }
}
