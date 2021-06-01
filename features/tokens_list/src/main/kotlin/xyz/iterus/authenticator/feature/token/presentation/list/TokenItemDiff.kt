package xyz.iterus.authenticator.feature.token.presentation.list

import androidx.recyclerview.widget.DiffUtil
import xyz.iterus.authenticator.core.model.Token
import xyz.iterus.authenticator.core.model.totp.TOTPToken

internal class TokenItemDiff: DiffUtil.ItemCallback<Token>() {

    override fun areContentsTheSame(oldToken: Token, newToken: Token): Boolean {
        if (oldToken != newToken)
            return false

        return if (newToken is TOTPToken) {
            !newToken.isNewToken()
        } else {
            true
        }
    }

    override fun areItemsTheSame(oldToken: Token, newToken: Token): Boolean =
        (oldToken.id == newToken.id)
}
