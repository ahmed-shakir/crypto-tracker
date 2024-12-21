package se.supernovait.cryptotracker.crypto.presentation.coin_list

import se.supernovait.cryptotracker.core.domain.util.NetworkError

sealed interface CoinListEvent {
    data class Error(val error: NetworkError): CoinListEvent
}