package xyz.iterus.authenticator.feature.token.presentation.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import xyz.iterus.authenticator.feature.token.R
import xyz.iterus.authenticator.feature.token.domain.model.Token
import xyz.iterus.authenticator.feature.token.domain.model.HOTPToken
import xyz.iterus.authenticator.feature.token.domain.model.TOTPToken

internal class TokensAdapter: RecyclerView.Adapter<TokenViewHolder>() {

    enum class ViewType(val value: Int) {
        HOTP(0), TOTP(1)
    }

    private val diff = AsyncListDiffer(this, TokenItemDiff())

    fun submitList(tokens: List<Token>) {
        diff.submitList(tokens)
    }

    override fun getItemViewType(position: Int): Int = when (val token = diff.currentList[position]) {
        is HOTPToken -> ViewType.HOTP.value
        is TOTPToken -> ViewType.TOTP.value
        else -> throw NotImplementedError("ViewType for $token")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TokenViewHolder = when (viewType) {
        ViewType.HOTP.value -> HOTPViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.layout_token_hotp, parent, false)
        )
        ViewType.TOTP.value -> TOTPViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.layout_token_totp, parent, false)
        )
        else -> throw NotImplementedError("ViewType with value=$viewType")
    }

    override fun onBindViewHolder(holder: TokenViewHolder, position: Int) {
        val token = diff.currentList[position]
        holder.bind(token)
    }

    override fun getItemCount(): Int = diff.currentList.size
}
