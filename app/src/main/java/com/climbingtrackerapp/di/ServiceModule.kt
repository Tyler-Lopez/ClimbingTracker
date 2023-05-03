package com.climbingtrackerapp.di

import android.app.Notification.BADGE_ICON_LARGE
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.climbingtrackerapp.R
import com.climbingtrackerapp.presentation.MainActivity
import com.climbingtrackerapp.service.RecordService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ServiceComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ServiceScoped

@Module
@InstallIn(ServiceComponent::class)
object ServiceModule {

    @ServiceScoped
    @Provides
    fun provideMainActivityPendingIntent(
        @ApplicationContext app: Context
    ) = PendingIntent.getActivity(
        app, 0,
        Intent(app, MainActivity::class.java).also {
           // it.action = Constants.ACTION_SHOW_TRACKING_FRAGMENT
        },
        PendingIntent.FLAG_UPDATE_CURRENT
    )

    @ServiceScoped
    @Provides
    fun provideNotificationBuilder(
        @ApplicationContext context: Context,
        pendingIntent: PendingIntent
    ): NotificationCompat.Builder {
        // todo... figure this out :(
        return NotificationCompat
            .Builder(context, RecordService.NOTIFICATION_CHANNEL_ID)
         //  .setAutoCancel(true)
            .setOngoing(true)
            .setContentTitle("Test Title")
            .setContentText("Test description")
            .setForegroundServiceBehavior(NotificationCompat.FOREGROUND_SERVICE_IMMEDIATE)
           .setSmallIcon(androidx.core.R.drawable.notification_template_icon_low_bg)
            .setBadgeIconType(NotificationCompat.BADGE_ICON_LARGE)
            //.setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
    }
}
