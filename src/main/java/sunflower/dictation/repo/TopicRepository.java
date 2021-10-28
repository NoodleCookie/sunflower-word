package sunflower.dictation.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sunflower.dictation.entity.DaoDictationTopic;
import sunflower.dictation.entity.DaoTopic;

@Repository
public interface TopicRepository extends JpaRepository<DaoDictationTopic, String> {
}
