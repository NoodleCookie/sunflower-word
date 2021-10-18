package sunflower.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sunflower.configuration.UserContext;
import sunflower.entity.Explain;
import sunflower.entity.WordCard;
import sunflower.repository.ExplainRepository;
import sunflower.repository.WordCardRepository;

@Service
public class ExplainServiceImpl implements ExplainService {

    private ExplainRepository explainRepository;

    private WordCardRepository wordCardRepository;

    public ExplainServiceImpl(ExplainRepository explainRepository, WordCardRepository wordCardRepository) {
        this.explainRepository = explainRepository;
        this.wordCardRepository = wordCardRepository;
    }

    @Override
    public WordCard insert(long id, Explain explain) {
        explain.setCreatedBy(UserContext.getUser());
        WordCard word = wordCardRepository.findById(id).orElseThrow(() -> new RuntimeException("no such word"));
        explain.setWordCard(word);
        explainRepository.save(explain);
        return word;
    }

    @Override
    public void delete(long id) {
        explainRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Integer update(Explain explain) {
        return explainRepository.update(explain.getId(), explain.getCh(), explain.getType());
    }
}
