package st.masoom.videochat

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import st.masoom.videochat.ui.theme.VideoChatTheme

const val APP_ID="APP_ID"

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalUnsignedTypes::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VideoChatTheme {
                Surface(
                    color = MaterialTheme.colorScheme.background,
                    modifier = Modifier.padding(16.dp)
                ) {
                    Navigation()
                }
            }
        }
    }
}
