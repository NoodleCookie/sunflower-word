package sunflower.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sunflower.entity.Explain;

@Repository
public interface ExplainRepository extends JpaRepository<Explain, Long> {
}
