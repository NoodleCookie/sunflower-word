package sunflower.dictation.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sunflower.dictation.entity.TopicWordMapping;

import java.util.List;

@Repository
public interface TopicWordMappingRepository extends JpaRepository<TopicWordMapping, Integer> {

    @Query("select tw.wordName from TopicWordMapping tw where tw.topicName=?1")
    List<String> getWordsByTopicName(String topicName);
}
