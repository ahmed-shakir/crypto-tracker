package se.supernovait.cryptotracker.crypto.data.mappers

import se.supernovait.cryptotracker.crypto.data.network.dto.CoinDto
import se.supernovait.cryptotracker.crypto.domain.Coin

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
