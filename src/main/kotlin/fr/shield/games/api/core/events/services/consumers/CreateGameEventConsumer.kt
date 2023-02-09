package fr.shield.games.api.core.events.services.consumers

import fr.shield.games.api.common.exceptions.ResourceNotFoundException
import fr.shield.games.api.core.events.models.EmissionEvent
import fr.shield.games.api.core.events.models.Event
import fr.shield.games.api.core.events.models.EventName.ERROR
import fr.shield.games.api.core.events.models.EventName.GAME_CREATED
import fr.shield.games.api.core.events.ports.EventConsumer
import fr.shield.games.api.core.games.ports.Games
import fr.shield.games.api.core.sessions.models.PlayerSession
import fr.shield.games.api.core.sessions.ports.GameSessionManager
import fr.shield.games.api.core.sessions.ports.PlayerSessionManager
import fr.shield.games.api.core.events.models.payloads.NewGame
import fr.shield.games.api.core.events.ports.PayloadMapper

class CreateGameEventConsumer(
    private val gameService: Games,
    private val playerSessionManager: PlayerSessionManager,
    private val gameManager: GameSessionManager,
    private val mapper: PayloadMapper
) : EventConsumer {

    override fun consume(data: Any, from: PlayerSession): Event {
        val newGame = mapper.convert(data, NewGame::class.java)

        val connectedPlayers = playerSessionManager.retrieveAllConnected()
        val gameOwnerSession = connectedPlayers.find { it == from }
        val player = gameOwnerSession?.player() ?: throw ResourceNotFoundException()
        val game = gameService.create(newGame.name, newGame.maxPlayers, player.id())

        return if (game != null && gameManager.registerGame(game)) {
            EmissionEvent(GAME_CREATED, game, connectedPlayers)
        }
        else {
            EmissionEvent(
                ERROR,
                "Could not register game",
                listOf(gameOwnerSession)
            )
        }
    }
}
