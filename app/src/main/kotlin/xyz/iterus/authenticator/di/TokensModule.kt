package xyz.iterus.authenticator.di

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import xyz.iterus.authenticator.core.token.GetNextRefreshTimeUseCase
import xyz.iterus.authenticator.core.token.hotp.HOTP
import xyz.iterus.authenticator.core.token.hotp.RFC4226
import xyz.iterus.authenticator.core.token.TokenRepo
import xyz.iterus.authenticator.core.token.totp.RFC6238
import xyz.iterus.authenticator.core.token.totp.TOTP
import xyz.iterus.authenticator.core.token.GetTokensUseCase
import xyz.iterus.authenticator.core.token.totp.SteamOTP
import xyz.iterus.authenticator.data.TokenMockRepo
import xyz.iterus.authenticator.ui.tokens.list.TokensAdapter
import xyz.iterus.authenticator.ui.tokens.TokensViewModel

val tokensModule = module {

    factory<HOTP> { RFC4226() }
    factory<TOTP> { RFC6238(get()) }
    factory { RFC6238(get()) }
    factory { SteamOTP(get()) }

    single<TokenRepo> { TokenMockRepo(get(), get(), get()) }
    factory { GetTokensUseCase(get()) }
    factory { GetNextRefreshTimeUseCase() }

    viewModel { TokensViewModel(get(), get()) }
    factory { TokensAdapter() }
}
