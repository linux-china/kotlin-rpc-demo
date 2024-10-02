package org.mvnsearch


import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.routing.*
import kotlinx.rpc.krpc.ktor.server.RPC
import kotlinx.rpc.krpc.ktor.server.rpc
import kotlinx.rpc.krpc.serialization.json.json

fun main() {
    embeddedServer(Netty, port = 8080) {
        module()
    }.start(wait = true)
}

fun Application.module() {
    install(RPC)

    routing {
        rpc("/my-service") {
            rpcConfig {
                serialization {
                    json()
                }
            }

            registerService<MyService> { ctx -> MyServiceImpl(ctx) }
        }
    }
}