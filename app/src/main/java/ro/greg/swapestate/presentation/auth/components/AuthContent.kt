package ro.greg.swapestate.presentation.auth.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kotlinx.coroutines.InternalCoroutinesApi
import ro.greg.shtistorm.presentation.auth.components.Email
import ro.greg.shtistorm.presentation.auth.components.EmailState
import ro.greg.shtistorm.presentation.auth.components.Password
import ro.greg.shtistorm.presentation.components.PasswordState
import ro.greg.shtistorm.presentation.theme.LightTextColor
import ro.greg.swapestate.core.Constants.CONTINUE
import ro.greg.swapestate.core.Constants.CREATE_ACCOUNT
import ro.greg.swapestate.core.Constants.PASSWORD
import ro.greg.swapestate.presentation.auth.AuthViewModel
import ro.greg.swapestate.core.Constants.SIGN_UP_SCREEN
import ro.greg.swapestate.presentation.components.Branding

@Composable
@InternalCoroutinesApi
fun AuthContent(
    navController: NavController,
    viewModel: AuthViewModel = hiltViewModel()
) {
        val passwordFocusRequest = remember { FocusRequester() }
        val confirmationPasswordFocusRequest = remember { FocusRequester() }
        val emailState = remember { EmailState() }
        val onSubmit = {
            if (emailState.isValid) {
                // TODO onEvent(WelcomeEvent.SignInSignUp(emailState.text))
            } else {
                emailState.enableShowErrors()
            }
        }
        var username by remember { mutableStateOf("") }
        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .absolutePadding(bottom = 48.dp, top = 15.dp, left = 15.dp, right = 15.dp),
            contentAlignment = Alignment.BottomCenter
        ) {
            Column() {

                Branding(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)

                )

                TextButton(
                    onClick = {
                        navController.navigate(SIGN_UP_SCREEN)
                    },
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                ) {
                    Text(
                        text = CREATE_ACCOUNT,
                        color =  LightTextColor,
                        fontSize = 12.sp
                    )
                }
                Email(emailState = emailState, imeAction = ImeAction.Done, onImeAction = onSubmit)
                Spacer(modifier = Modifier.height(16.dp))
                val passwordState = remember { PasswordState() }
                Password(
                    label = PASSWORD,
                    passwordState = passwordState,
                    imeAction = ImeAction.Next,
                    onImeAction = { confirmationPasswordFocusRequest.requestFocus() },
                    modifier = Modifier.focusRequester(passwordFocusRequest)
                )
                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = { viewModel.signIn(emailState.text, passwordState.text) },
                    modifier = Modifier.fillMaxWidth()
                        .padding(top = 28.dp, bottom = 3.dp)

                ) {
                    Text(
                        text = CONTINUE,
                        style = MaterialTheme.typography.subtitle2
                    )
                }
            }
    }
}

