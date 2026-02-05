import Foundation
import Combine
import ComposeApp

@MainActor
final class TodoViewModel: ObservableObject {

    @Published var title: String = ""
    @Published var isLoading: Bool = false
    @Published var error: String? = nil

    private let bridge = TodoBridge()
    private var closeable: Closeable? = nil
    
    func start() {
            closeable = bridge.observe(
                onLoading: { [weak self] loading in
                    guard let self else { return }
                    self.isLoading = loading.boolValue
                    if loading.boolValue {
                        self.title = ""
                        self.error = nil
                    }
                },
                onSuccess: { [weak self] title in
                    guard let self else { return }
                    self.title = title
                    self.error = nil
                },
                onError: { [weak self] message in
                    guard let self else { return }
                    self.error = message
                    self.title = ""
                }
            )

            bridge.load()
        }

    func retry() {
        bridge.retry()
    }

    deinit {
        closeable?.close()
        bridge.clear()
    }
}
