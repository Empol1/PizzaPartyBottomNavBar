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
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource

//To-do 8 completed

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
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val drawerWidth = screenWidth * 0.85f

    Box(
        modifier = Modifier
            .fillMaxHeight()
            .width(drawerWidth)
            .background(MaterialTheme.colorScheme.background)
            .clip(RoundedCornerShape(topEnd = 16.dp, bottomEnd = 16.dp))
            .padding(20.dp)
    ) {
        Column {

            DrawerItem("Pizza Order", iconRes = R.drawable.pizza_order_pic) {
                navController.navigate(BottomNavigationItems.PizzaScreen.route) {
                    popUpTo(navController.graph.startDestinationId) { saveState = true }
                }
            }
            DrawerItem("GPA App", iconRes = R.drawable.gpa_icon_calc) {
                navController.navigate(BottomNavigationItems.GpaAppScreen.route) {
                    popUpTo(navController.graph.startDestinationId) { saveState = true }
                }
            }
            DrawerItem("Screen 3", iconRes = R.drawable.person_screen3) {
                navController.navigate(BottomNavigationItems.Screen3.route) {
                    popUpTo(navController.graph.startDestinationId) { saveState = true }
                }
            }
        }
    }
}
@Composable
fun DrawerItem(label: String, iconRes: Int, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clip(RoundedCornerShape(64.dp)) // Set rounded corners
            .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.0f)) // Background color with transparency
            .clickable(onClick = onClick)
            .padding(8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = iconRes),
                contentDescription = null,
                modifier = Modifier.size(32.dp) // Set icon size
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = label)
        }
    }
}