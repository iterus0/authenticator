package xyz.iterus.authenticator.feature.token.domain.model.hotp

interface HOTP {

    enum class HashAlgorithm {
        SHA1, SHA256, SHA512
    }

    fun generateOTP(secret: String, counter: Long, digits: Int, algorithm: HashAlgorithm): String

    fun generateOTPNumber(secret: String, counter: Long, digits: Int, algorithm: HashAlgorithm): Int
}
