package xyz.iterus.authenticator.core.token

interface Token {
    val id: Int
    val label: String
    val imageUrl: String

    fun getToken(): String
}
