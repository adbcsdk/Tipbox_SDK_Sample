package com.adbc.tipboxsample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.adbc.tipboxsdk.sdk.TipBoxSdkAndroid

/**
 * TipBox SDK 테스트 앱.
 *
 *  - "스토어 포함" 스위치로 스토어 포함/미포함 모드를 전환한다.
 *  - "초기화" 버튼: 스위치에 따라 알맞은 채널 키로 SDK 를 초기화한다.
 *  - "오퍼월 실행" 버튼: 스위치에 따라 openOfferwall / openOfferwallWithoutStore 호출.
 *  - 매체 userId 입력은 스토어 미포함일 때만 표시된다.
 */
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    SampleScreen(activity = this)
                }
            }
        }
    }

    companion object {
        // TipBox 담당자에게 발급받은 채널 키로 바꿔 주세요.
        const val CHANNEL_KEY_WITH_STORE = "스토어_포함_채널키"
        const val CHANNEL_KEY_WITHOUT_STORE = "스토어_미포함_채널키"
        const val THEME_COLOR = "#78BAFF"
    }
}

@Composable
private fun SampleScreen(activity: ComponentActivity) {
    var storeIncluded by remember { mutableStateOf(true) }
    var initialized by remember { mutableStateOf(false) }
    var userId by remember { mutableStateOf("test_user_001") }
    var status by remember { mutableStateOf("초기화 필요") }

    Column(
        modifier = Modifier.fillMaxSize().systemBarsPadding().padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("TipBox SDK 샘플", style = MaterialTheme.typography.titleLarge)

        // 스토어 포함/미포함 스위치
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(if (storeIncluded) "스토어 포함" else "스토어 미포함", modifier = Modifier.weight(1f))
            Switch(
                checked = storeIncluded,
                onCheckedChange = {
                    storeIncluded = it
                    initialized = false           // 모드 바뀌면 다시 초기화 필요
                    status = "초기화 필요 (모드 변경됨)"
                }
            )
        }

        // 초기화 버튼 — 스위치에 맞는 채널 키로 init
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                val channelKey = if (storeIncluded)
                    MainActivity.CHANNEL_KEY_WITH_STORE
                else
                    MainActivity.CHANNEL_KEY_WITHOUT_STORE
                TipBoxSdkAndroid.init(
                    context = activity,
                    themeColor = MainActivity.THEME_COLOR,
                    channelKey = channelKey
                )
                initialized = true
                status = "초기화 완료 (${if (storeIncluded) "스토어 포함" else "스토어 미포함"})"
            }
        ) { Text("초기화") }

        // 스토어 미포함일 때만 userId 입력 표시
        if (!storeIncluded) {
            OutlinedTextField(
                value = userId,
                onValueChange = { userId = it },
                label = { Text("매체 userId") },
                modifier = Modifier.fillMaxWidth()
            )
        }

        // 오퍼월 실행 버튼 1개 — 스위치에 따라 분기
        Button(
            modifier = Modifier.fillMaxWidth(),
            enabled = initialized,
            onClick = {
                if (storeIncluded) {
                    TipBoxSdkAndroid.openOfferwall(activity)
                } else {
                    TipBoxSdkAndroid.openOfferwallWithoutStore(activity, userId)
                }
            }
        ) { Text("오퍼월 실행") }

        Text(status, style = MaterialTheme.typography.bodySmall)
    }
}
