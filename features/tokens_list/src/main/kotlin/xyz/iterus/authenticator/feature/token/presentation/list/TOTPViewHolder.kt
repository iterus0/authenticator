package xyz.iterus.authenticator.feature.token.presentation.list

import android.animation.ObjectAnimator
import android.view.View
import kotlinx.android.synthetic.main.layout_token_totp.view.*
import xyz.iterus.authenticator.feature.token.domain.model.Token
import xyz.iterus.authenticator.feature.token.domain.model.TOTPToken

internal class TOTPViewHolder(itemView: View): TokenViewHolder(itemView) {

    override fun bind(token: Token) {
        super.bind(token)

        if (token is TOTPToken) {
            bind(token)
        }
    }

    private fun bind(token: TOTPToken) {
        super.bind(token)

        // animate expiration timer
        val remaining = token.getRemainingTimeNormalized()
        val remainingMs = token.getRemainingTimeMs()

        val remainingProgress = (itemView.token_timer.max * remaining)
        val anim = ObjectAnimator.ofInt(itemView.token_timer, "progress", remainingProgress.toInt(), 0)
        anim.duration = remainingMs
        anim.start()
    }
}
