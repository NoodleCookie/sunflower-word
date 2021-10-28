package sunflower.dictation.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sunflower_topic")
public class DaoDictationTopic implements DaoTopic {

    @Id
    private String name;

    private String description;

    @Override
    public List<String> getWords() {
        return null;
    }
}
