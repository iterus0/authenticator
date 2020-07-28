package xyz.iterus.authenticator.ui.tokens.list

import androidx.recyclerview.widget.DiffUtil
import xyz.iterus.authenticator.core.token.Token
import xyz.iterus.authenticator.core.token.totp.TOTPToken

class TokenItemDiff(
    private val oldTokens: List<Token>,
    private val newTokens: List<Token>
): DiffUtil.Callback() {

    override fun areContentsTheSame(oldPos: Int, newPos: Int): Boolean {
        val oldToken = oldTokens[oldPos]
        val newToken = newTokens[newPos]

        if (oldToken != newToken)
            return false

        return if (newToken is TOTPToken) {
            !newToken.isNewToken()
        } else {
            true
        }
    }

    override fun areItemsTheSame(oldPos: Int, newPos: Int): Boolean =
        (oldTokens[oldPos].id == newTokens[newPos].id)

    override fun getOldListSize(): Int = oldTokens.size

    override fun getNewListSize(): Int = newTokens.size
}
