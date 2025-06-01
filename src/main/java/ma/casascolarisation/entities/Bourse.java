package ma.casascolarisation.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class Bourse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonBackReference("eleve-bourses")
    @ManyToOne
    private Eleve eleve;

    @JsonBackReference("don-bourses")
    @ManyToOne
    private Don don;

    @JsonBackReference("annee-bourses")
    @ManyToOne
    private AnneeScolaire annee;

    private BigDecimal montant;

    private LocalDate dateDebut;

    private LocalDate dateFin;

    private String notes;

    @Enumerated(EnumType.STRING)
    private StatutBourse statut;

    @Enumerated(EnumType.STRING)
    private Frequence frequence;

    public enum StatutBourse {
        ACTIVE,
        TERMINEE,
        SUSPENDUE
    }

    public enum Frequence {
        MENSUELLE,
        TRIMESTRIELLE,
        ANNUELLE
    }
}
