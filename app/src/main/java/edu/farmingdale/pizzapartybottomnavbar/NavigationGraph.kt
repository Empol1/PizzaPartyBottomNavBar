package edu.farmingdale.pizzapartybottomnavbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.clickable
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationGraph(navController: NavHostController, onBottomBarVisibilityChanged: (Boolean) -> Unit) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            DrawerMenu(drawerState, navController)
        }
    ) {
        NavHost(navController, startDestination = BottomNavigationItems.Welcome.route) {
            composable(BottomNavigationItems.Welcome.route) {
                onBottomBarVisibilityChanged(false)
                SplashScreen(navController = navController)
            }
            composable(BottomNavigationItems.PizzaScreen.route) {
                onBottomBarVisibilityChanged(true)
                PizzaPartyScreen()
            }
            composable(BottomNavigationItems.GpaAppScreen.route) {
                onBottomBarVisibilityChanged(true)
                GpaAppScreen()
            }
            composable(BottomNavigationItems.Screen3.route) {
                onBottomBarVisibilityChanged(true)
                Screen3()
            }
        }
    }
}

@Composable
fun DrawerMenu(drawerState: DrawerState, navController: NavHostController) {
    // Get the screen width in Dp
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val drawerWidth = screenWidth * 0.75f // Calculate 75% of the screen width

    Box(
        modifier = Modifier
            .fillMaxHeight() // Cover 100% height
            .width(drawerWidth) // Cover 75% of screen width
            .background(MaterialTheme.colorScheme.background) // Set background color
            .clip(RoundedCornerShape(topEnd = 16.dp, bottomEnd = 16.dp)) // Rounded corners
            .padding(16.dp) // Inner padding
    ) {
        Column {
            Text("Menu", style = MaterialTheme.typography.titleLarge)
            Divider()
            DrawerItem("Pizza Party") {
                navController.navigate(BottomNavigationItems.PizzaScreen.route) {
                    popUpTo(navController.graph.startDestinationId) { saveState = true }
                }
            }
            DrawerItem("GPA App") {
                navController.navigate(BottomNavigationItems.GpaAppScreen.route) {
                    popUpTo(navController.graph.startDestinationId) { saveState = true }
                }
            }
            DrawerItem("Screen 3") {
                navController.navigate(BottomNavigationItems.Screen3.route) {
                    popUpTo(navController.graph.startDestinationId) { saveState = true }
                }
            }
        }
    }
}
@Composable
fun DrawerItem(label: String, onClick: () -> Unit) {
    Text(
        text = label,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable(onClick = onClick)
    )
}