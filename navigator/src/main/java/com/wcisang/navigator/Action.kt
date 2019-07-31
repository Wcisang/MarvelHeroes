package com.wcisang.navigator

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.FragmentActivity

object Action {

    const val KEY_CHARACTER = "KEY_CHARACTER"

    fun getDetailActivityIntent(bundle: Bundle, host: FragmentActivity) : Intent{
        return getActivityIntent(host, bundle, "com.wcisang.detail","ui.DetailActivity")
    }

    private fun getActivityIntent(host: FragmentActivity, bundle: Bundle,packageName: String, className: String) : Intent{
        val intent = Intent("com.jeroenmols.modularization.sharing.open")
        intent.putExtras(bundle)
        return intent
    }
}