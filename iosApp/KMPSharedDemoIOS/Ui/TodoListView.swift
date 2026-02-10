//
//  TodoListView.swift
//  KMPSharedDemoIOS
//
//  Created by Kuls on 2/10/26.
//
import SwiftUI
import ComposeApp

struct TodoListView: View{
    
    @StateObject private var viewModel = TodosViewModel()
    
    var body: some View{
        
        if viewModel.isLoading{
            ProgressView()
        }
        
        if let error = viewModel.error{
            Text("Something went wrong")
            Text(error)
        }
        
        List{
            ForEach(viewModel.todos, id: \.self){todo in
                NavigationLink{
                    TodoDetailsView(todo: todo)
                } label:{
                    Text("#\(todo.id)")
                    Text(todo.title).lineLimit(2)
                }
            }
        }
        .navigationTitle("Todos")
        .onAppear{
            if viewModel.todos.isEmpty{
                viewModel.loadTodos()
            }
        }
        
    }
}
