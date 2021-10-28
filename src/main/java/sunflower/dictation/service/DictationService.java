package sunflower.dictation.service;

import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import sunflower.configuration.UserContext;
import sunflower.dictation.entity.*;
import sunflower.dictation.repo.MemoryPublisherRepository;
import sunflower.dto.BaiduPicDetectiveDto;
import sunflower.service.MediaService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DictationService {

    private final MediaService mediaService;

    private final ThreadLocal<MemoryDictationPublisher> memoryDictationPublisher = new ThreadLocal<>();
    private final ThreadLocal<MemoryDictationSubscriber> memoryDictationSubscriber = new ThreadLocal<>();

    public DictationService(MediaService mediaService) {
        this.mediaService = mediaService;
    }

    public void initialSimplePublisher() {
        memoryDictationSubscriber.set(new MemoryDictationSubscriber());
        MemoryDictationPublisher memoryDictationPublisher = new MemoryDictationPublisher();
        memoryDictationPublisher.initial();
        this.memoryDictationPublisher.set(memoryDictationPublisher);
        memoryDictationSubscriber.get().subscribe(this.memoryDictationPublisher.get());
    }

    @SneakyThrows
    public void publishSimpleTopic(String name, MultipartFile file) {
        initialSimplePublisher();
        List<String> words = mediaService.getWordsFromPicture(file.getBytes()).getWords_result().stream().map(BaiduPicDetectiveDto.WordsResult::getWords).map(String::trim).collect(Collectors.toList());
        memoryDictationPublisher.get().publish(DefaultSimpleDictationTopic.builder().mediaService(mediaService).words(words).name(name).build().downloadWords());
    }

    public Collection<String> getSimplePublishers() {
        return MemoryPublisherRepository.get();
    }


//    @SneakyThrows
//    public void addWordsToTopic(String name, MultipartFile file) {
//        DefaultSimpleDictationTopic simpleTopic = (DefaultSimpleDictationTopic) memoryDictationPublisher.get().getTopicMap().get(name);
//        List<String> words = mediaService.getWordsFromPicture(file.getBytes()).getWords_result().stream().map(BaiduPicDetectiveDto.WordsResult::getWords).map(String::trim).collect(Collectors.toList());
//        simpleTopic.getWords().addAll(words);
//    }

    public List<String> getAllSubscriber() {
        return new ArrayList<>(memoryDictationPublisher.get().getSubscriber());
    }

    public Collection<String> getSimpleTopics(String publisher) {
        return MemoryPublisherRepository.get(publisher).getSimpleTopics();
    }

    public Collection<String> getSimpleWords(String publisher, String topic) {
        return MemoryPublisherRepository.get(publisher).getTopicMap().get(topic).getWords();
    }

    public void subscribe() {
        memoryDictationSubscriber.get().subscribe(memoryDictationPublisher.get());
    }

    public List<String> getTopicMap(String name) {
        return memoryDictationPublisher.get().getTopicMap().get(name).getWords();
    }
}
