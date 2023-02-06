package fr.shield.games.api.core.events.ports

interface EventConsumerGenerator {
    fun generate(label: String): EventConsumer?
}
