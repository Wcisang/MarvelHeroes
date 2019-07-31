package com.wcisang.home.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.wcisang.core.domain.model.Character
import com.wcisang.home.R
import com.wcisang.home.paging.CharacterAdapter
import com.wcisang.navigator.Action
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(){

    private val viewModel : MainViewModel by viewModel()

    private lateinit var adapter : CharacterAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViews()
        viewModel.getCharacters()
        registerObserver()
    }

    private fun setupViews() {
        adapter = CharacterAdapter(viewModel.pagingState) {
            startDetail(it)
        }
        val layoutManager = LinearLayoutManager(this)
        rvMarvelCharacters.layoutManager = layoutManager
        rvMarvelCharacters.adapter = adapter
    }

    private fun startDetail(character: Character) {
        val bundle = Bundle()
        bundle.putParcelable(Action.KEY_CHARACTER, character)
        startActivity(Action.getDetailActivityIntent(this, bundle))
    }

    private fun registerObserver() {
        viewModel.characters.observe(this, Observer {
            adapter.submitList(it)
        })
        viewModel.pagingState.observe(this, Observer {
            adapter.changeLoadingStatus()
        })
    }
}
