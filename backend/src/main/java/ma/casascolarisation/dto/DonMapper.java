// DonMapper.java
package ma.casascolarisation.dto;

import ma.casascolarisation.entities.AnneeScolaire;
import ma.casascolarisation.entities.Don;
import ma.casascolarisation.entities.Donateur;

public class DonMapper {
    public static DonDto toDto(Don don) {
        if (don == null) return null;
        DonDto dto = new DonDto();
        dto.setId(don.getId());
        dto.setMontant(don.getMontant());
        dto.setDevise(don.getDevise());
        dto.setDate(don.getDate());
        dto.setDescription(don.getDescription());
        dto.setEstRecurrent(don.isEstRecurrent());
        dto.setStatut(don.getStatut() != null ? don.getStatut().name() : null);

        // Donateur (simple)
        if (don.getDonateur() != null) {
            DonateurSimpleDto d = new DonateurSimpleDto();
            d.setId(don.getDonateur().getId());
            d.setNom(don.getDonateur().getNom());
            d.setPrenom(don.getDonateur().getPrenom());
            dto.setDonateur(d);
        }

        // Annee Scolaire (simple)
        if (don.getAnnee() != null) {
            AnneeScolaireSimpleDto a = new AnneeScolaireSimpleDto();
            a.setId(don.getAnnee().getId());
            a.setLibelle(don.getAnnee().getLibelle());
            dto.setAnnee(a);
        }

        return dto;
    }

    public static Don toEntity(DonDto dto, Donateur donateur, AnneeScolaire annee) {
        if (dto == null) return null;
        Don don = new Don();
        don.setId(dto.getId());
        don.setMontant(dto.getMontant());
        don.setDevise(dto.getDevise());
        don.setDate(dto.getDate());
        don.setDescription(dto.getDescription());
        don.setEstRecurrent(dto.isEstRecurrent());
        don.setStatut(dto.getStatut() != null ? Don.StatutDon.valueOf(dto.getStatut()) : null);
        don.setDonateur(donateur);
        don.setAnnee(annee);
        return don;
    }
}
