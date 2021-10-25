package sunflower.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sunflower.entity.WordAudio;
import sunflower.entity.WordCard;

import java.util.List;
import java.util.Set;

@Repository
public interface WordAudioRepository extends JpaRepository<WordAudio, String> {

    @Query("select w.word from WordAudio w")
    Set<String> getAllKey();
}
