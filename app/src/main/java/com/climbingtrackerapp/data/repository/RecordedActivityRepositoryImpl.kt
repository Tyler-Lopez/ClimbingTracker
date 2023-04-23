package com.climbingtrackerapp.data.repository

import com.climbingtrackerapp.domain.repository.RecordedActivityRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class RecordedActivityRepositoryImpl : RecordedActivityRepository {
    private val _recordedActivityFlow: MutableStateFlow<Long> = MutableStateFlow(INITIAL_LENGTH)
    override val recordedActivityFlow: StateFlow<Long> = _recordedActivityFlow

    override fun recordReset() {
        _recordedActivityFlow.value = INITIAL_LENGTH
    }

    override fun recordIncrementTimeBy(additionalMs: Long) {
        _recordedActivityFlow.value += additionalMs
    }

    companion object {
        private const val INITIAL_LENGTH = 0L
    }
}
