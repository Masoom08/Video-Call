package st.masoom.videochat.viewModel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import st.masoom.videochat.TextFieldState
import androidx.compose.runtime.State
import androidx.compose.material3.*
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
//import io.agora.rtc.video.VideoEncoderConfiguration


class RoomViewModel: ViewModel() {

    private val _roomName = mutableStateOf(TextFieldState())
    val roomName: State<TextFieldState> = _roomName

    private val _onJoinEvent = MutableSharedFlow<String>()
    val onJointEvent = _onJoinEvent.asSharedFlow()

    fun onRoomEnter(name : String){
        _roomName.value = roomName.value.copy(
            text = name
        )
    }

    fun onJoinRoom(){
        if(roomName.value.text.isBlank()){
            _roomName.value = TextFieldState(text = roomName.value.text, error = "The room can't be empty")
            //_roomName.value = roomName.value.copy(error = "The room can't be empty")
            Log.e("RoomViewModel", "Room name is empty!")
            return
        }
        Log.d("RoomViewModel", "Joining room: ${roomName.value.text}")
        viewModelScope.launch {
            _onJoinEvent.emit(roomName.value.text)
        }
    }
}