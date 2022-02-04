package com.example.dashboard.main.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.core.base.BaseViewModel
import com.example.dashboard.main.contract.DashboardContract
import com.example.domain.usecases.users.GetAvatarsUseCase
import com.example.domain.usecases.users.GetUsersUseCase
import com.example.navigation.Routes
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val getUsers: GetUsersUseCase,
    private val getAvatars: GetAvatarsUseCase,
    private val stateHandle: SavedStateHandle
) : BaseViewModel<DashboardContract.Event, DashboardContract.State, DashboardContract.Effect>() {

    init {
        setEvent(DashboardContract.Event.OnDashboardScreenInit)
    }

    override fun setInitialState(): DashboardContract.State =
        DashboardContract.State(emptyList(), true)

    override fun handleEvents(event: DashboardContract.Event) {
        when (event) {
            is DashboardContract.Event.OnDashboardScreenInit -> getUsers()
            is DashboardContract.Event.OnUserClicked -> navigateToUserOverview(event)
        }
    }

    private fun getUsers() {
        getUsers(
            viewModelScope,
            Unit,
            { users ->
                setState { copy(users = users, showProgressBar = false) }
            },
            { error ->
                setEffect { DashboardContract.Effect.ShowError(error) }
            }
        )
    }

    private fun navigateToUserOverview(event: DashboardContract.Event.OnUserClicked) {
        val user = viewState.value.users.find { it.id == event.userId }
        user?.let {
            val routeWithArgument = Routes.UserOverview().route + "/${user.id}"
            val destination = Routes.UserOverview().copy(route = routeWithArgument)
            setEffect { DashboardContract.Effect.Navigation(destination) }
        } ?: run {
            setEffect { DashboardContract.Effect.ShowError(NoSuchElementException()) }
        }
    }
}