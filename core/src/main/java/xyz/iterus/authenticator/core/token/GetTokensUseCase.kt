package xyz.iterus.authenticator.core.token

class GetTokensUseCase(private val repo: TokenRepo) {

    suspend fun execute(): List<Token> = repo.getTokens()
}
