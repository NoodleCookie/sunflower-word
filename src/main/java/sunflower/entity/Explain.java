package sunflower.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Builder
@Table(name = "sunflower_word_explain")
@Entity
public class Explain extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    private PartOfSpeech type;

    private String ch;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JsonIgnore
    private WordCard wordCard;

    public enum PartOfSpeech {
        verb, noun, adj, adv, prep, conj, phrase, art;
    }
}
