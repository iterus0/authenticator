package xyz.iterus.authenticator.ui.tokens.list

import android.animation.ObjectAnimator
import android.view.View
import kotlinx.android.synthetic.main.layout_token_totp.view.*
import xyz.iterus.authenticator.core.token.totp.TOTPToken

class TOTPViewHolder(itemView: View): TokenViewHolder(itemView) {

    fun bind(token: TOTPToken) {
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
