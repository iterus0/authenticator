package xyz.iterus.authenticator.feature.token.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_tokens.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import xyz.iterus.authenticator.feature.token.R
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

        vm.tokens.observe(viewLifecycleOwner, {
            tokensAdapter.submitList(it)
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        token_list.adapter = null
    }
}
