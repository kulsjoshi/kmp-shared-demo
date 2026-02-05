//
//  ContentView.swift
//  KMPSharedDemoIOS
//
//  Created by Kuls on 2/5/26.
//

import SwiftUI
import ComposeApp

struct ContentView: View {
    
    @StateObject private var viewModel = TodoViewModel()
    
    var body: some View{
        VStack(spacing: 12){
            if viewModel.isLoading{
                ProgressView()
            } else if let error = viewModel.error {
                Text("Error: \(error)")
                Button("Retry"){
                    viewModel.retry()
                }
            } else {
                Text(viewModel.title.isEmpty ? "No data" : viewModel.title)
            }

        }
        .padding()
        .onAppear{
            viewModel.start()
        }
    }
}

#Preview {
    ContentView()
}
