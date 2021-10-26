package sunflower.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sunflower.entity.WordCard;

import java.util.List;

@Repository
public interface WordCardRepository extends JpaRepository<WordCard, Long> {

    List<WordCard> findAllByCreatedByOrderByCreatedTimeDesc(String createdBy);

    @Query(value = "select w from WordCard w where lower(w.word) like %?1% and w.createdBy = ?2 order by w.createdTime desc ")
    List<WordCard> findAllByEngLikeAndCreatedByIs(String eng, String creator);

    @Query(value = "select w from WordCard w where w.keyExplain like %?1% and w.createdBy = ?2 order by w.createdTime desc ")
    List<WordCard> findAllByKeyExplainLikeAndCreatedByIsOrderByCreatedTimeDesc(String ch, String creator);

    @Override
    void deleteById(Long id);

    @Modifying
    @Query(value = "update WordCard w set w.deleted=true where w.id = ?1 and w.createdBy=?2")
    void logicDeleteWord(Long id, String creator);

    @Modifying
    @Query(value = "update WordCard w set w.collected=true where w.id = ?1 and w.createdBy=?2")
    void collectWord(Long id, String creator);

    List<WordCard> findAllByDeletedIsAndCreatedByIsOrderByCreatedTimeDesc(boolean delete, String creator);

}
