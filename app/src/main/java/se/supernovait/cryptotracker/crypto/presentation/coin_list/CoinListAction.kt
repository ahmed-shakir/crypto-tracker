package se.supernovait.cryptotracker.crypto.presentation.coin_list

import se.supernovait.cryptotracker.crypto.presentation.models.CoinUi

sealed interface CoinListAction {
    data class OnCoinClick(val coinUi: CoinUi): CoinListAction
}