# kotlin-lotto-precourse

---

## 이번 프리코스 목표
- TDD 염두한 코드 작성
- 확장 함수 적극 활용
- 정확한 구조와 역할 분리

## 요구사항 Check
- [ ] indent 2까지만 허용
- [ ] 함수가 하나의 일만 하기
- [ ] 함수 15라인 이내 작성
- [ ] else 지양
- [ ] Enum 클래스 사용
- [ ] UI 로직 제외한 테스트 코드 작성
- [ ] 사용자가 잘못된 값을 입력할 경우 IllegalArgumentException을 발생시키고, "[ERROR]"로 시작하는 에러 메시지를 출력 후 그 부분부터 입력을 다시 받는다.

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
- enum class WinningLotto(당첨금: Int, 당첨개수: Int)
    - Three, Four, Five, FiveBonus, Six

## 테스트
- 로또 일치 시 현황 업데이트 되는가?

## 예외처리 (테스트)
- 구입 금액이 1,000원으로 나누어 떨어지지 않음
- 당첨 번호가 정수가 아님
- 당첨 번호가 6개가 아님
- 보너스 번호가 정수가 아님
- 보너스 번호가 1개가 아님
- ...