package xyz.iterus.authenticator.di

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import xyz.iterus.authenticator.core.hotp.HOTP
import xyz.iterus.authenticator.core.hotp.RFC4226
import xyz.iterus.authenticator.core.token.TokenRepo
import xyz.iterus.authenticator.core.totp.RFC6238
import xyz.iterus.authenticator.core.totp.TOTP
import xyz.iterus.authenticator.core.token.GetTokensUseCase
import xyz.iterus.authenticator.data.TokenMockRepo
import xyz.iterus.authenticator.ui.tokens.TokensViewModel

val tokensModule = module {

    factory<HOTP> { RFC4226() }
    factory<TOTP> { RFC6238(get()) }

    single<TokenRepo> { TokenMockRepo(get()) }
    factory { GetTokensUseCase(get()) }

    viewModel { TokensViewModel(get()) }
}
