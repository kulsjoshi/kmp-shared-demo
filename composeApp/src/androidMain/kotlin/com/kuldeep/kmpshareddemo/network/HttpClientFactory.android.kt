package com.kuldeep.kmpshareddemo.network

import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import kotlin.invoke

actual object HttpClientFactory {
    actual fun create(): HttpClient =
        HttpClient(OkHttp) {
            applySharedConfig()
        }
}