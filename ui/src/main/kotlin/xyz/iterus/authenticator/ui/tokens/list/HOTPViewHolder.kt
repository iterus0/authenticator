package xyz.iterus.authenticator.ui.tokens.list

import android.view.View
import kotlinx.android.synthetic.main.layout_token_hotp.view.*
import xyz.iterus.authenticator.core.token.hotp.HOTPToken

class HOTPViewHolder(itemView: View): TokenViewHolder(itemView) {

    fun bind(token: HOTPToken) {
        super.bind(token)

        itemView.token_counter.text = token.counter.toString()
    }
}
