package xyz.iterus.authenticator.token

import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.Assert.*
import xyz.iterus.authenticator.token.hotp.HOTP
import xyz.iterus.authenticator.token.hotp.RFC4226.HashAlgorithm
import xyz.iterus.authenticator.token.hotp.RFC4226

class RFC4226Test {

    // https://tools.ietf.org/html/rfc4226#appendix-D
    @Test
    fun `reference test values`() = runBlocking {
        // GIVEN
        val secret = "12345678901234567890"
        val counter = 0L
        val digits = 6
        val hotp: HOTP = RFC4226(HashAlgorithm.SHA1)

        val expectedTokens = listOf(
            "755224", "287082", "359152", "969429", "338314",
            "254676", "287922", "162583", "399871", "520489"
        )

        // WHEN
        val tokens = hotp.observeToken(secret, counter, digits)
            .take(expectedTokens.size)
            .toList()

        // THEN
        assertEquals(expectedTokens, tokens)
    }

    @Test
    fun `tokens with 8 digits`() = runBlocking {
        // GIVEN
        val secret = "12345678901234567890"
        val counter = 0L
        val digits = 8
        val hotp: HOTP = RFC4226(HashAlgorithm.SHA1)

        val expectedTokens = listOf(
            "84755224", "94287082", "37359152", "26969429", "40338314",
            "68254676", "18287922", "82162583", "73399871", "45520489"
        )

        // WHEN
        val tokens = hotp.observeToken(secret, counter, digits)
            .take(expectedTokens.size)
            .toList()

        // THEN
        assertEquals(expectedTokens, tokens)
    }

    @Test
    fun `tokens with non zero counter`() = runBlocking {
        // GIVEN
        val secret = "12345678901234567890"
        val counter = 754L
        val digits = 6
        val hotp: HOTP = RFC4226(HashAlgorithm.SHA1)

        val expectedTokens = listOf(
            "346918", "221452", "873973", "635076",
            "712384", "380974", "359347", "517065"
        )

        // WHEN
        val tokens = hotp.observeToken(secret, counter, digits)
            .take(expectedTokens.size)
            .toList()

        // THEN
        assertEquals(expectedTokens, tokens)
    }

    @Test
    fun `tokens with SHA256 algorithm`() = runBlocking {
        // GIVEN
        val secret = "1234567890ABCDEFGHI"
        val counter = 0L
        val digits = 6
        val hotp: HOTP = RFC4226(HashAlgorithm.SHA256)

        val expectedTokens = listOf(
            "273337", "030793", "294170", "018203",
            "769062", "439312", "643186", "748212"
        )

        // WHEN
        val tokens = hotp.observeToken(secret, counter, digits)
            .take(expectedTokens.size)
            .toList()

        // THEN
        assertEquals(expectedTokens, tokens)
    }

    @Test
    fun `tokens with SHA512 algorithm`() = runBlocking {
        // GIVEN
        val secret = "A1B2C3D4E5F6G7H8I9J0"
        val counter = 0L
        val digits = 6
        val hotp: HOTP = RFC4226(HashAlgorithm.SHA512)

        val expectedTokens = listOf(
            "219463", "941555", "254677", "660247",
            "401413", "955362", "511074", "983955"
        )

        // WHEN
        val tokens = hotp.observeToken(secret, counter, digits)
            .take(expectedTokens.size)
            .toList()

        // THEN
        assertEquals(expectedTokens, tokens)
    }
}
