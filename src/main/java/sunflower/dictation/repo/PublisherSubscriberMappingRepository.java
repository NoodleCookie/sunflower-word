package sunflower.dictation.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sunflower.dictation.entity.PublisherSubscriberMapping;

import java.util.List;

@Repository
public interface PublisherSubscriberMappingRepository extends JpaRepository<PublisherSubscriberMapping,Integer> {

    @Query("select ps.subscriber from PublisherSubscriberMapping ps where ps.publisher=?1")
    List<String> getAllSubscribersFromPublisher(String publisher);

    @Query("select ps.publisher from PublisherSubscriberMapping ps where ps.subscriber=?1")
    List<String> getAllPublishersFromSubscriber(String subscriber);

    @Query("select distinct ps.publisher from PublisherSubscriberMapping ps")
    List<String> getAllPublishers();
}
