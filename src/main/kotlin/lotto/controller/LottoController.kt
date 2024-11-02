package lotto.controller

import lotto.domain.entity.Lotto
import lotto.domain.entity.Lotto.Companion.toLottoNumbers
import lotto.view.InputView

class LottoController(private val view: InputView) {
    private var price: Int = 0
    private lateinit var winLotto: Lotto
    private var bonusNumber: Int = 0

    fun run() {
        price = getValidPurchasePrice(view.getPurchasePrice())
        winLotto = getValidWinningNumbers(view.getWinningNumbers())
        bonusNumber = getValidBonusNumber(view.getBonusNumber())
    }

    private fun getValidPurchasePrice(input: String): Int {
        while (true) {
            try {
                require(input.isValidNumber()) { INVALID_INT_NUMBER_EXCEPTION_MSG }
                require(!input.isZero()) { NOT_ZERO_EXCEPTION_MSG }

                return input.toInt()
            } catch (e: IllegalArgumentException) {
                println(e.message)
            }
        }
    }

    private fun getValidWinningNumbers(input: String): Lotto {
        while (true) {
            try {
                require(input.isValidNumbers()) { INVALID_NUMBERS_EXCEPTION_MSG }
                val inputNumbers = input.split(DELIMITER).map { it.toInt() }
                return inputNumbers.toLottoNumbers()
            } catch (e: IllegalArgumentException) {
                println(e.message)
            }
        }
    }

    private fun getValidBonusNumber(input: String): Int {
        while (true) {
            try {
                require(input.isValidNumber()) { INVALID_INT_NUMBER_EXCEPTION_MSG }
                require(!input.isZero()) { NOT_ZERO_EXCEPTION_MSG }
                require(input.toInt() in MIN_LOTTO_NUMBER..MAX_LOTTO_NUMBER) { OVER_RANGE_EXCEPTION_MSG }

                return input.toInt()
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

        private const val DELIMITER = ','
        private const val MIN_LOTTO_NUMBER = 1
        private const val MAX_LOTTO_NUMBER = 45

        private const val INVALID_INT_NUMBER_EXCEPTION_MSG = "[ERROR] 유효하지 않은 정수입니다."
        private const val INVALID_NUMBERS_EXCEPTION_MSG = "[ERROR] 유효하지 않은 당첨 번호 리스트입니다."
        private const val NOT_ZERO_EXCEPTION_MSG = "[ERROR] 구입 금액은 0원보다 커야 합니다."
        private const val OVER_RANGE_EXCEPTION_MSG = "[ERROR] 로또 번호는 1 이상 45 이하여야 합니다."
    }
}