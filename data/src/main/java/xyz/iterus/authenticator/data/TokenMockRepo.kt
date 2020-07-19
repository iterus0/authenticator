package xyz.iterus.authenticator.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import xyz.iterus.authenticator.core.hotp.HOTP
import xyz.iterus.authenticator.core.token.Token
import xyz.iterus.authenticator.core.token.TokenRepo
import xyz.iterus.authenticator.core.totp.TOTP
import xyz.iterus.authenticator.core.totp.TOTPToken

class TokenMockRepo(private val totp: TOTP): TokenRepo {

    override suspend fun getTokens(): List<Token> = withContext(Dispatchers.IO) {
        // simulate slow IO
        delay(2000)

        listOf<Token>(
            TOTPToken("lorem", "", "topsecret0123456789", 30, 6, HOTP.HashAlgorithm.SHA1, totp),
            TOTPToken("ipsum", "", "topsecret9876543210", 30, 6, HOTP.HashAlgorithm.SHA1, totp),
            TOTPToken("dolor", "", "topsecret.topsecret", 30, 6, HOTP.HashAlgorithm.SHA1, totp)
        )
    }
}
