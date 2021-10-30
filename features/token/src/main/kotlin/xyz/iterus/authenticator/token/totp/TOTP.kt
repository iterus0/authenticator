package xyz.iterus.authenticator.token.totp

import kotlinx.coroutines.flow.Flow

interface TOTP {

    fun generateToken(secret: String, time: Long, period: Int, digits: Int): String
    fun observeToken(secret: String, period: Int, digits: Int): Flow<String>
}
