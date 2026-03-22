package creative.invest_link_backend.repository;

import creative.invest_link_backend.domain.KospiItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface KospiItemRepository extends JpaRepository<KospiItem, Long> {
    Optional<KospiItem> findByStockCode(String stockCode);
}
