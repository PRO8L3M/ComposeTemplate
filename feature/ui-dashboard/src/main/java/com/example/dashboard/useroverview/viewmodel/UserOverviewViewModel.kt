package com.example.dashboard.useroverview.viewmodel

import androidx.lifecycle.SavedStateHandle
import com.example.core.base.BaseViewModel
import com.example.dashboard.useroverview.contract.UserOverviewContract
import javax.inject.Inject

class UserOverviewViewModel @Inject constructor(stateHandle: SavedStateHandle) :
    BaseViewModel<UserOverviewContract.Event, UserOverviewContract.State, UserOverviewContract.Effect>() {

    override fun setInitialState(): UserOverviewContract.State = UserOverviewContract.State(showProgressBar = true)

    override fun handleEvents(event: UserOverviewContract.Event) = when(event) {
        UserOverviewContract.Event.OnUserOverviewScreenInit -> {}
    }


}