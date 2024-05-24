Kotlinx RPC demo
=================

kotlinx.rpc is a Kotlin library for adding asynchronous Remote Procedure Call (RPC) services to your applications.

# Features

* Transportation: WebSocket, gRPC
* Serialization: JSON, Protobuf, CBOR

# Service Definition

Just to declare Kotlin interface to extend `RPC` and add service methods and fields declaration.

```kotlin
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
```

kotlinx.rpc communication model is almost alike RSocket, and you can take a look at [RSocket Kotlin](https://github.com/rsocket/rsocket-kotlin).

```kotlin
public interface RSocket : CoroutineScope {

    public suspend fun metadataPush(metadata: ByteReadPacket) {
        metadata.close()
        notImplemented("Metadata Push")
    }

    public suspend fun fireAndForget(payload: Payload) {
        payload.close()
        notImplemented("Fire and Forget")
    }

    public suspend fun requestResponse(payload: Payload): Payload {
        payload.close()
        notImplemented("Request Response")
    }

    public fun requestStream(payload: Payload): Flow<Payload> {
        payload.close()
        notImplemented("Request Stream")
    }

    public fun requestChannel(initPayload: Payload, payloads: Flow<Payload>): Flow<Payload> {
        initPayload.close()
        notImplemented("Request Channel")
    }
}
```

kotlinx.rpc Communication Model(almost alike RSocket):

* Request Response: `suspend fun call(param: String): String`
* Fire and Forget: `suspend fun sendNotification(event: String)`
* Request Stream: support by Kotlin Coroutines Flow
* Request Channel: support by Kotlin Coroutines Flow

Flow support: `Flow`, `SharedFlow`, and `StateFlow`.

# References

* kotlinx-rpc: https://github.com/Kotlin/kotlinx-rpc
* Getting Started with kotlinx.rpc: https://kotlin.github.io/kotlinx-rpc/getting-started.html
* kotlinx.coroutines: https://github.com/Kotlin/kotlinx.coroutines
* Ktor: https://ktor.io/
* kotlinx.serialization: https://github.com/Kotlin/kotlinx.serialization
* rsocket-kotlin: https://github.com/rsocket/rsocket-kotlin