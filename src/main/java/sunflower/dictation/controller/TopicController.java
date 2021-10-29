package sunflower.dictation.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sunflower.dictation.service.SimpleDictationService;
import sunflower.dictation.service.NormalDictationService;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
public class TopicController {

    private final SimpleDictationService simpleDictationService;
    private final NormalDictationService normalDictationService;

    public TopicController(SimpleDictationService simpleDictationService, NormalDictationService normalDictationService) {
        this.simpleDictationService = simpleDictationService;
        this.normalDictationService = normalDictationService;
    }

    @PostMapping("/simple-publisher")
    public void initialSimplePublisher() {
        simpleDictationService.initialSimplePublisher();
    }

    @PostMapping("/normal-publisher")
    public void initialPublisher() {
        normalDictationService.initDaoPublisher();
    }

    @GetMapping("/simple-publishers")
    public Collection<String> getSimplePublishers() {
        return simpleDictationService.getSimplePublishers();
    }

    @GetMapping("/normal-publishers")
    public Collection<String> getNormalPublishers() {
        return normalDictationService.getNormalPublisher();
    }

    @PostMapping("/topic/dictation/simple/{name}")
    public void publishSimpleTopic(@PathVariable("name") String name, @RequestPart("file") MultipartFile file) {
        simpleDictationService.publishSimpleTopic(name, file);
    }

    @PostMapping("/topic/dictation/normal/{name}")
    public void publishNormalTopic(@PathVariable("name") String name, @RequestPart("file") MultipartFile file) {
        normalDictationService.publishNormalTopic(name, "nothing", file);
    }

    @GetMapping("/topic/simple/{publisher}")
    public Collection<String> getSimpleTopics(@PathVariable("publisher") String publisher) {
        return simpleDictationService.getSimpleTopics(publisher);
    }

    @GetMapping("/topic/normal/{publisher}")
    public Collection<String> getNormalTopics(@PathVariable("publisher") String publisher) {
        return normalDictationService.getNormalTopics(publisher);
    }

    @GetMapping("/topic/simple-words/{publisher}/{topic}")
    public Collection<String> getSimpleWords(@PathVariable("topic") String topic, @PathVariable("publisher") String publisher) {
        return simpleDictationService.getSimpleWords(publisher, topic);
    }

    @GetMapping("/topic/normal-words/{publisher}/{topic}")
    public Collection<String> getNormalWords(@PathVariable("topic") String topic, @PathVariable("publisher") String publisher) {
        System.out.println(publisher);
        return normalDictationService.getNormalWords(topic);
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
        return simpleDictationService.getTopicMap(name.get("topic"));
    }
}
