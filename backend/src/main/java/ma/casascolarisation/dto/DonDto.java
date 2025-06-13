// DonDto.java
package ma.casascolarisation.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class DonDto {
    private Long id;
    private DonateurSimpleDto donateur;
    private AnneeScolaireSimpleDto annee;
    private BigDecimal montant;
    private String devise;
    private LocalDate date;
    private String description;
    private boolean estRecurrent;
    private String statut; // "PROMIS", "RECU", "AFFECTE"
}
