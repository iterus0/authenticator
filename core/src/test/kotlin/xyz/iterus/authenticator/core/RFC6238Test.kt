package xyz.iterus.authenticator.core

import org.junit.Test
import org.junit.Assert.*
import xyz.iterus.authenticator.core.token.hotp.HOTP
import xyz.iterus.authenticator.core.token.hotp.RFC4226
import xyz.iterus.authenticator.core.token.totp.RFC6238
import xyz.iterus.authenticator.core.token.totp.TOTP

class RFC6238Test {

    private val totp: TOTP = RFC6238(RFC4226())

    // https://tools.ietf.org/html/rfc6238.html#appendix-B
    @Test
    fun `reference test values for SHA1`() {
        // GIVEN
        val secret = "1234567890".repeat(2)
        val digits = 8
        val period = 30
        val alg = HOTP.HashAlgorithm.SHA1

        val ref = mapOf(59L to "94287082",
                1111111109L to "07081804",
                1111111111L to "14050471",
                1234567890L to "89005924",
                2000000000L to "69279037",
               20000000000L to "65353130")

        for ((time, expected_otp) in ref) {
            val otp = totp.generateOTP(secret, time, period, digits, alg)
            assertEquals(expected_otp, otp)
        }
    }

    // https://tools.ietf.org/html/rfc6238.html#appendix-B
    @Test
    fun `reference test values for SHA256`() {
        // GIVEN
        val secret = "1234567890".repeat(3) + "12"
        val digits = 8
        val period = 30
        val alg = HOTP.HashAlgorithm.SHA256

        val ref = mapOf(59L to "46119246",
                1111111109L to "68084774",
                1111111111L to "67062674",
                1234567890L to "91819424",
                2000000000L to "90698825",
               20000000000L to "77737706")

        for ((time, expected_otp) in ref) {
            val otp = totp.generateOTP(secret, time, period, digits, alg)
            assertEquals(expected_otp, otp)
        }
    }

    // https://tools.ietf.org/html/rfc6238.html#appendix-B
    @Test
    fun `reference test values for SHA512`() {
        // GIVEN
        val secret = "1234567890".repeat(6) + "1234"
        val digits = 8
        val period = 30
        val alg = HOTP.HashAlgorithm.SHA512

        val ref = mapOf(59L to "90693936",
                1111111109L to "25091201",
                1111111111L to "99943326",
                1234567890L to "93441116",
                2000000000L to "38618901",
               20000000000L to "47863826")

        for ((time, expected_otp) in ref) {
            val otp = totp.generateOTP(secret, time, period, digits, alg)
            assertEquals(expected_otp, otp)
        }
    }
}
