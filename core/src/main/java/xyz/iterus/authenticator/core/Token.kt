package xyz.iterus.authenticator.core

interface Token {
    val label: String
    val imageUrl: String

    fun getToken(): String
}
