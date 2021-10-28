package sunflower.dictation.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import sunflower.configuration.UserContext;
import sunflower.dictation.repo.MemoryPublisherRepository;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
public class MemoryDictationPublisher extends DefaultAbstractPublisher {

    private final Map<String, SimpleTopic> topicMap;

    public MemoryDictationPublisher() {
        this.topicMap = new HashMap<>();
    }

    @PostConstruct
    public void initial() {
        MemoryPublisherRepository.store(this);
    }

    @Override
    public String getHost() {
        return "simple-dictation-publisher-" + UserContext.getUser();
    }

    @Override
    public void before(SimpleTopic simpleTopic) {
        this.topicMap.put(simpleTopic.getName(), simpleTopic);
    }

    public Set<String> getSimpleTopics() {
        return topicMap.keySet();
    }
}
