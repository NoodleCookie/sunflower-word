package sunflower.dictation.entity;

public interface DaoPublisher extends Publisher{
    String daoTopic(DaoDictationTopic daoTopic);

    void completeTopic();
}
