package sunflower.dictation.entity;

import java.util.*;

public abstract class DefaultAbstractPublisher implements Publisher {

    Set<Subscriber> subscribers = new HashSet<>();

    public void add(Subscriber subscriber){
        subscribers.add(subscriber);
    }

    @Override
    public Collection<? extends Subscriber> getSubscriber() {
        return subscribers;
    }

    public void before(SimpleTopic simpleTopic){};
    public void after(SimpleTopic simpleTopic){};

    @Override
    public void publish(SimpleTopic simpleTopic) {
        before(simpleTopic);
        for (Subscriber subscriber : subscribers) {
            subscriber.process(simpleTopic);
        }
        after(simpleTopic);
    }
}
