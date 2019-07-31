package com.wcisang.widget.service

import android.content.Intent
import android.widget.RemoteViewsService
import com.wcisang.widget.ui.CharacterRemoteViewFactory
import com.wcisang.widget.usecase.GetCharactersWidgetUseCase
import org.koin.core.KoinComponent
import org.koin.core.inject

class CharacterWidgetService : RemoteViewsService(), KoinComponent{

    private val getCharactersWidgetUseCase: GetCharactersWidgetUseCase by inject()

    override fun onGetViewFactory(intent: Intent?): RemoteViewsFactory {
        return CharacterRemoteViewFactory(this, getCharactersWidgetUseCase)
    }


}