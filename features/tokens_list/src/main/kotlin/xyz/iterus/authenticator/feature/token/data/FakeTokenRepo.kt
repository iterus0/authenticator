package xyz.iterus.authenticator.feature.token.data

import xyz.iterus.authenticator.core.model.Token
import xyz.iterus.authenticator.feature.token.domain.repository.TokenRepo

internal class FakeTokenRepo: TokenRepo {

    private val data: HashMap<Int, Token> = HashMap()

    override suspend fun get(): List<Token> = ArrayList(data.values)

    override suspend fun add(token: Token) {
        data[token.id] = token
    }

    override suspend fun delete(token: Token) {
        data.remove(token.id)
    }

    override suspend fun update(token: Token) {
        data[token.id] = token
    }
}
