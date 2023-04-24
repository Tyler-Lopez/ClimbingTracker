package com.climbingtrackerapp.data.repository

import android.content.Context
import com.climbingtrackerapp.domain.repository.RecordRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import kotlin.time.Duration
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class RecordRepositoryImpl @Inject constructor(@ApplicationContext context: Context) :
    RecordRepository {

    private val _isRecording: MutableStateFlow<Boolean> = MutableStateFlow(value = false)
    private val _recordedActivityLength: MutableStateFlow<Duration> =
        MutableStateFlow(value = Duration.ZERO)

    override val isRecording: StateFlow<Boolean> = _isRecording
    override val recordedActivityLength: StateFlow<Duration> = _recordedActivityLength

    override fun updateActivityLength(newLength: Duration) {
        TODO("Not yet implemented")
    }
}
