package sunflower.dictation.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sunflower.dictation.service.DictationService;
import sunflower.dictation.service.TopicService;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
public class TopicController {

    private final DictationService dictationService;
    private final TopicService topicService;

    public TopicController(DictationService dictationService, TopicService topicService) {
        this.dictationService = dictationService;
        this.topicService = topicService;
    }

    @PostMapping("/simple-publisher")
    public void initialSimplePublisher() {
        dictationService.initialSimplePublisher();
    }

    @PostMapping("/normal-publisher")
    public void initialPublisher() {
        topicService.initDaoPublisher();
    }

    @GetMapping("/simple-publishers")
    public Collection<String> getSimplePublishers() {
        return dictationService.getSimplePublishers();
    }

    @GetMapping("/normal-publishers")
    public Collection<String> getNormalPublishers() {
        return topicService.getNormalPublisher();
    }

    @PostMapping("/topic/dictation/simple/{name}")
    public void publishSimpleTopic(@PathVariable("name") String name, @RequestPart("file") MultipartFile file) {
        dictationService.publishSimpleTopic(name, file);
    }

    @PostMapping("/topic/dictation/normal/{name}")
    public void publishNormalTopic(@PathVariable("name") String name, @RequestPart("file") MultipartFile file) {
        topicService.publishNormalTopic(name, "nothing", file);
    }

    @GetMapping("/topic/simple/{publisher}")
    public Collection<String> getSimpleTopics(@PathVariable("publisher") String publisher) {
        return dictationService.getSimpleTopics(publisher);
    }

    @GetMapping("/topic/normal/{publisher}")
    public Collection<String> getNormalTopics(@PathVariable("publisher") String publisher) {
        return topicService.getNormalTopics(publisher);
    }

    @GetMapping("/topic/simple-words/{publisher}/{topic}")
    public Collection<String> getSimpleWords(@PathVariable("topic") String topic, @PathVariable("publisher") String publisher) {
        return dictationService.getSimpleWords(publisher, topic);
    }

    @GetMapping("/topic/normal-words/{publisher}/{topic}")
    public Collection<String> getNormalWords(@PathVariable("topic") String topic, @PathVariable("publisher") String publisher) {
        System.out.println(publisher);
        return topicService.getNormalWords(topic);
    }

//    @GetMapping("/topic/subscriber")
//    public Collection<String> getSubscribers() {
//        return dictationService.getAllSubscriber();
//    }
//
//    @PostMapping("/topic/subscriber")
//    public void subscribe() {
//        dictationService.subscribe();
//    }

    @PostMapping("/topic/test")
    public List<String> getWords(@RequestBody Map<String, String> name) {
        return dictationService.getTopicMap(name.get("topic"));
    }
}
