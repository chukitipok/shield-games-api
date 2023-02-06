package fr.shield.games.api.core.games.services

import fr.shield.games.api.core.games.models.Game
import fr.shield.games.api.core.players.ports.Players
import fr.shield.games.api.core.games.ports.GameWriter
import fr.shield.games.api.core.games.ports.Games

class GameService(private val playerService: Players, private val roomRepository: GameWriter) : Games {
    override fun create(roomName: String, maxPlayers: Int, ownerId: String): Game? {
        val owner = playerService.retrieveById(ownerId) ?: return null
        return roomRepository.create(roomName, maxPlayers, owner)
    }
}
