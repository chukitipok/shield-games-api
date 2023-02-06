package fr.shield.games.api.core.events.ports

import fr.shield.games.api.core.sessions.models.PlayerSession

interface EventConsumer {
    fun consume(data: Any, from: PlayerSession): fr.shield.games.api.core.events.models.Event
}
