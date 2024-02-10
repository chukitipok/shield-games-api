package fr.shield.games.api.core.games.ports

import fr.shield.games.api.core.games.models.GameDTO
import fr.shield.games.api.core.players.models.Player

interface Games {
    fun create(roomName: String, maxPlayers: Int, ownerId: String): GameDTO?
    fun retrieveAllActive(): List<GameDTO>
    fun registerNewPlayer(gameId: String, player: Player): GameDTO?
}
