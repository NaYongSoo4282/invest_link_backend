package creative.invest_link_backend.contorller;

import creative.invest_link_backend.dto.StockSummaryResponseDto;
import creative.invest_link_backend.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/stocks")
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping("/{stockCode}/summary")
    public ResponseEntity<StockSummaryResponseDto> getStockSummary(@PathVariable String stockCode){

        StockSummaryResponseDto responseDto = dashboardService.getStockSummary(stockCode);

        return ResponseEntity.ok(responseDto);
    }

    @PostMapping("/{stockCode}/analyze-trigger")
    public ResponseEntity<String> triggerAiAnalysis(@PathVariable String stockCode) {

        // (실제 로직: 여기서 스프링 부트가 WebClient를 써서 파이썬 서버로 HTTP POST 요청을 쏩니다.)
        System.out.println("🚀 [백엔드 -> AI 서버] " + stockCode + " 분석 요청 전송 완료!");
        System.out.println("전송된 데이터(JSON): { \"stockCode\": \"" + stockCode + "\", \"currentPrice\": 84000, ... }");

        // 프론트엔드에게는 "AI한테 시켜놨으니 15초 뒤에 다시 와라!" 라고 응답합니다. (비동기 처리)
        return ResponseEntity.ok("AI 분석 요청이 트리거 되었습니다. 잠시 후 결과를 조회하세요.");
    }

    // 2. AI가 분석을 끝내고 프론트가 결과를 찾으러 왔을 때! (GET 요청)
    @GetMapping("/{stockCode}/narrative")
    public ResponseEntity<creative.invest_link_backend.dto.AiNarrativeResponseDto> getAiNarrative(@PathVariable String stockCode) {

        // (실제 로직: DB의 AiNarrative 테이블에서 가장 최신 캐시를 꺼내옵니다.)
        System.out.println("📥 [DB -> 프론트] " + stockCode + " AI 내러티브 캐시 조회!");

        // 임시 가짜 데이터 (Mock) 생성
        creative.invest_link_backend.dto.AiNarrativeResponseDto mockResponse =
                new creative.invest_link_backend.dto.AiNarrativeResponseDto(
                        "최근 3일간 외인 매수세가 유입되며 하방을 다지고 있습니다. PBR 1.6배로 역사적 저평가 구간입니다.",
                        java.time.LocalDateTime.now()
                );

        return ResponseEntity.ok(mockResponse);
    }
}
