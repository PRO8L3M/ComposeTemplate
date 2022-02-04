package com.example.dashboard.main.ui

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.navArgument
import coil.compose.rememberImagePainter
import com.example.dashboard.main.contract.DashboardContract
import com.example.dashboard.main.viewmodel.DashboardViewModel
import com.example.domain.models.users.User
import com.example.navigation.Destination
import com.example.navigation.Routes
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import java.util.*

@ExperimentalMaterialApi
@Composable
@Preview(showBackground = true)
fun Dashboard(onNavigationRequest: (Destination) -> Unit = {}) {
    val viewModel: DashboardViewModel = hiltViewModel()
    DashboardPage(
        state = viewModel.viewState.value,
        effectFlow = viewModel.effect,
        onEvent = viewModel::setEvent,
        onNavigationRequest = onNavigationRequest::invoke
    )
}

@ExperimentalMaterialApi
@Composable
fun DashboardPage(
    state: DashboardContract.State,
    effectFlow: Flow<DashboardContract.Effect>?,
    onEvent: (DashboardContract.Event) -> Unit,
    onNavigationRequest: (Destination) -> Unit
) {
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        effectFlow?.onEach { effect ->
            when (effect) {
                is DashboardContract.Effect.ShowError -> {
                    Toast.makeText(context, effect.error.localizedMessage, Toast.LENGTH_SHORT)
                        .show()
                }
                is DashboardContract.Effect.Navigation -> {
                    val destination: Destination = effect.destination
                    onNavigationRequest(destination)
                }
            }
        }?.collect()
    }

    if (state.showProgressBar) {
        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
            CircularProgressIndicator()
        }
    } else {
        Users(state.users, onEvent)
    }

}

@ExperimentalMaterialApi
@Preview(showBackground = true)
@Composable
fun Users(users: List<User> = emptyList(), onEvent: (DashboardContract.Event) -> Unit = {}) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(users) { user ->
            UserCell(user, onEvent)
        }
    }
}

@ExperimentalMaterialApi
@Preview(showBackground = true)
@Composable
fun UserCell(user: User = User.buildDefault(), onEvent: (DashboardContract.Event) -> Unit = {}) {
    Surface(onClick = { onEvent.invoke(DashboardContract.Event.OnUserClicked(user.id)) }) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(4.dp)
        ) {
            Image(
                painter = rememberImagePainter(user.imageUrl),
                contentDescription = null,
                modifier = Modifier.size(60.dp)
            )
            Column(modifier = Modifier.fillMaxWidth()) {
                val age = remember {
                    Calendar.getInstance().get(Calendar.YEAR) - user.yearOfBirth
                }
                Text(text = "${user.firstName} ${user.lastName}")
                Text(text = "Age: $age")
            }
        }
    }
}