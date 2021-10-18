package sunflower.service;

import sunflower.entity.WordCard;

import java.util.List;

public interface WordService {
    WordCard save(WordCard wordCard);

    WordCard update(WordCard wordCard);

    void delete(long id);

    List<WordCard> select();

}
