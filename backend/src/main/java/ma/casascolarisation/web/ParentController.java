package ma.casascolarisation.web;

import lombok.RequiredArgsConstructor;
import ma.casascolarisation.dto.ParentDto;
import ma.casascolarisation.dto.ParentMapper;
import ma.casascolarisation.entities.Parent;
import ma.casascolarisation.entities.Eleve;
import ma.casascolarisation.services.ParentService;
import ma.casascolarisation.services.EleveService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/parents")
@RequiredArgsConstructor
public class ParentController {

    private final ParentService parentService;
    private final EleveService eleveService;

    @GetMapping
    public List<ParentDto> getAll() {
        return parentService.findAll().stream()
                .map(ParentMapper::toDto)
                .toList();
    }

    @GetMapping("/{id}")
    public ParentDto getById(@PathVariable Long id) {
        Parent parent = parentService.findById(id);
        return ParentMapper.toDto(parent);
    }

    @PostMapping
    public ParentDto create(@RequestBody ParentDto dto) {
        Eleve eleve = eleveService.findById(dto.getEleveId());
        Parent parent = ParentMapper.toEntity(dto, eleve);
        Parent saved = parentService.save(parent);
        // Pour garantir que l'élève est chargé dans la réponse
        return ParentMapper.toDto(parentService.findById(saved.getId()));
    }

    @PutMapping("/{id}")
    public ParentDto update(@PathVariable Long id, @RequestBody ParentDto dto) {
        Eleve eleve = eleveService.findById(dto.getEleveId());
        Parent parent = ParentMapper.toEntity(dto, eleve);
        parent.setId(id);
        Parent updated = parentService.save(parent);
        return ParentMapper.toDto(parentService.findById(updated.getId()));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        parentService.delete(id);
    }
}
