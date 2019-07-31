package com.wcisang.navigator

import android.content.Context
import android.content.Intent
import android.os.Bundle

object Action {

    const val KEY_CHARACTER = "KEY_CHARACTER"

    fun getDetailActivityIntent(host: Context, bundle: Bundle? = null) : Intent{
        return getActivityIntent(host, bundle,"com.wcisang.detail.ui.DetailActivity")
    }

    private fun getActivityIntent(host: Context, bundle: Bundle?, className: String) : Intent{
        val intent = Intent(host, Class.forName(className))
        if (bundle != null)
            intent.putExtras(bundle)
        return intent
    }
}