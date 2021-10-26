package sunflower.dictation.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sunflower.dictation.service.DictationService;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
public class TopicController {

    private DictationService dictationService;

    public TopicController(DictationService dictationService) {
        this.dictationService = dictationService;
    }

    @PostMapping("/topic/dictation/{name}")
    public void publishTopic(@PathVariable("name") String name, @RequestPart("file") MultipartFile file) {
        dictationService.publish(name, file);
    }

    @PatchMapping("/topic/dictation/{name}")
    public void addWordsToTopic(@PathVariable("name") String name, @RequestPart("file") MultipartFile file) {
        dictationService.addWordsToTopic(name, file);
    }

    @GetMapping("/topic")
    public Collection<String> getTopic() {
        return dictationService.getAllTopic();
    }

    @GetMapping("/topic/subscriber")
    public Collection<String> getSubscribers() {
        return dictationService.getAllSubscriber();
    }

    @PostMapping("/topic/subscriber")
    public void subscribe() {
        dictationService.subscribe();
    }

    @PostMapping("/topic/test")
    public List<String> getWords(@RequestBody Map<String,String> name){
        return dictationService.getTopicMap(name.get("topic"));
    }
}
