package com.climbingtrackerapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text
import androidx.wear.compose.navigation.SwipeDismissableNavHost
import androidx.wear.compose.navigation.composable
import androidx.wear.compose.navigation.rememberSwipeDismissableNavController
import com.climbingtrackerapp.architecture.RouteReceiver
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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            println("${MaterialTheme.colors}")
            MaterialTheme(
                colors = wearColorPalette, typography = Typography
            ) {
                println("after ${MaterialTheme.colors}")

                val navController = rememberSwipeDismissableNavController()
                SwipeDismissableNavHost(
                    navController = navController, startDestination = "record"
                ) {
                    composable("select_climbing_type") {
                        SelectClimbingType(viewModel = hiltViewModel<SelectClimbingTypeViewModel>().apply {
                            registerRouteReceiver(routeReceiver = this@MainActivity)
                        })
                    }
                    composable("record") {
                        Record(viewModel = hiltViewModel<RecordViewModel>().apply {
                            registerRouteReceiver(routeReceiver = this@MainActivity)
                        })
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
        TODO("Not yet implemented")
    }
}
