package fr.shield.games.api.core.events.services.consumers

import fr.shield.games.api.core.events.models.EmissionEvent
import fr.shield.games.api.core.events.models.Event
import fr.shield.games.api.core.events.models.EventName
import fr.shield.games.api.core.events.models.payloads.JoinRoom
import fr.shield.games.api.core.events.ports.EventConsumer
import fr.shield.games.api.core.events.ports.PayloadMapper
import fr.shield.games.api.core.sessions.models.PlayerSession
import fr.shield.games.api.core.sessions.ports.PlayerSessionManager
import fr.shield.games.api.core.sessions.ports.RoomManager

class PlayerJoinsGameEventConsumer(
    private val playerSessionManager: PlayerSessionManager,
    private val roomManager: RoomManager,
    private val mapper: PayloadMapper
) : EventConsumer {
    override fun consume(data: Any, from: PlayerSession): Event {
        val (roomId) = mapper.convert(data, JoinRoom::class.java)
        val receivers = playerSessionManager.retrieveAllConnected()
        val room = roomManager.addPlayerToRoom(roomId, from)
            ?: return EmissionEvent(EventName.ERROR, "Could not add player session", listOf(from))

        return EmissionEvent(EventName.PLAYER_JOINED_ROOM, room.game(), receivers)
    }
}
