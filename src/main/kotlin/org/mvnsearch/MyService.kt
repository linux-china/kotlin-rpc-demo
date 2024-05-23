package org.mvnsearch

import kotlinx.rpc.RPC
import kotlin.coroutines.CoroutineContext

interface MyService : RPC {
    suspend fun hello(name: String): String
}

class MyServiceImpl(override val coroutineContext: CoroutineContext) : MyService {
    override suspend fun hello(name: String): String {
        return "Hello, $name! I'm server!"
    }
}