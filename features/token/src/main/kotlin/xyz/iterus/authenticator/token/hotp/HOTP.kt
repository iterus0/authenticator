package xyz.iterus.authenticator.token.hotp

import kotlinx.coroutines.flow.Flow

interface HOTP {

    fun generateToken(secret: String, counter: Long): String
    fun observeToken(secret: String, counter: Long): Flow<String>
}
