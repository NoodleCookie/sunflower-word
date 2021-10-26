package sunflower.dictation.entity;

import lombok.Builder;
import lombok.Data;
import sunflower.configuration.UserContext;
import sunflower.service.MediaService;

import java.util.List;

@Data
@Builder
public class DefaultSimpleDictationTopic implements SimpleTopic {

    private String name;

    private List<String> words;

    private MediaService mediaService;

    public DefaultSimpleDictationTopic downloadWords(){
        mediaService.downloadAudio(words);
        return this;
    }

    @Override
    public String getName() {
        return "[" + UserContext.getUser() + "]:DefaultSimpleDictationTopic:"+name;
    }



}
