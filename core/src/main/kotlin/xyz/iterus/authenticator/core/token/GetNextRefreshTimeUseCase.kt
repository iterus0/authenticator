package xyz.iterus.authenticator.core.token

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import xyz.iterus.authenticator.core.token.totp.TOTPToken

class GetNextRefreshTimeUseCase {

    suspend fun execute(tokens: List<Token>): Long? = withContext(Dispatchers.Default) {
        tokens
            .filterIsInstance<TOTPToken>()
            .map { it.getRemainingTimeMs() }
            .min()
    }
}
