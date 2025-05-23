package ma.casascolarisation.services.impl;

import lombok.RequiredArgsConstructor;
import ma.casascolarisation.entities.Don;
import ma.casascolarisation.repositories.DonRepo;
import ma.casascolarisation.services.DonService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DonServiceImpl implements DonService {

    private final DonRepo donRepo;

    @Override
    public List<Don> findAll() {
        return donRepo.findAll();
    }

    @Override
    public Don findById(Long id) {
        return donRepo.findById(id).orElse(null);
    }

    @Override
    public Don save(Don obj) {
        return donRepo.save(obj);
    }

    @Override
    public void delete(Long id) {
        donRepo.deleteById(id);
    }
}