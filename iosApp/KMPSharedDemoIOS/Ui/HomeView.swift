//
//  HomeView.swift
//  KMPSharedDemoIOS
//
//  Created by Kuls on 2/10/26.
//

import SwiftUI

struct HomeView: View{
    var body: some View{
        NavigationStack{
            VStack(spacing: 20){
                Text("KMP Shared Demo")
                    .font(.title2)
                
                NavigationLink("Open Todos"){
                    TodoListView()
                }
            }.navigationTitle("Home")
        }
    }
}
