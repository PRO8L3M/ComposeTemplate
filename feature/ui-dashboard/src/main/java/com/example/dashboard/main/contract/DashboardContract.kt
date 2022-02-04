package com.example.dashboard.main.contract

import com.example.core.base.ViewEffect
import com.example.core.base.ViewEvent
import com.example.core.base.ViewState
import com.example.domain.models.users.User
import com.example.navigation.Destination

object DashboardContract {

    // What user does e.g. click the button, touch screen, etc..
    sealed class Event : ViewEvent {
        object OnDashboardScreenInit : Event()
        data class OnUserClicked(val userId: Long) : Event()
    }

    // State of current screen (big model for whole screen)
    data class State(val users: List<User>, override val showProgressBar: Boolean) : ViewState

    // Effect of Event. e.g. user clicked on navigation button (event) -> navigate to any screen (effect)
    sealed class Effect : ViewEffect {
        data class ShowError(val error: Throwable) : Effect()
        data class Navigation(val destination: Destination) : Effect()
    }
}