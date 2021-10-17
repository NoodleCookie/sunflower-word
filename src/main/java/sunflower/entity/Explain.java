package sunflower.entity;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Builder
@Table(name = "sunflower_word_explain")
@Entity
public class Explain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    private PartOfSpeech type;

    private String ch;

    private enum PartOfSpeech {
        VERB
    }
}
