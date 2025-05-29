package ma.casascolarisation.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class NiveauScolaire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomNiveau;
    private int ordre;

    @JsonIgnore
    @OneToMany(mappedBy = "niveau", cascade = CascadeType.ALL)
    private List<Eleve> eleves;
}
