package xyz.iterus.authenticator.ui.tokens.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import xyz.iterus.authenticator.core.token.hotp.HOTPToken
import xyz.iterus.authenticator.core.token.Token
import xyz.iterus.authenticator.core.token.totp.TOTPToken
import xyz.iterus.authenticator.ui.R

class TokensAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var tokens = listOf<Token>()

    fun submitList(newTokens: List<Token>) {
        val diffResult = DiffUtil.calculateDiff( TokenItemDiff(tokens, newTokens) )
        tokens = newTokens
        diffResult.dispatchUpdatesTo(this)
    }


    enum class ViewType(val value: Int) {
        HOTP(0), TOTP(1)
    }

    override fun getItemViewType(position: Int): Int = when(tokens[position]) {
        is HOTPToken -> ViewType.HOTP.value
        is TOTPToken -> ViewType.TOTP.value
        else -> TODO("ViewType for ${tokens[position]}")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder = when(viewType) {
        ViewType.HOTP.value -> HOTPViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.layout_token_hotp, parent, false)
        )

        ViewType.TOTP.value -> TOTPViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.layout_token_totp, parent, false)
        )

        else -> TODO("ViewType with value=$viewType")
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val token = tokens[position]

        when (holder) {
            is HOTPViewHolder -> if (token is HOTPToken) { holder.bind(token) } else { onBindingError(holder, token) }

            is TOTPViewHolder -> if (token is TOTPToken) { holder.bind(token) } else { onBindingError(holder, token) }

            else -> TODO("viewHolder bind for $holder")
        }
    }

    override fun getItemCount(): Int = tokens.size


    private fun onBindingError(holder: RecyclerView.ViewHolder, token: Token) {
        throw IllegalArgumentException("Can't bind ${token.javaClass.simpleName} with ${holder.javaClass.simpleName}")
    }
}
