package sunflower.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sunflower.configuration.UserContext;
import sunflower.entity.Explain;
import sunflower.entity.WordCard;
import sunflower.repository.ExplainRepository;
import sunflower.repository.WordCardRepository;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

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
        wordCard.setCollected(false);
        List<Explain> explains = wordCard.getExplains();
        explains.forEach(explain -> explain.setCreatedBy(UserContext.getUser()));
        explainRepository.saveAll(explains);
        wordCard.setKeyExplain(explains.stream().map(Explain::getCh).collect(Collectors.joining(";")));
        return wordCardRepository.save(wordCard);
    }

    @Override
    public void update(WordCard wordCard) {
        save(wordCard);
    }

    @Override
    public WordCard query(long id) {
        Optional<WordCard> optionalWordCard = wordCardRepository.findById(id);
        return optionalWordCard.orElseGet(WordCard::new);
    }

    @Override
    public void delete(long id) {
        wordCardRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void logicDelete(long id) {
        wordCardRepository.logicDeleteWord(id,UserContext.getUser());
    }

    @Override
    @Transactional
    public void collectWord(long id) {
        if (!wordCardRepository.findById(id).get().isCollected()) {
            wordCardRepository.collectWord(id,UserContext.getUser());
        }else {
            wordCardRepository.cancelCollectWord(id,UserContext.getUser());
        }
    }

    @Override
    public List<WordCard> select() {
        return wordCardRepository.findAllByCreatedByOrderByCreatedTimeDesc(UserContext.getUser()).stream().filter(w-> !w.isDeleted()).collect(Collectors.toList());
    }

    @Override
    public List<WordCard> findByWord(String word) {
        return wordCardRepository.findAllByEngLikeAndCreatedByIs(word.toLowerCase(Locale.ROOT), UserContext.getUser()).stream().filter(w-> !w.isDeleted()).collect(Collectors.toList());
    }

    @Override
    public List<WordCard> findByCh(String ch) {
        return wordCardRepository.findAllByKeyExplainLikeAndCreatedByIsOrderByCreatedTimeDesc(ch, UserContext.getUser()).stream().filter(w-> !w.isDeleted()).collect(Collectors.toList());
    }

    @Override
    public List<WordCard> findDeletedWord() {
        return wordCardRepository.findAllByDeletedIsAndCreatedByIsOrderByCreatedTimeDesc(true,UserContext.getUser());
    }

    @Override
    public List<WordCard> findCollectedWord() {
        return wordCardRepository.findAllByCollectedIsAndCreatedByIsAndDeletedIsFalseOrderByCreatedTimeDesc(true,UserContext.getUser());
    }
}
