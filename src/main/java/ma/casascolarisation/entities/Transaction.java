package ma.casascolarisation.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonBackReference("don-transactions")
    @ManyToOne
    private Don don;

    @JsonBackReference("annee-transactions")
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
