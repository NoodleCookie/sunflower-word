package sunflower.dictation.entity;

import sunflower.configuration.UserContext;
import sunflower.dictation.repo.PublisherSubscriberMappingRepository;
import sunflower.dictation.repo.PublisherTopicMappingRepository;
import sunflower.dictation.repo.TopicRepository;

import javax.annotation.PostConstruct;
import java.util.Collection;

public class DaoDictationPublisher implements DaoPublisher {

    private TopicRepository topicRepository;
    private PublisherSubscriberMappingRepository publisherSubscriberMappingRepository;
    private PublisherTopicMappingRepository publisherTopicMappingRepository;

    public DaoDictationPublisher(TopicRepository topicRepository, PublisherSubscriberMappingRepository publisherSubscriberMappingRepository, PublisherTopicMappingRepository publisherTopicMappingRepository) {
        this.topicRepository = topicRepository;
        this.publisherTopicMappingRepository = publisherTopicMappingRepository;
        this.publisherSubscriberMappingRepository = publisherSubscriberMappingRepository;
    }

    @PostConstruct
    private void initial() {

    }


    @Override
    public String daoTopic(DaoDictationTopic daoTopic) {
        return topicRepository.save(daoTopic).getName();
    }

    @Override
    public void completeTopic() {

    }

    @Override
    public String getHost() {
        return "normal-dictation-publisher-" + UserContext.getUser();
    }

    @Override
    public Collection<String> getSubscriber() {
        return publisherSubscriberMappingRepository.getAllSubscribersFromPublisher(getHost());
    }

    @Override
    public void add(Subscriber subscriber) {
        if (!publisherSubscriberMappingRepository.getAllPublishersFromSubscriber(subscriber.getName()).contains(getHost())) {
//            throw new RuntimeException("you have subscribe the publisher");
            publisherSubscriberMappingRepository.save(PublisherSubscriberMapping.builder()
                    .publisher(getHost())
                    .subscriber(subscriber.getName())
                    .build());
        }
    }

    @Override
    public void publish(SimpleTopic simpleTopic) {
        daoTopic((DaoDictationTopic) simpleTopic);
        publisherTopicMappingRepository.save(PublisherTopicMapping.builder().topicName(simpleTopic.getName()).publisherHost(getHost()).build());
    }
}
