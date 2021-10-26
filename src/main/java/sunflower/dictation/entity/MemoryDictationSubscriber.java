package sunflower.dictation.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@EqualsAndHashCode(callSuper = true)
@Component
@Data
public class MemoryDictationSubscriber extends DefaultAbstractSubscriber{

    @Override
    public void process(SimpleTopic simpleTopic) {

    }
}
