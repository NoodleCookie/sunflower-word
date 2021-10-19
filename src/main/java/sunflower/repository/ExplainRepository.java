package sunflower.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sunflower.entity.Explain;
import sunflower.entity.WordCard;

import java.util.List;

@Repository
public interface ExplainRepository extends JpaRepository<Explain, Long> {

    @Modifying
    @Query("update Explain e set e.ch=?2 , e.type=?3 where e.id=?1")
    Integer update(long id, String ch, Explain.PartOfSpeech type);

    @Query("select e.wordCard from Explain e where e.ch like %?1%")
    List<WordCard> findWordIdWhereChLike(String ch);
}
