package fr.shield.games.api.core.events.services

import fr.shield.games.api.core.events.models.EventName
import fr.shield.games.api.core.events.models.EventName.*
import fr.shield.games.api.core.events.ports.EventConsumer
import fr.shield.games.api.core.events.ports.EventConsumerGenerator
import fr.shield.games.api.core.sessions.ports.PlayerSessionManager
import fr.shield.games.api.core.events.services.consumers.CreateGameEventConsumer
import fr.shield.games.api.core.events.services.consumers.RegisterPlayerEventConsumer
import fr.shield.games.api.core.games.ports.Games
import fr.shield.games.api.core.sessions.ports.GameSessionManager

class EventConsumerGeneratorService(
    private val games: Games,
    private val playerSessionManager: PlayerSessionManager,
    private val gameManager: GameSessionManager
) : EventConsumerGenerator {

    override fun generate(label: String): EventConsumer? {
        return when(EventName.from(label)) {
            CREATE_GAME -> CreateGameEventConsumer(games, playerSessionManager, gameManager)
            CARD_SELECTED -> TODO()
            GAME_CREATED -> TODO()
            GAME_END -> TODO()
            GAME_START -> TODO()
            PLAYER_JOINS_GAME -> TODO()
            ROW_SELECTED -> TODO()
            PLAYER_INFO -> RegisterPlayerEventConsumer(playerSessionManager)
            else -> null
        }
    }
}
