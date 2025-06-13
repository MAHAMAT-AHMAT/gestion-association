// DonController.java
package ma.casascolarisation.web;

import lombok.RequiredArgsConstructor;
import ma.casascolarisation.dto.DonDto;
import ma.casascolarisation.services.DonService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
// DonController.java
@RestController
@RequestMapping("/api/dons")
@RequiredArgsConstructor
public class DonController {

    private final DonService donService;

    @GetMapping
    public List<DonDto> findAll() {
        return donService.findAll();    // RETOURNE des DonDto
    }

    @GetMapping("/{id}")
    public DonDto findById(@PathVariable Long id) {
        return donService.findById(id); // RETOURNE UN DonDto
    }

    @PostMapping
    public DonDto save(@RequestBody DonDto obj) {
        return donService.save(obj);    // Prend un DonDto et retourne un DonDto
    }

    @PutMapping("/{id}")
    public DonDto update(@PathVariable Long id, @RequestBody DonDto updated) {
        updated.setId(id);
        return donService.save(updated);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        donService.delete(id);
    }
}
