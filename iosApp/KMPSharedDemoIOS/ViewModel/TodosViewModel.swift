//
//  TodosViewModel.swift
//  KMPSharedDemoIOS
//
//  Created by Kuls on 2/10/26.
//

import Foundation
import ComposeApp
import Combine

@MainActor
class TodosViewModel: ObservableObject{
    
    private let bridge = TodoBridge()
    private var closable: Closeable?
    
    @Published var isLoading = false
    @Published var todos:[Todo] = []
    @Published var error: String?
    
    func loadTodos(){
        closable = bridge.observeTodos(
            onLoading: { loading in
                print("Loading...")
                self.isLoading = loading.boolValue
            },
            onSuccess: { todos in
                print("List is featched > List Size: " ,todos.count)
                self.todos = todos
                
            },
            onError: {message in
                print("ERROR > Error message:" ,message)
                self.error = message
            })
        
        bridge.loadTodos()
    }
    
    deinit{
        closable?.close()
        bridge.clear()
    }
}
