package fr.shield.games.api.infrastructure.app.sockets

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import fr.shield.games.api.common.exceptions.ResourceNotFoundException
import fr.shield.games.api.core.players.ports.Players
import fr.shield.games.api.infrastructure.app.sockets.models.EventMessage
import fr.shield.games.api.infrastructure.app.sockets.models.GlobalEvent
import fr.shield.games.api.infrastructure.app.sockets.models.PlayerWebSocketSession
import org.springframework.web.socket.*

open class AppWebSocketHandler(private val players: Players) : WebSocketHandler {
    private val mapper = jacksonObjectMapper()

    override fun afterConnectionEstablished(session: WebSocketSession) {}

    override fun handleMessage(session: WebSocketSession, message: WebSocketMessage<*>) {
        if (message !is TextMessage) throw IllegalStateException("Unexpected WebSocket message type: $message")

        val event = mapper.readValue<GlobalEvent>(message.payload)
        val player = players.retrieveById(event.sender) ?: throw ResourceNotFoundException()
        val playerSession = PlayerWebSocketSession(player, session)

        val eventMessage = EventMessage(event, playerSession)

        handle(eventMessage)
    }

    override fun handleTransportError(session: WebSocketSession, exception: Throwable) {}

    override fun afterConnectionClosed(session: WebSocketSession, closeStatus: CloseStatus) {}

    override fun supportsPartialMessages(): Boolean = false

    open fun handle(message: EventMessage) {}
}
