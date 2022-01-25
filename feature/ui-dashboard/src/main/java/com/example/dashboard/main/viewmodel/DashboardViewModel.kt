package com.example.dashboard.main.viewmodel

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.core.base.BaseViewModel
import com.example.dashboard.main.contract.DashboardContract
import com.example.domain.models.users.User
import com.example.domain.usecases.users.GetAvatarsUseCase
import com.example.domain.usecases.users.GetUsersUseCase
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

    override fun setInitialState(): DashboardContract.State = DashboardContract.State(emptyList(), true)

    override fun handleEvents(event: DashboardContract.Event) {
        when(event) {
            is DashboardContract.Event.OnDashboardScreenInit -> getUsers()
            is DashboardContract.Event.OnUserClicked -> navigateToUserOverview(event)
        }
    }

    fun onUserClicked(id: Long) {
        setEvent(DashboardContract.Event.OnUserClicked(id))
    }

    private fun navigateToUserOverview(event: DashboardContract.Event.OnUserClicked) {
        val user = viewState.value.users.find { it.id == event.userId }
        user?.let {
            setEffect { DashboardContract.Effect.NavigateToUserOverview(user) }
        } ?: run {
            setEffect { DashboardContract.Effect.ShowError(NoSuchElementException()) }
        }
    }

    private fun getUsers() {
        getUsers(
            viewModelScope,
            Unit,
            ::onGetUsersSuccess,
            ::onGetUsersError
        )
    }

    private fun onGetUsersSuccess(users: List<User>) {
        setState { copy(users = users, showProgressBar = false) }
    }

    private fun onGetUsersError(error: Throwable) {
        Log.e("Users", error.stackTraceToString())
        setEffect { DashboardContract.Effect.ShowError(error) }
    }
}