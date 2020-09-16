package xyz.iterus.authenticator.feature.token.domain

import org.junit.Test
import org.junit.Assert.*
import org.junit.Rule
import org.koin.core.logger.Level
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.inject
import xyz.iterus.authenticator.feature.token.di.TokensModule
import xyz.iterus.authenticator.feature.token.domain.model.hotp.HOTP.HashAlgorithm
import xyz.iterus.authenticator.feature.token.domain.model.hotp.RFC4226

class RFC4226Test: KoinTest {

    private val hotp: RFC4226 by inject()

    @get:Rule
    val koinTestRule = KoinTestRule.create {
        // Workaround for: https://github.com/InsertKoinIO/koin/issues/847
        printLogger(Level.ERROR)
        modules(TokensModule.module)
    }

    // https://tools.ietf.org/html/rfc4226#appendix-D
    @Test
    fun `reference test values`() {
        // GIVEN
        val secret = "12345678901234567890"
        val digits = 6
        val alg = HashAlgorithm.SHA1

        val expectedOtp = listOf("755224", "287082", "359152", "969429", "338314",
                                  "254676", "287922", "162583", "399871", "520489")
        val count = 0L until expectedOtp.size

        // WHEN
        val otp = count.map { counter -> hotp.generateOTP(secret, counter, digits, alg) }

        // THEN
        assertEquals(expectedOtp, otp)
    }

    @Test
    fun `tokens with 8 digits`() {
        // GIVEN
        val secret = "12345678901234567890"
        val digits = 8
        val alg = HashAlgorithm.SHA1

        val expectedOtp = listOf("84755224", "94287082", "37359152", "26969429", "40338314",
                                  "68254676", "18287922", "82162583", "73399871", "45520489")
        val count = 0L until expectedOtp.size

        // WHEN
        val otp = count.map { counter -> hotp.generateOTP(secret, counter, digits, alg) }

        // THEN
        assertEquals(expectedOtp, otp)
    }

    @Test
    fun `tokens with non zero counter`() {
        // GIVEN
        val secret = "12345678901234567890"
        val digits = 6
        val alg = HashAlgorithm.SHA1
        val counter = 754L

        val expectedOtp = listOf("346918", "221452", "873973", "635076",
                                  "712384", "380974", "359347", "517065")
        val countRange = counter until counter+expectedOtp.size

        // WHEN
        val otp = countRange.map { c -> hotp.generateOTP(secret, c, digits, alg) }

        // THEN
        assertEquals(expectedOtp, otp)
    }

    @Test
    fun `tokens with SHA256 algorithm`() {
        // GIVEN
        val secret = "1234567890ABCDEFGHI"
        val digits = 6
        val alg = HashAlgorithm.SHA256

        val expectedOtp = listOf("273337", "030793", "294170", "018203",
                                  "769062", "439312", "643186", "748212")
        val count = 0L until expectedOtp.size

        // WHEN
        val otp = count.map { counter -> hotp.generateOTP(secret, counter, digits, alg) }

        // THEN
        assertEquals(expectedOtp, otp)
    }

    @Test
    fun `tokens with SHA512 algorithm`() {
        // GIVEN
        val secret = "A1B2C3D4E5F6G7H8I9J0"
        val digits = 6
        val alg = HashAlgorithm.SHA512

        val expectedOtp = listOf("219463", "941555", "254677", "660247",
                                  "401413", "955362", "511074", "983955")
        val count = 0L until expectedOtp.size

        // WHEN
        val otp = count.map { counter -> hotp.generateOTP(secret, counter, digits, alg) }

        // THEN
        assertEquals(expectedOtp, otp)
    }
}
