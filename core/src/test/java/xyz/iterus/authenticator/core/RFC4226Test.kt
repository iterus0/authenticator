package xyz.iterus.authenticator.core

import org.junit.Test
import org.junit.Assert.*
import xyz.iterus.authenticator.core.token.hotp.HOTP
import xyz.iterus.authenticator.core.token.hotp.RFC4226

class RFC4226Test {

    private val hotp: HOTP = RFC4226()

    // https://tools.ietf.org/html/rfc4226#appendix-D
    @Test
    fun `reference test values`() {
        // GIVEN
        val secret = "12345678901234567890"
        val digits = 6
        val alg = HOTP.HashAlgorithm.SHA1

        val expected_otp = listOf("755224", "287082", "359152", "969429", "338314",
                                  "254676", "287922", "162583", "399871", "520489")
        val count = 0L until expected_otp.size

        // WHEN
        val otp = count.map { counter -> hotp.generateOTP(secret, counter, digits, alg) }

        // THEN
        assertEquals(expected_otp, otp)
    }

    @Test
    fun `tokens with 8 digits`() {
        // GIVEN
        val secret = "12345678901234567890"
        val digits = 8
        val alg = HOTP.HashAlgorithm.SHA1

        val expected_otp = listOf("84755224", "94287082", "37359152", "26969429", "40338314",
                                  "68254676", "18287922", "82162583", "73399871", "45520489")
        val count = 0L until expected_otp.size

        // WHEN
        val otp = count.map { counter -> hotp.generateOTP(secret, counter, digits, alg) }

        // THEN
        assertEquals(expected_otp, otp)
    }

    @Test
    fun `tokens with non zero counter`() {
        // GIVEN
        val secret = "12345678901234567890"
        val digits = 6
        val alg = HOTP.HashAlgorithm.SHA1
        val counter = 754L

        val expected_otp = listOf("346918", "221452", "873973", "635076",
                                  "712384", "380974", "359347", "517065")
        val countRange = counter until counter+expected_otp.size

        // WHEN
        val otp = countRange.map { c -> hotp.generateOTP(secret, c, digits, alg) }

        // THEN
        assertEquals(expected_otp, otp)
    }

    @Test
    fun `tokens with SHA256 algorithm`() {
        // GIVEN
        val secret = "1234567890ABCDEFGHI"
        val digits = 6
        val alg = HOTP.HashAlgorithm.SHA256

        val expected_otp = listOf("273337", "030793", "294170", "018203",
                                  "769062", "439312", "643186", "748212")
        val count = 0L until expected_otp.size

        // WHEN
        val otp = count.map { counter -> hotp.generateOTP(secret, counter, digits, alg) }

        // THEN
        assertEquals(expected_otp, otp)
    }

    @Test
    fun `tokens with SHA512 algorithm`() {
        // GIVEN
        val secret = "A1B2C3D4E5F6G7H8I9J0"
        val digits = 6
        val alg = HOTP.HashAlgorithm.SHA512

        val expected_otp = listOf("219463", "941555", "254677", "660247",
                                  "401413", "955362", "511074", "983955")
        val count = 0L until expected_otp.size

        // WHEN
        val otp = count.map { counter -> hotp.generateOTP(secret, counter, digits, alg) }

        // THEN
        assertEquals(expected_otp, otp)
    }
}