package xyz.iterus.authenticator.di

import xyz.iterus.authenticator.feature.token.di.TokensModule

internal val koinModules = listOf(
    AppModule.module,
    TokensModule.module
)
