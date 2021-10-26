package sunflower.dictation.entity;

import java.util.Collection;

public interface Publisher {
    String getHost();

    Collection<? extends Subscriber> getSubscriber();

    void add(Subscriber subscriber);

    void publish(SimpleTopic simpleTopic);


}
