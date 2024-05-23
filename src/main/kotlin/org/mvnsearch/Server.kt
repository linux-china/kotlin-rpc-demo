package org.mvnsearch


import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.routing.*
import kotlinx.rpc.serialization.json
import kotlinx.rpc.transport.ktor.server.RPC
import kotlinx.rpc.transport.ktor.server.rpc

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