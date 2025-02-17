package st.masoom.videochat


import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import st.masoom.videochat.viewModel.VideoViewModel
import android.Manifest
import android.media.AudioManager
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.viewinterop.AndroidView
import io.agora.agorauikit_android.AgoraConnectionData
import io.agora.agorauikit_android.AgoraVideoViewer
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import android.content.Context
import androidx.compose.runtime.*
import io.agora.rtc.Constants
import io.agora.rtc.RtcEngine
import io.agora.rtc.RtcEngineConfig
import io.agora.rtc.IRtcEngineEventHandler
//import io.agora.rtc2.*

@OptIn(ExperimentalUnsignedTypes::class)
@Composable
fun VideoScreen(
    roomName : String,
    onNavigateUp : () -> Unit = {},
    viewModel : VideoViewModel = viewModel()
){
    var agoraView: AgoraVideoViewer? = null
    val context = LocalContext.current
    val audioManager = context.getSystemService(Context.AUDIO_SERVICE) as AudioManager

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions(),
        onResult = {
                perms ->
            viewModel.onPermissionResult(
                acceptedAudioPermission = perms[Manifest.permission.RECORD_AUDIO] == true,
                acceptedCameraPermission = perms[Manifest.permission.CAMERA] == true,


                )
        }
    )
    LaunchedEffect(key1 = true) {
        permissionLauncher.launch(
            arrayOf(
                Manifest.permission.RECORD_AUDIO,
                Manifest.permission.CAMERA,
            )
        )
    }

    // Ensure proper audio settings
    LaunchedEffect(key1 = true) {
        audioManager.mode = AudioManager.MODE_IN_COMMUNICATION
        audioManager.isSpeakerphoneOn = true
    }

    BackHandler{
        agoraView?.leaveChannel()
        onNavigateUp()
    }
    if(viewModel.hasAudioPermission.value && viewModel.hasCameraPermission.value){
        AndroidView(
            /*factory = {
                context ->
                // Initialize Agora Engine
                val config = RtcEngineConfig().apply {
                    mContext = context
                    mAppId = APP_ID
                    mEventHandler = null
                }

                try {
                    rtcEngine = RtcEngine.create(config)
                    rtcEngine?.setChannelProfile(Constants.CHANNEL_PROFILE_COMMUNICATION)
                    rtcEngine?.enableAudio()
                    rtcEngine?.adjustRecordingSignalVolume(100) // Ensure full microphone volume
                    rtcEngine?.adjustPlaybackSignalVolume(100) // Ensure clear speaker volume
                } catch (e: Exception) {
                    e.printStackTrace()
                }

                AgoraVideoViewer(
                    context, connectionData = AgoraConnectionData(
                        appId = APP_ID
                    )
                ).apply {
                    join(roomName)
                    agoraView = this
                }

                 */

            factory = {
                AgoraVideoViewer(
                    it, connectionData = AgoraConnectionData(
                        appId = APP_ID
                    )
                ).also {

                    it.join(roomName)
                    agoraView = it

                }


            },
            modifier = Modifier.fillMaxSize()
        )
    }
}