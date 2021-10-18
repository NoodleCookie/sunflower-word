package sunflower.service;

import sunflower.entity.Explain;
import sunflower.entity.WordCard;

public interface ExplainService {

    WordCard insert(long id, Explain explain);

    void delete(long id);

    Integer update(Explain explain);
}
