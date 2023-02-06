package fr.shield.games.api.core.events.models

import fr.shield.games.api.core.sessions.models.PlayerSession

interface Event {
    fun name(): String
    fun data(): Any?
    fun receivers(): List<PlayerSession>
}
