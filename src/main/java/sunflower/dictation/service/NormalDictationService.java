package sunflower.dictation.service;

import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import sunflower.dictation.entity.*;
import sunflower.dictation.repo.PublisherSubscriberMappingRepository;
import sunflower.dictation.repo.PublisherTopicMappingRepository;
import sunflower.dictation.repo.TopicRepository;
import sunflower.dictation.repo.TopicWordMappingRepository;
import sunflower.dto.BaiduPicDetectiveDto;
import sunflower.service.MediaService;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
public class NormalDictationService {

    private final TopicRepository topicRepository;
    private final PublisherSubscriberMappingRepository publisherSubscriberMappingRepository;
    private final PublisherTopicMappingRepository publisherTopicMappingRepository;
    private final TopicWordMappingRepository topicWordMappingRepository;

    private final MediaService mediaService;

    private final ThreadLocal<DaoDictationPublisher> daoDictationPublisher = new ThreadLocal<>();
    private final ThreadLocal<DaoSubscriber> daoSubscriber = new ThreadLocal<>();

    public NormalDictationService(TopicRepository topicRepository, PublisherSubscriberMappingRepository publisherSubscriberMappingRepository, PublisherTopicMappingRepository publisherTopicMappingRepository, TopicWordMappingRepository topicWordMappingRepository, MediaService mediaService) {
        this.topicRepository = topicRepository;
        this.mediaService = mediaService;
        this.publisherTopicMappingRepository = publisherTopicMappingRepository;
        this.topicWordMappingRepository = topicWordMappingRepository;
        this.publisherSubscriberMappingRepository = publisherSubscriberMappingRepository;
    }

    public void initDaoPublisher() {
        daoDictationPublisher.set(new DaoDictationPublisher(topicRepository, publisherSubscriberMappingRepository, publisherTopicMappingRepository));
        daoSubscriber.set(new DaoSubscriber());
        daoSubscriber.get().subscribe(daoDictationPublisher.get());
    }

    @SneakyThrows
    public void publishNormalTopic(String name, String description, MultipartFile file) {
        initDaoPublisher();
        List<String> words = mediaService.getWordsFromPicture(file.getBytes()).getWords_result().stream().map(BaiduPicDetectiveDto.WordsResult::getWords).map(wz->wz.toLowerCase(Locale.ROOT).trim()).collect(Collectors.toList());
        daoDictationPublisher.get().publish(DaoDictationTopic.builder().name(name).description(description).build());
        mediaService.downloadAudio(words);
        for (String word : words) {
            topicWordMappingRepository.save(TopicWordMapping.builder().topicName(name).wordName(word).build());
        }
    }

    public List<String> getNormalPublisher() {
        return publisherSubscriberMappingRepository.getAllPublishers();
    }

    public List<String> getNormalTopics(String publishHost) {
        return publisherTopicMappingRepository.getAllTopicsFromPublisherHost(publishHost);
    }

    public List<String> getNormalWords(String topic) {
        return topicWordMappingRepository.getWordsByTopicName(topic);
    }
}
