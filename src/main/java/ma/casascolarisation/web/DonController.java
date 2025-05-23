package ma.casascolarisation.web;

import lombok.RequiredArgsConstructor;
import ma.casascolarisation.entities.Don;
import ma.casascolarisation.services.DonService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dons")
@RequiredArgsConstructor
public class DonController {

    private final DonService donService;

    @GetMapping
    public List<Don> findAll() {
        return donService.findAll();
    }

    @GetMapping("/{id}")
    public Don findById(@PathVariable Long id) {
        return donService.findById(id);
    }

    @PostMapping
    public Don save(@RequestBody Don obj) {
        return donService.save(obj);
    }

    @PutMapping("/{id}")
    public Don update(@PathVariable Long id, @RequestBody Don updated) {
        updated.setId(id);
        return donService.save(updated);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        donService.delete(id);
    }
}
