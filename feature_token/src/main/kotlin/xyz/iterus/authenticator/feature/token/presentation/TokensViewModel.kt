package xyz.iterus.authenticator.feature.token.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import xyz.iterus.authenticator.core.model.Token
import xyz.iterus.authenticator.feature.token.domain.usecase.GetNextRefreshTimeUseCase
import xyz.iterus.authenticator.feature.token.domain.usecase.GetTokensUseCase

internal class TokensViewModel(
    private val getTokens: GetTokensUseCase,
    private val nextRefreshUseCase: GetNextRefreshTimeUseCase
): ViewModel() {

    private val _tokens = MutableLiveData<List<Token>>()
    val tokens: LiveData<List<Token>> = _tokens

    private var tokensRefreshJob: Job = Job()

    init {
        updateTokens()
    }

    private fun updateTokens() {
        viewModelScope.launch {
            val t = getTokens.execute()
            _tokens.value = t
            updateTokensRefreshJob(t)
        }
    }

    private suspend fun updateTokensRefreshJob(t: List<Token>) {
        val nextRefreshIn = nextRefreshUseCase.execute(t)

        if (nextRefreshIn != null) {
            tokensRefreshJob = viewModelScope.launch {
                delay(nextRefreshIn)
                updateTokens()
            }
        }
    }
}
