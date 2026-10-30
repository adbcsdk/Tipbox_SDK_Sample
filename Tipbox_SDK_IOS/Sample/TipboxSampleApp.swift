import SwiftUI

/// TipBox SDK 샘플 앱 진입점.
///
/// 실제 매체 앱은 보통 앱 시작 시 `TipBoxSdkIos.shared.start(...)` 를 1회 호출하지만,
/// 이 샘플은 스토어 포함/미포함 두 모드를 한 앱에서 테스트하기 위해
/// 초기화를 앱 시작이 아니라 ContentView 의 "초기화" 버튼에서 수행한다.
@main
struct TipboxSampleApp: App {
    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}
