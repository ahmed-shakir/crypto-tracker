package se.supernovait.cryptotracker.crypto.data.network

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import se.supernovait.cryptotracker.core.data.network.constructUrl
import se.supernovait.cryptotracker.core.data.network.safeCall
import se.supernovait.cryptotracker.core.domain.util.NetworkError
import se.supernovait.cryptotracker.core.domain.util.Result
import se.supernovait.cryptotracker.core.domain.util.map
import se.supernovait.cryptotracker.crypto.data.mappers.toCoin
import se.supernovait.cryptotracker.crypto.data.network.dto.CoinsResponseDto
import se.supernovait.cryptotracker.crypto.domain.Coin
import se.supernovait.cryptotracker.crypto.domain.CoinDataSource

class RemoteCoinDataSource(private val httpClient: HttpClient) : CoinDataSource {

    override suspend fun getCoins(): Result<List<Coin>, NetworkError> {
        return safeCall<CoinsResponseDto> {
            httpClient.get(urlString = constructUrl("/assets"))
        }.map { response ->
            response.data.map { it.toCoin() }
        }
    }
}
