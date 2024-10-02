package org.mvnsearch

import io.ktor.client.*
import io.ktor.client.request.*
import kotlinx.coroutines.runBlocking
import kotlinx.rpc.krpc.ktor.client.installRPC
import kotlinx.rpc.krpc.ktor.client.rpc
import kotlinx.rpc.krpc.ktor.client.rpcConfig
import kotlinx.rpc.krpc.serialization.json.json
import kotlinx.rpc.withService

fun main() = runBlocking {

    val rpcClient = HttpClient { installRPC() }.rpc {
        url("ws://localhost:8080/my-service")
        rpcConfig { serialization { json() } }
    }

    val myService: MyService = rpcClient.withService<MyService>()
    val welcome = myService.hello("Jackie")
    println(welcome)
}