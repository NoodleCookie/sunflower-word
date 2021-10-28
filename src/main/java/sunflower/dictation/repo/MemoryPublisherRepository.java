package sunflower.dictation.repo;

import org.springframework.stereotype.Repository;
import sunflower.dictation.entity.MemoryDictationPublisher;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Repository
public class MemoryPublisherRepository {

    private final static Map<String, MemoryDictationPublisher> MAP = new HashMap<>();

    public static void store(MemoryDictationPublisher memoryDictationPublisher) {
        if (!MAP.containsKey(memoryDictationPublisher.getHost())) {
//            throw new RuntimeException("the publishers has exists");
            MAP.put(memoryDictationPublisher.getHost(), memoryDictationPublisher);
        }
    }

    public static void remove(String host) {
        MAP.remove(host);
    }

    public static Set<String> get() {
        return MAP.keySet();
    }

    public static MemoryDictationPublisher get(String publisher) {
        return MAP.get(publisher);
    }
}
