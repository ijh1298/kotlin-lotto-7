package lotto.controller

import lotto.domain.entity.Lotto
import lotto.domain.entity.Lotto.Companion.toLottoNumbers
import lotto.service.LottoService
import lotto.view.InputView
import lotto.view.OutputView

class LottoController(
    private val inputView: InputView,
    private val outputView: OutputView,
    private val service: LottoService,
) {
    private var purchasePrice: Int = 0
    private lateinit var winLotto: Lotto
    private var bonusNumber: Int = 0

    fun run() {
        purchasePrice = loopUntilValid { getValidPurchasePrice(inputView.getPurchasePrice()) }
        val lottos = service.purchaseLottos(purchasePrice)
        outputView.showRandomLottos(lottos)

        winLotto = loopUntilValid { getValidWinningLotto(inputView.getWinningNumbers()) }
        bonusNumber = loopUntilValid { getValidBonusNumber(inputView.getBonusNumber()) }

        service.matchAllLotto(winLotto, lottos, bonusNumber)
        outputView.showStatus()

        val resultMoney = service.getResultMoney()
        val profitRate = service.getProfitRate(purchasePrice, resultMoney)
        outputView.showProfitRate(profitRate)
    }

    private fun getValidPurchasePrice(input: String): Int {
        require(input.isValidNumber()) { INVALID_INT_NUMBER_EXCEPTION_MSG }
        require(input.toInt() >= MIN_PURCHASE_PRICE && input.toInt() % 1000 == 0) { INVALID_PRICE_RANGE_EXCEPTION_MSG }
        return input.toInt()
    }

    private fun getValidWinningLotto(input: String): Lotto {
        require(input.isValidNumbers()) { INVALID_NUMBERS_EXCEPTION_MSG }
        val inputNumbers = input.split(DELIMITER).map { it.toInt() }
        return inputNumbers.toLottoNumbers()
    }

    private fun getValidBonusNumber(input: String): Int {
        require(input.isValidNumber()) { INVALID_INT_NUMBER_EXCEPTION_MSG }
        require(input.toInt() in MIN_LOTTO_NUMBER..MAX_LOTTO_NUMBER) { OVER_RANGE_EXCEPTION_MSG }
        require(input.toInt() !in winLotto.nums) { DUPLICATE_BONUS_NUMBER_EXCEPTION_MSG }
        return input.toInt()
    }

    private fun <T> loopUntilValid(action: () -> T): T {
        while (true) {
            try {
                return action()
            } catch (e: IllegalArgumentException) {
                println(e.message)
            }
        }
    }

    companion object {
        private fun String.isValidNumber() = this.all { it.isDigit() }

        private fun String.isZero() = this.toInt() == 0

        private fun String.isValidNumbers(): Boolean =
            try {
                this.split(',').map { it.toInt() }
                true
            } catch (e: NumberFormatException) {
                false
            }

        private fun String.isNoDuplicateNumbers(): Boolean {
            val numbers = this.split(',').map { it.toInt() }
            return numbers.toSet().size == 6
        }

        private const val DELIMITER = ','
        private const val MIN_LOTTO_NUMBER = 1
        private const val MAX_LOTTO_NUMBER = 45
        private const val MIN_PURCHASE_PRICE = 1000

        private const val INVALID_INT_NUMBER_EXCEPTION_MSG = "[ERROR] 유효하지 않은 정수입니다."
        private const val INVALID_NUMBERS_EXCEPTION_MSG = "[ERROR] 유효하지 않은 당첨 번호 리스트입니다."
        private const val OVER_RANGE_EXCEPTION_MSG = "[ERROR] 로또 번호는 1부터 45 사이의 숫자여야 합니다."
        private const val INVALID_PRICE_RANGE_EXCEPTION_MSG = "[ERROR] 구입 금액은 1000원 이상의 1000으로 나누어지는 금액이어야 합니다."
        private const val DUPLICATE_BONUS_NUMBER_EXCEPTION_MSG = "[ERROR] 보너스 번호가 당첨 번호에 있습니다."
    }
}