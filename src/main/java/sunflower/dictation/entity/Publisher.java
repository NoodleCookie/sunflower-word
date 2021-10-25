package sunflower.dictation.entity;

public interface Publisher {
    String getHost();

    void publish(SimpleTopic simpleTopic);
}
