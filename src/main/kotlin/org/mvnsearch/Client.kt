package org.mvnsearch

import io.ktor.client.HttpClient
import io.ktor.client.request.url
import kotlinx.coroutines.runBlocking
import kotlinx.rpc.client.withService
import kotlinx.rpc.serialization.json
import kotlinx.rpc.transport.ktor.client.installRPC
import kotlinx.rpc.transport.ktor.client.rpc
import kotlinx.rpc.transport.ktor.client.rpcConfig

fun main() = runBlocking {

    val rpcClient = HttpClient { installRPC() }.rpc {
        url("ws://localhost:8080/my-service")
        rpcConfig { serialization { json() } }
    }

    val myService: MyService = rpcClient.withService<MyService>()
    val welcome = myService.hello("Jackie")
    println(welcome)
}