package com.wcisang.home.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.wcisang.core.domain.model.Character
import com.wcisang.home.R
import com.wcisang.home.paging.CharacterAdapter
import com.wcisang.home.paging.CharacterLoadingAdapter
import com.wcisang.navigator.Action
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModel()
    private var job : Job? = null

    private var characterAdapter = CharacterAdapter() {
        startDetail(it)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViews()
        getCharacters()
    }

    override fun onStart() {
        super.onStart()
        getCharacters()
    }

    private fun setupViews() {
        rvMarvelCharacters.run {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = characterAdapter.withLoadStateHeaderAndFooter(
                header = CharacterLoadingAdapter { characterAdapter.retry() },
                footer = CharacterLoadingAdapter { characterAdapter.retry() }
            )
        }

        characterAdapter.addLoadStateListener { loadState ->
            rvMarvelCharacters.isVisible = loadState.refresh is LoadState.NotLoading
            pbMainLoading.isVisible = loadState.refresh is LoadState.Loading

            val errorState = loadState.source.append as? LoadState.Error
                ?: loadState.source.prepend as? LoadState.Error
                ?: loadState.append as? LoadState.Error
                ?: loadState.prepend as? LoadState.Error

            errorState?.let {
                Toast.makeText(
                    this,
                    "Error : ${it.error}",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    private fun startDetail(character: Character) {
        val bundle = Bundle()
        bundle.putParcelable(Action.KEY_CHARACTER, character)
        startActivity(Action.getDetailActivityIntent(this, bundle))
    }

    private fun getCharacters() {
        job?.cancel()
        job = lifecycleScope.launch {
            viewModel.getCharacters().collectLatest {
                characterAdapter.submitData(it)
            }
        }
    }
}
