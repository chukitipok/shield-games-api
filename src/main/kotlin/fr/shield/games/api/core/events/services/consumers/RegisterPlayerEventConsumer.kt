package fr.shield.games.api.core.events.services.consumers

import fr.shield.games.api.core.events.models.EmissionEvent
import fr.shield.games.api.core.events.models.Event
import fr.shield.games.api.core.events.models.EventName
import fr.shield.games.api.core.events.ports.EventConsumer
import fr.shield.games.api.core.sessions.models.PlayerSession
import fr.shield.games.api.core.sessions.ports.PlayerSessionManager

class RegisterPlayerEventConsumer(private val playerSessionManager: PlayerSessionManager) : EventConsumer {
    override fun consume(data: Any, from: PlayerSession): Event {
        val receiver = listOf(from)
        return if (playerSessionManager.registerPlayer(from)) {
            EmissionEvent(
                EventName.PLAYER_CONNECTED,
                from.session(),
                receiver
            )
        }
        else {
            EmissionEvent(
                EventName.ERROR,
                null,
                receiver
            )
        }
    }
}
