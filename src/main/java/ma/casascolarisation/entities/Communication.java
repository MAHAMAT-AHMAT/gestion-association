package ma.casascolarisation.entities;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class Communication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne
    private Donateur donateur;

    @JsonIgnore
    @ManyToOne
    private Utilisateur utilisateur;

    private String contenu;

    private LocalDateTime date;

    @Enumerated(EnumType.STRING)
    private TypeCommunication type;

    public enum TypeCommunication {
        EMAIL,
        WHATSAPP,
        COURRIER
    }
}
