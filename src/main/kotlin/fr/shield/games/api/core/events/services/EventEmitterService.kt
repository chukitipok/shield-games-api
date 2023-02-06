package fr.shield.games.api.core.events.services

import fr.shield.games.api.core.events.ports.EventEmitter

class EventEmitterService : EventEmitter {
    override fun emit(event: fr.shield.games.api.core.events.models.Event) = event.receivers().forEach { it.send(
        fr.shield.games.api.core.events.models.EventMessage(
            event.name(),
            event.data()
        )
    ) }
}
