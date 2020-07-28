package xyz.iterus.authenticator.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import xyz.iterus.authenticator.core.token.hotp.HOTP
import xyz.iterus.authenticator.core.token.hotp.HOTPToken
import xyz.iterus.authenticator.core.token.Token
import xyz.iterus.authenticator.core.token.TokenRepo
import xyz.iterus.authenticator.core.token.totp.SteamOTP
import xyz.iterus.authenticator.core.token.totp.TOTP
import xyz.iterus.authenticator.core.token.totp.TOTPToken

class TokenMockRepo(private val hotp: HOTP, private val totp: TOTP, private val sotp: SteamOTP): TokenRepo {

    override suspend fun getTokens(): List<Token> = withContext(Dispatchers.IO) {
        // simulate slow IO
        //delay(2000)

        listOf(
            TOTPToken(0, "lorem", "", totp, "topsecret9876543210", period = 12),
            HOTPToken(1, "ipsum", "", hotp, "wertkjhgcnlasrtiuhu", 16),
            TOTPToken(2, "dolor", "", totp, "topsecret.topsecret", period = 60),
            TOTPToken(3, "steam", "", sotp, "topsecret0123456789", digits = 5)
        )
    }
}
