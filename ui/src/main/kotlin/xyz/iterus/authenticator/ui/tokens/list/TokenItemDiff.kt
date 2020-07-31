package xyz.iterus.authenticator.ui.tokens.list

import androidx.recyclerview.widget.DiffUtil
import xyz.iterus.authenticator.core.token.Token
import xyz.iterus.authenticator.core.token.totp.TOTPToken

class TokenItemDiff: DiffUtil.ItemCallback<Token>() {

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
