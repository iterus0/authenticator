package xyz.iterus.authenticator.feature.token.presentation.list

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.layout_token_base.view.*
import xyz.iterus.authenticator.feature.token.R
import xyz.iterus.authenticator.feature.token.domain.model.Token

internal open class TokenViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    open fun bind(token: Token) {
        itemView.token_label.text = token.label
        itemView.token.text = token.getToken()

        Glide.with(itemView)
            .load(token.imageUrl)
            .circleCrop()
            .placeholder(R.drawable.thumb_placeholder)
            .into(itemView.token_image)
    }
}
