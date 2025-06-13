// DonService.java
package ma.casascolarisation.services;

import ma.casascolarisation.dto.DonDto;
import ma.casascolarisation.entities.Don;

import java.util.List;

public interface DonService {
    List<DonDto> findAll();
    DonDto findById(Long id);
    DonDto save(DonDto dto);
    void delete(Long id);
    Don findEntityById(Long id);
}
