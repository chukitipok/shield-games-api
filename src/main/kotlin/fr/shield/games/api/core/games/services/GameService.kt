package fr.shield.games.api.core.games.services

import fr.shield.games.api.core.games.models.Game
import fr.shield.games.api.core.games.models.GameDTO
import fr.shield.games.api.core.games.ports.GameWriter
import fr.shield.games.api.core.games.ports.Games
import fr.shield.games.api.core.players.models.Player
import fr.shield.games.api.core.players.ports.Players

class GameService(private val playerService: Players, private val roomRepository: GameWriter) : Games {
    override fun create(roomName: String, maxPlayers: Int, ownerId: String): GameDTO? {
        val owner = playerService.retrieveById(ownerId) ?: return null
        return roomRepository.create(roomName, maxPlayers, owner)
    }

    override fun retrieveAllActive(): List<GameDTO> = roomRepository.retrieveAllActive()

    override fun registerNewPlayer(gameId: String, player: Player): GameDTO? {
        val game = roomRepository.retrieveById(gameId) ?: return null
        return roomRepository.update(Game(game).addPlayer(player))
    }
}
