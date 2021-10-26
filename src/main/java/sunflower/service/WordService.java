package sunflower.service;

import sunflower.entity.WordCard;

import java.util.List;

public interface WordService {
    WordCard save(WordCard wordCard);

    void update(WordCard wordCard);

    void delete(long id);

    void logicDelete(long id);

    void collectWord(long id);

    List<WordCard> select();

    List<WordCard> findByWord(String word);

    List<WordCard> findByCh(String ch);

    List<WordCard> findDeletedWord();

    List<WordCard> findCollectedWord();
}
