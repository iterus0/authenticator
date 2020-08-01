package xyz.iterus.authenticator.feature.token.presentation.list

import android.view.View
import kotlinx.android.synthetic.main.layout_token_hotp.view.*
import xyz.iterus.authenticator.feature.token.domain.model.Token
import xyz.iterus.authenticator.feature.token.domain.model.hotp.HOTPToken

internal class HOTPViewHolder(itemView: View): TokenViewHolder(itemView) {

    override fun bind(token: Token) {
        super.bind(token)

        if (token is HOTPToken) {
            bind(token)
        }
    }

    private fun bind(token: HOTPToken) {
        super.bind(token)

        itemView.token_counter.text = token.counter.toString()
    }
}
