package com.climbingtrackerapp.architecture

import kotlinx.coroutines.flow.StateFlow

interface ViewState

interface StatePusher<TypeOfViewState: ViewState> {
    val viewState: StateFlow<TypeOfViewState?>
    fun TypeOfViewState.push()
}
