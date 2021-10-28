package sunflower.dictation.entity;

import sunflower.configuration.UserContext;

import java.util.*;
import java.util.stream.Collectors;

public abstract class DefaultAbstractPublisher implements Publisher {

    Set<Subscriber> subscribers = new HashSet<>();

    public void add(Subscriber subscriber){
        subscribers.add(subscriber);
    }

    @Override
    public String getHost() {
        return UserContext.getUser();
    }

    @Override
    public Collection<String> getSubscriber() {
        return subscribers.stream().map(Subscriber::getName).collect(Collectors.toList());
    }

    public abstract void before(SimpleTopic simpleTopic);

    @Override
    public void publish(SimpleTopic simpleTopic) {
        before(simpleTopic);
        for (Subscriber subscriber : subscribers) {
            subscriber.process(simpleTopic);
        }
    }
}
