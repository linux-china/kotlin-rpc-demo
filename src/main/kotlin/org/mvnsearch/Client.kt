package org.mvnsearch

import io.ktor.client.*
import io.ktor.client.plugins.websocket.*
import io.ktor.http.*
import kotlinx.coroutines.runBlocking
import kotlinx.rpc.client.withService
import kotlinx.rpc.serialization.json
import kotlinx.rpc.transport.ktor.client.rpc
import kotlinx.rpc.transport.ktor.client.rpcConfig

fun main() = runBlocking {
    val ktorClient = HttpClient {
        install(WebSockets)
    }

    val client = ktorClient.rpc {
        url {
            host = "localhost"
            port = 8080
            encodedPath = "my-service"
        }

        rpcConfig {
            serialization {
                json()
            }
        }
    }

    val myService: MyService = client.withService<MyService>()
    val welcome = myService.hello("Jackie")
    println(welcome)
}