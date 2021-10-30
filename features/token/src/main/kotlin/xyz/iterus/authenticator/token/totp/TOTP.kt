package xyz.iterus.authenticator.token.totp

interface TOTP {

    fun generate(secret: String, time: Long, period: Int): String
}
