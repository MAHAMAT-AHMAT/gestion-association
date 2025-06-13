package ma.casascolarisation.dto;

import ma.casascolarisation.entities.Transaction;
import ma.casascolarisation.entities.Don;
import ma.casascolarisation.entities.AnneeScolaire;

public class TransactionMapper {

    public static TransactionDto toDto(Transaction entity) {
        if (entity == null) return null;
        TransactionDto dto = new TransactionDto();
        dto.setId(entity.getId());
        dto.setDatePaiement(entity.getDatePaiement() != null ? entity.getDatePaiement().toString() : null);
        dto.setReference(entity.getReference());
        dto.setModePaiement(entity.getModePaiement() != null ? entity.getModePaiement().name() : null);

        if (entity.getDon() != null) {
            dto.setDon(toDonSimpleDto(entity.getDon()));
        }

        if (entity.getAnnee() != null) {
            dto.setAnnee(toAnneeScolaireSimpleDto(entity.getAnnee()));
        }
        return dto;
    }

    // DTO -> ENTITY
    public static Transaction toEntity(TransactionDto dto, Don don, AnneeScolaire annee) {
        if (dto == null) return null;
        Transaction entity = new Transaction();
        entity.setId(dto.getId());
        entity.setReference(dto.getReference());
        entity.setDatePaiement(dto.getDatePaiement() != null ? java.time.LocalDate.parse(dto.getDatePaiement()) : null);
        entity.setModePaiement(dto.getModePaiement() != null ?
                Transaction.ModePaiement.valueOf(dto.getModePaiement()) : null);
        entity.setDon(don);
        entity.setAnnee(annee);
        return entity;
    }

    // DON vers DonSimpleDto
    public static DonSimpleDto toDonSimpleDto(Don don) {
        if (don == null) return null;
        DonSimpleDto dto = new DonSimpleDto();
        dto.setId(don.getId());
        dto.setMontant(don.getMontant() != null ? don.getMontant().doubleValue() : null);
        dto.setDevise(don.getDevise());
        dto.setDonateurNom(
                don.getDonateur() != null
                        ? ((don.getDonateur().getNom() != null ? don.getDonateur().getNom() : "") +
                        " " +
                        (don.getDonateur().getPrenom() != null ? don.getDonateur().getPrenom() : ""))
                        : null
        );
        return dto;
    }


    public static AnneeScolaireSimpleDto toAnneeScolaireSimpleDto(AnneeScolaire annee) {
        if (annee == null) return null;
        AnneeScolaireSimpleDto dto = new AnneeScolaireSimpleDto();
        dto.setId(annee.getId());
        dto.setLibelle(annee.getLibelle());
        return dto;
    }

}
