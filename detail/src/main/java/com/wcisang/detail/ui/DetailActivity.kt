package com.wcisang.detail.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.wcisang.core.domain.model.Character
import com.wcisang.detail.R
import com.wcisang.navigator.Action
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    lateinit var character: Character

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        character = intent.getParcelableExtra(Action.KEY_CHARACTER) ?: throw IllegalArgumentException()

        tvCharName.text = character.name
    }
}
