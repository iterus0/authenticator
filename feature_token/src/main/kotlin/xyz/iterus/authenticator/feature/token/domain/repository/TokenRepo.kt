package xyz.iterus.authenticator.feature.token.domain.repository

import xyz.iterus.authenticator.feature.token.domain.model.Token

interface TokenRepo {

    suspend fun get(): List<Token>

    suspend fun add(token: Token)

    suspend fun delete(token: Token)

    suspend fun update(token: Token)
}
