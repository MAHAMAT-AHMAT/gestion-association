// DonServiceImpl.java
package ma.casascolarisation.services.impl;

import lombok.RequiredArgsConstructor;
import ma.casascolarisation.dto.DonDto;
import ma.casascolarisation.dto.DonMapper;
import ma.casascolarisation.entities.Don;
import ma.casascolarisation.entities.Donateur;
import ma.casascolarisation.entities.AnneeScolaire;
import ma.casascolarisation.repositories.DonRepo;
import ma.casascolarisation.repositories.DonateurRepo;
import ma.casascolarisation.repositories.AnneeScolaireRepo;
import ma.casascolarisation.services.DonService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DonServiceImpl implements DonService {

    private final DonRepo donRepo;
    private final DonateurRepo donateurRepo;
    private final AnneeScolaireRepo anneeScolaireRepo;

    @Override
    public List<DonDto> findAll() {
        return donRepo.findAll().stream()
                .map(DonMapper::toDto)
                .toList();
    }

    @Override
    public DonDto findById(Long id) {
        Don don = donRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Don introuvable pour l'id " + id));
        return DonMapper.toDto(don);
    }

    @Override
    public DonDto save(DonDto dto) {
        Donateur donateur = null;
        if (dto.getDonateur() != null && dto.getDonateur().getId() != null) {
            donateur = donateurRepo.findById(dto.getDonateur().getId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Donateur introuvable"));
        }

        AnneeScolaire annee = null;
        if (dto.getAnnee() != null && dto.getAnnee().getId() != null) {
            annee = anneeScolaireRepo.findById(dto.getAnnee().getId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Année introuvable"));
        }

        Don don = DonMapper.toEntity(dto, donateur, annee);

        Don saved = donRepo.save(don);
        return DonMapper.toDto(saved);
    }
    @Override
    public Don findEntityById(Long id) {
        return donRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Don introuvable"));
    }



    @Override
    public void delete(Long id) {
        donRepo.deleteById(id);
    }

}
