package com.climbingtrackerapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.wear.compose.navigation.SwipeDismissableNavHostState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.rememberSwipeToDismissBoxState
import androidx.wear.compose.material.SwipeToDismissBoxState
import androidx.wear.compose.navigation.*
import com.climbingtrackerapp.architecture.RouteReceiver
import com.climbingtrackerapp.presentation.screens.endClimb.EndClimb
import com.climbingtrackerapp.presentation.screens.endClimb.EndClimbViewModel
import com.climbingtrackerapp.presentation.screens.record.Record
import com.climbingtrackerapp.presentation.screens.record.RecordViewModel
import com.climbingtrackerapp.presentation.screens.selectClimbingGrade.SelectClimbingGrade
import com.climbingtrackerapp.presentation.screens.selectClimbingGrade.SelectClimbingGradeViewModel
import com.climbingtrackerapp.presentation.screens.selectClimbingType.SelectClimbingType
import com.climbingtrackerapp.presentation.screens.selectClimbingType.SelectClimbingTypeViewModel
import com.climbingtrackerapp.presentation.theme.Typography
import com.climbingtrackerapp.presentation.theme.wearColorPalette
import dagger.hilt.android.AndroidEntryPoint


/**
 * Simple "Hello, World" app meant as a starting point for a new project using Compose for Wear OS.
 *
 * Displays only a centered [Text] composable, and the actual text varies based on the shape of the
 * device (round vs. square/rectangular).
 *
 * If you plan to have multiple screens, use the Wear version of Compose Navigation. You can carry
 * over your knowledge from mobile and it supports the swipe-to-dismiss gesture (Wear OS's
 * back action). For more information, go here:
 * https://developer.android.com/reference/kotlin/androidx/wear/compose/navigation/package-summary
 */

@AndroidEntryPoint
class MainActivity : ComponentActivity(), RouteReceiver<MainDestination> {

    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme(
                colors = wearColorPalette,
                typography = Typography
            ) {
                /** The reason for externally creating the [SwipeToDismissBoxState] and constructing
                 * the [SwipeDismissableNavHostState] with it is to enable the swipe-dismiss gesture
                 * atop horizontal scrolling.
                 *
                 * It still does not seem to work perfectly, and crashes for me when there is nothing
                 * in the backstack. I've created an issue but otherwise cannot use the HorizontalPager
                 * until it is fixed.
                 *
                 * @see
                 * https://issuetracker.google.com/issues/218663790
                 * https://issuetracker.google.com/issues/228336555
                 * https://issuetracker.google.com/issues/279014600 // My issue
                 * **/
                rememberSwipeDismissableNavController()
                navController = rememberSwipeDismissableNavController()
                val swipeToDismissBoxState = rememberSwipeToDismissBoxState()
                val swipeToDismissNavHostState = rememberSwipeDismissableNavHostState(swipeToDismissBoxState)

                SwipeDismissableNavHost(
                    navController = navController,
                    state = swipeToDismissNavHostState,
                    startDestination = "select_climbing_type"
                ) {
                    composable("end_climb") {
                        EndClimb(viewModel = hiltViewModel<EndClimbViewModel>().apply {
                            registerRouteReceiver(routeReceiver = this@MainActivity)
                        })
                    }
                    composable("select_climbing_type") {
                        SelectClimbingType(viewModel = hiltViewModel<SelectClimbingTypeViewModel>().apply {
                            registerRouteReceiver(routeReceiver = this@MainActivity)
                        })
                    }
                    composable("record") {
                        Record(
                            viewModel = hiltViewModel<RecordViewModel>().apply {
                                registerRouteReceiver(routeReceiver = this@MainActivity)
                            }
                        )
                    }
                    composable("select_climbing_grade") {
                        SelectClimbingGrade(viewModel = hiltViewModel<SelectClimbingGradeViewModel>().apply {
                            registerRouteReceiver(routeReceiver = this@MainActivity)
                        })
                    }
                }
            }
        }
    }

    override fun onRoute(destination: MainDestination) {
        when (destination) {
            is MainDestination.NavigateRecord -> navigateRecord(destination)
            is MainDestination.NavigateSelectClimbingGrade -> navigateSelectClimbingGrade(destination)
            is MainDestination.NavigateUp -> navigateUp()
        }
    }

    private fun navigateRecord(destination: MainDestination.NavigateRecord) {
        navController.navigate("record") {
            popUpTo("select_climbing_type") {
                inclusive = true
            }
        }
    }

    private fun navigateSelectClimbingGrade(destination: MainDestination.NavigateSelectClimbingGrade) {
        navController.navigate("select_climbing_grade")
    }


    private fun navigateUp() {
        navController.navigateUp()
    }

    private fun sendCommandToService() {

    }

}
