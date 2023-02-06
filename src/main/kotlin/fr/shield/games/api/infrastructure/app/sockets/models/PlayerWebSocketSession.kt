package fr.shield.games.api.infrastructure.app.sockets.models

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import fr.shield.games.api.core.players.models.Player
import fr.shield.games.api.core.sessions.models.PlayerSession
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession

data class PlayerWebSocketSession(private val player: Player, private val session: WebSocketSession) : PlayerSession {
    private val mapper: ObjectMapper = jacksonObjectMapper()

    override fun player(): Player = player

    override fun session(): String = session.id

    override fun send(message: Any?) = session.sendMessage(TextMessage(mapper.writeValueAsString(message)))
}
