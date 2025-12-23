package com.example.donemate

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay

import com.example.donemate.ui.screens.sign_in.SignInScreen
import com.example.donemate.ui.screens.sign_in.SignInViewModel
import com.example.donemate.ui.screens.sign_up.SignUpScreen
import com.example.donemate.ui.screens.sign_up.SignUpViewModel
import com.example.donemate.ui.theme.DoneMateTheme
import kotlinx.serialization.Serializable

@Serializable
data object SignUp : NavKey

@Serializable
data object SignIn : NavKey


@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun DoneMateApp() {
    DoneMateTheme {
        val backStack = rememberNavBackStack(SignIn)
        val viewModelDecorator = rememberViewModelStoreNavEntryDecorator<NavKey>()
        val saveableStateDecorator = rememberSaveableStateHolderNavEntryDecorator<NavKey>()
        val currentDestination = backStack.lastOrNull()

        NavDisplay(
            backStack = backStack,
            onBack = { backStack.removeLastOrNull() },
            entryDecorators = listOf(
                saveableStateDecorator,
                viewModelDecorator
            ),
            modifier = Modifier
                .systemBarsPadding(),
            entryProvider = entryProvider {
                entry<SignUp> {
                    val viewModel = hiltViewModel<SignUpViewModel>()
                    SignUpScreen(
                        navigateToSignIn = { backStack.add(SignIn) },
                        vm = viewModel
                    )
                }
                entry<SignIn> {
                    val viewModel = hiltViewModel<SignInViewModel>()
                    SignInScreen(navigateToSignUp = { backStack.add(SignUp) }, vm = viewModel)
                }
            }
        )
    }
}