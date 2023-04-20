package com.climbingtrackerapp.architecture

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

abstract class BaseViewModel<TypeOfViewState : ViewState, TypeOfViewEvent : ViewEvent, TypeOfDestination : Destination>
    : ViewModel(), EventReceiver<TypeOfViewEvent>, StatePusher<TypeOfViewState>,
    RouteSender<TypeOfDestination> {

    private var routeReceiver: RouteReceiver<TypeOfDestination>? = null
    private var lastDebouncedMs: Long = 0L
    private val _viewState: MutableStateFlow<TypeOfViewState?> = MutableStateFlow(value = null)
    final override val viewState: StateFlow<TypeOfViewState?> = _viewState

    final override fun TypeOfViewState.push() {
        _viewState.value = this
    }

    final override fun TypeOfDestination.push() {
        routeReceiver?.onRoute(destination = this)
    }

    final override fun onEventDebounced(event: TypeOfViewEvent) {
        val currTime = System.currentTimeMillis()
        if (currTime > lastDebouncedMs + DEBOUNCE_TIME_MS) {
            lastDebouncedMs = currTime
            onEvent(event = event)
        }
    }

    final override fun registerRouteReceiver(routeReceiver: RouteReceiver<TypeOfDestination>) {
        this.routeReceiver = routeReceiver
    }

    companion object {
        /** A debounced event may be invoked no more frequently than this time. */
        private const val DEBOUNCE_TIME_MS = 1000L
    }
}
