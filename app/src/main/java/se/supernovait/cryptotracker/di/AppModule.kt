package se.supernovait.cryptotracker.di

import io.ktor.client.engine.cio.CIO
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module
import se.supernovait.cryptotracker.core.data.network.HttpClientFactory
import se.supernovait.cryptotracker.crypto.data.network.RemoteCoinDataSource
import se.supernovait.cryptotracker.crypto.domain.coin.CoinDataSource
import se.supernovait.cryptotracker.crypto.presentation.coin_list.CoinListViewModel

val appModule = module {
    single { HttpClientFactory.create(CIO.create()) }
    singleOf(::RemoteCoinDataSource).bind<CoinDataSource>()

    viewModelOf(::CoinListViewModel)
}
