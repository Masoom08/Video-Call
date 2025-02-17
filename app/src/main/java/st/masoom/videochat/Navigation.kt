package st.masoom.videochat

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@ExperimentalUnsignedTypes
@Composable
fun Navigation(){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "room_screen"
    ) {
        composable(route = "room_screen") { RoomScreen(onNavigate = navController::navigate) }
        composable(route = "video_screen/{roomName}", arguments = listOf(
            navArgument(name = "roomName") {
                type = NavType.StringType
            }
        )
        ) {
            val roomName = it.arguments?.getString("roomName") ?: return@composable
            VideoScreen(
                roomName = roomName,
                onNavigateUp = navController::navigateUp
            )
        }
    }
}