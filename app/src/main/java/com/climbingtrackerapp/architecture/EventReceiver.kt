package com.climbingtrackerapp.architecture

interface ViewEvent

interface EventReceiver<TypeOfViewEvent: ViewEvent> {
    fun onEvent(event: TypeOfViewEvent)
    fun onEventDebounced(event: TypeOfViewEvent)
}
