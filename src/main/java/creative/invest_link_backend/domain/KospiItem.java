package creative.invest_link_backend.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class KospiItem {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long id; //DB 내부용 PK

    @Column(unique = true, nullable = false, length = 10)
    private String stockCode; //종목 코드

    @Column(nullable = false, length = 50)
    private String stockName;// 종목 명

    //3개월 마다 업데이트 할 예정
    @Column(precision = 19, scale = 2)
    private BigDecimal eps; //주당 순이익

    @Column(precision = 19, scale = 2)
    private BigDecimal bps; //주당 순자산

    //정적 팩토리 메서드
    public static KospiItem createItem(String stockCode, String stockName, BigDecimal eps, BigDecimal bps) {
        //static 메서드니까, 바로 사용 가능하겠지.
        // 외부에서 create 못하게 만들었네. 무조건 메서드로 생성할 수 있게 했군

        KospiItem item = new KospiItem();
        item.stockCode = stockCode;
        item.stockName = stockName;
        item.eps = eps;
        item.bps = bps;
        return item;
    }

    public BigDecimal calculateRealTimePer(BigDecimal currentPrice) {
        if (this.eps == null || this.eps.compareTo(BigDecimal.ZERO) <= 0) {
            return BigDecimal.ZERO;
        }
        return currentPrice.divide(this.eps, 2, RoundingMode.HALF_UP);
    }

    public BigDecimal calculateRealTimePbr(BigDecimal currentPrice) {
        if(this.bps == null || this.bps.compareTo(BigDecimal.ZERO) <= 0) {
            return BigDecimal.ZERO;
        }
        return currentPrice.divide(this.bps, 2, RoundingMode.HALF_UP);
    }
}
