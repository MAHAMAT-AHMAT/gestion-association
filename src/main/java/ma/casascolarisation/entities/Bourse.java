package ma.casascolarisation.entities;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

    @JsonIgnoreProperties({"bourses", "parents", "depenses"})
    @ManyToOne
    private Eleve eleve;

    @JsonIgnoreProperties({"bourses", "transactions"})
    @ManyToOne
    private Don don;

    @JsonIgnoreProperties({"bourses", "dons", "eleves", "depenses", "transactions"})
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
