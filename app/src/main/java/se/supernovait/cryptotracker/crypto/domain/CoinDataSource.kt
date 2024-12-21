package se.supernovait.cryptotracker.crypto.domain

import se.supernovait.cryptotracker.core.domain.util.NetworkError
import se.supernovait.cryptotracker.core.domain.util.Result

interface CoinDataSource {
    suspend fun getCoins(): Result<List<Coin>, NetworkError>
}
