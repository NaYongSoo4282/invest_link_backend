package creative.invest_link_backend.repository;

import creative.invest_link_backend.domain.AiNarrative;
import creative.invest_link_backend.domain.KospiItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AiNarrativeRepository extends JpaRepository<AiNarrative, Long> {
    Optional<AiNarrative> findTopByKospiItemOrderByCreatedAtDesc(KospiItem kospiItem);
    //dynamic proxy??
}
