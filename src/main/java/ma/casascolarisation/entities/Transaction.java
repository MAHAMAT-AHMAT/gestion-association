package ma.casascolarisation.entities;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnoreProperties({"bourses", "transactions"})
    @ManyToOne
    private Don don;

    @JsonIgnoreProperties({"bourses", "dons", "eleves", "depenses", "transactions"})
    @ManyToOne
    private AnneeScolaire annee;

    private LocalDate datePaiement;

    private String reference;

    @Enumerated(EnumType.STRING)
    private ModePaiement modePaiement;

    @JsonIgnore
    @OneToMany(mappedBy = "transaction", cascade = CascadeType.ALL)
    private List<Facture> factures;

    public enum ModePaiement {
        VIREMENT,
        CASH,
        CHEQUE
    }
}
