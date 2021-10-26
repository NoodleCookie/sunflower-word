package sunflower.dictation.service;

import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import sunflower.dictation.entity.*;
import sunflower.dto.BaiduPicDetectiveDto;
import sunflower.service.MediaService;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DictationService {

    private final MemoryDictationPublisher memoryDictationPublisher;
    private final MemoryDictationSubscriber memoryDictationSubscriber;
    private final MediaService mediaService;


    public DictationService(MemoryDictationPublisher memoryDictationPublisher, MemoryDictationSubscriber memoryDictationSubscriber, MediaService mediaService) {
        this.memoryDictationPublisher = memoryDictationPublisher;
        this.memoryDictationSubscriber = memoryDictationSubscriber;
        this.mediaService = mediaService;
    }

    @SneakyThrows
    public void publish(String name, MultipartFile file) {
        List<String> words = mediaService.getWordsFromPicture(file.getBytes()).getWords_result().stream().map(BaiduPicDetectiveDto.WordsResult::getWords).collect(Collectors.toList());
        memoryDictationPublisher.publish(DefaultSimpleDictationTopic.builder().mediaService(mediaService).words(words).name(name).build().downloadWords());
    }

    @SneakyThrows
    public void addWordsToTopic(String name, MultipartFile file) {
        DefaultSimpleDictationTopic simpleTopic = (DefaultSimpleDictationTopic) memoryDictationPublisher.getTopicMap().get(name);
        List<String> words = mediaService.getWordsFromPicture(file.getBytes()).getWords_result().stream().map(BaiduPicDetectiveDto.WordsResult::getWords).collect(Collectors.toList());
        simpleTopic.getWords().addAll(words);
    }

    public List<String> getAllSubscriber() {
        return memoryDictationPublisher.getSubscriber().stream().map(Subscriber::getName).collect(Collectors.toList());
    }

    public Collection<String> getAllTopic() {
        return memoryDictationPublisher.getTopicMap().keySet();
    }

    public void subscribe() {
        memoryDictationSubscriber.subscribe(memoryDictationPublisher);
    }

    public List<String> getTopicMap(String name){
        return memoryDictationPublisher.getTopicMap().get(name).getWords();
    }
}
