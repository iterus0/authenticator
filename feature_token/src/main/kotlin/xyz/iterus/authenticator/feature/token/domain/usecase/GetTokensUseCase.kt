package xyz.iterus.authenticator.feature.token.domain.usecase

import xyz.iterus.authenticator.feature.token.domain.model.Token
import xyz.iterus.authenticator.feature.token.domain.repository.TokenRepo

class GetTokensUseCase(private val repo: TokenRepo) {

    suspend fun execute(): List<Token> = repo.get()
}
