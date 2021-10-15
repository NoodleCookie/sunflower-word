package sunflower.service;

import sunflower.entity.WordCard;

import java.util.List;

public interface WordService {
    WordCard save(WordCard wordCard);

    WordCard update(WordCard wordCard);

    void move(long id, boolean isMove);

    void delete(long id);

    List<WordCard> select();

    List<WordCard> selectByEng(String eng);

    List<WordCard> selectByChi(String chi);

    List<WordCard> selectByChiOrEng(String target);

}
