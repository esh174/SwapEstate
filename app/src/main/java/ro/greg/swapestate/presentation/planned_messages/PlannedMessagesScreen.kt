package ro.greg.swapestate.presentation.planned_messages

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kotlinx.coroutines.InternalCoroutinesApi
import ro.greg.swapestate.core.Constants
import ro.greg.swapestate.presentation.planned_messages.components.PlannedMessagesTopBar
import ro.greg.swapestate.presentation.auth.sign_up.SignUpViewModel
import ro.greg.swapestate.presentation.auth.sign_up.components.SignUpScreenContent
import ro.greg.swapestate.presentation.auth.sign_up.components.SignUpTopBar


@Composable
@InternalCoroutinesApi
fun PlannedMesagesScreen(
    navController: NavController,
    viewModel: SignUpViewModel = hiltViewModel(),
) {
    Scaffold(
        modifier = Modifier.systemBarsPadding(),
        topBar = {
            PlannedMessagesTopBar(navController = navController)
        }
    ) {
        it.calculateTopPadding()
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {


            Column(

                modifier = Modifier
                    .fillMaxWidth().weight(weight = 0.8f)
            ) {


                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp)
                ) {


                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(5.dp)
                    ) {
                        Text(
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold,
                            text = "Chat start"
                        )
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 6.dp),
                            fontWeight = FontWeight.Light,
                            text = "Hi! Please use chat after 11 PM..."
                        )
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(5.dp)
                    ) {
                        Text(
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold,
                            text = "End of lease"
                        )
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 6.dp),
                            fontWeight = FontWeight.Light,
                            text = "What a time it's been! I hope..."
                        )

                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(5.dp)
                    ) {
                        Text(
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold,
                            text = "Reservation confirmed"
                        )
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 6.dp),
                            fontWeight = FontWeight.Light,
                            text = "Hey! I've just confirmed your reservation..."
                        )

                    }
                }


            }
            Column(
                modifier = Modifier.weight(weight = 0.09f),
                verticalArrangement = Arrangement.Bottom){
                Button(
                    modifier = Modifier.padding(16.dp)
                        .fillMaxWidth(),
                    onClick = {

                        navController.navigate(Constants.PLANNED_MESSAGES_TEMPLATE_SCREEN)
                    },
                ) {
                    Text(text = "Add a template")
                }
            }



        }

    }
}
