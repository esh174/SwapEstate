package ro.greg.swapestate.presentation.auth.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.InternalCoroutinesApi
import ro.greg.swapestate.presentation.auth.AuthViewModel
import ro.greg.swapestate.core.Constants.SIGN_IN

@Composable
@InternalCoroutinesApi
fun AuthContent(
    proceedToSignUp: () -> Unit,
    viewModel: AuthViewModel = hiltViewModel()
) {
    Box(
        modifier = Modifier.fillMaxSize().padding(bottom = 48.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        Row( modifier = Modifier.fillMaxWidth()){
            Button(
                onClick = {
                    viewModel.signIn()
                }
            ) {
                Text(
                    text = SIGN_IN,
                    fontSize = 18.sp
                )
            }
            Button(
                onClick = {
                    proceedToSignUp()

                }
            ) {
                Text(
                    text = "PLEASE HELPMEEE",
                    fontSize = 18.sp
                )
            }
        }

    }
}