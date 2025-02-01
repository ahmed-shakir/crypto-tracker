package se.supernovait.cryptotracker.crypto.data.network

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import se.supernovait.cryptotracker.core.data.network.constructUrl
import se.supernovait.cryptotracker.core.data.network.safeCall
import se.supernovait.cryptotracker.core.domain.util.NetworkError
import se.supernovait.cryptotracker.core.domain.util.Result
import se.supernovait.cryptotracker.core.domain.util.map
import se.supernovait.cryptotracker.crypto.data.mappers.toCoin
import se.supernovait.cryptotracker.crypto.data.mappers.toCoinPrice
import se.supernovait.cryptotracker.crypto.data.network.dto.CoinHistoryDto
import se.supernovait.cryptotracker.crypto.data.network.dto.CoinsResponseDto
import se.supernovait.cryptotracker.crypto.domain.coin.Coin
import se.supernovait.cryptotracker.crypto.domain.coin.CoinDataSource
import se.supernovait.cryptotracker.crypto.domain.coin.CoinPrice
import java.time.ZoneId
import java.time.ZonedDateTime

class RemoteCoinDataSource(private val httpClient: HttpClient) : CoinDataSource {
    private val zoneIdUTC = "UTC"

    override suspend fun getCoins(): Result<List<Coin>, NetworkError> {
        return safeCall<CoinsResponseDto> {
            httpClient.get(urlString = constructUrl("/assets"))
        }.map { response ->
            response.data.map { it.toCoin() }
        }
    }

    override suspend fun getCoinHistory(
        coinId: String,
        start: ZonedDateTime,
        end: ZonedDateTime
    ): Result<List<CoinPrice>, NetworkError> {
        val startMs = start
            .withZoneSameInstant(ZoneId.of(zoneIdUTC))
            .toInstant()
            .toEpochMilli()
        val endMs = end
            .withZoneSameInstant(ZoneId.of(zoneIdUTC))
            .toInstant()
            .toEpochMilli()
        return safeCall<CoinHistoryDto> {
            httpClient.get(urlString = constructUrl("/assets/$coinId/history")) {
                parameter("interval", "h6")
                parameter("start", startMs)
                parameter("end", endMs)
            }
        }.map { response ->
            response.data.map { it.toCoinPrice() }
        }
    }
}
