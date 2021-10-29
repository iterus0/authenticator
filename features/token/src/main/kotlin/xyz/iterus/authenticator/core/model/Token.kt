package xyz.iterus.authenticator.core.model

interface Token {
    val id: Int
    val label: String
    val imageUrl: String

    fun getToken(): String
}
