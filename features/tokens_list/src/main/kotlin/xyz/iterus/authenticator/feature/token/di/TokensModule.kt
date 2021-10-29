package xyz.iterus.authenticator.feature.token.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import xyz.iterus.authenticator.feature.token.data.FakeTokenRepo
import xyz.iterus.authenticator.core.model.hotp.HOTP
import xyz.iterus.authenticator.core.model.hotp.RFC4226
import xyz.iterus.authenticator.core.model.totp.RFC6238
import xyz.iterus.authenticator.core.model.totp.Steam
import xyz.iterus.authenticator.core.model.totp.TOTP
import xyz.iterus.authenticator.feature.token.domain.repository.TokenRepo
import xyz.iterus.authenticator.feature.token.domain.usecase.*
import xyz.iterus.authenticator.feature.token.presentation.TokensViewModel
import xyz.iterus.authenticator.feature.token.presentation.list.TokensAdapter

object TokensModule {

    val module = module {

        factory { RFC4226() }
        factory { RFC6238(get()) }
        factory { Steam(get()) }
        factory<HOTP> { get<RFC4226>() }
        factory<TOTP> { get<RFC6238>() }

        single<TokenRepo> { FakeTokenRepo() }
        factory { GetTokensUseCase(get()) }
        factory { GetNextRefreshTimeUseCase() }

        viewModel { TokensViewModel(get(), get()) }

        factory { TokensAdapter() }
    }
}
