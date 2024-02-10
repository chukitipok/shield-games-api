package fr.shield.games.api.core.events.services

import fr.shield.games.api.core.events.models.Event
import fr.shield.games.api.core.events.models.EventMessage
import fr.shield.games.api.core.events.ports.EventEmitter

class EventEmitterService : EventEmitter {
    override fun emit(event: Event) = event.receivers().forEach { it.send(EventMessage(event.name(), event.data())) }
}
