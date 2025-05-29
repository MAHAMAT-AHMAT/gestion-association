package ma.casascolarisation.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
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

    @JsonBackReference("donateur-communications")
    @ManyToOne
    private Donateur donateur;

    @JsonBackReference("utilisateur-communications")
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
