package creative.invest_link_backend.dto;

import java.math.BigDecimal;

//to Front with BackendServer API
public record StockSummaryResponseDto (
    String stockCode,
    String stockName,
    BigDecimal currentPrice,
    BigDecimal calculatedPer,
    BigDecimal calculatedPbr,
    BigDecimal kospiIndex
) {}
