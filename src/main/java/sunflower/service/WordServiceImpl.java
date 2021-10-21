package sunflower.service;

import org.springframework.stereotype.Service;
import sunflower.configuration.UserContext;
import sunflower.entity.Explain;
import sunflower.entity.WordCard;
import sunflower.repository.ExplainRepository;
import sunflower.repository.WordCardRepository;

import java.util.List;

@Service
public class WordServiceImpl implements WordService {

    private ExplainRepository explainRepository;

    private WordCardRepository wordCardRepository;

    public WordServiceImpl(ExplainRepository explainRepository, WordCardRepository wordCardRepository) {
        this.explainRepository = explainRepository;
        this.wordCardRepository = wordCardRepository;
    }


    @Override
    public WordCard save(WordCard wordCard) {
        wordCard.setCreatedBy(UserContext.getUser());
        wordCard.setDeleted(false);
        List<Explain> explains = wordCard.getExplains();
        explains.forEach(explain -> explain.setCreatedBy(UserContext.getUser()));
        explainRepository.saveAll(explains);
        return wordCardRepository.save(wordCard);
    }

    @Override
    public void update(WordCard wordCard) {
        save(wordCard);
    }

    @Override
    public void delete(long id) {
        wordCardRepository.deleteById(id);
    }

    @Override
    public List<WordCard> select() {
        return wordCardRepository.findAllByCreatedByOrderByCreatedTimeDesc(UserContext.getUser());
    }

    @Override
    public List<WordCard> findByWord(String word) {
        return wordCardRepository.findAllByEngLikeAndCreatedByIs(word, UserContext.getUser());
    }

    @Override
    public List<WordCard> findByCh(String ch) {

        return null;
    }
}
