package sunflower.entity;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Builder
@Table(name = "sunflower_word_audio")
@Entity
public class WordAudio extends BaseEntity{

    @Id
    private String word;

    private byte[] audio;
}

