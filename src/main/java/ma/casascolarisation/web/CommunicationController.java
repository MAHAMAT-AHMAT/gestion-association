package ma.casascolarisation.web;

import lombok.RequiredArgsConstructor;
import ma.casascolarisation.entities.Communication;
import ma.casascolarisation.services.CommunicationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/communications")
@RequiredArgsConstructor
public class CommunicationController {

    private final CommunicationService communicationService;

    @GetMapping
    public List<Communication> findAll() {
        return communicationService.findAll();
    }

    @GetMapping("/{id}")
    public Communication findById(@PathVariable Long id) {
        return communicationService.findById(id);
    }

    @PostMapping
    public Communication save(@RequestBody Communication obj) {
        return communicationService.save(obj);
    }

    @PutMapping("/{id}")
    public Communication update(@PathVariable Long id, @RequestBody Communication updated) {
        updated.setId(id);
        return communicationService.save(updated);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        communicationService.delete(id);
    }
}
