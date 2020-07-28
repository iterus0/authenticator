package xyz.iterus.authenticator.core.token

interface TokenRepo {

    suspend fun getTokens(): List<Token>
}
