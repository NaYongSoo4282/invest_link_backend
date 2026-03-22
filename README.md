# invest_link_backend
investLink_source_code

with Front
{
  "current_price": 84000,
  "calculated_per": 15.0,
  "calculated_pbr": 1.62,
  "kospi_index": 2750.45,
  "chart_data": [
    {"date": "2026-03-20", "price": 82000},
    {"date": "2026-03-21", "price": 83500}
    // ... 1년 치 배열
  ]
}
GET /api/stocks/005930/summary

{
  "narrative_text": "현재 PBR은 과거 5년 평균 하단에 위치하며...",
  "updated_at": "2026-03-22T14:56:00"
}
GET /api/stocks/005930/narrative

**to AI JSON**
{
  "stock_code": "005930",
  "stock_name": "삼성전자",
  "real_time_context": {
    "current_price": 84000,   // AI가 헛소리 못하게 방금 긁어온 진짜 현재가 주입
    "eps": 5600               // DB에 있던 팩트 주입
  },
  "user_selected_indicators": [
    "EXCHANGE_RATE",          // 유저가 "환율 위주로 분석해줘!" 라고 선택한 정보
    "INTEREST_RATE"
  ]
}
POST http://ai-server-ip/api/analyz
