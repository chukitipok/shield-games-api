package fr.shield.games.api.infrastructure.app.sockets.models

import org.springframework.web.socket.WebSocketMessage

data class EventMessage(private val event: GlobalEvent, private val playerSession: PlayerWebSocketSession) : WebSocketMessage<EventMessagePayload> {
    private val data: EventMessagePayload = EventMessagePayload(event.name, event.data, playerSession)

    override fun getPayload(): EventMessagePayload = data

    override fun getPayloadLength(): Int = data.toString().length

    override fun isLast(): Boolean = true
}
