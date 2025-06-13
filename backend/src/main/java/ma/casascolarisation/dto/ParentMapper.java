package ma.casascolarisation.dto;

import ma.casascolarisation.entities.Parent;
import ma.casascolarisation.entities.Eleve;

public class ParentMapper {
    public static ParentDto toDto(Parent parent) {
        if (parent == null) return null;
        ParentDto dto = new ParentDto();
        dto.setId(parent.getId());
        dto.setNom(parent.getNom());
        dto.setPrenom(parent.getPrenom());
        dto.setProfession(parent.getProfession());
        dto.setTelephone(parent.getTelephone());
        dto.setEmail(parent.getEmail());
        dto.setLienParente(parent.getLienParente() != null ? parent.getLienParente().name() : null);

        if (parent.getEleve() != null) {
            dto.setEleveId(parent.getEleve().getId()); // utile pour POST/PUT
            EleveSimpleDto e = new EleveSimpleDto();
            e.setId(parent.getEleve().getId());
            e.setNom(parent.getEleve().getNom());
            e.setPrenom(parent.getEleve().getPrenom());
            dto.setEleve(e); // utile pour l'affichage
        }

        return dto;
    }

    public static Parent toEntity(ParentDto dto, Eleve eleve) {
        if (dto == null) return null;
        Parent parent = new Parent();
        parent.setId(dto.getId());
        parent.setNom(dto.getNom());
        parent.setPrenom(dto.getPrenom());
        parent.setProfession(dto.getProfession());
        parent.setTelephone(dto.getTelephone());
        parent.setEmail(dto.getEmail());
        parent.setLienParente(dto.getLienParente() != null ? Parent.LienParente.valueOf(dto.getLienParente()) : null);
        parent.setEleve(eleve);
        return parent;
    }
}
