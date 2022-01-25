package com.example.dashboard.useroverview.contract

import com.example.core.base.ViewEffect
import com.example.core.base.ViewEvent
import com.example.core.base.ViewState
import com.example.domain.models.users.User

object UserOverviewContract {

    sealed class Event : ViewEvent {
        object OnUserOverviewScreenInit : Event()
    }

    data class State(
        val user: User? = null,
        override val showProgressBar: Boolean
    ) : ViewState

    sealed class Effect : ViewEffect {
        data class ShowError(val error: Throwable) : Effect()
    }

}