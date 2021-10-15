package sunflower.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sunflower.entity.WordCard;

import java.util.List;

@Repository
public interface WordCardRepository extends JpaRepository<WordCard, Long> {

    List<WordCard> findAllByCreatedBy(String createdBy);

    @Query(value = "select w from WordCard w where w.eng like %?1% and w.createdBy = ?2")
    List<WordCard> findAllByEngLikeAndCreatedByIs(String eng, String creator);

    @Query(value = "select w from WordCard w where w.chi like %?1% and w.createdBy = ?2")
    List<WordCard> findAllByChiLikeAndCreatedByIs(String chi, String creator);

    @Query(value = "select w from WordCard w where w.chi like %?1% or w.eng like %?1% and w.createdBy = ?2")
    List<WordCard> findAllByEngAndChi(String target, String creator);

    @Query(value = "update WordCard w set w.deleted=?2 where w.id=?1")
    void moveCard(long id, boolean move);

    @Override
    void deleteById(Long id);
}
