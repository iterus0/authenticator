package xyz.iterus.authenticator.token.totp

import kotlinx.coroutines.flow.Flow

interface TOTP {

    fun generate(secret: String, time: Long, period: Int): String
    fun observeToken(secret: String, period: Int): Flow<String>
}
