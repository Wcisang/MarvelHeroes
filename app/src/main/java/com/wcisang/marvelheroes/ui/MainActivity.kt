package com.wcisang.marvelheroes.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.wcisang.marvelheroes.R
import com.wcisang.marvelheroes.paging.CharacterAdapter
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
        adapter = CharacterAdapter {
            Toast.makeText(this, it.name, Toast.LENGTH_SHORT).show()
        }
        val layoutManager = LinearLayoutManager(this)
        rvMarvelCharacters.layoutManager = layoutManager
        rvMarvelCharacters.adapter = adapter
    }

    private fun registerObserver() {
        viewModel.characters.observe(this, Observer {
            adapter.submitList(it)
        })
    }
}
