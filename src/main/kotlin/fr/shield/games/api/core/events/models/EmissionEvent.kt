package fr.shield.games.api.core.events.models

import fr.shield.games.api.core.sessions.models.PlayerSession

data class EmissionEvent(val name: EventName, val data: Any?, val receivers: List<PlayerSession>) : Event {
    override fun name(): String = name.label
    override fun data(): Any? = data
    override fun receivers(): List<PlayerSession> = receivers
}
