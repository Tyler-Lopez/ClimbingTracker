package com.climbingtrackerapp.domain.repository

import android.content.Context
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import kotlin.time.Duration
import kotlinx.coroutines.flow.MutableStateFlow

interface RecordRepository {
    /** When true, any service listening to this repository should start, otherwise it should be
     * stopped. **/
    val isRecording: StateFlow<Boolean>

    /** The length of the recorded activity **/
    val recordedActivityLength: StateFlow<Duration>

    fun updateActivityLength(newLength: Duration)
}
