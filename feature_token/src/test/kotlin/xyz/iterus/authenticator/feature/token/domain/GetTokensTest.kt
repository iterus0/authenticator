package xyz.iterus.authenticator.feature.token.domain

import io.mockk.coEvery
import io.mockk.mockkClass
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test
import org.junit.Assert.assertEquals
import org.koin.core.logger.Level
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.inject
import org.koin.test.mock.MockProviderRule
import org.koin.test.mock.declareMock
import xyz.iterus.authenticator.feature.token.di.TokensModule
import xyz.iterus.authenticator.feature.token.domain.model.hotp.HOTP
import xyz.iterus.authenticator.feature.token.domain.model.hotp.HOTPToken
import xyz.iterus.authenticator.feature.token.domain.model.totp.TOTP
import xyz.iterus.authenticator.feature.token.domain.model.totp.TOTPToken
import xyz.iterus.authenticator.feature.token.domain.repository.TokenRepo
import xyz.iterus.authenticator.feature.token.domain.usecase.GetTokensUseCase

@ExperimentalCoroutinesApi
class GetTokensTest: KoinTest {

    private val totp: TOTP by inject()
    private val hotp: HOTP by inject()
    private val useCase: GetTokensUseCase by inject()

    @get:Rule
    val koinTestRule = KoinTestRule.create {
        // Workaround for: https://github.com/InsertKoinIO/koin/issues/847
        printLogger(Level.ERROR)
        modules(TokensModule.module)
    }

    @get:Rule
    val mockProvider = MockProviderRule.create {clazz ->
        mockkClass(clazz)
    }

    @Test
    fun `returns list of tokens`() = runBlockingTest {
        // Given
        val testData = listOf(
            TOTPToken(0, "test0", "https://example.com/test.png", totp, "secret_test_key0"),
            HOTPToken(1, "test1", "https://example.com/test.png", hotp, "secret_test_key1")
        )

        declareMock<TokenRepo> {
            coEvery { get() } returns testData
        }

        // When
        val tokens = useCase.execute()

        // Then
        assertEquals(testData, tokens)
    }
}
