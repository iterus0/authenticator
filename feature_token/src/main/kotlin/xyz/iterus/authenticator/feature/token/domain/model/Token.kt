package xyz.iterus.authenticator.feature.token.domain.model

interface Token {
    val id: Int
    val label: String
    val imageUrl: String

    fun getToken(): String
}
