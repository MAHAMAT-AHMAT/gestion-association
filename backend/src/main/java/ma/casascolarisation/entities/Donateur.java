package ma.casascolarisation.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class Donateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String prenom;
    private String telephone;
    private String email;
    private String pays;
    private String nationalite;
    private String numeroCin;
    private String photo;
    private String ville;
    private String adresse;
    private String codePostal;

    private LocalDateTime dateCreation;
    private LocalDateTime dateModification;
    private Boolean estActif;

    @Enumerated(EnumType.STRING)
    private TypeDonateur typeDonateur;

    @Enumerated(EnumType.STRING)
    private TypeEntiteDonateur typeEntite;

    @JsonIgnore
    @OneToMany(mappedBy = "donateur", cascade = CascadeType.ALL)
    private List<Don> dons;

    @JsonIgnore
    @OneToMany(mappedBy = "donateur", cascade = CascadeType.ALL)
    private List<Facture> factures;

    public enum TypeDonateur {
        MORAL,
        FINANCIER,
        LES_DEUX
    }

    public enum TypeEntiteDonateur {
        PERSONNE,
        ENTREPRISE,
        AUTRE
    }
    @PrePersist
    protected void onCreate() {
        this.dateCreation = LocalDateTime.now();
        this.dateModification = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.dateModification = LocalDateTime.now();
    }

}
