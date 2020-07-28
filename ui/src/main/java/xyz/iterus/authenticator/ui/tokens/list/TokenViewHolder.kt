package xyz.iterus.authenticator.ui.tokens.list

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.layout_token.view.*
import xyz.iterus.authenticator.core.token.Token
import xyz.iterus.authenticator.ui.R

open class TokenViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    fun bind(token: Token) {
        itemView.token_label.text = token.label
        itemView.token.text = token.getToken()

        Glide.with(itemView)
            .load(token.imageUrl)
            .circleCrop()
            .placeholder(R.drawable.thumb_placeholder)
            .into(itemView.token_image)
    }
}
