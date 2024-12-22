package se.supernovait.cryptotracker.crypto.domain.coin

import java.time.ZonedDateTime

data class CoinPrice(
    val priceUsd: Double,
    val dateTime: ZonedDateTime
)
