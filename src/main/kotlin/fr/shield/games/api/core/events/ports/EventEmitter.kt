package fr.shield.games.api.core.events.ports

interface EventEmitter {
    fun emit(event: fr.shield.games.api.core.events.models.Event)
}
