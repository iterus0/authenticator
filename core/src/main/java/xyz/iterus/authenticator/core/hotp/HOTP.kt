package xyz.iterus.authenticator.core.hotp

interface HOTP {

    enum class HashAlgorithm {
        SHA1, SHA256, SHA512
    }

    fun generateOTP(secret: String, counter: Long, digits: Int, algorithm: HashAlgorithm): Int
}
