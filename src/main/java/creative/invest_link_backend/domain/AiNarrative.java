package creative.invest_link_backend.domain;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AiNarrative {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "narrative_id")
    private Long id;

    //[메모리 포인터 연결] : 이 내러티브가 어떤 종목의 것인가?
    //FetchType.LAZY : 지연 로딩. 실무 JPA의 절대 철칙이다.
    //N+1문제 발생할 수 있음, 사용하지 않으면
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id", nullable = false)
    private KospiItem kospiItem;

    //AI가 뱉어낼 핵심 텍스트 (MySQL TEXT 타입 매칭)
    @Column(columnDefinition = "TEXT", nullable = false)
    private String narrativeText;

    //[캐시 무효화의 핵심 키] 이 리포트가 쓰일 당시의 주가!
    @Column(precision = 19, scale = 2, nullable = false)
    private BigDecimal createdPrice;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;


    public static AiNarrative createNarrative(KospiItem item, String text, BigDecimal price){
        AiNarrative narrative = new AiNarrative();
        narrative.kospiItem = item;
        narrative.narrativeText = text;
        narrative.createdPrice = price;
        narrative.createdAt = LocalDateTime.now();
        return narrative;
    }
}
