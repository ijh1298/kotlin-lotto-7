# kotlin-lotto-precourse

---

## 이번 프리코스 목표
- TDD 염두한 코드 작성
- 확장 함수 적극 활용
- 정확한 구조와 역할 분리

## 기능
- 입력
    - 구입 금액 (1,000원으로 나누어 떨어질 것)
    - 당첨 번호 (쉼표를 기준으로 구분할 것)
    - 보너스 번호
- 출력
    - 8개의 로또 당첨 번호 리스트
    - 당첨 통계 (일치 개수, 수익률)
- LottoService
    - 당첨 번호 저장
    - 로또 생성
    - 당첨 로또 현황 업데이트

## 클래스
- Lotto 클래스
    - 6개가 아니면 throw IllegalArgumentException
    - 당첨 여부(일치 개수) 확인
    -
- enum class WinningLotto(당첨금: Int, 당첨개수: Int)
    - Three, Four, Five, FiveBonus, Six

## 예외처리
- 구입 금액이 1,000원으로 나누어 떨어지지 않음
- 당첨 번호가 정수가 아님
- 당첨 번호가 6개가 아님
- 보너스 번호가 정수가 아님
- 보너스 번호가 1개가 아님
- ...