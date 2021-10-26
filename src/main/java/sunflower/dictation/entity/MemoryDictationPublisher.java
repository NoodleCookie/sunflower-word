package sunflower.dictation.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import sunflower.configuration.UserContext;

import java.util.HashMap;
import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@Component
@Data
public class MemoryDictationPublisher extends DefaultAbstractPublisher {

    private final Map<String, SimpleTopic> topicMap;

    public MemoryDictationPublisher() {
        this.topicMap = new HashMap<>();
    }

    @Override
    public String getHost() {
        return UserContext.getUser();
    }

    @Override
    public void after(SimpleTopic simpleTopic) {
//        this.topicMap.remove(simpleTopic.getName());
    }

    @Override
    public void before(SimpleTopic simpleTopic) {
        this.topicMap.put(simpleTopic.getName(),simpleTopic);
    }
}
