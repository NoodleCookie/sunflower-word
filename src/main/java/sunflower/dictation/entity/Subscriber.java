package sunflower.dictation.entity;

public interface Subscriber {

    void subscribe(Publisher publisher);

    String getName();

    void process(SimpleTopic simpleTopic);
}
