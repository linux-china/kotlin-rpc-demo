package org.mvnsearch

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.rpc.RPC
import kotlinx.serialization.Serializable
import kotlin.coroutines.CoroutineContext

interface MyService : RPC {
    suspend fun hello(name: String): String
}

class MyServiceImpl(override val coroutineContext: CoroutineContext) : MyService {
    override suspend fun hello(name: String): String {
        return "Hello, $name! I'm server!"
    }
}

interface BonusService : RPC {
    val liveBonuses: Flow<Int>
    val total: StateFlow<Int>
    suspend fun calculateBonuses(order: Order): Int
    suspend fun sendNotification(event: String)
    suspend fun sendFlow(flow: Flow<Int>)
    suspend fun receiveFlow(name: String): Flow<Int>
    suspend fun channel(flow: Flow<Int>): Flow<Int>
}

@Serializable
data class Order(val user: String, val coffeeId: Int)