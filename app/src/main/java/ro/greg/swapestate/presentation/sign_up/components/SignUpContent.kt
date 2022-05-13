package ro.greg.swapestate.presentation.sign_up.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import ro.greg.swapestate.core.Constants
import ro.greg.swapestate.core.Constants.CONFIRM_PASSWORD
import ro.greg.swapestate.core.Constants.CONTINUE
import ro.greg.swapestate.core.Constants.SIGN_UP_DETAILS_SCREEN
import ro.greg.swapestate.core.Constants.TERMS_MESSAGE
import ro.greg.swapestate.presentation.components.Branding
import ro.greg.swapestate.presentation.sign_up.SignUpViewModel
import ro.greg.shtistorm.presentation.auth.components.Email
import ro.greg.shtistorm.presentation.auth.components.EmailState
import ro.greg.shtistorm.presentation.auth.components.Password
import ro.greg.shtistorm.presentation.components.ConfirmPasswordState
import ro.greg.shtistorm.presentation.components.PasswordState

@Composable
fun SignUpContent(
    navController: NavController,
    viewModel: SignUpViewModel = hiltViewModel()

) {
    Box(modifier = Modifier
        .fillMaxSize()
        .absolutePadding(bottom = 15.dp, top = 15.dp, left = 15.dp, right = 15.dp)
        ,
        contentAlignment = Alignment.BottomCenter){
        Column(modifier = Modifier.fillMaxWidth()) {
            val passwordFocusRequest = remember { FocusRequester() }
            val confirmationPasswordFocusRequest = remember { FocusRequester() }
            val emailState = remember { EmailState() }

            Branding(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)

            )
            Email(emailState, onImeAction = { passwordFocusRequest.requestFocus() })

            Spacer(modifier = Modifier.height(16.dp))
            val passwordState = remember { PasswordState() }
            Password(
                label = Constants.PASSWORD,
                passwordState = passwordState,
                imeAction = ImeAction.Next,
                onImeAction = { confirmationPasswordFocusRequest.requestFocus() },
                modifier = Modifier.focusRequester(passwordFocusRequest)
            )

            Spacer(modifier = Modifier.height(16.dp))
            val confirmPasswordState = remember { ConfirmPasswordState(passwordState = passwordState) }
            Password(
                label = CONFIRM_PASSWORD,
                passwordState = confirmPasswordState,
                onImeAction = { viewModel.signUp(emailState.text, passwordState.text )
                },
                modifier = Modifier.focusRequester(confirmationPasswordFocusRequest)
            )

            Spacer(modifier = Modifier.height(16.dp))
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Text(
                    text = TERMS_MESSAGE,
                    style = MaterialTheme.typography.caption
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
            Button(
                modifier = Modifier.padding(16.dp)
                    .fillMaxWidth(),
                onClick = {
                    viewModel.signUp(emailState.text, passwordState.text)
                    navController.navigate(SIGN_UP_DETAILS_SCREEN)
                },
                enabled = emailState.isValid &&
                        passwordState.isValid && confirmPasswordState.isValid


            ) {
                Text(text = CONTINUE)
            }

        }

    }

}