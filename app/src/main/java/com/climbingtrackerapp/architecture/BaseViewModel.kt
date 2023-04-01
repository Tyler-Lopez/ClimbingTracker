package com.climbingtrackerapp.architecture

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

abstract class BaseViewModel<TypeOfViewState : ViewState, TypeOfViewEvent : ViewEvent>
    : ViewModel(), EventReceiver<TypeOfViewEvent>, StatePusher<TypeOfViewState> {

    private var lastDebouncedMs: Long = 0L
    private val _viewState: MutableStateFlow<TypeOfViewState?> = MutableStateFlow(value = null)
    final override val viewState: StateFlow<TypeOfViewState?> = _viewState

    final override fun TypeOfViewState.push() {
        _viewState.value = this
    }

    final override fun onEventDebounced(event: TypeOfViewEvent) {
        val currTime = System.currentTimeMillis()
        if (currTime > lastDebouncedMs + DEBOUNCE_TIME_MS) {
            lastDebouncedMs = currTime
            onEvent(event)
        }
    }

    companion object {
        /** A debounced event may be invoked no more frequently than this time. */
        private const val DEBOUNCE_TIME_MS = 1000L
    }
}
