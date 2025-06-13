package ma.casascolarisation.dto;

import lombok.Data;

@Data
public class TransactionDto {
    private Long id;
    private DonSimpleDto don;
    private AnneeScolaireSimpleDto annee;
    private String datePaiement;
    private String reference;
    private String modePaiement;
}
