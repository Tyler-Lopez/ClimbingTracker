package com.climbingtrackerapp.di

import android.app.PendingIntent
import android.content.Context
import androidx.core.app.NotificationCompat
import com.climbingtrackerapp.domain.service.RecordService
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
    fun provideNotificationBuilder(
        @ApplicationContext context: Context,
        pendingIntent: PendingIntent
    ): NotificationCompat.Builder {
        return NotificationCompat
            .Builder(context, RecordService.NOTIFICATION_CHANNEL_ID)
            .setAutoCancel(true)
            .setOngoing(true)
            .setContentTitle("Test Title")
            .setContentText("Test description")
            .setContentIntent(pendingIntent)
    }
}
