package xyz.iterus.authenticator.feature.token.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_tokens.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import xyz.iterus.authenticator.feature.token.R
import xyz.iterus.authenticator.feature.token.domain.model.Token
import xyz.iterus.authenticator.feature.token.presentation.list.TokensAdapter

internal class TokensFragment: Fragment(R.layout.fragment_tokens) {

    private val vm: TokensViewModel by viewModel()
    private val tokensAdapter: TokensAdapter by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        token_list.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = tokensAdapter
        }

        vm.tokens.observe(viewLifecycleOwner, Observer<List<Token>> {
            tokensAdapter.submitList(it)
        })
    }
}
