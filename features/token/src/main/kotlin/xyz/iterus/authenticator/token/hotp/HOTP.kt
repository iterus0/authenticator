package xyz.iterus.authenticator.token.hotp

import kotlinx.coroutines.flow.Flow

interface HOTP {

    fun generateToken(secret: String, counter: Long, digits: Int): String
    fun observeToken(secret: String, counter: Long, digits: Int): Flow<String>
}
