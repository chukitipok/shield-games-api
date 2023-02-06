package fr.shield.games.api.core.events.services.consumers

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.convertValue
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import fr.shield.games.api.core.events.models.EventName.ERROR
import fr.shield.games.api.core.events.models.EventName.GAME_CREATED
import fr.shield.games.api.core.events.ports.EventConsumer
import fr.shield.games.api.core.games.ports.Games
import fr.shield.games.api.core.sessions.models.PlayerSession
import fr.shield.games.api.core.sessions.ports.GameSessionManager
import fr.shield.games.api.core.sessions.ports.PlayerSessionManager
import fr.shield.games.api.core.events.models.payloads.NewGame

class CreateGameEventConsumer(
    private val gameService: Games,
    private val playerSessionManager: PlayerSessionManager,
    private val gameManager: GameSessionManager
) : EventConsumer {

    private val mapper: ObjectMapper = jacksonObjectMapper()

    private fun mapThisMotherFucker(data: Any): NewGame = mapper.convertValue(data)

    override fun consume(data: Any, from: PlayerSession): fr.shield.games.api.core.events.models.Event {
        val newGame = mapThisMotherFucker(data)

        val connectedPlayers = playerSessionManager.retrieveAllConnected()
        val gameOwnerSession = connectedPlayers.find { it == from } ?: throw fr.shield.games.api.common.exceptions.ResourceNotFoundException()
        val player = gameOwnerSession.player() ?: throw fr.shield.games.api.common.exceptions.ResourceNotFoundException()
        val game = gameService.create(newGame.name, newGame.maxPlayers, player.id())

        return if (game != null && gameManager.registerGame(game)) {
            fr.shield.games.api.core.events.models.EmissionEvent(GAME_CREATED, game, connectedPlayers)
        }
        else {
            fr.shield.games.api.core.events.models.EmissionEvent(
                ERROR,
                "Could not register player",
                listOf(gameOwnerSession)
            )
        }
    }
}
