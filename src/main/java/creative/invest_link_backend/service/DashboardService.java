package creative.invest_link_backend.service;


import creative.invest_link_backend.domain.KospiItem;
import creative.invest_link_backend.dto.StockSummaryResponseDto;
import creative.invest_link_backend.repository.KospiItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true) // 읽기 전용으로 만든다 : 이게 그거였나? DB가 검증하지 않는거?
public class DashboardService {

    private final KospiItemRepository kospiItemRepository;

    public StockSummaryResponseDto getStockSummary(String stockCode) {
        //종목 찾기
        KospiItem item = kospiItemRepository.findByStockCode(stockCode).
                orElseThrow(() -> new IllegalArgumentException("해당 종목이 없습니다." + stockCode));

        //외부 API에서 실시간 주가 가져오기 (임시 하드코딩) - 나중에 한투 API 붙일 곳
        BigDecimal currentPrice = new BigDecimal("84000");
        BigDecimal kospiIndex = new BigDecimal("2750.45");

        //domain 캡슐화의 위력?? 이게 무슨 말이지.
        BigDecimal realtimePer = item.calculateRealTimePer(currentPrice);
        BigDecimal realtimePbr = item.calculateRealTimePbr(currentPrice);

        return new StockSummaryResponseDto(
                item.getStockCode(),
                item.getStockName(),
                currentPrice,
                realtimePer,
                realtimePbr,
                kospiIndex
        );
    }
}
