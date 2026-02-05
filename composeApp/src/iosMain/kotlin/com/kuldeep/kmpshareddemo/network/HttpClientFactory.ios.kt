package com.kuldeep.kmpshareddemo.network

import io.ktor.client.HttpClient
import io.ktor.client.engine.darwin.Darwin

actual object HttpClientFactory {
    actual fun create(): HttpClient =
        HttpClient(Darwin) {
            applySharedConfig()
        }
}