package sunflower.service;

import org.springframework.stereotype.Service;
import sunflower.configuration.UserContext;
import sunflower.entity.WordCard;
import sunflower.repository.WordCardRepository;

import java.util.List;

@Service
public class WordServiceImpl implements WordService {

    private WordCardRepository wordCardRepository;

    public WordServiceImpl(WordCardRepository wordCardRepository) {
        this.wordCardRepository = wordCardRepository;
    }

    @Override
    public WordCard save(WordCard wordCard) {
        wordCard.setCreatedBy(UserContext.getUser());
        wordCard.setDeleted(false);
        return wordCardRepository.save(wordCard);
    }

    @Override
    public WordCard update(WordCard wordCard) {
        return null;
    }

    @Override
    public void delete(long id) {
        wordCardRepository.deleteById(id);
    }

    @Override
    public void move(long id, boolean isMove) {
        wordCardRepository.moveCard(id, isMove);
    }

    @Override
    public List<WordCard> select() {
        return wordCardRepository.findAllByCreatedBy(UserContext.getUser());
    }

    @Override
    public List<WordCard> selectByEng(String eng) {
        return wordCardRepository.findAllByEngLikeAndCreatedByIs(eng, UserContext.getUser());
    }

    @Override
    public List<WordCard> selectByChi(String chi) {
        return wordCardRepository.findAllByChiLikeAndCreatedByIs(chi, UserContext.getUser());
    }

    @Override
    public List<WordCard> selectByChiOrEng(String target) {
        return wordCardRepository.findAllByEngAndChi(target, UserContext.getUser());
    }
}
