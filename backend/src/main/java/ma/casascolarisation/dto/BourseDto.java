package ma.casascolarisation.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class BourseDto {
    private Long id;
    private Long eleveId; // pour POST/PUT
    private Long donId; // pour POST/PUT
    private Long anneeId; // pour POST/PUT
    private EleveSimpleDto eleve; // pour affichage GET
    private DonSimpleDto don; // pour affichage GET
    private AnneeScolaireSimpleDto annee; // pour affichage GET
    private BigDecimal montant;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private String notes;
    private String statut;   // "ACTIVE", "TERMINEE", "SUSPENDUE"
    private String frequence; // "MENSUELLE", "TRIMESTRIELLE", "ANNUELLE"
}
