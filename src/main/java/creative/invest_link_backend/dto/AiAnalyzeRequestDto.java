package creative.invest_link_backend.dto;

import java.math.BigDecimal;
import java.util.List;

//to AI
public record AiAnalyzeRequestDto(
        String stockCode,
        String stockName,
        BigDecimal currentPrice,
        BigDecimal eps,
        List<String> userSelectedIndicators // [Exchange_Rate] ...
) {}
