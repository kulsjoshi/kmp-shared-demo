//
//  TodoDetailsView.swift
//  KMPSharedDemoIOS
//
//  Created by Kuls on 2/10/26.
//
import SwiftUI
import ComposeApp

struct TodoDetailsView: View{
    let todo: Todo
    
    var body: some View{
        VStack(spacing: 16){
            Text(todo.title)
                .font(.title3)
                .multilineTextAlignment(.center)
            
            Divider()
                
            
            Text("ID # \(todo.id)")
            
            Text("Details screen (demo)")
                .foregroundColor(.secondary)
        }
        .padding()
        .navigationTitle("Details")
    }
    
    
}
