package creative.invest_link_backend.dto;

import java.math.BigDecimal;

public record AiAnalyzeResponseDto(
        String stockCode,
        String aiNarrative,
        BigDecimal per,
        BigDecimal pbr,
        BigDecimal operatingProfitYoy
) {}
//Record 는 불변객체이며, 가볍다. 알아서 해준다는 장점이 있다. 무엇인지는 확실히 공부할 것.
/*
자바 14부터 도입됨.
과거에는 private final 변수 도입 후, Getter 선언하였고, equals와 hashCode를 재정의하는 코드를 구현해야 헀다.
->
Lombok의 등장
화나서 롬복을 만들었다. 하지만, 이것은 꼼수에 가깝다.
AST(이게 뭐지)를 강제로 조작해서 코드를 우겨넣는 꼼수였다.
->
C언어의 struct 처럼 순수하게 데이터만 나르는 불변 객체 전용 문법이 탄생한 것이다.


==> 작동 방식

final class로 만들고, java.lang.Record를 강제 상속시킨다.
불변 메모리 할당 : 모든 변수를 private fianl 로 선언하여, 힙 메모리에 올라가는 순간 Read-only로 박제한다.
접근자(get..) 자동생성한다.

TradeOff -> GC에 의해 정리되기 전까지 ㅅ줭할 수 없다.
 */