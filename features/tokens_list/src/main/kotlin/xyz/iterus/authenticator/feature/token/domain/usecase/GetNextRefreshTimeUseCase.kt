package xyz.iterus.authenticator.feature.token.domain.usecase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import xyz.iterus.authenticator.feature.token.domain.model.Token
import xyz.iterus.authenticator.feature.token.domain.model.TOTPToken

class GetNextRefreshTimeUseCase {

    suspend fun execute(tokens: List<Token>): Long? = withContext(Dispatchers.Default) {
        tokens
            .filterIsInstance<TOTPToken>()
            .map { it.getRemainingTimeMs() }
            .minOrNull()
    }
}
