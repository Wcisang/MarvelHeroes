package com.wcisang.widget.ui

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import androidx.core.app.TaskStackBuilder
import com.wcisang.navigator.Action
import com.wcisang.widget.R
import com.wcisang.widget.service.CharacterWidgetService


/**
 * Implementation of App Widget functionality.
 */
class CharacterWidget : AppWidgetProvider() {

    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    override fun onEnabled(context: Context) {
    }

    override fun onDisabled(context: Context) {
    }

    companion object {

        internal fun updateAppWidget(
            context: Context, appWidgetManager: AppWidgetManager,
            appWidgetId: Int
        ) {
            val views = RemoteViews(context.packageName, R.layout.character_widget)

            val intent = Intent(context, CharacterWidgetService::class.java)
            views.setRemoteAdapter(R.id.lvCharacters, intent)

            val clickIntentTemplate = Action.getDetailActivityIntent(context)
            val clickPendingIntentTemplate = TaskStackBuilder.create(context)
                .addNextIntentWithParentStack(clickIntentTemplate)
                .getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)
            views.setPendingIntentTemplate(R.id.lvCharacters, clickPendingIntentTemplate)

            appWidgetManager.updateAppWidget(appWidgetId, views)
        }
    }
}

