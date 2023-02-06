package fr.shield.games.api.infrastructure.web.handlers

import fr.shield.games.api.core.events.models.payloads.NoData
import fr.shield.games.api.core.events.ports.EventConsumerGenerator
import fr.shield.games.api.core.events.ports.EventEmitter
import fr.shield.games.api.core.players.ports.Players
import fr.shield.games.api.infrastructure.app.sockets.AppWebSocketHandler
import fr.shield.games.api.infrastructure.app.sockets.models.EventMessage
import org.springframework.stereotype.Component

@Component
class SixTakesWebSocketHandler(
    private val consumerGenerator: EventConsumerGenerator,
    private val eventEmitter: EventEmitter,
    players: Players
) : AppWebSocketHandler(players) {

    override fun handle(message: EventMessage) {
        val event = message.payload
        val data = event.data ?: NoData()

        val consumer = consumerGenerator.generate(event.name) ?: return
        val eventToEmit = consumer.consume(data, event.sender)

        eventEmitter.emit(eventToEmit)
    }
}
