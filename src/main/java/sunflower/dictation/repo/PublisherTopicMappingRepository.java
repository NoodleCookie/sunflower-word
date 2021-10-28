package sunflower.dictation.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sunflower.dictation.entity.PublisherSubscriberMapping;
import sunflower.dictation.entity.PublisherTopicMapping;

import java.util.List;

@Repository
public interface PublisherTopicMappingRepository extends JpaRepository<PublisherTopicMapping,Integer> {

    @Query("select pt.topicName from PublisherTopicMapping pt where pt.publisherHost=?1")
    List<String> getAllTopicsFromPublisherHost(String publisherHost);
}
