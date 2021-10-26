package sunflower.dictation.entity;

import sunflower.configuration.UserContext;

public abstract class DefaultAbstractSubscriber implements Subscriber {

    @Override
    public String getName() {
        return UserContext.getUser();
    }

    @Override
    public void subscribe(Publisher publisher) {
        publisher.add(this);
    }
}
