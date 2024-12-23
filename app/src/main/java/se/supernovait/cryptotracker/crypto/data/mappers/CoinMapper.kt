package se.supernovait.cryptotracker.crypto.data.mappers

import se.supernovait.cryptotracker.crypto.data.network.dto.CoinDto
import se.supernovait.cryptotracker.crypto.data.network.dto.CoinPriceDto
import se.supernovait.cryptotracker.crypto.domain.coin.Coin
import se.supernovait.cryptotracker.crypto.domain.coin.CoinPrice
import java.time.Instant
import java.time.ZoneId

fun CoinDto.toCoin(): Coin {
    return Coin(
        id = this.id,
        rank = this.rank,
        name = this.name,
        symbol = this.symbol,
        marketCapUsd = this.marketCapUsd,
        priceUsd = this.priceUsd,
        changePercent24Hr = this.changePercent24Hr
    )
}

fun CoinPriceDto.toCoinPrice(): CoinPrice {
    return CoinPrice(
        priceUsd = this.priceUsd,
        dateTime = Instant
            .ofEpochMilli(this.time)
            .atZone(ZoneId.systemDefault())
    )
}
