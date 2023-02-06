package fr.shield.games.api.core.events.services.consumers

import fr.shield.games.api.core.events.ports.EventConsumer
import fr.shield.games.api.core.sessions.models.PlayerSession
import fr.shield.games.api.core.sessions.ports.PlayerSessionManager

class RegisterPlayerEventConsumer(private val playerSessionManager: PlayerSessionManager) : EventConsumer {
    override fun consume(data: Any, from: PlayerSession): fr.shield.games.api.core.events.models.Event {
        val receiver = listOf(from)
        return if (playerSessionManager.registerPlayer(from)) {
            fr.shield.games.api.core.events.models.EmissionEvent(
                fr.shield.games.api.core.events.models.EventName.PLAYER_CONNECTED,
                from.session(),
                receiver
            )
        }
        else {
            fr.shield.games.api.core.events.models.EmissionEvent(
                fr.shield.games.api.core.events.models.EventName.ERROR,
                null,
                receiver
            )
        }
    }
}
