package sunflower.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Builder
@Table(name = "sunflower_word_card")
@Entity
public class WordCard extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String word;

    private String eg;

    private String note;

    @OneToMany(targetEntity = Explain.class, cascade = {CascadeType.DETACH})
    @JoinColumn(name = "word_card_id", referencedColumnName = "id")
    private List<Explain> explains;

    private boolean deleted;
}
