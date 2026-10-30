import SwiftUI
import UIKit
import TipBoxSDKCore

/// TipBox SDK 테스트 화면.
///
///  - "스토어 포함" 스위치로 스토어 포함/미포함 모드를 전환한다.
///  - "초기화" 버튼: 스위치에 맞는 채널 키로 SDK 를 초기화한다.
///  - "오퍼월 실행" 버튼 1개: 스위치에 따라 showOfferwall / showOfferwallWithoutStore 분기.
///  - 매체 userId 입력은 스토어 미포함일 때만 표시된다.
struct ContentView: View {

    // TipBox 담당자에게 발급받은 채널 키로 바꿔 주세요.
    private let channelKeyWithStore = "스토어_포함_채널키"
    private let channelKeyWithoutStore = "스토어_미포함_채널키"
    private let themeColor = "#78BAFF"

    @State private var storeIncluded = true
    @State private var initialized = false
    @State private var userId = "test_user_001"
    @State private var status = "초기화 필요"

    var body: some View {
        VStack(alignment: .leading, spacing: 16) {
            Text("TipBox SDK 샘플").font(.title2).bold()

            Divider()

            // 스토어 포함/미포함 스위치
            Toggle(storeIncluded ? "스토어 포함" : "스토어 미포함", isOn: $storeIncluded)
                .onChange(of: storeIncluded) { _ in
                    initialized = false            // 모드 바뀌면 다시 초기화 필요
                    status = "초기화 필요 (모드 변경됨)"
                }

            // 초기화 — 스위치에 맞는 채널 키로 start
            Button("초기화") {
                let key = storeIncluded ? channelKeyWithStore : channelKeyWithoutStore
                TipBoxSdkIos.shared.start(channelKey: key, themeColor: themeColor)
                initialized = true
                status = "초기화 완료 (\(storeIncluded ? "스토어 포함" : "스토어 미포함"))"
            }
            .buttonStyle(.borderedProminent)
            .frame(maxWidth: .infinity)

            // 스토어 미포함일 때만 매체 userId 입력
            if !storeIncluded {
                TextField("매체 userId", text: $userId)
                    .textFieldStyle(.roundedBorder)
            }

            // 오퍼월 실행 — 스위치에 따라 분기
            Button("오퍼월 실행") {
                if storeIncluded {
                    TipBoxSdkIos.shared.showOfferwall()
                } else {
                    TipBoxSdkIos.shared.showOfferwallWithoutStore(userId: userId)
                }
            }
            .buttonStyle(.bordered)
            .frame(maxWidth: .infinity)
            .disabled(!initialized || (!storeIncluded && userId.isEmpty))

            Divider()

            Text(status).font(.footnote).foregroundColor(.secondary)

            Spacer()
        }
        .padding(24)
    }
}
