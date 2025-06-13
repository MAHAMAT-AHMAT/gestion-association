package ma.casascolarisation.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cheminFichier;

    private LocalDateTime dateCreation;

    @Enumerated(EnumType.STRING)
    private TypeDocument type;

    @Enumerated(EnumType.STRING)
    private TypeEntiteDocument entite;

    private Long idEntiteLiee;

    public enum TypeDocument {
        FACTURE,
        RECU,
        JUSTIFICATIF
    }

    public enum TypeEntiteDocument {
        DON,
        DEPENSE,
        BOURSE,
        ELEVE
    }
}
