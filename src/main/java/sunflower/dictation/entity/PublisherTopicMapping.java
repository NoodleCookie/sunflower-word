package sunflower.dictation.entity;

import lombok.*;

import javax.persistence.*;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sunflower_publisher_topic")
public class PublisherTopicMapping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String publisherHost;

    private String topicName;
}
